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
public class M_BSC_NetWorkDiagSubLine extends X_BSC_NetWorkDiagSubLine {

	/**
	 * @param ctx
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagSubLine_ID
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx,
			int BSC_NetWorkDiagSubLine_ID, String trxName) {
		super(ctx, BSC_NetWorkDiagSubLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
