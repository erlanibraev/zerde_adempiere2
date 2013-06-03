package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MAGRApprovalList extends X_AGR_ApprovalList 
{

	public MAGRApprovalList(Properties ctx, int AGR_ApprovalList_ID, String trxName) 
	{
		super(ctx, AGR_ApprovalList_ID, trxName);
	}
	
	public MAGRApprovalList (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	
	public static List<MAGRApprovalList> getOfAGR_StageList(Properties ctx, int AGR_Stage_ID, int AD_Table_ID, int Record_ID, String trxName)
	{
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(AGR_Stage_ID);
		parameters.add(AD_Table_ID);
		parameters.add(Record_ID);		
		
		List<MAGRApprovalList> list = new Query(ctx, I_AGR_ApprovalList.Table_Name, "AGR_Stage_ID=? AND AD_Table_ID=? AND Record_ID=?", trxName)
		.setParameters(parameters)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	

}
