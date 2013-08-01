package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MPFROrderBy extends X_PFR_OrderBy 
{
	private static final long serialVersionUID = 3534954917109910372L;

	public MPFROrderBy(Properties ctx) 
	{
		super(ctx);
	}
	/** Standard Constructor */
    public MPFROrderBy (Properties ctx, int PFR_OrderBy_ID, String trxName)
    {
      super (ctx, PFR_OrderBy_ID, trxName);
      /** if (PFR_OrderBy_ID == 0)
        {
			setPFR_OrderBy_ID (0);
        } */
    }
    /** Load Constructor */
    public MPFROrderBy (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    /**
     * GENERATE 'ORDER BY' PART OF SQL FOR MPFRCalculation
     * @param PFR_Calculation_ID
     * @return
     */
    public static String getOrderBy(int PFR_Calculation_ID)
    {
    	StringBuilder sql = new StringBuilder();
    	
    	MPFROrderBy[] orders = MPFRCalculation.getLinesOrderBy(Env.getCtx(), PFR_Calculation_ID, null);
    	
    	if(orders.length > 0)
    		sql.append(" ORDER BY ");
    	
    	int counter = 0;    	
    	for(MPFROrderBy order : orders)
    	{
    		if(counter > 0)
    			sql.append(", ");
    		
    		sql.append(MColumn.getColumnName(Env.getCtx(), order.getAD_Column_ID()));
    		
    		counter++;
    	}
    	
    	return sql.toString();
    }
}
