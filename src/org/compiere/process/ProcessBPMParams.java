package org.compiere.process;

import org.compiere.apps.DialogParameters;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ProcessBPMParams extends SvrProcess {

	int AD_Table_ID;
	
	@Override
	protected void prepare() {
		
		AD_Table_ID = getProcessInfo().getTable_ID();
		getTable_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		new DialogParameters(Env.getFrame(Env.getWindow(0).getParent()), AD_Table_ID, getRecord_ID());
		
		return Msg.translate(getCtx(), "Success");
	}

}
