package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MAGRDispatcher extends X_AGR_Dispatcher 
{
	private static final long serialVersionUID = -458002004089594904L;

	public MAGRDispatcher(Properties ctx, int AGR_Dispatcher_ID, String trxName) 
	{
		super(ctx, AGR_Dispatcher_ID, trxName);
	}
	
    public MAGRDispatcher (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

}
