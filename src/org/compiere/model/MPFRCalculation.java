package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MPFRCalculation extends X_PFR_Calculation 
{
	private static final long serialVersionUID = -2287385954375568093L;
	
    public MPFRCalculation(Properties ctx)
    { 
      super (ctx, null, null);
    } 

	public MPFRCalculation(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}

    public MPFRCalculation (Properties ctx, int PFR_Calculation_ID, String trxName)
    {
    	super (ctx, PFR_Calculation_ID, trxName);
    }
    
    public static MPFRWhereClause[] getLinesWhere(Properties ctx, int PFR_Calculation_ID, String trxName)
	{
		List<MPFRWhereClause> list = new Query(ctx, I_PFR_WhereClause.Table_Name, "PFR_Calculation_ID=?", trxName)
		.setParameters(PFR_Calculation_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MPFRWhereClause[] retValue = new MPFRWhereClause[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
    
    public static MPFRGroupBy[] getLinesGroupBy(Properties ctx, int PFR_Calculation_ID, String trxName)
	{
		List<MPFRGroupBy> list = new Query(ctx, I_PFR_GroupBy.Table_Name, "PFR_Calculation_ID=?", trxName)
		.setParameters(PFR_Calculation_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MPFRGroupBy[] retValue = new MPFRGroupBy[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
    
    public static MPFROrderBy[] getLinesOrderBy(Properties ctx, int PFR_Calculation_ID, String trxName)
	{
		List<MPFROrderBy> list = new Query(ctx, I_PFR_OrderBy.Table_Name, "PFR_Calculation_ID=?", trxName)
		.setParameters(PFR_Calculation_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MPFROrderBy[] retValue = new MPFROrderBy[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
    
    public static Object getValueFromSQL(int PFR_Calculation_ID) throws SQLException
    {
    	String sql = getSQLValue(PFR_Calculation_ID);
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	Object retValue = null;
    	
    	try
    	{
    		pstmt = DB.prepareStatement(sql, null);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next())
    		{
    			retValue = rs.getObject(1);
    		}
    	}
    	catch(SQLException ex) {
    		CLogger.get().log(Level.SEVERE, "sql = " + sql+", \n Message: "+ex.getMessage());
    	}
    	finally
    	{
    		rs.close(); pstmt.close();
    		rs = null; pstmt = null;
    	}
    	
    	return retValue;
    }
    
    /** 
     * 
     */
    public static String getSQLValue(int PFR_Calculation_ID)
    {
    	//General Query
    	StringBuilder sql = new StringBuilder();
    	StringBuilder sqlSelect = new StringBuilder();
    	StringBuilder sqlWhere = new StringBuilder();
    	StringBuilder sqlGroupBy = new StringBuilder();
    	StringBuilder sqlOrderBy = new StringBuilder();
    	
    	MPFRCalculation calculation = new MPFRCalculation(Env.getCtx(), PFR_Calculation_ID, null);
    	
    	// -->> Generate 'SELECT PART' OF SQL
    	
    	sqlSelect.append("SELECT ");
    	sqlSelect.append(calculation.getPFR_CalcType().getSqlValue()).append("(");
    	sqlSelect.append(MColumn.getColumnName(Env.getCtx(), calculation.getAD_Column_ID())).append(")");
    	sqlSelect.append(" FROM ");
    	sqlSelect.append(MTable.getTableName(Env.getCtx(), calculation.getAD_Table_ID()));    	
    	
    	// <<--    	
    	// -->> Generate 'WHERE PART' OF SQL
    	
    	sqlWhere.append(MPFRWhereClause.getWhereClause(PFR_Calculation_ID));
    	
    	// <<--
    	// -->> Generate 'GROUP BY PART' OF SQL
    	
    	sqlGroupBy.append(MPFRGroupBy.getGroupBy(PFR_Calculation_ID));
    	
    	// <<--
    	// -->> Generate 'ORDER BY PART' OF SQL
    	
    	sqlOrderBy.append(MPFROrderBy.getOrderBy(PFR_Calculation_ID));
    	
    	// <<--
    	sql.append(sqlSelect).append(sqlWhere).append(sqlGroupBy).append(sqlOrderBy);
    	
    	return sql.toString();
    } 
}
