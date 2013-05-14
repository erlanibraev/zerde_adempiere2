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
public class MBPMBudgetCall extends X_BPM_BudgetCall {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5851240296742909043L;

	/**
	 * @param ctx
	 */
	public MBPMBudgetCall(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_BudgetCall_ID
	 * @param trxName
	 */
	public MBPMBudgetCall(Properties ctx, int BPM_BudgetCall_ID, String trxName) {
		super(ctx, BPM_BudgetCall_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMBudgetCall(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

}
