/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCDashboard extends X_BSC_Dashboard {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2489998524305817971L;
	private MBSCCard card = null;
	private MPeriod period = null;
	private static MBSCDashboard[] dashboards = null;

	/**
	 * @param ctx
	 * @param BSC_Dashboard_ID
	 * @param trxName
	 */
	public MBSCDashboard(Properties ctx, int BSC_Dashboard_ID, String trxName) {
		super(ctx, BSC_Dashboard_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCDashboard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCDashboard(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public static MBSCDashboard[] getDashboard(int C_Period_ID) {
		dashboards = getDashboards(false);
		for(int i=0; i < dashboards.length; i++)
			dashboards[i].setPeriod((C_Period_ID == 0 ? null : new MPeriod(Env.getCtx(),C_Period_ID,null)));
		return dashboards;
	}

	public MBSCCard getCard() {
		if ((card == null && period != null) || (card != null && period != null && card.getC_Period_ID() != period.getC_Period_ID())) {
			int C_BPartner_ID = getC_BPartner_ID();
			int C_Period_ID = getPeriod().getC_Period_ID();
			int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
			String sql = "SELECT * FROM BSC_Card WHERE AD_Client_ID = ? AND C_BPartner_ID = ? AND C_Period_ID = ?"; 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql,null);
				pstmt.setInt (1, AD_Client_ID);
				pstmt.setInt (2, C_BPartner_ID);
				pstmt.setInt (3, C_Period_ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					card = new MBSCCard(Env.getCtx(), rs, get_TrxName());
				}
				
			} catch (Exception e) {
				log.log(Level.SEVERE, "product", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return card;
	}

	public void setCard(MBSCCard card) {
		this.card = card;
	}

	public MPeriod getPeriod() {
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}
	
	@Override 
	protected boolean beforeSave(boolean newRecord) {
		if(getC_BPartner_ID() > 0 && getHR_Job_ID() == 0) {
			setHR_Job_ID(getHR_Job_ID_by_C_BPartner_ID(getC_BPartner_ID()));
		}
		return super.beforeSave(newRecord);
	}
	
	public static int getHR_Job_ID_by_C_BPartner_ID(int C_BPartner_ID) {
		int result = 0;
		if (C_BPartner_ID > 0) {
			String sql = "SELECT HR_Job_ID FROM HR_Employee WHERE AD_Client_ID = "+Integer.toString(Env.getAD_Client_ID(Env.getCtx()))+" AND IsActive = 'Y' AND C_BPartner_ID = ?";
			result = DB.getSQLValue(null, sql,C_BPartner_ID);
		}
		return result;
	}
	
	public static MBSCDashboard[] getDashboards(boolean force) {
		if (force || dashboards == null) {
			int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
			String WhereClause = "IsActive = 'Y' AND AD_Client_ID = ?";
			List<MBSCDashboard> list = new Query(Env.getCtx(), MBSCDashboard.Table_Name, WhereClause, null).setParameters(AD_Client_ID).list();
			dashboards = (list == null ? null : list.toArray(new MBSCDashboard[list.size()]));
		}
		return dashboards;
	}
}
