/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Y.Ibrayev
 *
 */
public class CalloutBSCDashboard extends CalloutEngine {

	public String BPartner(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String result = null;
		if (value != null) {
			int C_BPartner_ID = new BigDecimal((mTab.getValue("C_BPartner_ID") != null ?  mTab.getValue("C_BPartner_ID").toString(): "0")).intValue();
			if (C_BPartner_ID > 0) {
				int HR_Job_ID = MBSCDashboard.getHR_Job_ID_by_C_BPartner_ID(C_BPartner_ID);
				mTab.setValue("HR_Job_ID", new Integer(HR_Job_ID));
			}
		}
		return result;
	}

}
