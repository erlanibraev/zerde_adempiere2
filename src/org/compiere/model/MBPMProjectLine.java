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
public class MBPMProjectLine extends X_BPM_ProjectLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8426594633255808605L;

	/**
	 * @param ctx
	 */
	public MBPMProjectLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_ProjectLine_ID
	 * @param trxName
	 */
	public MBPMProjectLine(Properties ctx, int BPM_ProjectLine_ID,
			String trxName) {
		super(ctx, BPM_ProjectLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMProjectLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

}
