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
public class MBSCKeySuccessFactorLine extends X_BSC_KeySuccessFactorLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5459643890084422068L;
	
	private MParameter parameter = null;
	/**
	 * @param ctx
	 * @param BSC_KeySuccessFactor_ID
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLine(Properties ctx,
			int BSC_KeySuccessFactor_ID, String trxName) {
		super(ctx, BSC_KeySuccessFactor_ID, trxName);
		parameter = initParameter();
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		parameter = initParameter();
	}

	/**
	 * @param ctx
	 */
	public MBSCKeySuccessFactorLine(Properties ctx) {
		super(ctx);
		parameter = initParameter();
	}
	
	private MParameter initParameter() {
		if (getBSC_Parameter_ID() > 0 && (parameter == null || parameter.getBSC_Parameter_ID() != getBSC_Parameter_ID())) {
			parameter = new MParameter(getCtx(), getBSC_Parameter_ID(), get_TrxName());
		}
		return parameter;
	}

	public MParameter getParameter() {
		return this.parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
	}

}
