/**
 * 
 */
package org.compiere.apps;

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
 * @author V.Sokolov
 *
 */
public class DialogAgreement {
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(DialogAgreement.class);
	
	public static boolean dialog(ProcessInfo pi, Properties m_ctx, String Message){
		
		MProcess process = new MProcess(Env.getCtx(),  pi.getAD_Process_ID(), pi.getTransactionName());
		log.info("Agremeent for "+process.get_TableName());
		boolean retValue = false;
		
		String[] choices = { Util.cleanAmp(Msg.getMsg(m_ctx, "Approved")), Util.cleanAmp(Msg.getMsg(m_ctx, "Not approved"))};
	    String input = (String) JOptionPane.showInputDialog(null, Message,
	    		Msg.translate(Env.getCtx(), "Agreement"), 
	    		JOptionPane.QUESTION_MESSAGE, 
	    		null, 		// Use default icon
	        choices, 		// Array of choices
	        choices[0]); 	// Initial choice

	    if(input == null)
	    	return retValue;
	    
	    return retValue = input.equals(Util.cleanAmp(Msg.getMsg(m_ctx, "approved")));
		
	}
	
	public static void dialogOK(String title, String message, int messageType){
		JFrame parent = new JFrame();
		String titleDialog = "Dialog";
		if(title.length() > 0)
			titleDialog = title;

	    JOptionPane.showMessageDialog(parent, message, titleDialog,  messageType);
	}

}
