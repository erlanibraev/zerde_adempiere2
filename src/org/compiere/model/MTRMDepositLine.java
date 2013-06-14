package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MTRMDepositLine extends X_TRM_DepositLine 
{
	private static final long serialVersionUID = -1963144110715950145L;

	public MTRMDepositLine(Properties ctx, int TRM_DepositLine_ID,
			String trxName) 
	{
		super(ctx, TRM_DepositLine_ID, trxName);
	}
	
	public MTRMDepositLine (Properties ctx, ResultSet rs, String trxName)
	{
	      super (ctx, rs, trxName);
	}
	
	public static MTRMDepositLine[] getOfBankStatement(Properties ctx, int C_BankStatement_ID, String trxName)
	{
		List<MTRMDepositLine> list = new Query(ctx, I_TRM_DepositLine.Table_Name, "C_BankStatement_ID=?", trxName)
		.setParameters(C_BankStatement_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MTRMDepositLine[] retValue = new MTRMDepositLine[list.size ()];
		list.toArray (retValue);
		return retValue;
	}

}
