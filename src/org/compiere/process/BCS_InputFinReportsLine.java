package org.compiere.process;

import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Env;


public class BCS_InputFinReportsLine extends SvrProcess {

	/** Current context */
	private Properties m_ctx;
	/**     */
	private ProcessInfo pi;



	private static CLogger s_log = CLogger.getCLogger(BCS_InputFinReportsLine.class);

	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		log.info("");
		m_ctx = Env.getCtx();
		pi = getProcessInfo();
		ProcessInfoParameter[] para = getParameter();
	}

	@Override
	protected String doIt() throws Exception {
		return "";
	}
	
}