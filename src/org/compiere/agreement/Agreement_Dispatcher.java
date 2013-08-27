package org.compiere.agreement;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.catalina.Logger;
import org.compiere.apps.DialogAgreement;
import org.compiere.model.MAGRAgreementList;
import org.compiere.model.MAGRDispatcher;
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
		AGR_Agreement_ID = MAGRDispatcher.AGR_Agreement_ID(document.get_ValueAsInt("AGR_Dispatcher_ID"));
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		C_BPartner_ID =  getBPartner();
	}
	//Start agreement process
	public int startAgreement(boolean isAprove,String message)
	{
		MAGRStage currentStage = getCurrentStage();
		
		int returnValue = 0;
		
		//Check if current stage is a last stage of current agreement
		if(currentStage.isLastStage() && currentStage.isAllApproved(AD_Table_ID, Record_ID))
		{
			DialogAgreement.dialogOK("Ошибка доступа", "Документ согласован", 0);
			return returnValue;
		}
	
		//Check if current user has access for current stage
		if(!currentStage.isUserHasAccess(AD_User_ID, AD_Table_ID, Record_ID))
		{
			DialogAgreement.dialogOK("Ошибка доступа", "У вас нет доступа к данному этапу согласования", 0);
			return returnValue;
		}
		
		boolean isStageApproved = false;
		
		if(isAprove)
		{
			isStageApproved = aprove(currentStage, message);
			returnValue = isStageApproved ? 1 : 0;
		}
		else
		{
			isStageApproved = dissAprove(currentStage, message);
			returnValue = isStageApproved ? 2 : 0;
		}
		
		if(isStageApproved)
		{
			quit(currentStage, !isAprove);
			if(isAprove && currentStage.canMoveForward())
			{
				createNextStage(currentStage, !isAprove);
			}
			else if(!isAprove && currentStage.canMoveBack())
			{
				createNextStage(currentStage, !isAprove);
			}
			else
			{
				returnValue = 3;
			}
		}
		return returnValue;
	}
	
	private boolean aprove(MAGRStage stage, String message)
	{
		stage.Approve(AD_Table_ID, Record_ID, C_BPartner_ID, message);
		
		if(stage.isAllApproved(AD_Table_ID, Record_ID))
			return true;
		
		return false;
	}
	
	private boolean dissAprove(MAGRStage stage, String message)
	{
		stage.Dissapprove(AD_Table_ID, Record_ID, C_BPartner_ID, message);
		
		return true;
	}
	
	private void createNextStage(MAGRStage stage, boolean approved)
	{
		MAGRNode[] nodeArray = MAGRNode.getOfAGR_Stage(Env.getCtx(), stage.get_ID(), approved, null);
		
		for(MAGRNode node : nodeArray)
		{
			MAGRStage nextStage = new MAGRStage(Env.getCtx(), node.getAGR_NextStage_ID(), null);
			FillAgreementList(nextStage);
			
			document.set_ValueOfColumn(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID, nextStage.get_ID());
			document.saveEx();
		}
		
		quit(stage, approved);
	}
	//Fill agreement list
	private void FillAgreementList(MAGRStage toStage)
	{
		ArrayList<Integer> signers = toStage.getSigners(AD_Table_ID, Record_ID);
		//get current date&time
		Timestamp stamp = new Timestamp(System.currentTimeMillis());

		for(Integer signer : signers)
		{
			MAGRAgreementList list = new MAGRAgreementList(Env.getCtx(), null, null);			
			list.setAD_Table_ID(AD_Table_ID);
			list.setRecord_ID(Record_ID);
			list.setSigner_ID(signer);
			list.setAGR_Stage_ID(toStage.get_ID());
			list.setRecordCreated(stamp);
			list.setRecordUpdated(stamp);
			
			if(!list.save())
			{
				Log.log(Logger.ERROR, "Agreement List not saved");
			}
		}
	}
	//Get C_BPartner_ID of current user (using AD_User_ID)
	private int getBPartner()
	{
		String sql = "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = " + AD_User_ID;
		
		return DB.getSQLValue(null, sql);
	}

	private void quit(MAGRStage stage, boolean isBack)
	{
		X_AD_Ref_List list = null;
		if(!isBack)
			list = new X_AD_Ref_List(Env.getCtx(), stage.getAD_Ref_List_ID(), null);
		else
			list = new X_AD_Ref_List(Env.getCtx(), stage.getAD_Ref_List2_ID(), null);

		document.set_ValueOfColumn(columnDocStatus, list.getValue());
		document.saveEx();
	}
	//Get current stage of agreement
	private MAGRStage getCurrentStage()
	{
		MAGRStage stage = null;
		if(AGR_Stage_ID == 0 || !isHasRecordsInAgreementList())
		{
			stage = MAGRStage.getFirstStage(Env.getCtx(), AGR_Agreement_ID, null);
			if(stage != null)
			{
				Agreement_PrepareList prepareList = new Agreement_PrepareList(AGR_Agreement_ID, C_BPartner_ID, AD_Table_ID, Record_ID);
				prepareList.FillAgreementList();
				FillAgreementList(stage);
			}
		}
		else
		{
			stage = new MAGRStage(Env.getCtx(), AGR_Stage_ID, null);
		}
		
		return stage;
	}
	
	private boolean isHasRecordsInAgreementList()
	{
		return DB.getSQLValue(null, "SELECT AGR_AgreementList_ID FROM AGR_AgreementList WHERE Record_ID = " + Record_ID) > -1;
	}
}
