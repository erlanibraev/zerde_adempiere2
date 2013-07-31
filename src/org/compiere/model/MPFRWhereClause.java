package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPFRWhereClause extends X_PFR_WhereClause 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6767554120785647507L;

	public MPFRWhereClause(Properties ctx, int PFR_WhereClause_ID, String trxName) 
	{
		super(ctx, PFR_WhereClause_ID, trxName);
	}

    public MPFRWhereClause (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public MPFRWhereClause(Properties ctx)
    { 
      super (ctx, null, null);
    } 
}
