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
		
		return null;
	}

} 
