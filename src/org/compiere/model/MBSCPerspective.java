/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.apps.graph.BSCViewPerspective;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCPerspective extends X_BSC_Perspective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4449044670845316669L;
	private static CLogger log = CLogger.getCLogger (MBSCPerspective.class);

	/**
	 * @param ctx
	 * @param BSC_Perspective_ID
	 * @param trxName
	 */
	public MBSCPerspective(Properties ctx, int BSC_Perspective_ID,
			String trxName) {
		super(ctx, BSC_Perspective_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCPerspective(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCPerspective(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public static List<MBSCPerspective> getPerspectives() {
		List<MBSCPerspective> result = new ArrayList<MBSCPerspective>();
		int AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		String sql = "SELECT * FROM BSC_Perspective WHERE AD_Org_ID = ? AND AD_Client_ID = ? AND IsActive ='Y'";
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, AD_Org_ID);
			pstmt.setInt(2, AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MBSCPerspective perspective = new MBSCPerspective(Env.getCtx(), rs, null);
				result.add(perspective);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MBSCPerspective:", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return (result.size() == 0 ? null: result);
	}
}
