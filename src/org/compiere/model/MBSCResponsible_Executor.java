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
public class MBSCResponsible_Executor extends X_BSC_Responsible_Executor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203120666420880756L;

	/**
	 * @param ctx
	 */
	public MBSCResponsible_Executor(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Responsible_Executor_ID
	 * @param trxName
	 */
	public MBSCResponsible_Executor(Properties ctx,
			int BSC_Responsible_Executor_ID, String trxName) {
		super(ctx, BSC_Responsible_Executor_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCResponsible_Executor(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
