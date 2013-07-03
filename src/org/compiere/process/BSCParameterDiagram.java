/**
 * 
 */
package org.compiere.process;

import org.adempiere.apps.graph.BSCView;
import org.compiere.apps.AEnv;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MPeriod;
import org.compiere.util.DB;
import org.compiere.util.Env;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCParameterDiagram extends SvrProcess {

	
	private BSCView view = new BSCView();
	private int C_Period_ID = 0; 
	private int BSC_Parameter_ID = 0;
	
	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		for(ProcessInfoParameter param: getParameter()) {
			if (param.getParameterName().equals("C_Period_ID")) {
				C_Period_ID = Integer.parseInt(param.getParameter().toString());
			} else if (param.getParameterName().equals("BSC_Parameter_ID")) {
				BSC_Parameter_ID = Integer.parseInt(param.getParameter().toString());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		FormFrame myWindow = new FormFrame(Env.getWindow(0).getGraphicsConfiguration());
		if (myWindow.openForm(getBSCView_ID())) {
			view = (BSCView) myWindow.getFormPanel();
			if (C_Period_ID > 0) {
				view.setPeriod(new MPeriod(Env.getCtx(),C_Period_ID,null));
			}
			if (BSC_Parameter_ID > 0) {
				view.load(BSC_Parameter_ID);
			}
			AEnv.showCenterScreen(myWindow);
			myWindow.setVisible(true);
			myWindow.setAlwaysOnTop(true);
		}
		return null;
	}
	
	protected int getBSCView_ID() {
		int result = 0;
		String sql = "SELECT AD_Form_ID FROM AD_Form WHERE Name like 'BSC_View'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch(SQLException e) {
			log.log(Level.SEVERE, sql, e);		
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return result;
	}
}
