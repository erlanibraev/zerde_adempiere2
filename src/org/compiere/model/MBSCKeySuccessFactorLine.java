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
public class MBSCKeySuccessFactorLine extends X_BSC_KeySuccessFactor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5459643890084422068L;

	/**
	 * @param ctx
	 * @param BSC_KeySuccessFactor_ID
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLine(Properties ctx,
			int BSC_KeySuccessFactor_ID, String trxName) {
		super(ctx, BSC_KeySuccessFactor_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCKeySuccessFactorLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

}
