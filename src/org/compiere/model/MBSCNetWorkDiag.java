/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngineNWD;

/**
 * @author A.Kulantayev
 *
 */
public class MBSCNetWorkDiag extends X_BSC_NetWorkDiag implements DocAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5834082338440682566L;

	/**
	 * @param ctx
	 */
	public MBSCNetWorkDiag(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiag_ID
	 * @param trxName
	 */
	public MBSCNetWorkDiag(Properties ctx, int BSC_NetWorkDiag_ID,
			String trxName) {
		super(ctx, BSC_NetWorkDiag_ID, trxName);
		// TODO Auto-generated constructor stub
		if(BSC_NetWorkDiag_ID == 0)
		{
			setDateAcct(new Timestamp(System.currentTimeMillis()));
			setDocAction(DOCACTION_Complete);
			setDocStatus(DOCSTATUS_Drafted);
			setProcessed(false);
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCNetWorkDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/** */
	private MBSCNetWorkDiagLine[] m_lines = null;
	
	public MBSCNetWorkDiagLine[] getLines (boolean requery){
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		List<MBSCNetWorkDiagLine> list = new Query(getCtx(), I_BSC_NetWorkDiagLine.Table_Name, "BSC_NetWorkDiag_ID = ?", get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(X_BSC_NetWorkDiagLine.COLUMNNAME_BSC_NetWorkDiagLine_ID)
										.list();
		m_lines = list.toArray(new MBSCNetWorkDiagLine[list.size()]);
		return m_lines;
	}

	@Override
	public boolean processIt(String processAction) throws Exception {
		m_processMsg = null;
		DocumentEngineNWD engine = new DocumentEngineNWD (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		log.info(toString());
		setProcessing(false);
		return true;
	}

	@Override
	public boolean invalidateIt() {
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}

	@Override
	public String prepareIt() {
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
//		Std Period open?
//      MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_NetWorkDiagram, getAD_Org_ID());
		
		MBSCNetWorkDiagLine[] lines = getLines(false);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		else {
		for(int i = 0; i<lines.length; i++){
			MBSCNetWorkDiagSubLine[] sublines = lines[i].getSubLines(false);
			if(sublines.length == 0){
				m_processMsg = "NoSubLines on "+(i+1)+" position";
				return DocAction.STATUS_Invalid;
			}
			else {
				for(int j = 0; j <sublines.length; j++){
					MBSCAction[] actions = sublines[j].getActions(false);
					MBSCTargetIndicator[] targetIndicators = sublines[j].getTarget_Indicator(false);
					if(actions.length == 0 || targetIndicators.length == 0){
						m_processMsg = "NoAction or Target Indicator on Line "+(i+1)+" -->Subline "+(j+1);
						return DocAction.STATUS_Invalid;
					}
					else{
						for(int r=0; r<actions.length; r++){
							MBSCResponsibleExecutor[] responsibleExecutors = actions[r].getResponsible_Executors(false);
							if(responsibleExecutors.length == 0){
								
								m_processMsg = "No Responsible Executor(s) on Line "+(i+1)+" -->Subline "+(j+1)+" -->Action "+(r+1);
								return DocAction.STATUS_Invalid;
							}
						}
					}
				}
			}
		}
	}
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
			
		return DocAction.STATUS_WaitingConfirmation;
	}

	@Override
	public boolean approveIt() {
		System.out.println("------>>------------>>MBSCNetWorkDiag.approveIt()");
		return true;
	}

	@Override
	public boolean rejectIt() {
		System.out.println("------>>------------->>MBSCNetWorkDiag.rejectIt()");
		return true;
	}
	
	@Override
	public String completeIt() {
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		MBSCNetWorkDiagLine[] lines = getLines(false);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		//User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
		return false;
	return true;
	
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		return true;
	}

	@Override
	public boolean reverseAccrualIt() {
		log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		return false;
	}

	@Override
	public boolean reActivateIt() {
		log.info(toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
				return false;	
			
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
				return false;
		
		setProcessed(false);	
		setDocStatus(DOCSTATUS_Drafted);
		return true;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return m_processMsg;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return getUpdatedBy();
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

}
