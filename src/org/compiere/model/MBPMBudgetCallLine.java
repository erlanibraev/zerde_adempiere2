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
public class MBPMBudgetCallLine extends X_BPM_BudgetCallLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1468455812587961444L;

	/**
	 * @param ctx
	 */
	public MBPMBudgetCallLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_BudgetCallLine_ID
	 * @param trxName
	 */
	public MBPMBudgetCallLine(Properties ctx, int BPM_BudgetCallLine_ID,
			String trxName) {
		super(ctx, BPM_BudgetCallLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMBudgetCallLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	
	/* 
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		return super.afterSave(newRecord, success);
	}

}
