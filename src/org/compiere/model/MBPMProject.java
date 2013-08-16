/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.process.BPMDocumentEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;

/**
 * @author V.Sokolov
 *
 */
public class MBPMProject extends X_BPM_Project implements DocAction{
	
	private String		m_processMsg = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7041996758972564192L;
	/** */
	public static final int TempProjectID = 1000;  // TODO The temp project to preliminary calculations

	/**
	 * @param ctx
	 */
	public MBPMProject(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Project_ID
	 * @param trxName
	 */
	public MBPMProject(Properties ctx, int BPM_Project_ID, String trxName) {
		super(ctx, BPM_Project_ID, trxName);
	}
	
	private MBPMProject currentProject = null;
	
	/* 
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		currentProject = getCurrentProject(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
		
		return true;
	}
	
	/* 
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		if(newRecord && success){
			
			MBPMForm[] formLine = getLineForm(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
			for(MBPMForm f: formLine){
				MBPMProjectLine pLine = new MBPMProjectLine(getCtx(), 0, get_TrxName());
				pLine.setBPM_Form_ID(f.getBPM_Form_ID());
				pLine.setBPM_Project_ID(getBPM_Project_ID());
				pLine.saveEx();
			}
			
			if(currentProject != null){
				currentProject.setProcessed(true);
				currentProject.setIsActive(false);
				currentProject.saveEx();
				
				MBPMProjectLine[] pLine = MBPMProject.getLines(getCtx(), currentProject.getBPM_Project_ID(), get_TrxName());
				for(MBPMProjectLine p : pLine){
					p.setProcessed(true);
					p.setIsActive(false);
					p.saveEx();
				}
			}
		}
		
		return true;
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMProject(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MBPMProjectLine[] getLines(Properties ctx, int BPM_Project_ID, String trxName){
		
		List<MBPMProjectLine> list = new Query(ctx, I_BPM_ProjectLine.Table_Name, "BPM_Project_ID=?", trxName)
		.setParameters(BPM_Project_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMProjectLine[] retValue = new MBPMProjectLine[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	private MBPMForm[] getLineForm(Properties ctx, int BPM_VersionBudget_ID, String trxName){
		
		List<MBPMForm> list = new Query(ctx, I_BPM_Form.Table_Name, "BPM_VersionBudget_ID=?", trxName)
		.setParameters(BPM_VersionBudget_ID).setOnlyActiveRecords(true)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMForm[] retValue = new MBPMForm[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	private MBPMProject getCurrentProject(Properties ctx, int BPM_VersionBudget_ID, String trxName){
		
		MBPMProject project = null;
		
		project = new Query(ctx, I_BPM_Project.Table_Name, "BPM_VersionBudget_ID=?", trxName)
		.setParameters(BPM_VersionBudget_ID).setOnlyActiveRecords(true)
		.setOnlyActiveRecords(true)
		.first();
		
		return project;
	}

	@Override
	public void setDocStatus(String newStatus) 
	{
		super.setDocStatus(newStatus);
	}

	@Override
	public String getDocStatus() {
		return super.getDocStatus();
	}

	@Override
	public boolean processIt(String action) throws Exception 
	{
		BPMDocumentEngine engine = new BPMDocumentEngine(this, getDocStatus());
		//DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String prepareIt() 
	{
		setProcessed(false);
		setIsActive(true);
		return STATUS_InProgress;
	}

	@Override
	public boolean approveIt() 
	{
		setDocStatus(MBPMProject.STATUS_Approved);
		setProcessed(true);
		setIsActive(false);
		return true;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {
		
		String result = DocAction.ACTION_Complete;
		return result;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeIt() {
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setIsActive(false);
		setDocAction(DocAction.ACTION_Close);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		setDocStatus(MBPMProject.STATUS_Closed);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		setDocStatus(MBPMProject.ACTION_Prepare);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentNo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocAction() {
		return super.getDocAction();
	}

}
