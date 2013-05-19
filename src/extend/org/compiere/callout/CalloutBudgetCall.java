/**
 * 
 */
package extend.org.compiere.callout;

import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPMABP;
import org.compiere.model.MBPMBudgetCall;
import org.compiere.model.MBPMVersionBudget;
import org.compiere.model.MPeriod;
import org.compiere.model.MUser;
import org.compiere.model.X_BPM_BudgetCallLine;
import org.compiere.model.X_BPM_VersionBudget;
import org.compiere.util.Env;
import org.eevolution.model.X_HR_Employee;

/**
 *  Used in the table BPM_BudgetCall
 * @author V.Sokolov
 *
 */
public class CalloutBudgetCall extends CalloutEngine {
	
	/**
	 * Used in the column C_Year_ID
	 */
	public void setYear(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		X_BPM_VersionBudget budget = MBPMVersionBudget.getVersionBudget();
		if(budget == null)
			throw new AdempiereException("No active version");
		
		mTab.setValue("C_Year_ID", budget.getC_Year_ID());
		
	}
	
	/**
	 * The current version
	 * Used in the column BPM_VersionBudgetLine_ID
	 */
	public void setVersion(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		mTab.setValue("BPM_VersionBudgetLine_ID", MBPMVersionBudget.getCurrentVersion());
		
	}
	
	/**
	 * Used in the column BPM_ABP_ID 
	 */
	public void setABP(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		MUser user = MUser.get(ctx, Env.getAD_User_ID(ctx));
		int C_BPartner_ID = user.getC_BPartner_ID();
		X_HR_Employee employee = X_HR_Employee.getHR_Employee(C_BPartner_ID, null);
		
		if(employee == null)
			throw new AdempiereException("The employee is not active");
		
		mTab.setValue("BPM_ABP_ID", MBPMABP.getABP(employee.getHR_Department_ID()).getBPM_ABP_ID());
		
	}
	
	/* Create a budget periods */
	public void createBudgetLine(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		int Year = Env.getContextAsInt(ctx, WindowNo, "C_Year_ID");
		
		MPeriod[] period = MBPMBudgetCall.getPeriodBudget(Year);
		
		int budgetCall = (Integer) mTab.getValue("BPM_BudgetCall_ID");
		if(mTab.getValue("C_Charge_ID") == null){
			mTab.dataIgnore();
			ADialog.warn(999, null, "Не указана статья Расходов/Доходов");
			return;
		}
		int chargeCall =  (Integer) mTab.getValue("C_Charge_ID");
		int table =  (Integer) mTab.getValue("AD_Table_ID");
		String record =  (String) mTab.getValue("Record_ID");
		int n = 0;

		for(MPeriod p: period){
			if(n > 0)
				mTab.dataNew(true);
			mTab.setValue("C_Period_ID", p.getC_Period_ID());
			mTab.setValue("C_UOM_ID", 100);
			mTab.setValue("Quantity", 0);
			mTab.setValue("AmountUnit", 0);
			mTab.setValue("Amount(", 0);
			mTab.setValue("BPM_BudgetCall_ID", budgetCall);
			mTab.setValue("C_Charge_ID", chargeCall);
			mTab.setValue("PaymentMonth",X_BPM_BudgetCallLine.PAYMENTMONTH_Current);
			mTab.setValue("AD_Table_ID",  table);
			mTab.setValue("Record_ID", record);
			mTab.dataSave(true);
			n++;
		}
		mTab.dataRefresh();
		
	}

}
