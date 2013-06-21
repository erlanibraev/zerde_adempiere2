package extend.org.compiere.callout;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.X_CMS_Contract;
import org.compiere.model.X_C_BankAccount;
import org.compiere.util.Env;

public class TreasureCallout extends CalloutEngine {
	
	@SuppressWarnings("unused")
	public void setBank(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{		
		if(value == null) return;

		X_C_BankAccount account = new X_C_BankAccount(ctx, (Integer)mTab.getValue("C_BankAccount_ID"), null);
		
		if(account == null)
			throw new AdempiereException("Wrong bank account");
		
		mTab.setValue("C_Bank_ID", account.getC_Bank_ID());
		
		setCurrentBalance(ctx, WindowNo, mTab, mField, value);
	}
	
	@SuppressWarnings("unused")
	public void setCurrentBalance(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{		
		X_C_BankAccount account = new X_C_BankAccount(ctx, (Integer)mTab.getValue("C_BankAccount_ID"), null);
		
		if(account == null)
			throw new AdempiereException("Wrong bank account");
		
		//mTab.setValue("Sum", account.getCurrentBalance());		
	}
	
	public void SetDescription(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return;
 	
		int CMS_Contract_ID = (Integer)mTab.getValue("CMS_Contract_ID");
		
		X_CMS_Contract contract = new X_CMS_Contract(Env.getCtx(), CMS_Contract_ID, null);
			
		if(contract != null)
		{
			try
			{
				mTab.setValue("Description", contract.getDescription());
				mTab.setValue("ApplAmount", contract.getContractSummary());
				mTab.setValue("PaymentTerms", contract.getPaymentTerms());
			}
			catch(Exception e){}
		}
	}

}
