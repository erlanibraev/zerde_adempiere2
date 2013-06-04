/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author A.Kulantayev
 *
 */
public class M_BSC_Action extends X_BSC_Action {

	/**
	 * @param ctx
	 */
	public M_BSC_Action(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Action_ID
	 * @param trxName
	 */
	public M_BSC_Action(Properties ctx, int BSC_Action_ID, String trxName) {
		super(ctx, BSC_Action_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_Action(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
