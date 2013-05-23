package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MAGRAgreement extends X_AGR_Agreement 
{
	private static final long serialVersionUID = -704183934751993540L;

	public MAGRAgreement(Properties ctx, int AGR_Agreement_ID, String trxName) 
	{
		super(ctx, AGR_Agreement_ID, trxName);
	}

	 /** Load Constructor */
    public MAGRAgreement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
}
