/**
 * 
 */
package org.compiere.process;

import java.text.MessageFormat;
import java.util.Properties;
import org.compiere.model.MBPMEmployeeLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;
import org.compiere.model.X_BPM_EmployeeLine;
import org.compiere.model.X_BPM_VersionBudget;
import org.compiere.model.X_C_BPartner;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import extend.org.compiere.utils.Util;


/**
 * Sending notifications
 * used in the table BPM_VersionBudget column SendMail
 * @author V.Sokolov
 *
 */
public class BPMSendNotif extends SvrProcess {

	
	/** Current context		*/
	private Properties m_ctx;
	/* */
	private X_BPM_VersionBudget version = null;
	
	/* 
	 */
	protected void prepare() {
		
		m_ctx = getCtx();
		version = new X_BPM_VersionBudget(getCtx(), getRecord_ID(), get_TrxName()); 
	}
	
	/* 
	 */
	@Override
	protected String doIt() throws Exception {
		
		MOrg org = new MOrg(m_ctx, Env.getAD_Org_ID(m_ctx), get_TrxName());
		MBPartner bpOrg = new MBPartner(m_ctx, org.getLinkedC_BPartner_ID(get_TrxName()), get_TrxName()); 
		
		Object[] param = new Object[]{bpOrg.getDescription(), "30 марта"};
		String message = MessageFormat.format("						Уведомление \r\r" +
				"	Настоящим уведомляем, что в соответствии с регламентом бюджетного процесса в {0}, " +
				"в рамках формирования/корректировки годового бюджета Вам необходимо в срок до {1} г. представить бюджетные заявки по закрепленным за Вами АБП.", param);
		
		X_BPM_EmployeeLine[] empLine = MBPMEmployeeLine.getEmployeeLines();
		for(X_BPM_EmployeeLine emp: empLine){
			X_C_BPartner bp = new X_C_BPartner(m_ctx, emp.getC_BPartner_ID(), get_TrxName());
			MUser[] user = MUser.getOfBPartner(m_ctx, bp.getC_BPartner_ID(), get_TrxName());
			
			
			Util.sendMail(user[0].getAD_User_ID(), getAD_User_ID(), "Бюджет на "+version.getC_Year().getFiscalYear()+" год", message, false);
		}
		
		
		version.setNumberSend(version.getNumberSend()+1);
		version.saveEx();
		
		return Msg.translate(m_ctx, "Success");
	}

}
