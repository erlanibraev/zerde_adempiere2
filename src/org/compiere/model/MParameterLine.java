package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MFormula.MVariables;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MParameterLine extends X_BSC_ParameterLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -426197082808681320L;
	private Map<String,MVariable> variables = new HashMap<String,MVariable>();

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
	
	public BigDecimal calculate() {
		BigDecimal result = new BigDecimal(0);
		if (isFormula()) {
			MFormula formula = new MFormula(getCtx(), getBSC_Formula_ID(), get_TrxName());
			formula.setArguments(getArguments());
			result = formula.calculate();
		}
		return result;
	}
	
	public HashMap getArguments() {
		HashMap result = new HashMap();
		for(String key:getVariables().keySet()) {
			MVariable var = getVariables().get(key);
			if(var != null) {
				MParameter par = new MParameter(getCtx(),var.getBSC_Parameter_ID(),get_TrxName());
				par.setPeriod((MPeriod)this.getC_Period());
				result.put(key,par.getValueNumber());
			} else {
				
			}
		}
		return result;
	}
	
	protected void loadVariables() {
		variables.clear();
		if(isFormula()) {
			MFormula formula = new MFormula(getCtx(), getBSC_Formula_ID(), get_TrxName());
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
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, "product", e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}	
			
		}
	}
	
	
}
