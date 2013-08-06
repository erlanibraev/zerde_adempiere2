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
public class MBPMFormCell extends X_BPM_FormCell {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -899362471081511560L;

	/**
	 * @param ctx
	 */
	public MBPMFormCell(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormCell_ID
	 * @param trxName
	 */
	public MBPMFormCell(Properties ctx, int BPM_FormCell_ID, String trxName) {
		super(ctx, BPM_FormCell_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormCell(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	



}
