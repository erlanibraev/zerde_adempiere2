package org.compiere.process;

import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MUser;
import org.compiere.model.X_C_BPartner;
import org.compiere.model.X_R_MailText;
import org.compiere.model.X_TRM_Application;
import org.compiere.util.DB;
import org.compiere.util.EMail;

public class TRM_InformDepartmentHeader extends SvrProcess {

	private int TRS_Application_ID = 0;
	
	@Override
	protected void prepare() {
		
		TRS_Application_ID = getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
		if(TRS_Application_ID == 0) return null;
		
		X_TRM_Application application = new X_TRM_Application(getCtx(), TRS_Application_ID, get_TrxName());
		
		int header_ID = getHeader(getDepartment(application.getInitiator_ID()));
		
		String label = "Согласование финансовой заявки";
		String message = "Вам необходимо согласовать заявку №" + application.getDocumentNo() + ".\n\r";
		message += "Наименование работ и услуг: " + application.getDescription();
		
		try
		{
			sendMail(header_ID, application.getInitiator_ID(), label, message);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.toString());
		}
		
		return null;
	}
	
	private int getHeader(int HR_Department_ID)
	{
		int HR_Header_ID = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT u.AD_User_ID \n");
		sql.append("FROM AD_User u \n");
		sql.append("INNER JOIN C_BPartner b ON u.C_BPartner_ID = b.C_BPartner_ID \n");
		sql.append("INNER JOIN HR_Employee e ON e.C_BPartner_ID = b.C_BPartner_ID \n");
		sql.append("INNER JOIN HR_Job j ON j.HR_Job_ID = e.HR_Job_ID \n");
		sql.append("WHERE e.IsActive = 'Y' AND (LOWER(j.Name) like '%директор%' or LOWER(j.Name) like LOWER('%главный бух%')) AND e.HR_Department_ID =" + HR_Department_ID);
		
		HR_Header_ID = DB.getSQLValue(get_TrxName(), sql.toString());
		
		return HR_Header_ID;
	}

	private int getDepartment(int AD_User_ID)
	{
		int HR_Department_ID = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT h.HR_Department_ID \n");
		sql.append("FROM HR_Department h \n");
		sql.append("INNER JOIN HR_Employee e ON h.HR_Department_ID = e.HR_Department_ID \n");
		sql.append("INNER JOIN C_BPartner b ON e.C_BPartner_ID = b.C_BPartner_ID \n");
		sql.append("INNER JOIN AD_User u ON b.C_BPartner_ID = u.C_BPartner_ID \n");
		sql.append("WHERE e.IsActive = 'Y' AND u.AD_User_ID = " + AD_User_ID);
		
		HR_Department_ID = DB.getSQLValue(get_TrxName(), sql.toString());
		
		return HR_Department_ID;
	}
	
	private boolean sendMail(int userToID, int userFromID, String label, String message) throws Exception
	{
		MUser userFrom = new MUser(getCtx(), userFromID, get_TrxName());
		MUser userTo = new MUser(getCtx(), userToID, get_TrxName());

		MClient client = new MClient(getCtx(), getAD_Client_ID(), get_TrxName());
		
		X_R_MailText rText = new X_R_MailText(getCtx(), null, get_TrxName());		
		
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
	
		sql.append("select c_bpartner_id from ad_user where ad_user_id = " + userFromID);
		int C_BPartner_ID = DB.getSQLValue(get_TrxName(), sql.toString());
		
		if(C_BPartner_ID == -1)
			throw new Exception("Ошибка получения имени отправителя");
		
		X_C_BPartner bpartner = new X_C_BPartner(getCtx(), C_BPartner_ID, get_TrxName());
		
		rText.setName(bpartner.getName());
		rText.setMailHeader(label);
		rText.setMailText(message);
		
		if(!rText.save())
			throw new Exception("Невозможно сохранить сообщение");
		
		MMailText mailText = new MMailText(getCtx(), rText.get_ID(), get_TrxName());
		
		EMail mail = client.createEMail(userFrom, userTo, mailText.getMailHeader(), mailText.getMailText());	
		
		try 
		{
			EMail.SENT_OK.equals(mail.send());
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, e.toString());
		}
		
		return true;
	}
}
