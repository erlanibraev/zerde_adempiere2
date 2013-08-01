package org.compiere.process;

import org.compiere.model.MPFRCalculation;
import org.compiere.pfr.QueryDialog;
import org.compiere.util.Env;

public class QuerySettings extends SvrProcess 
{
	private int AD_Table_ID = 0;
	
	@Override
	protected void prepare() 
	{
		MPFRCalculation calc = new MPFRCalculation(getCtx(), getRecord_ID(), get_TrxName());
		
		AD_Table_ID = calc.getAD_Table_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		QueryDialog dialog = new QueryDialog(Env.getFrame(Env.getWindow(0).getParent()), AD_Table_ID, getRecord_ID());
		
		return null;
	}

}
