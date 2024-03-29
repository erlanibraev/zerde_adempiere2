package org.compiere.process;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.agreement.Agreement_Dispatcher;
import org.compiere.apps.DialogAgreement;
import org.compiere.model.MAGRStage;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MClient;
import org.compiere.model.MCurrency;
import org.compiere.model.MMailText;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.X_CMS_Contract;
import org.compiere.model.X_C_BPartner;
import org.compiere.model.X_R_MailText;
import org.compiere.model.X_cms_incotermstype;
import org.compiere.model.X_cms_paymentstype;
import org.compiere.model.X_cms_proposal;
import org.compiere.model.X_cms_statusestype;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MHRDepartment;
import org.eevolution.model.X_HR_Department;

import extend.org.compiere.hrm.SearchReplace;
import extend.org.compiere.utils.Util;

public class Cms_TransferData extends SvrProcess 
{

	private int p_AD_Client_ID;
	private int p_AD_Org_ID;
	private int p_AD_User_ID;
	
	private int HR_Department_ID = 0;
	
	private X_cms_proposal proposal;
	private X_CMS_Contract contract;
	
	/** Current context					*/
	private Properties m_ctx;
	/**	 */
	private ProcessInfo pi;
	/** */
	private String trx = null;
	
	private String contractFile = "";
	/** */
	private String localFilePath = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");	
	
	@Override
	protected void prepare() 
	{
		proposal = new X_cms_proposal(getCtx(), getRecord_ID(), trx);
				
		HR_Department_ID = proposal.getHR_Department_ID();
		
		p_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		p_AD_Org_ID = Env.getAD_Org_ID(getCtx());

		p_AD_User_ID = 1000000;

		m_ctx = Env.getCtx();
		pi = getProcessInfo();
		
		contractFile = "Шаблон_Договора";
	}

	@Override
	protected String doIt() throws Exception {
		
		if(!isApproved())
			return "Заявка не согласованна";
			
		if(HR_Department_ID == -1)
			throw new Exception("Unable to receive HR_Department_ID");
		
		contract = new X_CMS_Contract(getCtx(), null, trx);
		
		String dn = MSequence.getDocumentNo (p_AD_Client_ID, "CMS_Contract", trx, contract);
		if (dn == null)
			throw new DBException ("No DocumentNo");
		
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		
		contract.setAD_Org_ID(p_AD_Org_ID);
		contract.setAD_User_ID(p_AD_User_ID);
		contract.setBeginningDateExecution(proposal.getBeginningDateExecution());
		contract.setEndDateExecution(proposal.getEndDateExecution());
		contract.setcms_incotermstype_ID(proposal.getcms_incotermstype_ID());
		contract.setcms_paymentstype_ID(proposal.getcms_paymentstype_ID());
		contract.setDescription(proposal.getDescription());
				
		if(HR_Department_ID != -1)		
			contract.setHR_Department_ID(HR_Department_ID);
		
		MUser creator = new MUser(getCtx(), proposal.getCreator_ID(), trx);
		
		contract.setC_BPartner_ID(proposal.getC_BPartner_ID());		
		contract.setResponisbleEmployee_ID(creator.getC_BPartner_ID());
		contract.setC_Currency_ID(MCurrency.get(m_ctx, "KZT").get_ID());
		contract.setcms_statusestype_ID(X_cms_statusestype.CMS_Project);				
		contract.setcms_contractstype_ID(proposal.getcms_contractstype_ID());
		contract.setContractSummary(proposal.getSummary());
		
		Date date = new Date();		
		java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
		proposal.setdatefiling(timeStampDate);
		
		if(contract.save())
		{		
			proposal.setCMS_Contract_ID(contract.get_ID());
			GetDocument();
			proposal.setProcessed(true);
			proposal.setProcessing(true);
			proposal.save();	
		}

		try
		{
			SendMail();
		}
		catch (Exception e)
		{
			throw new Exception("Не удалось отправить уведомление о создании заявки. Ошибка: " + e.toString());
		}
		return "success";
	}
	
