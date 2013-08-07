/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCKeySuccessFactorLink extends X_BSC_KeySuccessFactorLink {

	/**
	 * 
	 */
	private static final long serialVersionUID = 464389732777807960L;

	/**
	 * @param ctx
	 * @param BSC_KeySuccessFactorLink_ID
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLink(Properties ctx,
			int BSC_KeySuccessFactorLink_ID, String trxName) {
		super(ctx, BSC_KeySuccessFactorLink_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCKeySuccessFactorLink(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCKeySuccessFactorLink(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public static MBSCKeySuccessFactorLink[] getKeySuccessFactorLink(String WhereClause) {
		String wc = ( WhereClause != null ? WhereClause + " AND ": "") + " AD_Client_ID = ?";
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		List<MBSCKeySuccessFactorLink> result = new Query(Env.getCtx(),MBSCKeySuccessFactorLink.Table_Name, wc, null)
		                                           .setParameters(AD_Client_ID)
		                                           .list();
		return (result == null ? null : result.toArray(new MBSCKeySuccessFactorLink[result.size()]));
	}
	
	public static boolean haveLink(int link1, int link2) {
		boolean result = false;
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		String wc = "AD_Client_ID = ? AND ((BSC_KeySuccessFactor_ID = ? AND BSC_KeySuccessFactor_Link_ID = ?) OR (BSC_KeySuccessFactor_ID = ? AND BSC_KeySuccessFactor_Link_ID = ?))";
		List<MBSCKeySuccessFactorLink> list = new Query(Env.getCtx(),MBSCKeySuccessFactorLink.Table_Name, wc, null)
        .setParameters(AD_Client_ID, link1, link2, link2, link1)
        .list();
		if(list != null) {
			result = list.size() > 0;
		}
		return result;
	}
	
	public static boolean addLink(int from, int to) {
		boolean result = false;
		if (!MBSCKeySuccessFactorLink.haveLink(from, to)) {
			MBSCKeySuccessFactorLink newLink = new MBSCKeySuccessFactorLink(Env.getCtx(),0,null);
			newLink.setBSC_KeySuccessFactor_ID(from);
			newLink.setBSC_KeySuccessFactor_Link_ID(to);
			result = newLink.save();
		}
		return result;
	}
}
