/**
 * 
 */
package extend.org.copmiere.callout;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPMABP;
import org.compiere.model.MBPMVersionBudget;
import org.compiere.model.MUser;
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

}
