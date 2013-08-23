package org.compiere.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.compiere.agreement.Agreement_Dispatcher;
import org.compiere.apps.DialogAgreement;
import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.model.MAGRStage;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AGR_AgreementList;
import org.compiere.model.X_AGR_Stage;
import org.compiere.util.ASyncProcess;
import org.compiere.util.Env;

public class AgreementProcess extends SvrProcess implements ASyncProcess 
{
	/** Current context		*/
	private Properties m_ctx;
	/**	 */
	private ProcessInfo pi;
	
	private String trx;
	
	private static Logger log = Logger.getLogger("AgreementProcess");
	
	@Override
	protected void prepare() 
	{		
		m_ctx = Env.getCtx();
		pi = getProcessInfo();
		trx = get_TrxName();
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
			
			if(stage.isLastStage() && stage.isAllApproved(getTable_ID(), po.get_ID()))
			{
				DialogAgreement.dialogOK("Ошибка доступа", "Документ согласован", 0);
				return "Документ согласован";
			}
			
			if(!stage.isUserHasAccess(Env.getAD_User_ID(getCtx()), getTable_ID(), po.get_ID()))
			{
				DialogAgreement.dialogOK("Ошибка доступа", "У вас нет доступа к данному этапу согласования", 0);
				return "Ошибка доступа";
			}
			
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
				
		Agreement_Dispatcher dispatcher = new Agreement_Dispatcher(po, po.get_Table_ID(), po.get_ID());
		
		dispatcher.startAgreement(approved, message);
		
		int AD_Reference_ID = (new MAGRStage(m_ctx, AGR_Stage_ID, trx)).getAD_Reference_ID();
		
		if(AD_Reference_ID > 0)
		{
			try {
				callProcess(AD_Reference_ID,po.get_ID(),table.getAD_Table_ID(),AGR_Stage_ID, dispatcher.STAGE_Approved);
			}
			catch(Exception ex) {
				log.severe("Couldn't invoke process for this agreement stage. " + ex.toString());
			}
		}
		return "";
	}
	
	private void callProcess(int AD_Process_ID, int Record_ID, int AD_Table_ID, int AGR_Stage_ID, boolean approved)
	{
		ProcessInfo pi = new ProcessInfo ("The stage process", AD_Process_ID);
		pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		pi.setRecord_ID(Record_ID);
		pi.setTable_ID(AD_Table_ID);
		
		List<ProcessInfoParameter> po = new ArrayList<ProcessInfoParameter>();
		po.add(new ProcessInfoParameter(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID, new BigDecimal(AGR_Stage_ID),null,"",""));
		po.add(new ProcessInfoParameter(X_AGR_AgreementList.COLUMNNAME_IsApproved, approved,null,"",""));
		ProcessInfoParameter[] pp = new ProcessInfoParameter[po.size()];
		po.toArray(pp);
		pi.setParameter(pp);
		//	Execute Process
		ProcessParameterPanel pu = new ProcessParameterPanel(0, pi);
		ProcessCtl.process(this, 0, (IProcessParameter) pu, pi, null);
	}

	@Override
	public void lockUI(ProcessInfo pi) {		
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
	}

	@Override
	public boolean isUILocked() {
		return false;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
	}
}
