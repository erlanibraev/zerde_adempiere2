package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBPMCategory extends X_BPM_Category {
	private static final long serialVersionUID = -4620210497509268336L;

	public MBPMCategory(Properties ctx) {
		super(ctx);
	}
	 /** Standard Constructor */
    public MBPMCategory (Properties ctx, int BPM_Category_ID, String trxName)
    {
      super (ctx, BPM_Category_ID, trxName);
    }

    /** Load Constructor */
    public MBPMCategory (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public static int value(int BPM_Category_ID)
    {
    	return (new MBPMCategory(Env.getCtx(), BPM_Category_ID, null)).getValueNumber().intValue();
    }
    
    public static int minValue()
    {
    	int value = DB.getSQLValue(null, "SELECT MIN(ValueNumber) FROM BPM_Category");
    	
    	return value >= 0 ? value : 0;
    }
    
    public static int maxValue()
    {
    	int value = DB.getSQLValue(null, "SELECT MAX(ValueNumber) FROM BPM_Category");
    	
    	return value >= 0 ? value : 0;
    }
}
