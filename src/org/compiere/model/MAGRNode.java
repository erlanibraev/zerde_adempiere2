package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MAGRNode extends X_AGR_Node 
{
	private static final long serialVersionUID = -231556931277061675L;

	public MAGRNode(Properties ctx, int AGR_Node_ID, String trxName) 
	{
		super(ctx, AGR_Node_ID, trxName);
	}

	public MAGRNode (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public static List<MAGRNode> getOfAGR_StageList(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRNode> list = new Query(ctx, I_AGR_Node.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static MAGRNode[] getOfTRM_Stage(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRNode> list = new Query(ctx, I_AGR_Node.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MAGRNode[] retValue = new MAGRNode[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
}
