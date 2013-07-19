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
			
			// Установка формулы для расчета выходного параметра
			MFormula formula = MBSCCardLine.getFormulaByUnit((String) value);
			if (formula != null) {
				mTab.setValue("BSC_Formula_ID", new Integer(formula.getBSC_Formula_ID()));
			}
			
			// Установка коэффициента, в зависимости от периода
			Integer C_Period_ID = new Integer(Env.getContext(ctx, WindowNo, "C_Period_ID"));
			if (C_Period_ID > 0) {
				MBSCCoefficent coefficent = MBSCCoefficent.getCoefficentByUnit(Env.getCtx(),(String) value, C_Period_ID.intValue());
				if (coefficent != null) {
					mTab.setValue("BSC_Coefficient_ID", coefficent.getBSC_Coefficient_ID());
				}
			}
		}
		return result;
	}
}
