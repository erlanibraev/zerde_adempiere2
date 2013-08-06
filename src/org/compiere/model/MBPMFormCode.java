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
public class MBPMFormCode extends X_BPM_FormCode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14579634785106435L;

	/**
	 * @param ctx
	 */
	public MBPMFormCode(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormCode_ID
	 * @param trxName
	 */
	public MBPMFormCode(Properties ctx, int BPM_FormCode_ID, String trxName) {
		super(ctx, BPM_FormCode_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormCode(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	

	

}
