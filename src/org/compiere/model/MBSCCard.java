/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.process.DocAction;
import org.compiere.process.BSCDocumentEngine;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCCard extends X_BSC_Card implements DocAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3366362516386767291L;
	
	MPeriod period = null;

	
	public MPeriod getPeriod() {
		if (period == null || period.getC_Period_ID() != getC_Period_ID()) {
			setPeriod(new MPeriod(Env.getCtx(),getC_Period_ID(),get_TrxName()));
		}
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

	/**
	 * @param ctx
	 * @param BSC_Card_ID
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, int BSC_Card_ID, String trxName) {
		super(ctx, BSC_Card_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 */
	public MBSCCard(Properties ctx) {
		super(ctx);
	}

	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	//private boolean		m_justPrepared = false;
	
	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#processIt(java.lang.String)
	 */
	@Override
	public boolean processIt(String action) throws Exception {
		log.severe("BSC_Card: processIt('" +action+"')");
		m_processMsg = null;
		BSCDocumentEngine engine = new BSCDocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#unlockIt()
	 */
	@Override
	public boolean unlockIt() {
		log.severe("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#invalidateIt()
	 */
	@Override
	public boolean invalidateIt() {
		log.severe("invalidateIt - " + toString());
		setDocAction(DocAction.ACTION_Prepare);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#prepareIt()
	 */
	@Override
	public String prepareIt() {
		log.severe("BSC_Card: prepareIt()");
		String result = DocAction.STATUS_Invalid;
		if (DOCSTATUS_NotApproved.equals(getDocStatus()) || DOCSTATUS_Drafted.equals(getDocStatus())) {
			setDocStatus(DOCSTATUS_WaitingConfirmation);
			result = DOCSTATUS_WaitingConfirmation;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#approveIt()
	 */
	@Override
	public boolean approveIt() {
		log.severe("BSC_Card: approveIt()");
		setDocStatus(DOCSTATUS_Approved);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#rejectIt()
	 */
	@Override
	public boolean rejectIt() {
		log.severe("BSC_Card: rejectIt()");
		setDocStatus(DOCSTATUS_Drafted);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#completeIt()
	 */
	@Override
	public String completeIt() {
		log.severe("BSC_Card: completeIt()");
		String result = DocAction.STATUS_Invalid;
		if (DOCSTATUS_WaitingConfirmation.equals(getDocStatus())) {
			setDocStatus(DOCSTATUS_InProgress);
			result  = DOCSTATUS_InProgress;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#voidIt()
	 */
	@Override
	public boolean voidIt() {
		log.severe("BSC_Card: voidIt()");
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;

		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DocAction.ACTION_None);
			return false;
		}

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DocAction.ACTION_None);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#closeIt()
	 */
	@Override
	public boolean closeIt() {
		log.severe("BSC_Card: closeIt()");
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DocAction.ACTION_None);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		setDocStatus(DOCSTATUS_Closed);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reverseCorrectIt()
	 */
	@Override
	public boolean reverseCorrectIt() {
		log.severe("BSC_Card: reverseCorrectIt()");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reverseAccrualIt()
	 */
	@Override
	public boolean reverseAccrualIt() {
		log.severe("BSC_Card: reverseAccrualIt()");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reActivateIt()
	 */
	@Override
	public boolean reActivateIt() {
		log.severe("BSC_Card: reActivateIt()");
		setDocStatus(DOCSTATUS_Drafted);
		setProcessed(false);
		setDocAction(DocAction.ACTION_None);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getSummary()
	 */
	@Override
	public String getSummary() {
		log.severe("BSC_Card: getSummary()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getDocumentInfo()
	 */
	@Override
	public String getDocumentInfo() {
		log.severe("BSC_Card: getDocumentInfo()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#createPDF()
	 */
	@Override
	public File createPDF() {
		log.severe("BSC_Card: createPDF()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getProcessMsg()
	 */
	@Override
	public String getProcessMsg() {
		log.severe("BSC_Card: getProcessMsg()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getDoc_User_ID()
	 */
	@Override
	public int getDoc_User_ID() {
		log.severe("BSC_Card: getDoc_User_ID()");
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getC_Currency_ID()
	 */
	@Override
	public int getC_Currency_ID() {
		log.severe("BSC_Card: getC_Currency_ID()");
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getApprovalAmt()
	 */
	@Override
	public BigDecimal getApprovalAmt() {
		log.severe("BSC_Card: getApprovalAmt()");
		return null;
	}
	
	public MBSCCardLine[] getLines(boolean requery) {
		MBSCCardLine[] result = null;
		
		return result;
	}
	
	@Override
	public Timestamp getDateAcct() {
		Timestamp result = super.getDateAcct();
		if (result == null) {
			result = new Timestamp(System.currentTimeMillis());
			setDateAcct(result);
		}
		return result;
	}
}
