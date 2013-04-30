/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCCoefficent extends X_BSC_Coefficient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959171876051879722L;
	private MFormula formula = null;

	/**
	 * 
	 * @param ctx
	 * @param BSC_Coefficient_ID
	 * @param trxName
	 */
	protected MBSCCoefficent(Properties ctx, int BSC_Coefficient_ID,
			String trxName) {
		super(ctx, BSC_Coefficient_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	protected MBSCCoefficent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	protected MBSCCoefficent(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public MFormula getFormula() {
		if (formula == null || formula.getBSC_Formula_ID() != getBSC_Formula_ID() ) {
			setFormula(new MFormula(Env.getCtx(), getBSC_Formula_ID(), get_TrxName()));
		}
		return formula;
	}

	public void setFormula(MFormula formula) {
		this.formula = formula;
	}
	/**
	 * 
	 * @param value  
	 * Входное значения для формулы. В формуле обязано называться "Value" 
	 * @param max
	 * Входное значения для формулы. В формуле обязано называться "Max"
	 * @param min
	 * Входное значения для формулы. В формуле обязано называться "Min"
	 * @return 
	 * Возвращает значение формулы которое укзано в справочнике.
	 * Формула должна содержать один (или все) из входных параметров - "Value", "Max", "Min"
	 */
	public BigDecimal calcCoefficient(String value, BigDecimal max, BigDecimal min) {
		/* Warning BullSheet detected!
		 * powered by Ibrayev Y.E.
		 * При вводе формулы расчета коэффициента обязательно  
		 */
		BigDecimal result = null;
		if (getFormula() != null) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("Value", value); // Magic name variable
			map.put("Max", max); // Magic name variable
			map.put("Min", min); // Magic name variable
			getFormula().setArguments(map);
			result = getFormula().calculate(); 
		}
		return result;
	}
}
