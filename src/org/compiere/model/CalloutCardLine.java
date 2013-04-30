/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalloutCardLine extends CalloutEngine {

	/**
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String unit(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String result = null;
		if (value != null) {
			log.info("--- CalloutCardLine.coefficient ---");
			MFormula formula = MBSCCardLine.getFormulaByUnit((String) value);
			if (formula != null) {
				mTab.setValue("BSC_Formula_ID", new Integer(formula.getBSC_Formula_ID()));
			}
		}
		return result;
	}
}
