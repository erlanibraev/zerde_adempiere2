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
public class M_BSC_Target_Indicator extends X_BSC_Target_Indicator {

	/**
	 * @param ctx
	 */
	public M_BSC_Target_Indicator(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Target_Indicator_ID
	 * @param trxName
	 */
	public M_BSC_Target_Indicator(Properties ctx, int BSC_Target_Indicator_ID,
			String trxName) {
		super(ctx, BSC_Target_Indicator_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_Target_Indicator(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
