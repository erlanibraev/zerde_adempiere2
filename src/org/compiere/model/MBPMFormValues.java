/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static MBPMFormValues getFormValueLine(int BPM_FormLine_ID, int BPM_FormColumn_ID){
		
		MBPMFormValues value = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Model Form values
		String sql_ = "SELECT * FROM "+I_BPM_FormValues.Table_Name+" \n" +
					  "WHERE  "+X_BPM_FormValues.COLUMNNAME_BPM_FormLine_ID+"="+BPM_FormLine_ID+" \n" +
					  "AND "+X_BPM_FormValues.COLUMNNAME_BPM_FormColumn_ID+"="+BPM_FormColumn_ID;
		
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
	

}
