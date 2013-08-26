package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	
	public static List<MAGRStage> getOfAGR_Agreement(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> list = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=?", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	private MAGRStage getTransitStage(boolean isBack)
	{
		MAGRNode[] nodes = MAGRNode.getOfTRM_Stage(getCtx(), get_ID(), get_TrxName());
		
		MAGRStage transitStage = null;
		
		for(int i = 0; i < nodes.length; i++)
		{
			MAGRStage nextStage = new MAGRStage(getCtx(), nodes[i].getAGR_NextStage_ID(), get_TrxName());
			
			if(isBack && nodes[i].isBack())
				transitStage = nextStage;
			else if(!isBack &&!nodes[i].isBack())
				transitStage = nextStage;
		}
		
		return transitStage;
	}
	
	public MAGRStage getPreviousStage()
	{
		return getTransitStage(true);
	}
	
	public MAGRStage getNextStage()
	{
		return getTransitStage(false);
	}
	
	public boolean hasNodes()
	{
		return MAGRNode.getOfTRM_Stage(getCtx(), this.get_ID(), get_TrxName()).length > 0;
	}
	
	public static MAGRStage getFirstStage(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> stages = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=? AND StageType LIKE '" + X_AGR_Stage.STAGETYPE_Initial + "'", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true).list();
		
		MAGRStage firstStage = stages.size() > 0 ? stages.get(0) : null;
		
		return firstStage;
	}
	
	public ArrayList<Integer> getSigners(int AD_Table_ID, int Record_ID)
	{
		ArrayList<MAGRApprovalList> lines = (ArrayList<MAGRApprovalList>) MAGRApprovalList.getOfAGR_StageList(getCtx(), get_ID(), AD_Table_ID, Record_ID, get_TrxName());
		
		ArrayList<Integer> signers = new ArrayList<Integer>();
		
		for(int i = 0;i < lines.size(); i++)
			signers.add(lines.get(i).getC_BPartner_ID());
		
		return signers;
	}
	
	public boolean isUserHasAccess(int AD_User_ID, int AD_Table_ID, int Record_ID)
	{
		MUser user = new MUser(getCtx(), AD_User_ID, get_TrxName());		
		
		return getSigners(AD_Table_ID, Record_ID).contains(user.getC_BPartner_ID());
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
	public void Approve(int AD_Table_ID, int Record_ID, int C_BPartner_ID, String message)
	{
		ArrayList<MAGRAgreementList> stageList = MAGRAgreementList.getOfStage(getCtx(), get_TrxName(), AD_Table_ID, Record_ID);
		
		for(int i = 0; i < stageList.size(); i++)
		{
			if(stageList.get(i).getSigner_ID() == C_BPartner_ID && stageList.get(i).isNotApproved() && get_ID() == stageList.get(i).getAGR_Stage_ID())
			{
				stageList.get(i).setSign_Y(true);
				stageList.get(i).setSign_N(false);
				stageList.get(i).setIsApproved(true);
				stageList.get(i).setDescription(message);
				stageList.get(i).setRecordUpdated(new Timestamp(System.currentTimeMillis()));
				stageList.get(i).save();
				break;
			}
		}
	}

	public boolean canMoveForward()
	{
		return getTransitStage(false) != null;
	}
	
	public boolean canMoveBack()
	{
		return getTransitStage(true) != null;
	}
	
	//Check if this stage is the last stage of agreement
	public boolean isLastStage()
	{
		boolean retValue = true;
		
		if(getStageType().equals(STAGETYPE_Initial))
		{
			return false;
		}
		if(getStageType().equals(STAGETYPE_Final))
		{
			return true;
		}
		ArrayList<MAGRNode> nodes = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), get_ID(), get_TrxName());
		
		for(MAGRNode node : nodes)
		{
			if(node.isBack())
				retValue = false;
		}
		
		return retValue;
	}
}
