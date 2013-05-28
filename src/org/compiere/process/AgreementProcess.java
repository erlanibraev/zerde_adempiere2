package org.compiere.process;

import java.util.ArrayList;
import java.util.Properties;

import org.compiere.agreement.Agreement_Dispatcher;
import org.compiere.apps.DialogAgreement;
import org.compiere.model.MAGRStage;
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

        if(po == null) return "PO is null";
        
		int AGR_Stage_ID = po.get_ValueAsInt("AGR_Stage_ID");
        boolean isHasStage = false;
		
        String message = "";
        
		if(AGR_Stage_ID > 0)
		{
			MAGRStage stage = new MAGRStage(getCtx(), AGR_Stage_ID, get_TrxName());
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
			return "Exit|Cancel";
		//else if(retValue == 1)
		//approved = retValue == 1;
				
		Agreement_Dispatcher dispatcher = new Agreement_Dispatcher(po, po.get_Table_ID(), po.get_ID());
		
		dispatcher.startAgreement(approved, message);
		
		return "";
	}
}
