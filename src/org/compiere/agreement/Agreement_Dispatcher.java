package org.compiere.agreement;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.catalina.Logger;
import org.compiere.apps.DialogAgreement;
import org.compiere.model.MAGRAgreement;
import org.compiere.model.MAGRAgreementList;
import org.compiere.model.MAGRApprovalList;
import org.compiere.model.MAGRDispatcher;
import org.compiere.model.MAGRNode;
import org.compiere.model.MAGRStage;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AGR_Dispatcher;
import org.compiere.model.X_AGR_Stage;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.jfree.util.Log;

public class Agreement_Dispatcher 
{
	
	private MAGRStage currentStage = null;
	private PO document;
	
	private int AGR_Agreement_ID = 0;
	private int AGR_Stage_ID = 0;
	private int AD_User_ID = 0;
	private int AD_Table_ID = 0;
	private int Record_ID = 0;
	private int C_BPartner_ID = 0;
	
	private final String columnDocStatus = "DocStatus";
		
	public Agreement_Dispatcher(PO document, int AD_Table_ID, int Record_ID)
	{
		this.AD_Table_ID = AD_Table_ID;
		this.Record_ID = Record_ID;		
		this.document = document;
		
		AGR_Stage_ID = document.get_ValueAsInt(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID);
		AGR_Agreement_ID = AGR_Agreement();
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());	
		C_BPartner_ID = getBPartner();
	}
	//Start agreement process
	public boolean startAgreement(boolean isApprove,String message)
	{				
		boolean isApproved = false;
		//Get current AGR_Stage
		//If AGR_Stage is null then new AGR_Stage will be started
		currentStage = getCurrentStage();
		
		//Check if current stage is a last stage of current agreement
		if(currentStage.isLastStage() && currentStage.isAllApproved(AD_Table_ID, Record_ID))
		{
			DialogAgreement.dialogOK("Ошибка доступа", "Документ согласован", 0);
			return false;
		}
				
		//Check if current user has access for current stage
		if(!currentStage.isUserHasAccess(AD_User_ID, AD_Table_ID, Record_ID))
		{
			DialogAgreement.dialogOK("Ошибка доступа", "У вас нет доступа к данному этапу согласования", 0);
			return false;
		}

		//Check if current agreement is validate
		try 
		{
			if(!validation()) 
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}			
				
		//If stage not approved
		if(!isApprove)
		{
			Dissaprove(currentStage, message);
		}
		else
		{
			isApproved = Approve(currentStage, message);
		}
		
		return isApproved;
	}
	//Dissaprove document and quit from agreement process
	private void Dissaprove(MAGRStage stage,String message)
	{
		stage.Dissapprove(AD_Table_ID, Record_ID, C_BPartner_ID, message);
		createNextStage(stage, true);
	}
	//Approve document and check for possibility to move to the next stage
	private boolean Approve(MAGRStage stage, String message)
	{		
		stage.Approve(AD_Table_ID, Record_ID, C_BPartner_ID, message);
		
		if(stage.isCanMove(AD_Table_ID, Record_ID))
		{
			createNextStage(stage, false);
		}
		else if(stage.isLastStage() && stage.isAllApproved(AD_Table_ID, Record_ID))
		{
			quit(stage);
			return true;
		}
		
		return false;
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
		int AGR_Dispatcher_ID = document.get_ValueAsInt(X_AGR_Dispatcher.COLUMNNAME_AGR_Dispatcher_ID);
		int value = 0;
		
		if(AGR_Dispatcher_ID > 0)
		{
			MAGRDispatcher dispatcher = new MAGRDispatcher(Env.getCtx(), AGR_Dispatcher_ID, null);
			
			value = dispatcher.getAGR_Agreement_ID();
		}
		return value;
	}
	
	private boolean validation() throws SQLException
	{
		MAGRAgreement agreement = new MAGRAgreement(Env.getCtx(), AGR_Agreement_ID, null);
		
		if(!agreement.checkAgreement()) return false;
						
		return true;		
	}
	//Get current stage of agreement
	private MAGRStage getCurrentStage()
	{
		MAGRStage stage = null;
		if(AGR_Stage_ID == 0)
		{
			stage = MAGRStage.getFirstStage(Env.getCtx(), AGR_Agreement_ID, null);
			if(stage != null)// && stage.isUserHasAccess(AD_User_ID, AD_Table_ID, Record_ID))
			{
				Agreement_PrepareList prepareList = new Agreement_PrepareList(AGR_Agreement_ID, C_BPartner_ID, AD_Table_ID, Record_ID);
				prepareList.FillAgreementList();
				FillAgreementList(stage, stage);
			}
		}
		else
		{
			stage = new MAGRStage(Env.getCtx(), AGR_Stage_ID, null);
		}
		
		return stage;
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
			
			document.set_ValueOfColumn(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID, stage.get_ID());
			document.saveEx();
			
			//If document is not approved, agreement list will not be filled
			if((currentStage.get_ID() != nodeArray[i].getAGR_NextStage_ID()) && isNotFilled(stage))
			{
				FillAgreementList(stage,currentStage);
			}
			
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
	private void FillAgreementList(MAGRStage toStage, MAGRStage fromStage)
	{
		ArrayList<Integer> signers = toStage.getSigners(AD_Table_ID, Record_ID);
		ArrayList<Integer> currentSigners = fromStage.getSigners(AD_Table_ID, Record_ID);
		//get current date&time
		Timestamp stamp = new Timestamp(System.currentTimeMillis());

		for(int i = 0; i < signers.size(); i++)
		{
			MAGRAgreementList list = new MAGRAgreementList(Env.getCtx(), null, null);			
			list.setAD_Table_ID(AD_Table_ID);
			list.setRecord_ID(Record_ID);
			list.setSigner_ID(signers.get(i));
			list.setAGR_Stage_ID(toStage.get_ID());
			list.setRecordCreated(stamp);
			list.setRecordUpdated(stamp);
			
			if(!list.save())
			{
				Log.log(Logger.ERROR, "Agreement List not saved");
			}
			
			if(currentSigners.contains(signers.get(i)) && toStage.get_ID() != fromStage.get_ID())
			{
				toStage.Approve(AD_Table_ID, Record_ID, signers.get(i), "");
				
				if(toStage.isCanMove(AD_Table_ID, Record_ID))
				{
					createNextStage(toStage, false);
				}
				else if(toStage.isLastStage() && toStage.isAllApproved(AD_Table_ID, Record_ID))
				{
					quit(toStage);
				}
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
