package org.compiere.model;

import java.util.Properties;

public class MTRMDepositLine extends X_TRM_DepositLine 
{
	private static final long serialVersionUID = -1963144110715950145L;

	public MTRMDepositLine(Properties ctx, int TRM_DepositLine_ID,
			String trxName) 
	{
		super(ctx, TRM_DepositLine_ID, trxName);
	}

}
