/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.apps.graph.BSCView;
import org.adempiere.apps.graph.BSCViewCardLine;
import org.compiere.apps.AEnv;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MPeriod;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCCardDiagram extends SvrProcess {

	private BSCViewCardLine view = new BSCViewCardLine();
	private int BSC_Card_ID = 0;
	private static CLogger sLog = CLogger.getCLogger(BSCCardDiagram.class);
	
	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		for(ProcessInfoParameter param: getParameter()) {
			if (param.getParameterName().equals("BSC_Card_ID")) {
				BSC_Card_ID = Integer.parseInt(param.getParameter().toString());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		FormFrame myWindow = new FormFrame(Env.getWindow(0).getGraphicsConfiguration());
		if (myWindow.openForm(getBSCViewCardLine())) {
			view = (BSCViewCardLine) myWindow.getFormPanel();
			if (BSC_Card_ID > 0) {
				view.load(BSC_Card_ID,0);
			}
			AEnv.addToWindowManager(myWindow);
			AEnv.showCenterScreen(myWindow);
			myWindow.setVisible(true);
			myWindow.setFocusableWindowState(true);
			myWindow.toFront();
		}
		return null;
	}

	public static int getBSCViewCardLine() {
		int result = 0;
		String sql = "SELECT AD_Form_ID FROM AD_Form WHERE Name like 'BSC_ViewCardLine'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch(SQLException e) {
			sLog.log(Level.SEVERE, sql, e);		
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return result;
	}
}
