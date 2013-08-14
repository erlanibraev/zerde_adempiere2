/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.model.I_BPM_Form;
import org.compiere.model.I_BPM_Project;
import org.compiere.model.MBPMProject;
import org.compiere.model.MBPMProjectLine;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class BPM_RecalculateFormValues extends SvrProcess implements ASyncProcess {
	
	/** Current context		*/
	private Properties m_ctx;
	/** */
	private int BPM_Project_ID = 0;
	/**/
	private int AD_Process_ID;

	/* 
	 */
	@Override
	protected void prepare() {
		
		m_ctx = Env.getCtx();
		BPM_Project_ID = getRecord_ID();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The name must start with a report hrm_order
		String sql_ = "select distinct t.ad_process_id from ad_process t "+ 
					  "where (lower(t.value) like lower('%CalcForm%') or lower(t.name) like lower('%CalcForm%')) "+ 
				      "and (lower(t.value) like lower('%CalcForm%') or lower(t.name) like lower('%CalcForm%'))";
		try
		{
			pstmt = DB.prepareStatement(sql_, null);
			rs = pstmt.executeQuery();
			if (rs.next())
				AD_Process_ID = rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

	}

	/* 
	 */
	@Override
	protected String doIt() throws Exception {

		if(AD_Process_ID == 0)
			return  "The process can not be found";
		//  Prepare Process
		ProcessInfo pi = new ProcessInfo ("The recalculate form values", AD_Process_ID);
		//  Prepare Process
		pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
		pi.setAD_Client_ID(Env.getAD_Client_ID(m_ctx));
		
		MBPMProjectLine[] pLine = MBPMProject.getLines(m_ctx, BPM_Project_ID, get_TrxName()); 

		List<ProcessInfoParameter> po = new ArrayList<ProcessInfoParameter>();
		for(MBPMProjectLine l: pLine){
			po.clear();
			po.add(new ProcessInfoParameter(I_BPM_Form.COLUMNNAME_BPM_Form_ID, l.getBPM_Form_ID(),null,"",""));
			po.add(new ProcessInfoParameter(I_BPM_Project.COLUMNNAME_BPM_Project_ID, l.getBPM_Project_ID(),null,"",""));
			//
			ProcessInfoParameter[] pp = new ProcessInfoParameter[po.size()];
			po.toArray(pp);
			pi.setParameter(pp);
			//	Execute Process
			ProcessParameterPanel pu = new ProcessParameterPanel(0, pi);
			ProcessCtl.process(this, 0, (IProcessParameter) pu, pi, null);
		}
		
		return Msg.translate(m_ctx, "Success");
	}

	/* 
	 */
	@Override
	public void lockUI(ProcessInfo pi) {
		
	}

	/* 
	 */
	@Override
	public void unlockUI(ProcessInfo pi) {
		
	}

	/* 
	 */
	@Override
	public boolean isUILocked() {
		return false;
	}

	/*
	 */
	@Override
	public void executeASync(ProcessInfo pi) {
		
	}

}
