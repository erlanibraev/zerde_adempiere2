package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MAGRStageListLine extends X_AGR_StageListLine 
{

	public MAGRStageListLine(Properties ctx, int AGR_StageListLine_ID, String trxName) 
	{
		super(ctx, AGR_StageListLine_ID, trxName);
	}
	
	public MAGRStageListLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	
	public static List<MAGRStageListLine> getOfAGR_StageList(Properties ctx, int AGR_StageList_ID, String trxName)
	{
		List<MAGRStageListLine> list = new Query(ctx, I_AGR_StageListLine.Table_Name, "AGR_StageList_ID=? AND isAlsoInclude='N' AND isExceptFor='N'", trxName)
		.setParameters(AGR_StageList_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static List<MAGRStageListLine> getOfAGR_StageListAlsoInclude(Properties ctx, int AGR_StageList_ID, String trxName)
	{
		List<MAGRStageListLine> list = new Query(ctx, I_AGR_StageListLine.Table_Name, "AGR_StageList_ID=? AND isAlsoInclude = 'Y'", trxName)
		.setParameters(AGR_StageList_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static List<MAGRStageListLine> getOfAGR_StageListAvoid(Properties ctx, int AGR_StageList_ID, String trxName)
	{
		List<MAGRStageListLine> list = new Query(ctx, I_AGR_StageListLine.Table_Name, "AGR_StageList_ID=? AND isExceptFor = 'Y'", trxName)
		.setParameters(AGR_StageList_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}

}
