package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MAGRStage extends X_AGR_Stage 
{
	private static final long serialVersionUID = -1253764804008680542L;
	private static String firstStageName = "start";

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
		List<MAGRStage> list = new Query(ctx, I_AGR_Stage.Table_Name, "TRM_Agreement_ID=?", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static MAGRStage getFirstStage(Properties ctx, int AGR_Agreement_ID, String trxName)
	{
		List<MAGRStage> stages = new Query(ctx, I_AGR_Stage.Table_Name, "AGR_Agreement_ID=? AND lower(Name) LIKE '" + firstStageName + "'", trxName)
		.setParameters(AGR_Agreement_ID)
		.setOnlyActiveRecords(true).list();//.get(0));
		
		MAGRStage firstStage = stages.size() > 0 ? stages.get(0) : null;
		
		return firstStage;
	}
	
	public ArrayList<Integer> getSigners()
	{
		ArrayList<Integer> signers = new ArrayList<Integer>();
		
		List<MAGRStageList> stageOptions = MAGRStageList.getOfAGR_StageList(getCtx(), super.get_ID(), get_TrxName());
		
		if(stageOptions.size() == 0) return signers;
		
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
	
}
