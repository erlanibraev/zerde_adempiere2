package extend.org.compiere.callout;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MCMSContract;
import org.compiere.model.X_C_BankAccount;
import org.compiere.model.X_cms_payment;
import org.compiere.util.Env;

public class Callout_TRMApplication extends CalloutEngine 
{
	@SuppressWarnings("unused")
	public void setCurrentBalance(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{		
		if(value != null)
		{
			MCMSContract contract = new MCMSContract(Env.getCtx(), (Integer)value, null);
			
			if(contract != null)
			{
				mTab.setValue("ApplAmount", contract.getSummary());
			}
//			X_cms_payaccount = new X_C_BankAccount(ctx, (Integer)mTab.getValue("C_BankAccount_ID"), null);
			
//			if(account == null)
//				throw new AdempiereException("Wrong bank account");
			
			//mTab.setValue("Sum", account.getCurrentBalance());		
		}
	}

}
