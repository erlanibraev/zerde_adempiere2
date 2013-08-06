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
public class MBPMFormLine extends X_BPM_FormLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8467103165656931102L;

	/**
	 * @param ctx
	 */
	public MBPMFormLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormLine_ID
	 * @param trxName
	 */
	public MBPMFormLine(Properties ctx, int BPM_FormLine_ID, String trxName) {
		super(ctx, BPM_FormLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

}