	private boolean isApproved()
	{
		MTable table = MTable.get(getCtx(), getTable_ID());
        log.info("Table = " + table);
        
        PO po = table.getPO (getRecord_ID(), trx);

        if(po == null) 
        	return false;
        
		int AGR_Stage_ID = po.get_ValueAsInt("AGR_Stage_ID");
        boolean isHasStage = false;
		
        String message = "";
        
        boolean isHasRecordsInAgreementList = DB.getSQLValue(null, "SELECT AGR_AgreementList_ID FROM AGR_AgreementList WHERE Record_ID = " + getRecord_ID()) > 0;
        
        if(AGR_Stage_ID > 0 && isHasRecordsInAgreementList)
		{
			MAGRStage stage = new MAGRStage(getCtx(), AGR_Stage_ID, trx);
			if(!stage.getStageType().equals(MAGRStage.STAGETYPE_Initial))
				isHasStage = true;
		}
		
		int retValue = 0;
		boolean approved = false;
		
		ArrayList<Object> value = new ArrayList<Object>();
		
		if(isHasStage)
		{
			value = DialogAgreement.dialogApproved(pi, m_ctx, "Согласование");
			
			if(value.size() == 1)
			{
				retValue = 0;
			}
			else
			{
				approved = (Boolean) value.get(0);
				message = (String)value.get(1);
				retValue = 1;
			}
		}
		else
		{
			retValue = DialogAgreement.dialogSendAgreement(pi, m_ctx, "Отправить на согласование") ? 1 : 0;
			approved = retValue == 1;
		}				
		
		if(retValue == 0)
			return false;
				
		Agreement_Dispatcher dispatcher = new Agreement_Dispatcher(po, po.get_Table_ID(), po.get_ID());
		
		int mode = dispatcher.startAgreement(approved, message);		
		
		return mode == 3;
	}
	
	private String GetDocument() throws Exception 
	{
		
		//
		MAttachmentEntry entry = Util.getAttachment(pi, m_ctx, contractFile);
		if(entry == null)
			throw new AdempiereException(Msg.translate(m_ctx, "NotFoundTemplate")+" "+contractFile);
		
		File inputFile = Util.getAttachmentEntryFile(entry);
		// We define a path to generate
		String fileExtension = entry.getName().substring(entry.getName().lastIndexOf("."),entry.getName().length());
		StringBuffer fullPath = new StringBuffer(localFilePath); 
		fullPath.append("Contract_").append(contractFile).append(fileExtension); 
		
		HashMap<String, String> searchTerms = new HashMap<String, String>();
		
		if(proposal.getCMS_Contract_CreatDate() != null)		
		{
			String date = proposal.getCMS_Contract_CreatDate().toString();
			date = date.substring(8,10) + "." + date.substring(5,7) + "." + date.substring(0,4);					
			searchTerms.put("DateOfCreate", date);
		}
		else
		{
			searchTerms.put("DateOfCreate", "");
		}
		
		X_HR_Department department = new X_HR_Department(getCtx(), proposal.getHR_Department_ID(), trx);		
	
		if(department.getName() != null)
			searchTerms.put("Department", department.getName());
		
		
		X_C_BPartner bpartner = new X_C_BPartner(getCtx(), proposal.getC_BPartner_ID(), trx);		
		if(bpartner.getName() != null)
			searchTerms.put("Contragent", bpartner.getName());
		
		searchTerms.put("Subject", proposal.getDescription());
		
		X_cms_paymentstype payment = new X_cms_paymentstype(getCtx(), proposal.getcms_paymentstype_ID(), trx);
		searchTerms.put("PaymentType", payment.getName());
		
		if(proposal.getBeginningDateExecution() != null && proposal.getEndDateExecution() != null)
		{
			String beginingDate = proposal.getBeginningDateExecution().toString();
			String endDate = proposal.getEndDateExecution().toString();		
			beginingDate = beginingDate.substring(8,10) + "." + beginingDate.substring(5,7) + "." + beginingDate.substring(0,4);
			endDate = endDate.substring(8,10) + "." + endDate.substring(5,7) + "." + endDate.substring(0,4);
			searchTerms.put("Sheeping", beginingDate + " - " + endDate);
		}
		else
		{
			searchTerms.put("Sheeping", "");
		}
		
		X_cms_incotermstype incoterms = new X_cms_incotermstype(getCtx(), proposal.getcms_incotermstype_ID(), trx);
		searchTerms.put("Incoterms", incoterms.getName());
		
		searchTerms.put("Quantity", proposal.getquantityqualitygoods());
		
		searchTerms.put("Comment", proposal.getcomment());
		
		searchTerms.put("SpecialConditions", proposal.getSpecialConditions());
		
//		X_AD_User user = new X_AD_User(getCtx(), proposal.getHR_Header(), trx);
		
		X_C_BPartner header = new X_C_BPartner(getCtx(), proposal.getHR_Header_ID(), trx);		
		
		if(header.getName() != null)
			searchTerms.put("DepartmentHeader", header.getName());		
		
		SearchReplace searchReplace = new SearchReplace();				
		
		searchReplace.searchAndReplace(inputFile, fullPath.toString(), searchTerms);
	
		/*try{    
			String fileName = fullPath.toString();
	   	   	String command = "winword \""+fileName+"\"";
	   	   	Runtime.getRuntime().exec("cmd /c start "+command);
	   	}catch(Exception e){
	   		e.printStackTrace();
	   	}*/
		
		File outputFile = new File(fullPath.toString());
		MAttachment outputEntry = new MAttachment(m_ctx, proposal.get_Table_ID(), proposal.get_ID(), trx);
		outputEntry.addEntry(outputFile);
		outputEntry.setTitle("Заявка на договор");
		outputEntry.setTextMsg("");
		outputEntry.saveEx();
		
		return null;
	}
		
