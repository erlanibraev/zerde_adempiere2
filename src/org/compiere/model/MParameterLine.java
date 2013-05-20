package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MParameterLine extends X_BSC_ParameterLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -426197082808681320L;
	private Map<String,MVariable> variables = new HashMap<String,MVariable>();
	private MFormula formula = null;

	public Map<String,MVariable> getVariables() {
		if (variables.size() == 0 && isFormula()) {
			loadVariables();
		}
		return variables;
	}

	public void setVariables(Map<String,MVariable> variables) {
		this.variables = variables;
	}

	public MParameterLine(Properties ctx, int BSC_ParameterLine_ID, String trxName) {
		super(ctx, BSC_ParameterLine_ID, trxName);
	}

	public MParameterLine(Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	
	public String getValue() {
		String result = super.getValueNumber();
		if (isFormula()) {
			result = calculate();
			setValue(result);
		}
		return result;
	}
	
	public void setValue(String Value) {
		if(!isFormula()) {
			super.setValueNumber(Value);
		} else {
			super.setValueNumber(calculate());
		}
		save();
	}
	
	public String calculate() {
		String result = "0";
		if (isFormula()) {
			MFormula formula = new MFormula(getCtx(), getBSC_Formula_ID(), get_TrxName());
			formula.setArguments(getArguments());
			result = formula.calculate();
		}
		return result;
	}
	
	public HashMap<String,Object> getArguments() {
		HashMap<String,Object> result = new HashMap<String,Object>();
		for(String key:getVariables().keySet()) {
			MVariable var = getVariables().get(key);
			result.put(key,null);
			if(var != null) {
				MParameter par = new MParameter(getCtx(),var.getBSC_Parameter_ID(),get_TrxName());
				if (par != null) {
					par.setPeriod((MPeriod)this.getC_Period());
					result.put(key,par.getValue());
				} 
			} else {
				//TODO если в БД не найдена переменная. Что делать?
			}
		}
		return result;
	}
	
	protected void loadVariables() {
		variables.clear();
		if(isFormula()) {
			fillVariables();
//			MFormula formula = new MFormula(getCtx(), getBSC_Formula_ID(), get_TrxName());
			variables = getCurrentVariable(getBSC_ParameterLine_ID()); 
/*			
			
			for(String key: formula.getVariables()) {
				variables.put(key, null);
			}
			String sql = "SELECT * FROM BSC_Variable WHERE BSC_ParameterLine_ID = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;		
			try {
				pstmt = DB.prepareStatement(sql,null);
				pstmt.setInt (1, getBSC_ParameterLine_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MVariable var = new MVariable(Env.getCtx(),rs,this.get_TrxName());
					String key = var.getName();
					variables.put(key, var);
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "MParameterLine: ", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
*/				
		}
	}
	
	protected void addVariables(MParameterLine prev) {
		deleteVariables();
		for(String key: prev.getVariables().keySet()) {
			MVariable var_prev = prev.getVariables().get(key);
			MVariable var = new MVariable(Env.getCtx(),0,get_TrxName());
			var.setAD_Client_ID(var_prev.getAD_Client_ID());
			var.setAD_Org_ID(var_prev.getAD_Org_ID());
			var.setBSC_Parameter_ID(var_prev.getBSC_Parameter_ID());
			var.setBSC_ParameterLine_ID(getBSC_ParameterLine_ID());
			var.setName(var_prev.getName());
			var.setDescription(var_prev.getDescription());
			var.save();
			if (var.getParameter().IsFormula()) { // Здесь идет рекурсия по дереву! 
			//TODO отсечь циклы!
				var.getParameter().addParameterLine(getC_Period_ID());
			}
			getVariables().put(key, var);
		}
	}
	
	protected void deleteVariables() {
		String sql = "DELETE BSC_Variable WHERE BSC_ParameterLine_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_ParameterLine_ID());
			rs = pstmt.executeQuery();
			getVariables().clear();
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MParameterLine: ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
	}
	
	public MFormula getFormula() {
		if (this.formula == null || this.formula.getBSC_Formula_ID() != getBSC_Formula_ID()) {
			setFormula(new MFormula(Env.getCtx(),getBSC_Formula_ID(), get_TrxName())); 
		}
		return this.formula;
	}
	
	public void setFormula(MFormula formula) {
		this.formula = formula;
	}

	
	/**
	 * @param BSC_ParameterLine_ID
	 * @param variables
	 */
	public void fillVariables() {
		if (getFormula() == null) {
			return;
		}
		
		Set<String> frormulaVariables = getFormula().getVariables();
		
		HashMap<String, MVariable> currentVariable = getCurrentVariable(getBSC_ParameterLine_ID());
		// Добавляем не существующие переменные
		for(String var: frormulaVariables) {
			boolean b = false;
			for(String key: currentVariable.keySet()) {
				b = (var.equals(key)) || b;
				if(b) {
					break;
				}
			}
			if (!b) {
				MVariable newVar = new MVariable(Env.getCtx(),0,get_TrxName());
				newVar.setBSC_ParameterLine_ID(getBSC_ParameterLine_ID());
				newVar.setName(var);
				newVar.save();
			}
		}
		// Удаляем не нужные
		for(String key: currentVariable.keySet()) {
			boolean b = false;
			for(String var:frormulaVariables) {
				b = (var.equals(key)) || b;
				if (b) {
					break;
				}
			}
			if(!b) {
				MVariable var = currentVariable.get(key);
				var.delete(true, get_TrxName());
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
				MVariable var = new MVariable(Env.getCtx(),rs,this.get_TrxName());
				currentVariable.put(var.getName(), var);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MParameterLine: ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return currentVariable;
	}
	
}
