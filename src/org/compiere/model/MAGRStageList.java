package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MAGRStageList extends X_AGR_StageList 
{
	private static final long serialVersionUID = 3851340249690638509L;

	public MAGRStageList(Properties ctx, int AGR_StageList_ID, String trxName) 
	{
		super(ctx, AGR_StageList_ID, trxName);
	}
	
	public MAGRStageList(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}
	
	public static MAGRStageList[] getOfAGR_Stage(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRStageList> list = new Query(ctx, I_AGR_StageList.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MAGRStageList[] retValue = new MAGRStageList[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
	
	public static List<MAGRStageList> getOfAGR_StageList(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRStageList> list = new Query(ctx, I_AGR_StageList.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public int getActiveSigner()
	{
		int retValue = 0;
		
		if(!isActive());
		else if(isHeaderActive()) retValue = getHR_Header_ID();
		else if(isAlternateActive()) retValue = getAlternate_ID();
		else retValue = getAlternate2_ID();
		
		return retValue;
	}

}
