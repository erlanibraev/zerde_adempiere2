package org.compiere.apps;

import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * The various dialog boxes for budgeting module
 * @author V.Sokolov
 *
 */
public class DialogAgreement {
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(DialogAgreement.class);
	
	/**
	 *  A confirmation dialog box
	 * @return  ArrayList [X, Y]
	 * 			X - Boolean Type, Approved = true, Not approved = false
	 * 			Y - String Type, reason
	 */
	public static ArrayList<Object> dialogApproved(ProcessInfo pi, Properties m_ctx, String Message){
		
		MProcess process = new MProcess(Env.getCtx(),  pi.getAD_Process_ID(), pi.getTransactionName());
		log.info("Agremeent for "+process.get_TableName());
		
		String[] choices = { Util.cleanAmp(Msg.getMsg(m_ctx, "Approved")), Util.cleanAmp(Msg.getMsg(m_ctx, "Not approved"))};
		DialogApproved dialog = new DialogApproved(null, true, Msg.translate(Env.getCtx(), "Agreement"), choices);
		AEnv.showCenterScreen(dialog);
		dialog.dispose();
		
		ArrayList<Object> value = new ArrayList<Object>();
		if(dialog.getItem() != null)
			value.add(dialog.getItem().equals(Util.cleanAmp(Msg.getMsg(m_ctx, choices[0]))));
		if(dialog.getDescription() != null)
			value.add(dialog.getDescription());
		else
			value.add("");
		
		return value;
	}
	
	public static void dialogOK(String title, String message, int messageType){
		JFrame parent = new JFrame();
		String titleDialog = "Dialog";
		if(title.length() > 0)
			titleDialog = title;

	    JOptionPane.showMessageDialog(parent, message, titleDialog,  messageType);
	}
	
	public static boolean dialogSendAgreement(ProcessInfo pi, Properties m_ctx, String Message){
		
		MProcess process = new MProcess(Env.getCtx(),  pi.getAD_Process_ID(), pi.getTransactionName());
		log.info("Send agremeent for "+process.get_TableName());
		
		String[] choices = { Util.cleanAmp(Msg.getMsg(m_ctx, "Send")), Util.cleanAmp(Msg.getMsg(m_ctx, "Cancel"))};
	    int i = JOptionPane.showOptionDialog(null, Message,
	    		Msg.translate(Env.getCtx(), "Send agreement"), 
	    		JOptionPane.DEFAULT_OPTION,
	    		JOptionPane.QUESTION_MESSAGE, 
	    		null, 		// Use default icon
	        choices, 		// Array of choices
	        choices[0]); 	// Initial choice

	    return i == JOptionPane.YES_OPTION;
	}

}
