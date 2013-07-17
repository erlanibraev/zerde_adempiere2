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
public class MBSCCardLineLink extends X_BSC_CardLine_Link {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3961804065639296745L;

	/**
	 * @param ctx
	 * @param BSC_CardLine_Link_ID
	 * @param trxName
	 */
	public MBSCCardLineLink(Properties ctx, int BSC_CardLine_Link_ID,
			String trxName) {
		super(ctx, BSC_CardLine_Link_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCardLineLink(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCCardLineLink(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

}
