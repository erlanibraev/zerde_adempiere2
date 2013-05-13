/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCKeySuccessFactor extends X_BSC_KeySuccessFactor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3373207423111841579L;

	/**
	 * @param ctx
	 * @param BSC_KeySuccessFactor_ID
	 * @param trxName
	 */
	public MBSCKeySuccessFactor(Properties ctx, int BSC_KeySuccessFactor_ID,
			String trxName) {
		super(ctx, BSC_KeySuccessFactor_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCKeySuccessFactor(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCKeySuccessFactor(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

}
