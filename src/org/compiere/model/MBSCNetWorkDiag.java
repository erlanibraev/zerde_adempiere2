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
import org.compiere.process.DocumentEngine;
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
	private M_BSC_NetWorkDiagLine[] m_lines = null;
	
	public M_BSC_NetWorkDiagLine[] getLines (boolean requery){
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		List<M_BSC_NetWorkDiagLine> list = new Query(getCtx(), I_BSC_NetWorkDiagLine.Table_Name, "BSC_NetWorkDiag_ID = ?", get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(M_BSC_NetWorkDiagLine.COLUMNNAME_BSC_NetWorkDiagLine_ID)
										.list();
		m_lines = list.toArray(new M_BSC_NetWorkDiagLine[list.size()]);
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
		return false;
	}

	@Override
	public boolean invalidateIt() {
		log.info(toString());
		
		return false;
	}

	@Override
	public String prepareIt() {
		System.out.println("1");
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String completeIt() {

		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return "getDocumentInfo()";
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
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

}
