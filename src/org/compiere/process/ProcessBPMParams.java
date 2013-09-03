package org.compiere.process;

import org.compiere.apps.DialogParameters;
import org.compiere.model.MTable;
import org.compiere.model.X_BPM_BudgetCallLine;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ProcessBPMParams extends SvrProcess {

	int AD_Table_ID;
	
	@Override
	protected void prepare() {
		
		AD_Table_ID = getProcessInfo().getTable_ID();
		
	}

	@Override
	protected String doIt() throws Exception {
		
		new DialogParameters(Env.getFrame(Env.getWindow(0).getParent()), MTable.getTable_ID(X_BPM_BudgetCallLine.Table_Name.toLowerCase()+"_v"), getRecord_ID());
		
		return Msg.translate(getCtx(), "Success");
	}

}
