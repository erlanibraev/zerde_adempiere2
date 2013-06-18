/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class MBPMSubs extends X_BPM_Subs {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -635594007776277367L;

	/**
	 * @param ctx
	 */
	public MBPMSubs(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Subs_ID
	 * @param trxName
	 */
	public MBPMSubs(Properties ctx, int BPM_Subs_ID, String trxName) {
		super(ctx, BPM_Subs_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMSubs(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/*
	 * 
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {

		if(getBPM_Share() > 100)
			throw new AdempiereException(Msg.translate(getCtx(), "Share >100"));
		
		return true;
	}

}
