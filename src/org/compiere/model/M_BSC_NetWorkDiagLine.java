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
public class M_BSC_NetWorkDiagLine extends X_BSC_NetWorkDiagLine {

	/**
	 * @param ctx
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagLine_ID
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx, int BSC_NetWorkDiagLine_ID,
			String trxName) {
		super(ctx, BSC_NetWorkDiagLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
