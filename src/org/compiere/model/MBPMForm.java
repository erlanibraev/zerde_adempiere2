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
public class MBPMForm extends X_BPM_Form {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5236262513637596250L;

	/**
	 * @param ctx
	 */
	public MBPMForm(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Form_ID
	 * @param trxName
	 */
	public MBPMForm(Properties ctx, int BPM_Form_ID, String trxName) {
		super(ctx, BPM_Form_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMForm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	



}
