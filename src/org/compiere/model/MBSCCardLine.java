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
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * @author Y.Ibrayev
 *
 */
public class MBSCCardLine extends X_BSC_CardLine {
	
	protected static CLogger sLog = CLogger.getCLogger ("MBSCCardLine"); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5442201269292225823L;
	private MFormula formula = null;
	private HashMap<String, Object> arguments = new HashMap<String,Object>();
	private MBSCCoefficent coefficient = null;
	private MBSCCard card = null;
	private MParameter parameter = null;

	/**
	 * @param ctx
	 * @param BSC_CardLine_ID
	 * @param trxName
	 */
	public MBSCCardLine(Properties ctx, int BSC_CardLine_ID, String trxName) {
		super(ctx, BSC_CardLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCardLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCCardLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal calculate() {
		BigDecimal result =  null;
		if(getFormula() != null) {
			if (getBSC_Parameter_ID() != 0) {
				calcParameter();
			}
			getFormula().setArguments(getArguments());
			result = getFormula().calculate();
			setValueNumber(result);
			save();
		}
		return result;
	}
	
	/**
	 * 
	 */
	private void calcParameter() {
		if (getBSC_Parameter_ID() != 0) {
			getParameter().setPeriod(getCard().getPeriod());
			setValue(getParameter().getValueNumber().toString());
		} else {
			setValue("");
		}
	}

	public MFormula getFormula() {
		if (formula == null || formula.getBSC_Formula_ID() != getBSC_Formula_ID()) {
			setFormula(new MFormula(Env.getCtx(),getBSC_Formula_ID(),get_TrxName()));
		}
		return formula;
	}

	public void setFormula(MFormula formula) {
		this.formula = formula;
	}

	public HashMap<String,Object> getArguments() {
		if (arguments.size() == 0) {
			// ToDo: Add arguments
			for (String varName: getFormula().getVariables()) {
				try{
					arguments.put(varName, getValue(varName));
				} catch (Exception e) {
					log.log(Level.SEVERE,"BSCCardLine: ",e);
				}
			}
		}
		return arguments;
	}

	public void setArguments(HashMap<String,Object> arguments) {
		this.arguments = arguments;
	}

	/**
	 * @param varName
	 * @return
	 * Определяем переменные для линии Карты ССП
	 * Стандартные:
	 * Value
	 * Min
	 * Max
	 * Coefficient
	 * Wight
	 * @throws Exception 
	 */
	private Object getValue(String varName) throws Exception {
		Object result = null;
		if (varName.equalsIgnoreCase("Max")) {
			result  = getValueMax();
		} else if (varName.equalsIgnoreCase("Min")) {
			result = getValueMin();
		} else if (varName.equalsIgnoreCase("Coefficient")) {
			if (getCoefficient() != null) {
				result = getCoefficient().calcCoefficient(getValue(),getValueMax(),getValueMin());
			} else {
				throw new Exception("MBSCardLine: variable not found -"+varName);
			}
		} else if (varName.equalsIgnoreCase("Value")) {
			result = getValue();
		} else if (varName.equalsIgnoreCase("Wight")) {
			result = getWeight();
		} else {
			log.log(Level.SEVERE,"MBSCardLine: variable not found - "+varName);
			throw new Exception("MBSCardLine: variable not found - "+varName);
		}
		return result;
	}

	/**
	 * @return
	 */
	public MBSCCoefficent getCoefficient() {
		if (coefficient == null) {
			if (getBSC_Coefficient_ID() != 0) {
				coefficient = new MBSCCoefficent(Env.getCtx(),getBSC_Coefficient_ID(),this.get_TrxName());
			}
		}
		return coefficient;
	}

	public void setCoefficient(MBSCCoefficent coefficient) {
		this.coefficient = coefficient;
	}
	
	public MBSCCard getCard() {
		if (card == null || card.getBSC_Card_ID() != getBSC_Card_ID()) {
			card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
		}
		return card;
	}

	public void setCard(MBSCCard card) {
		this.card = card;
	}
	
	@Override
	public int getBSC_Formula_ID() {
		int result =  super.getBSC_Formula_ID();
		if (getFormulaByUnit() != null && (result == 0 || result != getFormulaByUnit().getBSC_Formula_ID()) ) {
			result = getFormulaByUnit().getBSC_Formula_ID();
			setBSC_Formula_ID(result);
		} 
		return result;
	}
	
	private MFormula getFormulaByUnit() {
		MFormula result = getFormulaByUnit(getUnit());
		return result;
	}
	
	public MParameter getParameter() {
		if (parameter == null || parameter.getBSC_Parameter_ID() != getBSC_Parameter_ID()) {
			setParameter(new MParameter(Env.getCtx(),getBSC_Parameter_ID(),get_TrxName()));
			parameter.setPeriod(getCard().getPeriod());
		}
		return parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
	}
	
	public static MFormula getFormulaByUnit(String unit) {
		MFormula result = null;
		String sql = "SELECT * FROM BSC_Formula WHERE BSC_Formula_ID in (SELECT BSC_Formula_ID FROM BSC_UnitFormula WHERE Unit = ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setString (1, unit);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new MFormula(Env.getCtx(), rs, null);
			}
		} catch (SQLException e) {
			sLog.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
}
