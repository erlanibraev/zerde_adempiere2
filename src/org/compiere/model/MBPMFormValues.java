/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author V.Sokolov
 *
 */
public class MBPMFormValues extends X_BPM_FormValues {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2218423859000507823L;

	/**
	 * @param ctx
	 */
	public MBPMFormValues(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormValues_ID
	 * @param trxName
	 */
	public MBPMFormValues(Properties ctx, int BPM_FormValues_ID, String trxName) {
		super(ctx, BPM_FormValues_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormValues(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MBPMFormValues getFormValueLine(int BPM_FormLine_ID, int BPM_FormColumn_ID, int BPM_VersionBudget_ID, int BPM_Project_ID){
		
		MBPMFormValues value = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Model Form values
		String sql_ = "SELECT * FROM "+I_BPM_FormValues.Table_Name+" \n" +
					  "WHERE  "+X_BPM_FormValues.COLUMNNAME_BPM_FormLine_ID+"="+BPM_FormLine_ID+" \n" +
					  "AND "+X_BPM_FormValues.COLUMNNAME_BPM_FormColumn_ID+"="+BPM_FormColumn_ID+" \n" +
					  "AND "+X_BPM_FormValues.COLUMNNAME_BPM_VersionBudget_ID+"="+BPM_VersionBudget_ID+" \n" +
					  "AND "+X_BPM_FormValues.COLUMNNAME_BPM_Project_ID+"="+BPM_Project_ID;
		
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();
			if(rs.next())
				value = new MBPMFormValues(Env.getCtx(), rs, null);		
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.INFO, "SQL = "+sql_, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return value;
	}
	
	public static Integer[] getRows(Properties ctx, int BPM_Form_ID, int BMP_Project_ID, String trxName) throws SQLException
	{
		String sql = "SELECT l.BPM_FormLine_ID FROM BPM_FormLine l \n" +
				"INNER JOIN BPM_FormValues v ON l.BPM_FormLine_ID = v.BPM_FormLine_ID \n" +
				" WHERE v.BPM_Form_ID = ? \n" +
				" AND v.BPM_Project_ID = ? \n" +
				"GROUP BY l.BPM_FormLine_ID \n" +
				"ORDER BY l.LineNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Integer> rows = new ArrayList<Integer>();
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, BPM_Form_ID);
			pstmt.setInt(2, BMP_Project_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				rows.add(rs.getInt(1));
			}
		}
		catch(Exception ex){}
		finally
		{
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		
		Integer[] retValue = new Integer[rows.size()];
		rows.toArray(retValue);
		
		return retValue;
	}
	
	public static MBPMFormValues[] getValuesOrdered(Properties ctx, int BPM_FormLine_ID, int BMP_Project_ID, String trxName)
	{
		List<MBPMFormValues> list = new ArrayList<MBPMFormValues>();
		String sql = "SELECT v.BPM_FormValues_ID FROM BPM_FormValues v \n" +
				"INNER JOIN BPM_FormColumn c ON v.BPM_FormColumn_ID = c.BPM_FormColumn_ID \n" +
				"WHERE v.BPM_FormLine_ID = ? \n AND v.BPM_Project_ID = ? AND c.isActive = 'Y'" +
				"ORDER BY c.OrderColumn";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, BPM_FormLine_ID);
			pstmt.setInt(2, BMP_Project_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MBPMFormValues value = new MBPMFormValues(Env.getCtx(), rs.getInt(1), null);
				
				list.add(value);
			}
		}
		catch(Exception ex){}
		finally
		{
			try 
			{
				rs.close();
				pstmt.close();
			} catch (SQLException e) {e.printStackTrace();}
			rs = null; pstmt = null;
		}
		
		MBPMFormValues[] retValue = new MBPMFormValues[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
	
	public static MBPMFormValues[] getValues(Properties ctx, int BPM_FormLine_ID, String trxName)
	{
		List<MBPMFormValues> list = new Query(ctx, I_BPM_FormValues.Table_Name, "BPM_FormLine_ID=?", trxName)
		.setParameters(BPM_FormLine_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMFormValues[] retValue = new MBPMFormValues[list.size ()];
		list.toArray (retValue);
		return retValue;
	}

}
