package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eevolution.model.MHREmployee;

public class MAGRStage extends X_AGR_Stage 
{
	private static final long serialVersionUID = -1253764804008680542L;

	public MAGRStage(Properties ctx, int AGR_Stage_ID, String trxName) 
	{
		super(ctx, AGR_Stage_ID, trxName);
	}
	
	public MAGRStage(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public static MAGRStage[] getOfAGR_Stage(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> list = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=?", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MAGRStage[] retValue = new MAGRStage[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
	
	public static List<MAGRStage> getOfAGR_StageList(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> list = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=?", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static MAGRStage getFirstStage(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> stages = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=? AND StageType LIKE '" + X_AGR_Stage.STAGETYPE_Initial + "'", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true).list();
		
		MAGRStage firstStage = stages.size() > 0 ? stages.get(0) : null;
		
		return firstStage;
	}
	
	public ArrayList<Integer> getSigners()
	{
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(get_ID());
		List<MAGRStageList> stageOptions = null;
		if(!isMultiStage())
			stageOptions = new Query(getCtx(), I_AGR_StageList.Table_Name, "AGR_Stage_ID=?", get_TrxName())
			.setParameters(parameters)
			.setOnlyActiveRecords(true).list();
		else
			stageOptions = MAGRStageList.getOfAGR_StageList(getCtx(), super.get_ID(), get_TrxName());

		if(stageOptions == null) return null;
		
		ArrayList<Integer> signers = new ArrayList<Integer>();
		
		MAGRStageList option = null;
		
		for(int i = 0; i < stageOptions.size(); i++)
		{
			option = stageOptions.get(i);
			
			if(option.isHeaderActive())
			{
				signers.add(option.getHR_Header_ID());
			}
			else if(option.isAlternateActive())
			{
				signers.add(option.getAlternate_ID());
			}
			else
			{
				signers.add(option.getAlternate2_ID());
			}
		}
		
		return signers;
	}
	
	public boolean isUserHasAccess(int AD_User_ID)
	{
		MUser user = new MUser(getCtx(), AD_User_ID, get_TrxName());		
		
		return getSigners().contains(user.getC_BPartner_ID());
	}

	//Check for approvement of agreement with all persons
	public boolean isCanMove(int AD_Table_ID, int Record_ID)
	{
		if(getStageType().equals(X_AGR_Stage.STAGETYPE_Initial)) return true;
		ArrayList<MAGRAgreementList> stageList = MAGRAgreementList.getOfStage(getCtx(), get_TrxName(), AD_Table_ID, Record_ID, get_ID());
		
		for(int i = 0; i < stageList.size(); i++)
			if(!stageList.get(i).isSign_Y())
				return false;
		
		ArrayList<MAGRNode> nodes = new ArrayList<MAGRNode>();
		
		nodes.addAll(MAGRNode.getOfAGR_StageList(getCtx(), get_ID(), get_TrxName()));
		
		if(nodes.size() == 1 && nodes.get(0).isBack()) return false;
		
		else return nodes.size() > 0;
	}
	
	public boolean isAllApproved(int AD_Table_ID, int Record_ID)
	{
		if(getStageType().equals(X_AGR_Stage.STAGETYPE_Initial)) return true;
		
		ArrayList<MAGRAgreementList> stageList = MAGRAgreementList.getOfStage(getCtx(), get_TrxName(), AD_Table_ID, Record_ID, get_ID());
		
		return stageList.size() == 0;
	}
	
	//Dissapprove stage
	public void Dissapprove(int AD_Table_ID, int Record_ID, int C_BPartner_ID, String message)
	{
		ArrayList<MAGRAgreementList> stageList = MAGRAgreementList.getOfStage(getCtx(), get_TrxName(), AD_Table_ID, Record_ID, get_ID());
		
		for(int i = 0; i < stageList.size(); i++)
		{
			if(stageList.get(i).getSigner_ID() == C_BPartner_ID && stageList.get(i).isNotApproved())
			{
				stageList.get(i).setSign_N(true);
				stageList.get(i).setSign_Y(false);
				stageList.get(i).setIsApproved(true);
				stageList.get(i).setDescription(message);
				stageList.get(i).setRecordUpdated(new Timestamp(System.currentTimeMillis()));
			}
			stageList.get(i).setIsApproved(true);
			stageList.get(i).save();
		}
	}
	
	//Approve stage
	public void Approve(int AD_Table_ID, int Record_ID, int C_BPartner_ID)
	{
		ArrayList<MAGRAgreementList> stageList = MAGRAgreementList.getOfStage(getCtx(), get_TrxName(), AD_Table_ID, Record_ID);
		
		for(int i = 0; i < stageList.size(); i++)
		{
			if(stageList.get(i).getSigner_ID() == C_BPartner_ID && stageList.get(i).isNotApproved() && get_ID() == stageList.get(i).getAGR_Stage_ID())
			{
				stageList.get(i).setSign_Y(true);
				stageList.get(i).setSign_N(false);
				stageList.get(i).setIsApproved(true);
				stageList.get(i).setRecordUpdated(new Timestamp(System.currentTimeMillis()));
				stageList.get(i).save();
				break;
			}
		}
	}

	//Check if this stage is the last stage of agreement
	public boolean isLastStage()
	{
		boolean retValue = false;
		ArrayList<MAGRNode> nodes = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), get_ID(), get_TrxName());
		
		if(nodes.size() == 1 && nodes.get(0).isBack() && nodes.get(0).getAGR_NextStage_ID() > 0)
			retValue = true;

		return retValue;
	}
}
