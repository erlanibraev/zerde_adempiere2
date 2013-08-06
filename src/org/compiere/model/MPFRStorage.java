package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MPFRStorage extends X_PFR_Storage 
{
	private static final long serialVersionUID = -4351736205460031790L;

	public MPFRStorage(Properties ctx) 
	{
		super(ctx);
	}

    /** Standard Constructor */
    public MPFRStorage (Properties ctx, int PFR_Storage_ID, String trxName)
    {
      super (ctx, PFR_Storage_ID, trxName);
    }

    /** Load Constructor */
    public MPFRStorage (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public static MPFRStorage getLastStorage(Properties ctx, int PFR_Calculation_ID, String trxName)
	{
		MPFRStorage storage = null;
		
		int lastId = DB.getSQLValue(null, "SELECT t.PFR_Storage_ID FROM PFR_Storage t WHERE t.EndDate = \n"
				+ "(SELECT MAX(tt.EndDate) FROM PFR_Storage tt WHERE tt.PFR_Calculation_ID = t.PFR_Calculation_ID) \n"
				+ "AND t.PFR_Calculation_ID = " + PFR_Calculation_ID);
		
		if(lastId > 0)
			storage = new MPFRStorage(Env.getCtx(), lastId, null);
		
		return storage;
	}
}
