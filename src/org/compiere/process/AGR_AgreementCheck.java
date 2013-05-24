package org.compiere.process;

import org.compiere.apps.ADialog;
import org.compiere.model.MAGRAgreement;

public class AGR_AgreementCheck extends SvrProcess 
{
	int AGR_Agreement_ID = 0;
	@Override
	protected void prepare() 
	{
		AGR_Agreement_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(AGR_Agreement_ID <= 0) return null;
		
		MAGRAgreement agreement = new MAGRAgreement(getCtx(), AGR_Agreement_ID, get_TrxName());
		
		String returnValue = "";
		
		if(agreement.checkAgreement())
			returnValue = "Согласование " + agreement.getName() + " настроенно верно";
		else
			returnValue = "Согласование " + agreement.getName() + " настроенно не верно";
		
		ADialog dialog = new ADialog();
		
		dialog.ask(25, null, returnValue);
		
		return returnValue;
	}

}
