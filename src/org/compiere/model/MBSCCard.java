/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCCard extends X_BSC_Card {

	MPeriod period = null;
	
	public MPeriod getPeriod() {
		if (period == null || period.getC_Period_ID() != getC_Period_ID()) {
			setPeriod(new MPeriod(Env.getCtx(),getC_Period_ID(),get_TrxName()));
		}
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

	/**
	 * @param ctx
	 * @param BSC_Card_ID
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, int BSC_Card_ID, String trxName) {
		super(ctx, BSC_Card_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCCard(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

}
