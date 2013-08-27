package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

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
    
    public static int AGR_Agreement_ID(int AGR_Dispatcher_ID)
    {
    	return (new MAGRDispatcher(Env.getCtx(), AGR_Dispatcher_ID, null)).getAGR_Agreement_ID();	
    }

}
