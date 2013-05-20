/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalloutParameterLine extends CalloutEngine {
	
	private String trxName = null;
	
	public String getTrxName() {
		return trxName;
	}

	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}

	/**
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String fillVariables(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String result = null;
		if (value != null) {
			BigDecimal BSC_Formula_ID = new BigDecimal(value.toString());
			if (isCalloutActive() || BSC_Formula_ID == null || BSC_Formula_ID.intValue() == 0) {
				result = "";
			} else {
				try {
					mTab.dataSave(true);
					int BSC_ParameterLine_ID = mTab.getRecord_ID(); 
					if (mTab.getValue("BSC_ParameterLine_ID") != null) {
						BSC_ParameterLine_ID = (Integer) mTab.getValue("BSC_ParameterLine_ID");
					}
					MFormula formula = new MFormula(Env.getCtx(),BSC_Formula_ID.intValue(),getTrxName());
					Set<String> variables = formula.getVariables();
					fill(BSC_ParameterLine_ID,variables);
				} catch(Exception e) {
					result = e.getMessage();
					log.log(Level.SEVERE,"CalloutParameterLine: ",e);
				}
			}
		}
		return result;
	}

	/**
	 * @param BSC_ParameterLine_ID
	 * @param variables
	 */
	private void fill(int BSC_ParameterLine_ID, Set<String> variables) {
		HashMap<String, MVariable> currentVariable = getCurrentVariable(BSC_ParameterLine_ID);
		// Добавляем не существующие переменные
		for(String var: variables) {
			boolean b = false;
			for(String key: currentVariable.keySet()) {
				b = (var.equals(key)) || b;
				if(b) {
					break;
				}
			}
			if (!b) {
				MVariable newVar = new MVariable(Env.getCtx(),0,getTrxName());
				newVar.setBSC_ParameterLine_ID(BSC_ParameterLine_ID);
				newVar.setName(var);
				newVar.save();
			}
		}
		// Удаляем не нужные
		for(String key: currentVariable.keySet()) {
			boolean b = false;
			for(String var:variables) {
				b = (var.equals(key)) || b;
				if (b) {
					break;
				}
			}
			if(!b) {
				MVariable var = currentVariable.get(key);
				var.delete(true, getTrxName());
			}
		}
	}

	/**
	 * @param BSC_ParameterLine_ID
	 * @return ArrayList<MVariable>
	 */
	private HashMap<String,MVariable> getCurrentVariable(int BSC_ParameterLine_ID) {
		HashMap<String,MVariable> currentVariable = new HashMap<String,MVariable>();
		String sql = "SELECT * FROM BSC_Variable WHERE BSC_ParameterLine_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, BSC_ParameterLine_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MVariable var = new MVariable(Env.getCtx(),rs,this.getTrxName());
				currentVariable.put(var.getName(), var);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "CalloutParameterLine: ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return currentVariable;
	}

}
