package org.compiere.process;

import org.compiere.model.X_BSC_NetWorkDiag;

public class BSC_NetWorkDiag extends SvrProcess {
	
	private X_BSC_NetWorkDiag netWorkDiag = null;
	
	@Override
	protected void prepare() {	
		netWorkDiag = new X_BSC_NetWorkDiag(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		if(netWorkDiag.getDocStatus().equals(netWorkDiag.DOCSTATUS_ForApproval))
			{
				return "Send email for approval!";
			}
		if(netWorkDiag.getDocStatus().equals(netWorkDiag.DOCSTATUS_Deflected))
		{
			return "Document is Deflected";
		}
		if(netWorkDiag.getDocStatus().equals(netWorkDiag.DOCSTATUS_Endorsed))
		{
			return "Document is Endorsed";
		}
		return null;
	}

} 
