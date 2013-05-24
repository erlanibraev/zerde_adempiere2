package org.compiere.process;

import java.util.Properties;

import org.compiere.agreement.Agreement_Dispatcher;
import org.compiere.apps.DialogAgreement;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AGR_Stage;
import org.compiere.util.Env;

public class AgreementProcess extends SvrProcess 
{
	/** Current context		*/
	private Properties m_ctx;
	/**	 */
	private ProcessInfo pi;
	
	@Override
	protected void prepare() 
	{		
		m_ctx = Env.getCtx();
		pi = getProcessInfo();
	}

	@Override
	protected String doIt() throws Exception 
	{				
        MTable table = MTable.get(getCtx(), getTable_ID());
        log.info("Table = " + table);
        
        PO po = table.getPO (getRecord_ID(), get_TrxName());

		
		if(po == null) return null;
		
		boolean approved = false;
		
		//if(trm_application.getAGR_Stage_ID() > 0)
		if(po.get_ValueAsInt(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID) > 0)
		{
			if(DialogAgreement.dialogApproved(pi, m_ctx, "Согласование"))
				approved = true;
		}
		else
		{
			if(!DialogAgreement.dialogSendAgreement(pi, m_ctx, "Отправить на согласование"))
				return "";
			else approved = true;
		}
		
		Agreement_Dispatcher dispatcher = new Agreement_Dispatcher(po, po.get_Table_ID(), po.get_ID());
		
		dispatcher.startAgreement(approved);
		
		return "";
	}
}
