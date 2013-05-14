/**
 * 
 */
package extend.org.copmiere.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.X_C_Charge;

/**
 * Used in the table BPM_ServicesWork
 * @author V.Sokolov
 * 
 */
public class CalloutVerifyItemName extends CalloutEngine {

	
	/**
	 * Assign a name in accordance with item in a budget 
	 * Used in the column C_Charge_ID
	 */
	public void checkName(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		if(value == null)
			return;
		
		X_C_Charge charge = new X_C_Charge(ctx, (Integer) value, null);
		mTab.setValue("Name", charge.getName());
		
	}
	
}
