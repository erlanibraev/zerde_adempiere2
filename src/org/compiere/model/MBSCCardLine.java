/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
	
	protected static CLogger sLog = CLogger.getCLogger(MBSCCardLine.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5442201269292225823L;
	private MFormula formula = null;
	private HashMap<String, Object> arguments = new HashMap<String,Object>();
	private MBSCCoefficent coefficient = null;
	private MBSCCard card = null;
	private MParameter parameter = null;
	private MParameter parameter_Out = null;
	private MParameter parameter_Q = null;
	private MBSCCardLine[] linkLines = null;
	private MBSCCardLine link = null;
	
	// Constatnts for formul
	public static String CARDLINEVARIABLE_MAX = "Max";
	public static String CARDLINEVARIABLE_MIN = "Min";
	public static String CARDLINEVARIABLE_VALUE = "Value";
	public static String CARDLINEVARIABLE_COEFFICIENT = "Coefficient";
	public static String CARDLINEVARIABLE_WEIGHT = "Weight";
	public static String CARDLINEVARIABLE_Q = "Q";
	
	public MParameter getParameter_Out() {
		if (getBSC_Parameter_Out_ID() > 0 && (parameter_Out == null || parameter_Out.getBSC_Parameter_ID() != getBSC_Parameter_Out_ID())) {
			parameter_Out = new MParameter(Env.getCtx(),getBSC_Parameter_Out_ID(),get_TrxName());
			if (getCard() != null &&  getCard().getPeriod() != null) {
				parameter_Out.setPeriod(getCard().getPeriod());
			}
		}
		return parameter_Out;
	}

	public void setParameter_Out(MParameter parameter_Out) {
		this.parameter_Out = parameter_Out;
		if (parameter_Out != null) {
			setBSC_Parameter_Out_ID(parameter_Out.getBSC_Parameter_ID());
		} else {
			setBSC_Parameter_Out_ID(0);
		}
	}

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
			saveParameterOutValue();
			result = new BigDecimal(getParameter_Out().getValue(getCard().getPeriod()));
			setValueNumber(result);
			save();
		}
		return result;
	}
	
	@Override 
	public void setValueNumber (BigDecimal ValueNumber) {
		super.setValueNumber(ValueNumber);
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
			MBSCCard card = getCard();
			param.setPeriod(card.getPeriod());
			if (!param.IsFormula()) {
				param.setValue(Value);
				param.save();
			} else {
				Value = param.getValue(card.getPeriod());
			}
		}
	}
	
	/**
	 * 
	 */
	private void calcParameter() {
		if (getBSC_Parameter_ID() != 0) {
//			getParameter().setPeriod(getCard().getPeriod());
			setValue(getParameter().getValue(getCard().getPeriod()));
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
		if (varName.equalsIgnoreCase(CARDLINEVARIABLE_MAX)) {
			result  = getValueMax().toString();
		} else if (varName.equalsIgnoreCase(CARDLINEVARIABLE_MIN)) {
			result = getValueMin().toString();
		} else if (varName.equalsIgnoreCase(CARDLINEVARIABLE_COEFFICIENT)) {
			if (getCoefficient() != null) {
				result = getCoefficient().calcCoefficient(getValue(),getValueMax(),getValueMin()).toString();
			} else {
				throw new Exception("MBSCardLine: variable not found - "+varName);
			}
		} else if (varName.equalsIgnoreCase(CARDLINEVARIABLE_VALUE)) {
			if (StringUtils.isNumeric(getValue())) {
				result = new BigDecimal(getValue());
			} else {
				result = getValue();
			}
		} else if (varName.equalsIgnoreCase(CARDLINEVARIABLE_WEIGHT)) {
			result = getWeight().toString();
		} else if (varName.equalsIgnoreCase(CARDLINEVARIABLE_Q)) {
			result  = getQ();
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
		if (getBSC_Card_ID() > 0 && (card == null || card.getBSC_Card_ID() != getBSC_Card_ID())) {
			card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),get_TrxName());
		}
		return card;
	}

	public void setCard(MBSCCard card1) {
		this.card = card1;
		if (card != null || card.getBSC_Card_ID() != getBSC_Card_ID()) {
			setBSC_Card_ID(card.getBSC_Card_ID());
		}
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
		MBSCCard card = getCard();
		MParameter param = null;
		if( getBSC_Parameter_Out_ID() > 0) {
			param = getParameter_Out();
		} else {
			param = MParameter.createParameter(getName(), getDescription(), card.getC_BPartner_ID(), card.getC_Period_ID());
			param.save();
			setParameter_Out(param);
		}
		param.setPeriod(card.getPeriod());
		MParameterLine paramLine = param.getCurrentParameterLine();
		paramLine.setValueMax(new BigDecimal(1));
		paramLine.setValueMin(new BigDecimal(0));
		if (!paramLine.isFormula() || (paramLine.getBSC_Formula_ID() != getCoefficient().getBSC_Formula_ID() && getCoefficient().getBSC_Formula_ID() > 0)) {
			paramLine.setIsFormula(true);
			paramLine.setBSC_Formula_ID(getCoefficient().getBSC_Formula_ID());
			paramLine.save();
		}
		setVar(param);
		param.save();
	}
	
	protected int getFormula_paramOutValue_ID() {
		int result = 0;
		String sql = "SELECT * FROM BSC_Formula WHERE Name like 'paramOutValue'";
		result = DB.getSQLValue(get_TrxName(), sql);
		return result;
	}
	
	protected void setVar(MParameter param) {
		//Тут установить переменные Value, Min, Max, Coefficient, Q
		
		MParameter valueParam = getParameter();
		MParameterLine paramLine = param.getCurrentParameterLine();
		MBSCCard card = getCard();
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
			paramVar.setPeriod(card.getPeriod());
			
			int formulaValue_ID = getFormulaValue_ID();
			
			if (key.equals(CARDLINEVARIABLE_VALUE) && formulaValue_ID > 0) {
				
				MParameterLine pLine = paramVar.getCurrentParameterLine();
				try {
					if (getBSC_Parameter_ID() > 0 ) {
						pLine.setIsFormula(true);
						pLine.setBSC_Formula_ID(formulaValue_ID);
						MVariable var = pLine.getVariables().get(key);
						var.setBSC_Parameter_ID(valueParam.getBSC_Parameter_ID());
						var.save();
					} else {
						pLine.setIsFormula(false);
						paramVar.setValue(getValue(key).toString());
					}
					pLine.setValueMax(getValueMax());
					pLine.setValueMin(getValueMin());
					pLine.save();
				} catch (Exception e) {
					sLog.log(Level.SEVERE, "setVar", e);
				}
			} else if (key.equals(CARDLINEVARIABLE_Q)) {
				setParameter_Q(paramVar);
				MParameterLine pLine = paramVar.getCurrentParameterLine();
				pLine.setIsFormula(true);
				pLine.setBSC_Formula_ID(getBSC_Formula_ID());
				pLine.setValueMax(new BigDecimal(1));
				pLine.setValueMin(new BigDecimal(0));
				pLine.save();
				setVar(paramVar);
			}else {
				try {
					paramVar.setValue(getValue(key).toString());
				} catch (Exception e) {
					sLog.log(Level.SEVERE, "setVar", e);
				}
			}
			paramVar.save();
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
			copyValues(this, newLine);
			newLine.setBSC_Card_ID(BSC_Card_ID);
			
/*			
			newLine.setAD_Client_ID(getAD_Client_ID());
			newLine.setAD_Org_ID(getAD_Org_ID());
			newLine.setName(getName());
			newLine.setDescription(getDescription());
			newLine.setBSC_Coefficient_ID(getBSC_Coefficient_ID());
			newLine.setBSC_Formula_ID(getBSC_Formula_ID());
			newLine.setUnit(getUnit());
			newLine.setWeight(getWeight());
			newLine.setValueMax(getValueMax());
			newLine.setValueMin(getValueMin());
			newLine.setBSC_Parameter_ID(getBSC_Parameter_ID());
			newLine.setBSC_Parameter_Out_ID(getBSC_Parameter_Out_ID());
			newLine.setBSC_Perspective_ID(getBSC_Perspective_ID());
*/			
			newLine.setValue("0");
			newLine.setValueNumber(new BigDecimal(0));
			if(newLine.save()) {
				MParameter par = newLine.getParameter();
				if (par != null) {
					par.createNewLine(getCard().getC_Period_ID(),C_Period_ID);
					par.save();
				}
				par = new MParameter(Env.getCtx(),newLine.getBSC_Parameter_Out_ID(),get_TrxName());
				if (par != null) {
					par.createNewLine(getCard().getC_Period_ID(),C_Period_ID);
					par.save();
				}
			}
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		saveParameterOutValue();
		return super.beforeSave(newRecord);
	}
	
	@Override
	public BigDecimal getQ() {
		BigDecimal result = calcQ();
		setQ(result);
		return result;
	}
	
	private BigDecimal calcQ() {
		BigDecimal result = null;
		MFormula formula = getFormula();
		if (formula != null) {
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			try {
				for(String key: formula.getVariables()) {
						map.put(key, getValue(key));
						formula.setArguments(map);
						result = new BigDecimal(getFormula().calculate()); 
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "BSCCardLine.calcQ",e);
			}
		}
		return result;
	}
	
	public MParameter getParameter_Q() {
		return parameter_Q;
	}

	public void setParameter_Q(MParameter parameter_Q) {
		this.parameter_Q = parameter_Q;
	}
	
	public MBSCCardLine[] getLinkLines(String whereClause) {
		String whereClauseFinal = " BSC_CardLine_ID IN (SELECT link_BSC_CardLine_ID FROM BSC_CardLine_Link WHERE BSC_CardLine_ID=? )";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<MBSCCardLine> list = new Query(getCtx(), MBSCCardLine.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(getBSC_CardLine_ID())
										.list();
		return list.toArray(new MBSCCardLine[list.size()]);
	}
	
	public MBSCCardLine[] getLinkLines (boolean requery)
	{
		if (linkLines == null || linkLines.length == 0 || requery)
			linkLines = getLinkLines(null);
		set_TrxName(linkLines, get_TrxName());
		return linkLines;
	}	//	getLines

	public MBSCCardLine[] getLinkLines () {
		return getLinkLines(false);
	}
	
	public MBSCCardLine getLink(boolean requery) {
		if (link == null || requery)
			link = getLink(null);
//		set_TrxName(link, get_TrxName());
		return link;
	}
	
	public MBSCCardLine getLink() {
		return getLink(false);
	}
	
	public MBSCCardLine getLink(String whereClause) {
		String whereClauseFinal = " BSC_CardLine_ID IN (SELECT BSC_CardLine_ID FROM BSC_CardLine_Link WHERE link_BSC_CardLine_ID=? )";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<MBSCCardLine> list = new Query(getCtx(), MBSCCardLine.Table_Name, whereClauseFinal, get_TrxName())
		.setParameters(getBSC_CardLine_ID())
		.list();
		return (list.size() > 0 ? list.get(0) : null);
	}
}
