/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_BPM_BudgetCall;
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

	/* Current context		*/
	private Properties m_ctx;
	/* */
	private ProcessInfo pi;
	/* */
	int callID = 0;
	/* */
	String url;
	StringBuffer param = new StringBuffer("/budget/index.action?");
	
	/* 
	 */
	@Override
	protected void prepare() {
		
		log.info(" Print Budget Call ");
		m_ctx = Env.getCtx();
		pi = getProcessInfo();

		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)	{
			String name = para[i].getParameterName();
			if (name == null); 
				//
			else if (name.compareTo(I_BPM_BudgetCall.COLUMNNAME_BPM_BudgetCall_ID) == 0 && para[i].getParameter() != null)
				callID = new BigDecimal(Integer.parseInt(para[i].getParameter().toString())).intValue();
			else
			{
				log.log(Level.INFO, "Unknown Parameter: " + name);
			}
		}
		
		if(callID == 0)
			throw new AdempiereException("Неверный параметр заявки. Обратитесь к Администратору.");
			
		pi.setRecord_ID(callID);
		param.append("callID="+pi.getRecord_ID());
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
