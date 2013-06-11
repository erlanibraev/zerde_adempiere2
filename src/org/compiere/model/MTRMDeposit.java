package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MTRMDeposit extends X_TRM_Deposit 
{
	private static final long serialVersionUID = -8860470031117295336L;

	public MTRMDeposit(Properties ctx, int TRM_Deposit_ID, String trxName) 
	{
		super(ctx, TRM_Deposit_ID, trxName);
	}

    /** Load Constructor */
    public MTRMDeposit (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public static MTRMDeposit[] getOfCMS_Contract(Properties ctx, int CMS_Contract_ID, String trxName)
	{
		List<MTRMDeposit> list = new Query(ctx, I_TRM_Deposit.Table_Name, "CMS_Contract_ID=?", trxName)
		.setParameters(CMS_Contract_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MTRMDeposit[] retValue = new MTRMDeposit[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
}
