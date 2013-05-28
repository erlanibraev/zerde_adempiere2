package org.compiere.process;

import java.util.ArrayList;

import org.compiere.apps.ADialog;
import org.compiere.apps.DialogAgreement;
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
		{
			ArrayList<String> errors = agreement.getErrors();
			
			for(int i = 0; i < errors.size(); i++)
				returnValue += errors.get(i) + "\n";
		}
		
		DialogAgreement.dialogOK("Согласование", returnValue, 0);
				
		return returnValue;
	}

}
