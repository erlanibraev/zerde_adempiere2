/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.apps.ADialog;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalloutBSCParameter extends CalloutEngine {
	/**
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String WithoutPeriod(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String result = null;
		if (value != null) {
			int BSC_Parameter_ID = new BigDecimal((mTab.getValue("BSC_Parameter_ID") != null ?  mTab.getValue("BSC_Parameter_ID").toString(): "0")).intValue();
			if (BSC_Parameter_ID > 0) {
				MParameter parameter = new MParameter(Env.getCtx(),BSC_Parameter_ID,null);
				parameter.setwithout_period((Boolean) value);
			}
		}
		return result;
	}
}
