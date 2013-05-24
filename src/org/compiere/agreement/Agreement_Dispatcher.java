package org.compiere.agreement;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.Logger;
import org.compiere.model.MAGRAgreement;
import org.compiere.model.MAGRAgreementList;
import org.compiere.model.MAGRNode;
import org.compiere.model.MAGRStage;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AGR_Stage;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.jfree.util.Log;

public class Agreement_Dispatcher 
{
	
	private MAGRStage currentStage = null;
	private ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
	private PO document;
	
	private int AGR_Agreement_ID = 0;
	private int AGR_Stage_ID = 0;
	private int AD_User_ID = 0;
	private int AD_Table_ID = 0;
	private int HR_Department_ID = 0;
	private int Record_ID = 0;
	private int C_BPartner_ID = 0;
	
	//
	private final String columnDocStatus = "DocStatus";
		
	public Agreement_Dispatcher(PO document, int AD_Table_ID, int Record_ID)
	{
		this.AD_Table_ID = AD_Table_ID;
		AGR_Stage_ID = document.get_ValueAsInt(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID);// ((IAgreement)document).getStage();
		AGR_Agreement_ID = AGR_Agreement();
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());	
		HR_Department_ID = getHR_Department();
		C_BPartner_ID = getBPartner();

		this.Record_ID = Record_ID;
		
		this.document = document;
	}
	
	//Start agreement process
	public boolean startAgreement(boolean isApprove)
	{	
		try 
		{
			if(!validation()) 
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		currentStage = getCurrentStage();
		
		if(!currentStage.isUserHasAccess(AD_User_ID, HR_Department_ID))
			return false;
		
		if(!isApprove)
		{
			Dissaprove(currentStage);
			//quit(currentStage);
			return true;
		}
		else
		{
			Approve(currentStage);
		}
		
		return true;
	}
	
	//Dissaprove document and quit from agreement process
	private void Dissaprove(MAGRStage stage)
	{
		stage.Dissapprove(AD_Table_ID, Record_ID, C_BPartner_ID);
		createNextStage(stage, true);
	}
	//Approve document and check for possibility to move to the next stage
	private void Approve(MAGRStage stage)
	{		
		stage.Approve(AD_Table_ID, Record_ID, C_BPartner_ID);
		
		if(stage.isCanMove(AD_Table_ID, Record_ID))
		{
			createNextStage(stage, false);
		}
		else if(stage.isLastStage())
		{
			quit(stage);
		}
	}
	
	//Get HR_Department_ID of current user (using AD_User_ID)
	private int getHR_Department()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT e.HR_Department_ID ");
		buffer.append("FROM HR_Employee e ");
		buffer.append("INNER JOIN C_BPartner b ON b.C_BPartner_ID = e.C_BPartner_ID ");
		buffer.append("INNER JOIN AD_User u	ON u.C_BPartner_ID = b.C_BPartner_ID ");
		buffer.append("WHERE e.isActive = 'Y' AND u.AD_User_ID = " + AD_User_ID);
		
		return DB.getSQLValue(null, buffer.toString());
	}
	
	//Get C_BPartner_ID of current user (using AD_User_ID)
	private int getBPartner()
	{
		String sql = "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = " + AD_User_ID;
		
		return DB.getSQLValue(null, sql);
	}
	
	//Get agreement for this type of document
	private int AGR_Agreement()
	{
		return 1000000;
	}
		
	private boolean validation() throws SQLException
	{
		MAGRAgreement agreement = new MAGRAgreement(Env.getCtx(), AGR_Agreement_ID, null);
		
		if(!agreement.checkAgreement()) return false;
		//if(!stageCheck() || !stageOptionCheck() || !nodeCheck()) return false;
						
		return true;		
	}

	//Get current stage of agreement
	private MAGRStage getCurrentStage()
	{
		MAGRStage stage = null;
		if(AGR_Stage_ID == 0)
		{
			stage = MAGRStage.getFirstStage(Env.getCtx(), AGR_Agreement_ID, null);
		}
		else
		{
			stage = new MAGRStage(Env.getCtx(), AGR_Stage_ID, null);
		}
		
		return stage;
		
/*		loadSigners(stage);
		
		MUser user = new MUser(Env.getCtx(), AD_User_ID, null);
		
		if(!signers.contains(user.getC_BPartner())) return;
*/				
	}
	//Get next stage if stage is approved
	private MAGRStage createNextStage(MAGRStage currentStage, boolean isBack)
	{
		MAGRNode[] nodeArray = MAGRNode.getOfTRM_Stage(Env.getCtx(), currentStage.get_ID(), null);
		
		for(int i = 0; i < nodeArray.length; i++)
		{
			//If document is not approved and current node is not back
			//Following code will be escaped
			if(!isBack && nodeArray[i].isBack()) continue;
			if(isBack && !nodeArray[i].isBack()) continue;
			
			MAGRStage stage = new MAGRStage(Env.getCtx(), nodeArray[i].getAGR_NextStage_ID(), null);
			
			//If document is not approved, agreement list will not be filled
			if((currentStage.get_ID() != nodeArray[i].getAGR_NextStage_ID()) && isNotFilled(stage))
			{
				FillAgreementList(stage);
			}
						
			document.set_ValueOfColumn(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID, stage.get_ID());
			document.saveEx();
			
			//Exit from agreement
			if(stage.get_ID() == currentStage.get_ID()) 
			{
				quit(currentStage);
				break;//continue;
			}
		}
		return null;
	}
	
	private boolean isNotFilled(MAGRStage stage)
	{
		ArrayList<MAGRAgreementList> lines = MAGRAgreementList.getOfStage(Env.getCtx(), null, AD_Table_ID, Record_ID, stage.get_ID());	
		
		return lines.size() == 0;
	}	
	//Fill agreement list
	private void FillAgreementList(MAGRStage stage)
	{
		ArrayList<Integer> signers = stage.getSigners(HR_Department_ID);
		
		for(int i = 0; i < signers.size(); i++)
		{
			MAGRAgreementList list = new MAGRAgreementList(Env.getCtx(), null, null);			
			list.setAD_Table_ID(AD_Table_ID);
			list.setRecord_ID(Record_ID);
			list.setSigner_ID(signers.get(i));
			list.setAGR_Stage_ID(stage.get_ID());
			if(!list.save())
			{
				Log.log(Logger.ERROR, "Agreement List not saved");
			}
		}		
	}

	private void quit(MAGRStage stage)
	{
		X_AD_Ref_List list = new X_AD_Ref_List(Env.getCtx(), stage.getAD_Ref_List_ID(), null);
		document.set_ValueOfColumn(columnDocStatus, list.getValue());
		document.saveEx();
	}

}