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
public class MBPMABP extends X_BPM_ABP {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3607333607745941879L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMABP.class);

	/**
	 * @param ctx
	 */
	public MBPMABP(Properties ctx) {
		super(ctx);
	}
	
	/**
	 * @param ctx
	 * @param BPM_ABP_ID
	 * @param trxName
	 */
	public MBPMABP(Properties ctx, int BPM_ABP_ID, String trxName) {
		super(ctx, BPM_ABP_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMABP(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Find the right ABP appropriate Department
	 * @param Department_ID
	 * @return
	 */
	public static X_BPM_ABP getABP(int Department_ID){
		
		X_BPM_ABP abp = new X_BPM_ABP(Env.getCtx(), 0, null);
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The current budget
		String sql_ = "select * from bpm_abp where hr_department_id="+Department_ID;
		
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();
			if(rs.next())
				abp = new X_BPM_ABP(Env.getCtx(), rs, null);		
		}
		catch (SQLException e)
		{
			s_log.log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return abp;
	}

}
