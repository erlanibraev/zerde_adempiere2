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
import org.eevolution.model.X_HR_Employee;

/**
 * @author V.Sokolov
 *
 */
public class MBPMVersionBudget extends X_BPM_VersionBudget {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327769826133151517L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMVersionBudget.class);

	/**
	 * @param ctx
	 */
	public MBPMVersionBudget(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_VersionBudget_ID
	 * @param trxName
	 */
	public MBPMVersionBudget(Properties ctx, int BPM_VersionBudget_ID,
			String trxName) {
		super(ctx, BPM_VersionBudget_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMVersionBudget(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/* 
	 * 
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		X_BPM_VersionBudgetLine verLine = null;
		
		if(newRecord && success){
			verLine = new X_BPM_VersionBudgetLine(getCtx(),0,get_TrxName());
			verLine.setBPM_VersionBudget_ID(get_ID());
			verLine.setBPM_VersionType(X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_Current);
			verLine.setBPM_Version(X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_Current);
			verLine.saveEx();
		}
		
		return success;
	}
	
	/* Find the active version of the budget */
	public static X_BPM_VersionBudget getVersionBudget(){
		
		X_BPM_VersionBudget budget = new X_BPM_VersionBudget(Env.getCtx(), 0, null);
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The current budget
		String sql_ = "select * from bpm_versionBudget where isActive='Y'";
		
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();
			if(rs.next())
				budget = new X_BPM_VersionBudget(Env.getCtx(), rs, null);		
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
		
		return budget;
	}
	
	/* Find the active version of the budget into the current branch */
	public static int getCurrentVersion(){
		
		int current = 0;
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The current version of the budget
		String sql_ = "select r.bpm_versionbudgetline_id from bpm_versionBudget t \n" +
				"join bpm_versionbudgetline r on r.bpm_versionBudget_id=t.bpm_versionBudget_id \n" +
				"where t.isActive='Y' and r.bpm_versionType='"+X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_Current+"'";
		
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();
			if(rs.next())
				current = rs.getInt(1);			
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
		
		return current;
	}
	

}
