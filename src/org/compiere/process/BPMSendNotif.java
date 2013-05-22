/**
 * 
 */
package org.compiere.process;

import java.io.File;
import java.text.MessageFormat;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MBPMEmployeeLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;
import org.compiere.model.X_BPM_EmployeeLine;
import org.compiere.model.X_BPM_VersionBudget;
import org.compiere.model.X_C_BPartner;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.joda.time.DateTime;

import extend.org.compiere.hrm.ExcelCell;
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
	/**	 */
	private ProcessInfo pi;
	/* */
	private X_BPM_VersionBudget version = null;
	/* */
	private String templateName = "MailNotification";
	
	/* 
	 */
	protected void prepare() {
		
		log.info("Send Mail: beginning of the budget");
		m_ctx = getCtx();
		pi = getProcessInfo();
		
		version = new X_BPM_VersionBudget(getCtx(), getRecord_ID(), get_TrxName()); 
	}
	
	/* 
	 */
	@Override
	protected String doIt() throws Exception {
		
		//
		MAttachmentEntry entry = Util.getAttachment(pi, m_ctx, templateName);
		if(entry == null)
			throw new AdempiereException(Msg.translate(m_ctx, "NotFoundTemplate")+" "+templateName);
		
		File inputFile = Util.getAttachmentEntryFile(entry);
		
		Workbook tableBook = null;
		Sheet sheet = null;
		 try {
			 tableBook = Workbook.getWorkbook(inputFile);
			 sheet = tableBook.getSheet(0);
		} catch (Exception e) {
		  	ADialog.error(999, null, "Error tableBook. " + e.getMessage());
		   	return "Error tableBook.";
		}

		ExcelCell cellSubject =  Util.getCellStart(tableBook,"sub");
		ExcelCell cellMessage =  Util.getCellStart(tableBook,"mess");
		ExcelCell cellSignature =  Util.getCellStart(tableBook,"sign");
		
		String subject = sheet.getCell(cellSubject.getC()+1, cellSubject.getR()).getContents();
		String textMail = sheet.getCell(cellMessage.getC()+1, cellMessage.getR()).getContents();
		String signature = sheet.getCell(cellSignature.getC()+1, cellSignature.getR()).getContents();
		
		MOrg org = new MOrg(m_ctx, Env.getAD_Org_ID(m_ctx), get_TrxName());
		MBPartner bpOrg = new MBPartner(m_ctx, org.getLinkedC_BPartner_ID(get_TrxName()), get_TrxName()); 
		DateTime dateFinish = new DateTime(version.getDateFinish().getTime());

		/*
		 * Params:
		 * 		{0} - year budget 
		 * 		{1} - Organization (AD_Org_ID)
		 * 		{2} - the last date of submission of the budget request 
		 */
		Object[] param = new Object[]{version.getC_Year().getFiscalYear(), 
										bpOrg.getDescription(), 
		dateFinish.getDayOfMonth()+" "+Util.getMonthName(version.getDateFinish(), Env.getAD_Language(getCtx()), true)+" "+dateFinish.getYear()};
		StringBuffer msgHtml = new StringBuffer();
		
		// message HTML
		msgHtml.append("<table border=\"center\" width=\"80%\"> \n")
		.append("<tr> \n")
		.append("	<td align=\"center\"> \n")
		.append("		<font size=\"4\">Уведомление</font> ")
		.append("	</td> \n")
		.append("</tr> \n")
		.append("<tr> \n")
		.append("	<td> \n")
		.append("		<pre> \n")
		.append(textMail)
		.append("		</pre> \n")
		.append("	</td> \n")
		.append("</tr> \n")
		.append("<tr> \n")
		.append("	<td align=\"right\"><font size=\"1\">");
		if(signature.length() > 0)
			msgHtml.append(signature);
		else
			msgHtml.append("ADempiere ERP Business Suite");
		msgHtml.append("</font></td> \n")
		.append("</tr> \n")
		.append("</table>");
		
		String message = "";
		if(version.isHtml())
			message = MessageFormat.format(msgHtml.toString(), param);
		else
			message = "							Уведомление \r\r" + MessageFormat.format(textMail, param);
		
		X_BPM_EmployeeLine[] empLine = MBPMEmployeeLine.getEmployeeLines();
		for(X_BPM_EmployeeLine emp: empLine){
			X_C_BPartner bp = new X_C_BPartner(m_ctx, emp.getC_BPartner_ID(), get_TrxName());
			MUser[] user = MUser.getOfBPartner(m_ctx, bp.getC_BPartner_ID(), get_TrxName());
			if(user[0].getAD_User_ID() != 0 || user[0].getEMail() != null || user[0].getEMail().length() != 0)
				Util.sendMail(user[0].getAD_User_ID(), getAD_User_ID(),  MessageFormat.format(subject.toString(), param), message, version.isHtml());
		}
		
		version.setNumberSend(version.getNumberSend()+1);
		version.saveEx();
		
		tableBook.close();
		inputFile.delete();
		
		return Msg.translate(m_ctx, "Success");
	}

}
