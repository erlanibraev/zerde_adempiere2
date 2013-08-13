package org.compiere.model;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.compiere.pfr.QueryDialog;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

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
    
    public int getMaxLine(int PFR_Calculation_ID)
    {
    	int line = DB.getSQLValue(null, "SELECT MAX(Line) FROM "+get_TableName()+" WHERE "+X_PFR_WhereClause.COLUMNNAME_PFR_Calculation_ID+" = " + PFR_Calculation_ID);
    	
    	return line > 0 ? line : 0;
    }
    
    /**
     * Generate Clause for query OF MPFRCalculation
     * Return 'WHERE PART' OF SQL VALUE
     * @author E.Maimanov
     * @param PFR_Calculation_ID
     * @return
     */
    public static String getWhereClause(int PFR_Calculation_ID, LinkedHashMap<String, Object> parameters)
    {
    	StringBuilder sql = new StringBuilder();
    	MPFRWhereClause[] clauses = MPFRCalculation.getLinesWhere(Env.getCtx(), PFR_Calculation_ID, null);
    	
    	sql.append(" WHERE ");    	
    	
    	String quotesRequired = "";
    	String dateRequiredOpen = "";
    	String dateRequiredClose = "";
    	
    	int counter = 0;    	
    	MColumn column = null;
    	
    	String value1 = "";
    	
    	for(MPFRWhereClause clause : clauses)
    	{
    		
    		column = new MColumn(Env.getCtx(), clause.getAD_Column_ID(), null);
    		
    		if(parameters.get(clause.getColumnName()) != null && !clause.isStatic())
    			value1 = parameters.get(clause.getColumnName()).toString();
    		else
    			value1 = clause.getValue1();
    		
    		if(column.getAD_Reference_ID() == DisplayType.String
    				|| column.getAD_Reference_ID() == DisplayType.Text
    				|| column.getAD_Reference_ID() == DisplayType.YesNo)
    			quotesRequired = "'";
    		else if(column.getAD_Reference_ID() == DisplayType.Date)
    		{
    			dateRequiredOpen = "cast(";
    			dateRequiredClose = " as date)";
    			quotesRequired = "'";
    		}
    		else if(column.getAD_Reference_ID() == DisplayType.DateTime)
    		{
    			dateRequiredOpen = "cast(";
    			dateRequiredClose = " as timestamp)";
    			quotesRequired = "'";
    		}
    		else 
    		{
    			dateRequiredOpen = "";
    			dateRequiredClose = "";
    			quotesRequired = "";
    		}
    		if(counter > 0)
    			sql.append(clause.getAndOr()).append(" \n");
    		if(clause.getOpenBracket() != null)
    			sql.append(clause.getOpenBracket()).append(" \n");
    		sql.append(clause.getColumnName()).append(" \n");
    		
    		String chargeIN = (parameters.get(I_BPM_FormParameters.COLUMNNAME_C_Charge_ID) == null) ? "" : (String) parameters.get(I_BPM_FormParameters.COLUMNNAME_C_Charge_ID);
    		if(chargeIN.indexOf(",") != -1)
    			clause.setOperation(QueryDialog.OPERATORS[QueryDialog.IN_INDEX].getName());
    		
    		sql.append(clause.getOperation()).append(" \n");
    		
    		if(clause.getOperation().trim().equals(QueryDialog.OPERATORS[QueryDialog.IN_INDEX].getName().trim()))
    		{
    			String subSql = "";
    			if(chargeIN.indexOf(",") == -1)
    				subSql = MPFRCalculation.getSQLValue(Integer.parseInt(value1), parameters);
    			else
    				subSql = (String) parameters.get(I_BPM_FormParameters.COLUMNNAME_C_Charge_ID);
    			
    			sql.append(dateRequiredOpen)
	    		.append(quotesRequired);
    			sql.append("(").append(subSql).append(")");
    		}
    		else
    		{
        		sql.append(dateRequiredOpen)
	    		.append(quotesRequired)
        		.append(value1);
    		}

	    		
	    		sql.append(quotesRequired)
	    		.append(dateRequiredClose)
	    		.append(" \n");
    		if(clause.getValue2() != null && !clause.getValue2().isEmpty())
    			sql.append(" AND ")
	    			.append(dateRequiredOpen)
	    			.append(quotesRequired)
	    			.append(clause.getValue2())
	    			.append(quotesRequired)
	    			.append(dateRequiredClose)
	    			.append(" \n");
    		
    		if(clause.getCloseBracket() != null)
    			sql.append(clause.getCloseBracket())
    			.append(" \n"); 		
    		
    		counter++;
    	}
    	
    	if(clauses.length > 0)
    		sql.append(" AND ");
    	
    	sql.append("AD_Client_ID = ").append(Env.getAD_Client_ID(Env.getCtx())).append(" \n")
    	.append(" AND AD_Org_ID = ").append(Env.getAD_Org_ID(Env.getCtx()));
    	
    	return sql.toString();
    }
}
