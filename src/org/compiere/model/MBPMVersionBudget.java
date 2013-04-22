/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author V.Sokolov
 *
 */
public class MBPMVersionBudget extends X_BPM_VersionBudget {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327769826133151517L;

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
	

}