	private boolean SendMail(int userToID, int userFromID, String label, String message) throws Exception
	{
		MUser userFrom = new MUser(getCtx(), userFromID, trx);
		MUser userTo = new MUser(getCtx(), userToID, trx);

		MClient client = new MClient(getCtx(), userFromID, trx);
		
		X_R_MailText rText = new X_R_MailText(getCtx(), null, trx);
		
		
		
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
	
		sql.append("select c_bpartner_id from ad_user where ad_user_id = " + userFromID);
		int C_BPartner_ID = DB.getSQLValue(trx, sql.toString());
		
		if(C_BPartner_ID == -1)
			throw new Exception("Ошибка получения имени отправителя");
		
		X_C_BPartner bpartner = new X_C_BPartner(getCtx(), C_BPartner_ID, trx);
		
		rText.setName(bpartner.getName());
		rText.setMailHeader(label);
		rText.setMailText(message);
		
		if(!rText.save())
			throw new Exception("Unnable to save mail pattern");
		
		MMailText mailText = new MMailText(getCtx(), rText.get_ID(), trx);
		
		EMail mail = client.createEMail(userFrom, userTo, mailText.getMailHeader(), mailText.getMailText());	
		
		try 
		{
			EMail.SENT_OK.equals(mail.send());
		}
		catch(Exception e){}
		
		return true;
	}
		
	private boolean SendMail() throws Exception
	{			
		StringBuffer message = new StringBuffer();

		MUser creator = new MUser(m_ctx, proposal.getCreator_ID(), trx);
		
		int C_BPartner_ID = creator.getC_BPartner_ID();
		
		if(C_BPartner_ID <= 0)
			throw new Exception("Unable to receive C_BPartner_ID");
				
		MHRDepartment department = new MHRDepartment(m_ctx, proposal.getHR_Department_ID(), trx);
								
		X_C_BPartner bpartner = new X_C_BPartner(getCtx(), C_BPartner_ID, trx);
				
		String label = "Заявка на разработку договора";
		
		message.append("Департамент \"");
		message.append(department.getName().trim());
		message.append("\" сформировал заявку №");
		message.append(proposal.getDocumentNo());
		message.append(" на создание договора №");
		message.append(contract.getDocumentNo());
		message.append(". \nКонтактное лицо - ");
		message.append(bpartner.getName());		
		
		ArrayList<Integer> users = sendToUser();
		
		if(users.size() == 0)
			throw new Exception("Не удалось загрузить почтовые адреса пользователей");
		
		for(int i = 0; i < users.size(); i++)
		{
			SendMail(users.get(i), p_AD_User_ID, label, message.toString());
		}
		
		return true;
	}
		
	private ArrayList<Integer> sendToUser() throws SQLException
	{
		ArrayList<Integer> users = new ArrayList<Integer>();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select u.ad_user_id " +
					  "from hr_employee e " +
					  "inner join hr_job j on e.hr_job_id = j.hr_job_id "+
					  "inner join hr_department d on e.hr_department_id = d.hr_department_id "+
					  "inner join c_bpartner bp on e.c_bpartner_id = bp.c_bpartner_id "+
					  "inner join ad_user u on u.c_bpartner_id = bp.c_bpartner_id "+
					  "where e.isactive != 'N' and "+
					  "lower(d.name) like '%департамент правового обес%' and "+
					  "(lower(j.name) like '%директор%' or "+
					  "lower(bp.name) like '%жиенб%') ");
		
		PreparedStatement pstmt = null; 
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(buffer.toString(), trx);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				users.add(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){}
		finally
		{
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		return users;
	}

}

