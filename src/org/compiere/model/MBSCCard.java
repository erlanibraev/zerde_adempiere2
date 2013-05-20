/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
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

	private ArrayList<MBSCCardLine> cardLine = new ArrayList<MBSCCardLine>(); 
	private MParameter parameter = null; 
	
	public MParameter getParameter() {
		if (parameter == null && getBSC_Parameter_ID() > 0) {
			parameter = new MParameter(Env.getCtx(),getBSC_Parameter_ID(),get_TrxName());
			parameter.setPeriod(getPeriod());
		}
		return parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
	}

	public ArrayList<MBSCCardLine> getCardLine() {
		cardLine.clear();
		String sql = "SELECT * FROM BSC_CardLine WHERE BSC_Card_ID = ?  AND isActive = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, getBSC_Card_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cardLine.add(new MBSCCardLine(Env.getCtx(), rs, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return cardLine;
	}

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
	
	public BigDecimal calculate() {
		BigDecimal result = new BigDecimal(0);
		for(MBSCCardLine line:getCardLine()) {
			result = result.add(line.calculate());
		}
		if (getBSC_Parameter_ID() > 0 ) {
			setValueNumber(result);
			save();
		}
		return result;
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
		if (STATUS_NotApproved.equals(getDocStatus()) || STATUS_Drafted.equals(getDocStatus())) {
			setDocStatus(STATUS_WaitingConfirmation);
			result = STATUS_Completed;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#approveIt()
	 */
	@Override
	public boolean approveIt() {
		log.severe("BSC_Card: approveIt()");
		setDocStatus(STATUS_Approved);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#rejectIt()
	 */
	@Override
	public boolean rejectIt() {
		log.severe("BSC_Card: rejectIt()");
		setDocStatus(STATUS_Drafted);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#completeIt()
	 */
	@Override
	public String completeIt() {
		log.severe("BSC_Card: completeIt()");
		String result = DocAction.STATUS_Invalid;
		if (STATUS_WaitingConfirmation.equals(getDocStatus())) {
			setDocStatus(STATUS_InProgress);
			result = STATUS_Completed;
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

		if (STATUS_Closed.equals(getDocStatus())
			|| STATUS_Reversed.equals(getDocStatus())
			|| STATUS_Voided.equals(getDocStatus()))
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
		setDocStatus(STATUS_Closed);
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
		setDocStatus(STATUS_Drafted);
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
	
	@Override
	public void setValueNumber (BigDecimal ValueNumber)
	{
		super.setValueNumber(ValueNumber);
		getParameter().setPeriod(getPeriod());
		getParameter().setValueNumber(ValueNumber);
		getParameter().save();
	}

}
