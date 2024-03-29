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

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCKeySuccessFactor extends X_BSC_KeySuccessFactor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3373207423111841579L;
	private static CLogger log = CLogger.getCLogger (MBSCPerspective.class);
	private List<MParameter> parameters = null;
	private List<MBSCKeySuccessFactorLine> lines = null;

	/**
	 * @param ctx
	 * @param BSC_KeySuccessFactor_ID
	 * @param trxName
	 */
	public MBSCKeySuccessFactor(Properties ctx, int BSC_KeySuccessFactor_ID,
			String trxName) {
		super(ctx, BSC_KeySuccessFactor_ID, trxName);
		parameters = initParameters();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCKeySuccessFactor(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		parameters = initParameters();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCKeySuccessFactor(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<MParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<MParameter> parameters) {
		this.parameters = parameters;
	}
	
	public List initParameters() {
		String whereClause = "BSC_Parameter_ID in (SELECT BSC_Parameter_ID FROM BSC_KeySuccessFactorLine WHERE BSC_KeySuccessFactor_ID = ?)";
		List<MParameter> result = new Query(Env.getCtx(),MParameter.Table_Name,whereClause,get_TrxName())
		                             .setParameters(getBSC_KeySuccessFactor_ID())
		                             .list();
		parameters = result;
		return result;
	}
	
	public List<MBSCKeySuccessFactorLine> getLines() {
		return getLines(false);
	}
	
	public List<MBSCKeySuccessFactorLine> getLines(boolean force) {
		if (force || lines == null) {
			lines = getLines(null);
		}
		return lines;
	}
	
	public List<MBSCKeySuccessFactorLine> getLines(String whereClause) {
		String endWhereClause = (whereClause != null ? whereClause + " AND " : "") +"BSC_KeySuccessFactor_ID = ? AND IsActive = 'Y'";
		lines = new Query(Env.getCtx(),MBSCKeySuccessFactorLine.Table_Name,endWhereClause,get_TrxName())
        .setParameters(getBSC_KeySuccessFactor_ID())
        .list();
		return lines;
	}
	
	public MBSCKeySuccessFactorLine getLine(int BSC_KeySuccessFactorLine_ID) {
		MBSCKeySuccessFactorLine result = null;
		for(MBSCKeySuccessFactorLine item:getLines()) {
			if(item.getBSC_KeySuccessFactorLine_ID() == BSC_KeySuccessFactorLine_ID) {
				result = item;
				break;
			}
		}
		return result;
	}

//---------------------------------------------------------------------------------------
	public static List<MBSCKeySuccessFactor> load(int BSC_Perspective_ID) {
		List<MBSCKeySuccessFactor> result = new ArrayList<MBSCKeySuccessFactor>();
		int AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		String sql = "SELECT * FROM BSC_KeySuccessFactor WHERE AD_Client_ID = ? AND IsActive ='Y' AND BSC_Perspective_ID = ?";
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql,null);
//			pstmt.setInt(1, AD_Org_ID);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, BSC_Perspective_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MBSCKeySuccessFactor keySuccessFactor = new MBSCKeySuccessFactor(Env.getCtx(), rs, null);
				result.add(keySuccessFactor);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MBSCKeySuccessFactor:", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}

	public static MBSCKeySuccessFactor get(int ID) {
		MBSCKeySuccessFactor result = null;
		String sql = "SELECT * FROM BSC_KeySuccessFactor WHERE BSC_KeySuccessFactor_ID = ?";
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new MBSCKeySuccessFactor(Env.getCtx(), rs, null);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MBSCKeySuccessFactor:", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
}
