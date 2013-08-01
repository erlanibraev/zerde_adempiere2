package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPFRCalcType extends X_PFR_CalcType 
{
	private static final long serialVersionUID = 5787220714259113607L;

	public MPFRCalcType(Properties ctx) 
	{
		super(ctx);
		
	}
	/** Standard Constructor */
    public MPFRCalcType (Properties ctx, int PFR_CalcType_ID, String trxName)
    {
      super (ctx, PFR_CalcType_ID, trxName);
    }
    /** Load Constructor */
    public MPFRCalcType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
}
