/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
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
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCardLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 */
	public MBSCCardLine(Properties ctx) {
		super(ctx);
	}

	public BigDecimal calculate() {
		BigDecimal result =  null;
		if(getFormula() != null) {
			if (getBSC_Parameter_ID() != 0) {
				calcParameter();
			}
			getFormula().setArguments(getArguments());
			result = new BigDecimal(getFormula().calculate());
			setValueNumber(result);
			save();
		}
		return result;
	}
	
	@Override 
	public void setValueNumber (BigDecimal ValueNumber) {
		super.setValueNumber(ValueNumber);
		saveParameterOutValue();
	}
	
	@Override
	public String getValue() {
		String result = super.getValue();
		if (getBSC_Parameter_ID() > 0) {
			MParameter param = getParameter();
			MBSCCard card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
			param.setPeriod(card.getPeriod());
			if (param.getCurrentParameterLine().isFormula()) {
				result = param.getCurrentParameterLine().calculate();
			}
		}
		setValue(result);
		return result;
	}
	
	@Override
	public void setValue (String Value) {
		super.setValue(Value);
		if (getBSC_Parameter_ID() > 0) {
			MParameter param = getParameter();
			MBSCCard card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
			param.setPeriod(card.getPeriod());
			if (!param.IsFormula()) {
				param.setValue(Value);
				param.save();
			} else {
				Value = param.getCurrentParameterLine().calculate();
			}
		}
	}
	
	/**
	 * 
	 */
	private void calcParameter() {
		if (getBSC_Parameter_ID() != 0) {
			getParameter().setPeriod(getCard().getPeriod());
			setValue(getParameter().getValue());
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
			result  = getValueMax().toString();
		} else if (varName.equalsIgnoreCase("Min")) {
			result = getValueMin().toString();
		} else if (varName.equalsIgnoreCase("Coefficient")) {
			if (getCoefficient() != null) {
				result = getCoefficient().calcCoefficient(getValue(),getValueMax(),getValueMin()).toString();
			} else {
				throw new Exception("MBSCardLine: variable not found - "+varName);
			}
		} else if (varName.equalsIgnoreCase("Value")) {
			if (StringUtils.isNumeric(getValue())) {
				result = new BigDecimal(getValue());
			} else {
				result = getValue();
			}
		} else if (varName.equalsIgnoreCase("Weight")) {
			result = getWeight().toString();
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
			if (getBSC_Parameter_ID() > 0) {
				setParameter(new MParameter(Env.getCtx(),getBSC_Parameter_ID(),get_TrxName()));
				parameter.setPeriod(getCard().getPeriod());
			}
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
			sLog.log(Level.SEVERE, "getFormulaByUnit", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
	
	// Нужно добавить для ParamOut стандартные параметры max, min, coefficient, value, weight;
	
	protected void saveParameterOutValue() {
		MBSCCard card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
		if( getBSC_Parameter_Out_ID() > 0) {
			MParameter param = new MParameter(Env.getCtx(),getBSC_Parameter_Out_ID(),get_TrxName());
			param.setPeriod(card.getPeriod());
			MParameterLine paramLine = param.getCurrentParameterLine();
			if (!paramLine.isFormula() && paramLine.getBSC_Formula_ID() != getBSC_Formula_ID()) {
				paramLine.setIsFormula(true);
				paramLine.setBSC_Formula_ID(getBSC_Formula_ID());
				paramLine.save();
			}
			setVar(param);
		} else {
			MParameter param = MParameter.createParameter(getName(), getDescription(), card.getC_BPartner_ID(), card.getC_Period_ID());
			param.setPeriod(card.getPeriod());
			setBSC_Parameter_Out_ID(param.getBSC_Parameter_ID());
			setVar(param);
		}
	}
	
	protected void setVar(MParameter param) {
		//Тут установить переменные Value, Min, Max, Coefficient
		
		MParameter valueParam = getParameter();
		MParameterLine paramLine = param.getCurrentParameterLine();
		MBSCCard card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
		Map<String,MVariable> args = paramLine.getVariables(); 
		for(String key: args.keySet()) {
			MParameter paramVar = null;
			int vBSC_Parameter_ID = args.get(key).getBSC_Parameter_ID();
			if (vBSC_Parameter_ID == 0) {
				paramVar = MParameter.createParameter("(" +key + ") " + getName(), getDescription(), card.getC_BPartner_ID(), card.getC_Period_ID());
				MVariable var = paramLine.getVariables().get(key);
				var.setBSC_Parameter_ID(paramVar.getBSC_Parameter_ID());
				var.save();
			} else {
				paramVar = new MParameter(Env.getCtx(),vBSC_Parameter_ID, get_TrxName());
			}
			
			int formulaValue_ID = getFormulaValue_ID();
			paramVar.setPeriod(card.getPeriod());
			
			if (key.equals("Value") && formulaValue_ID > 0) {
				
				MParameterLine pLine = paramVar.getCurrentParameterLine();
				
				if (pLine != null && getBSC_Parameter_ID() > 0 ) {
					pLine.setIsFormula(true);
					pLine.setBSC_Formula_ID(formulaValue_ID);
					MVariable var = pLine.getVariables().get(key);
					var.setBSC_Parameter_ID(valueParam.getBSC_Parameter_ID());
					var.save();
					pLine.save();
				} else {
					try {
						pLine.setIsFormula(false);
						paramVar.setValue(getValue(key).toString());
					} catch (Exception e) {
						sLog.log(Level.SEVERE, "setVar", e);
					}
				}
				
			} else {
				try {
					paramVar.setValue(getValue(key).toString());
				} catch (Exception e) {
					sLog.log(Level.SEVERE, "setVar", e);
				}
			}
		}
	}
	
	protected static int getFormulaValue_ID() {
		int result = 0;
		String sql = "SELECT * FROM BSC_Formula WHERE Name like 'Value' "; //"and AD_Client_ID = " +Env.getAD_Client_ID(Env.getCtx());
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(X_BSC_Formula.COLUMNNAME_BSC_Formula_ID);
			}
		} catch (SQLException e) {
			sLog.log(Level.SEVERE, "getFormulaValue_ID", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
	
	public void copyCardLine(int BSC_Card_ID, int C_Period_ID) {
		MBSCCard newCard = new MBSCCard(Env.getCtx(),BSC_Card_ID,get_TrxName());
		if (newCard != null && newCard.getBSC_Card_ID() > 0) {
			MBSCCardLine newLine = new MBSCCardLine(Env.getCtx(),0,get_TrxName());
			newLine.setAD_Client_ID(getAD_Client_ID());
			newLine.setAD_Org_ID(getAD_Org_ID());
			newLine.setName(getName());
			newLine.setDescription(getDescription());
			newLine.setBSC_Card_ID(BSC_Card_ID);
			newLine.setBSC_Coefficient_ID(getBSC_Coefficient_ID());
			newLine.setBSC_Formula_ID(getBSC_Formula_ID());
			newLine.setUnit(getUnit());
			newLine.setWeight(getWeight());
			newLine.setValueMax(getValueMax());
			newLine.setValueMin(getValueMin());
			newLine.setBSC_Parameter_ID(getBSC_Parameter_ID());
			newLine.setBSC_Parameter_Out_ID(getBSC_Parameter_Out_ID());
			newLine.setBSC_Perspective_ID(getBSC_Perspective_ID());
			newLine.setValue("0");
			newLine.setValueNumber(new BigDecimal(0));
			if(newLine.save()) {
				MParameter par = newLine.getParameter();
				if (par != null) {
					par.createNewLine(C_Period_ID);
					par.save();
				}
				par = new MParameter(Env.getCtx(),newLine.getBSC_Parameter_Out_ID(),get_TrxName());
				if (par != null) {
					par.createNewLine(C_Period_ID);
					par.save();
				}
			}
		}
	}
}
