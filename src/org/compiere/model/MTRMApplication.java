package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;

public class MTRMApplication extends X_TRM_Application implements DocAction, ASyncProcess {

	public MTRMApplication(Properties ctx, int TRS_Application_ID,
			String trxName) {
		super(ctx, TRS_Application_ID, trxName);
	}

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
/*	protected boolean afterSave (boolean newRecord, boolean success)
	{
/*		
		int ad_process_id = getProcess();
		
		if(ad_process_id == -1) return true;
		
		ProcessInfo pi = new ProcessInfo("Inform Department Header", ad_process_id, this.get_Table_ID(), this.get_ID());
		pi.setAD_Client_ID(this.getAD_Client_ID());
		pi.setAD_User_ID(this.getInitiator_ID());
		
		ProcessParameterPanel pu = new ProcessParameterPanel(0, pi);
		ProcessCtl.process(this, 0, (IProcessParameter) pu, pi, null);
		//ProcessCtl.pr

		return success;
	}	//	afterSave
	*/
	private int getProcess()
	{
		String sql = "select ad_process_id from ad_process where lower(name) like '%trm_informheader%'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int ad_process_id = 0;//DB.getSQLValue(get_TrxName(), sql);
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				ad_process_id = rs.getInt(1);
		}
		catch(Exception e){}
		
		return ad_process_id;
	}
	
	@Override
	public void setDocStatus(String newStatus) {
	}

	@Override
	public String getDocStatus() {
		return null;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		return false;
	}

	@Override
	public boolean unlockIt() {
		return false;
	}

	@Override
	public boolean invalidateIt() {
		return false;
	}

	@Override
	public String prepareIt() {
		return null;
	}

	@Override
	public boolean approveIt() {
		return false;
	}

	@Override
	public boolean rejectIt() {
		return false;
	}

	@Override
	public String completeIt() {
		return null;
	}

	@Override
	public boolean voidIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		return false;
	}

	@Override
	public boolean reActivateIt() {
		return false;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	@Override
	public String getDocAction() {
		return null;
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUILocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

}
