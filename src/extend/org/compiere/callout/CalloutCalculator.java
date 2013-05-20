/**
 * 
 */
package extend.org.compiere.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MParameter;
import org.compiere.model.MParameterLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MVariable;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 * Class for calculate parameter 
 */
public class CalloutCalculator extends CalloutEngine {
	
	public String calculate(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String result = null;
		BigDecimal BSC_ParameterLine_ID = (BigDecimal) value;
		if (isCalloutActive() || BSC_ParameterLine_ID == null || BSC_ParameterLine_ID.intValue() == 0) {
			result = "";
		} else {	
			try {
				MParameterLine parameterLine = new MParameterLine(ctx,BSC_ParameterLine_ID.intValue(),null);
				parameterLine.setValue(parameterLine.calculate());
			} catch (Exception e) {
				result = e.getMessage();
				log.log(Level.SEVERE,result);
			}
		}
		return result;
	}
	
}
