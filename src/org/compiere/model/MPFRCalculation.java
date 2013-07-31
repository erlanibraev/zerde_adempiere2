package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPFRCalculation extends X_PFR_Calculation 
{
	private static final long serialVersionUID = -2287385954375568093L;

	public MPFRCalculation(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}

    public MPFRCalculation (Properties ctx, int PFR_Calculation_ID, String trxName)
    {
    	super (ctx, PFR_Calculation_ID, trxName);
    }
}
