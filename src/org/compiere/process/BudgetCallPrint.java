/**
 * 
 */
package org.compiere.process;

import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

import extend.org.compiere.utils.Util;

/**
 * Add the process in the system and do not forget to attach templates:
 * 	1. templateFirst
 * 	2. templateSecond
 * 	3. templateThree
 * @author V.Sokolov
 *
 */
public class BudgetCallPrint extends SvrProcess{

	/** Current context		*/
	private Properties m_ctx;
	/**	 */
	private ProcessInfo pi;
	/**  */
	String url;
	StringBuffer param = new StringBuffer("/budget/index.action?");
	
	/* 
	 */
	@Override
	protected void prepare() {
		
		log.info(" Print Budget Call ");
		m_ctx = Env.getCtx();
		pi = getProcessInfo();

		
		param.append("callID="+getRecord_ID());
		param.append("&processID="+pi.getAD_Process_ID());
		param.append("&lang="+Env.getAD_Language(m_ctx));

		url = getHttpHost();
	}
				

	/* 
	 */
	@Override
	protected String doIt() throws Exception {
		
		if(url.startsWith("http"))
			Env.startBrowser(url+param);
		
		
		return Msg.translate(m_ctx, "Success");
	}
	
	private String getHttpHost(){
		
		if(Ini.getCodeBase() != null)
			return "http://" + Ini.getCodeBase().getHost();
		else
			return Util.getHostHttp();
		 
	}
	
}
