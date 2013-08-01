package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MPFRGroupBy extends X_PFR_GroupBy 
{
	private static final long serialVersionUID = -6482905803560758286L;

	public MPFRGroupBy(Properties ctx) 
	{
		super(ctx);
	}

	public MPFRGroupBy (Properties ctx, int PFR_GroupBy_ID, String trxName)
    {
      super (ctx, PFR_GroupBy_ID, trxName);
    }

    /** Load Constructor */
    public MPFRGroupBy (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    /**
     * GENERATE 'GROUP BY' PART OF SQL FOR MPFRCalculation
     * @param PFR_Calculation_ID
     * @return
     */
    public static String getGroupBy(int PFR_Calculation_ID)
    {
    	StringBuilder sql = new StringBuilder();
    	
    	MPFRGroupBy[] groups = MPFRCalculation.getLinesGroupBy(Env.getCtx(), PFR_Calculation_ID, null);
    	
    	if(groups.length > 0)
    		sql.append(" GROUP BY ");
    	
    	int counter = 0;    	
    	for(MPFRGroupBy group : groups)
    	{
    		if(counter > 0)
    			sql.append(", ");
    		
    		sql.append(MColumn.getColumnName(Env.getCtx(), group.getAD_Column_ID()));
    		
    		counter++;
    	}
    	
    	return sql.toString();
    }
}
