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
public class MBSCTargetIndicator extends X_BSC_Target_Indicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7947212432113472033L;

	/**
	 * @param ctx
	 */
	public MBSCTargetIndicator(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Target_Indicator_ID
	 * @param trxName
	 */
	public MBSCTargetIndicator(Properties ctx, int BSC_Target_Indicator_ID,
			String trxName) {
		super(ctx, BSC_Target_Indicator_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCTargetIndicator(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
