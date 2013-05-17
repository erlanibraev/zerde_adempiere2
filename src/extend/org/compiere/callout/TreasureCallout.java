package extend.org.compiere.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.X_CMS_Contract;
import org.compiere.util.Env;

public class TreasureCallout extends CalloutEngine {
	
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
