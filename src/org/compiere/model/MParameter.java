/**
 * @autor: Ibrayev Y.A.
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MParameter extends X_BSC_Parameter {

	/**
	 * @class: Parameter for BSC  
	 */
	
	protected static CLogger sLog = CLogger.getCLogger ("MParameter"); 
	
	private static final long serialVersionUID = -909939771956323658L;

	public static boolean GOAL_max = true;
	public static boolean GOAL_min = false;
	public static String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
	
	private ArrayList<MParameterLine> parameterLine = new ArrayList<MParameterLine>();
	private MPeriod currentPeriod = null;
	private MParameterLine currentParameterLine = null;

	protected MParameterLine getCurrentParameterLine() {
		return currentParameterLine;
	}

	protected void setCurrentParameterLine(MParameterLine currentParameterLine) {
		this.currentParameterLine = currentParameterLine;
	}

	protected MPeriod getCurrentPeriod() {
		return currentPeriod;
	}

	protected void setCurrentPeriod(MPeriod currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public ArrayList<MParameterLine> getParameterLine() {
		return parameterLine;
	}

	public void setParameterLine(ArrayList<MParameterLine> parameterLine) {
		this.parameterLine = parameterLine;
	}
	
	public MParameterLine getParameterLine(MPeriod period) {
		MParameterLine result = null;
		for(MParameterLine line:getParameterLine()) {
			if(line.getC_Period_ID() ==period.getC_Period_ID()) {
				result = line;
				break;
			}
		}
		return result;
	}

	public MParameter(Properties ctx, int BSC_Parameter_ID, String trxName) {
		super(ctx, BSC_Parameter_ID, trxName);
		loadParameterLine();
	}
	
	public MPeriod getPeriod() {
		MPeriod result = null;
		if (getCurrentPeriod() == null) {
			for(MParameterLine line:parameterLine) {
				MPeriod per = (MPeriod) line.getC_Period();
				if (result == null || result.getEndDate().after(per.getStartDate())) {
					result = per;
				}
			}
			setCurrentPeriod(result);
		} else {
			result = getCurrentPeriod(); 
		}
		return result;
	}
	
	public void setPeriod(MPeriod period) {
		boolean haveP = false;
		if (period == null) {
			setCurrentPeriod(null);
			period = getPeriod();
		}	
		for(MParameterLine line:parameterLine) {
			if (period.equals((MPeriod)line.getC_Period())) {
				setCurrentPeriod(period);
				setCurrentParameterLine(line);
				haveP = true;
				break;
			}
		}
		if (!haveP) {
			try {
				MParameterLine pl = new MParameterLine(Env.getCtx(),0,this.get_TrxName());
				pl.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
				pl.setAD_Client_ID(Env.getAD_Client_ID(getCtx()));
				pl.setC_Period_ID(period.getC_Period_ID());
				pl.setBSC_Parameter_ID(this.getBSC_Parameter_ID());
				pl.save();
				getParameterLine().add(pl);
				setCurrentParameterLine(pl);
			} catch(Exception e) {
				log.log(Level.SEVERE, "setPeriod", e);
			}
		}
	}
	
	public BigDecimal getValueNumber() {
		BigDecimal result = new BigDecimal(0);
		if (getCurrentParameterLine() != null) {
			result = new BigDecimal((getCurrentParameterLine().getValue() == null ? "0" : getCurrentParameterLine().getValue())); 
		}
		return result;
	}
	
	public void setValueNumber(BigDecimal value) {
		getCurrentParameterLine().setValueNumber(value.toString());
	}
	
	public BigDecimal getValueMax() {
		BigDecimal result = new BigDecimal(0);
		if (getCurrentParameterLine() != null) {
			result = getCurrentParameterLine().getValueMax(); 
		}
		return result;
	}
	
	public void setValueMax(BigDecimal value) {
		getCurrentParameterLine().setValueMax(value);
	}
	
	public BigDecimal getValueMin() {
		BigDecimal result = new BigDecimal(0);
		if (getCurrentParameterLine() != null) {
			result = getCurrentParameterLine().getValueMin(); 
		}
		return result;
	}
	
	public void setValueMin(BigDecimal value) {
		getCurrentParameterLine().setValueMin(value);
	}
	
	public boolean IsFormula() {
		boolean result = false;
		if (getCurrentParameterLine() != null) {
			result = getCurrentParameterLine().isFormula() ; 
		}
		return result;
	}
	
	public void setIsFormula(boolean value) {
		getCurrentParameterLine().setIsFormula(value);
	}
	
	protected void loadParameterLine() {
		parameterLine.clear();
		String sql = "SELECT * FROM BSC_ParameterLine WHERE BSC_Parameter_ID = ? and isActive = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_Parameter_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MParameterLine al = new MParameterLine(Env.getCtx(),rs,this.get_TrxName());
				parameterLine.add(al);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
	}
	
	protected static String runCalc(MParameter parameter, MPeriod period, Set<Integer> forCycle) throws Exception {
		String result = null;
		if (forCycle == null) {
			forCycle = new HashSet<Integer>();
			forCycle.add(parameter.getBSC_Parameter_ID());
		}
		if (parameter.getParameterLine(period).isFormula()) {
			Map<String,MVariable> variable = parameter.getParameterLine(period).getVariables(); 
			for(String key: variable.keySet()) {
				MVariable var = variable.get(key);
				MParameter param = new MParameter(Env.getCtx(),var.getBSC_Parameter_ID(),null);
				if( !forCycle.add(param.getBSC_Parameter_ID())) {
					// Здесь вызваем исключение, что найден цикл!
					throw new Exception("MParameter: detected cycle in "+parameter.getName()+"; ID - "+parameter.getBSC_Parameter_ID()+". "+param.getName()+" ID - "+param.getBSC_Parameter_ID()+" parameter exists");
				}
				String value = runCalc(param, period, forCycle);
				param.getParameterLine(period).setValue(value);
			}
			result = parameter.getParameterLine(period).calculate();
		} else {
			result = parameter.getParameterLine(period).getValue();
		}
		return result;
	}
	
	protected void addParameterLine(int c_Period_ID) {
		if(getParameterLine(new MPeriod(Env.getCtx(),c_Period_ID,get_TrxName())) == null) {
			int mp = getMaxPeriodInLine();
			MParameterLine pl_prev = getParameterLine(new MPeriod(Env.getCtx(),mp,get_TrxName()));
			MParameterLine pl = new MParameterLine(Env.getCtx(),0,get_TrxName());
			pl.setC_Period_ID(c_Period_ID);
			pl.setBSC_Parameter_ID(getBSC_Parameter_ID());
			pl.setBSC_Formula_ID(pl_prev.getBSC_Formula_ID());
			pl.setGoal(pl_prev.isGoal());
			pl.setValueMin(pl_prev.getValueMin());
			pl.setValueMax(pl_prev.getValueMax());
			pl.setIsFormula(pl_prev.isFormula());
			pl.save();
			if (pl.isFormula()) { // Здесь идет рекурсия по дереву!
				//TODO отсечь циклы!
				pl.addVariables(pl_prev);
			}
			loadParameterLine();
		}
	}

	/**
	 * @return 
	 * Возвращает существующий максимальный период в БД для данного параметра
	 */
	private int getMaxPeriodInLine() {
		int result = 0;
		String sql = " with t1 as ( \n"
                    +" select pl.* \n"
                    +"      , p.periodno \n" 
                    +" from BSC_ParameterLine pl \n"
                    +" join C_Period p "
                    +"   on p.C_Period_ID = pl.C_Period_ID \n"
                    +" ), t2 as ( \n"
                    +" select BSC_Parameter_ID \n"
                    +"      , max(periodno) as periodno \n"
                    +" from t1 \n"     
                    +" group by BSC_Parameter_ID \n"
                    +" ), t3 ( \n"
                    +" select t1.* \n" 
                    +" from t1 \n"
                    +" right join t2 \n" 
                    +"         on t1.BSC_Parameter_ID = t2.BSC_Parameter_ID \n"
                    +"        and t1.periodno = t2.periodno \n"
                    +" order by t1.BSC_ParameterLine_ID \n" 
                    +" ) \n"
                    +" select C_Period_ID from t3 \n"
                    +" where BSC_Period_ID = ? \n";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_Parameter_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(0);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
	
	// Создание выходного параметра для Линии карты ССП
	
	public static MParameter createParameterForCardLine(String Name, String Description, int C_BPartner_ID, int BSC_Formula_ID, int C_Period_ID) {
		MParameter result = null;
		if (C_BPartner_ID > 0 && BSC_Formula_ID > 0 && C_Period_ID > 0) {
			MBPartner partner = new MBPartner(Env.getCtx(),C_BPartner_ID,null);
			MPeriod period = new MPeriod(Env.getCtx(),C_Period_ID,null);
			MFormula formula = new MFormula(Env.getCtx(),BSC_Formula_ID,null);
			if (partner != null && formula != null && period != null) {
				MParameter param = new MParameter(Env.getCtx(),0,null);
				param.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
				param.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
				param.setName(Name);
				param.setDescription(Description);
				param.setC_BPartner_ID(C_BPartner_ID);
				if (param.save()) { //TODO переделать на Exception
					MParameterLine paramLine = createParameterLine(param, BSC_Formula_ID, C_Period_ID);
					if (paramLine != null) {
						result = param;
					} else {
						param.delete(true);
					}
						
				}
			}
		}
		return result;		
	}
	
	public static MParameterLine createParameterLine(MParameter param, int BSC_Formula_ID, int C_Period_ID) {
		MParameterLine result = null;
		MFormula formula = new MFormula(Env.getCtx(),BSC_Formula_ID,null);
		if (param != null && BSC_Formula_ID > 0 && formula != null && C_Period_ID > 0) {
			MParameterLine paramLine = new MParameterLine(Env.getCtx(),0,null);
			paramLine.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			paramLine.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
			paramLine.setBSC_Parameter_ID(param.getBSC_Parameter_ID());
			paramLine.setIsFormula(true);
			paramLine.setBSC_Formula_ID(BSC_Formula_ID);
			paramLine.setC_Period_ID(C_Period_ID);
			paramLine.setGoal(true);
			paramLine.setIsActive(true);
			if(paramLine.save()) {
				if (createVariable(param, formula, C_Period_ID)) {
					result = paramLine;
				} else {
					paramLine.delete(true);
				}
			}
		}
		return result;
	}
	
	protected static boolean createVariable(MParameter param, MFormula formula, int C_Period_ID) {
		boolean result = false;
		HashMap<String, Object> arg = formula.getArguments();
		boolean save = true;
		for(String key:arg.keySet()) {
			if (save) {
				MVariable var = new MVariable(Env.getCtx(),0,null);
				var.setName(key);
				var.setBSC_Parameter_ID(param.getBSC_Parameter_ID());
				MParameter varParam = createParameter(key, param.getDescription(), param.getC_BPartner_ID(), C_Period_ID);
				if (varParam != null) {
					save = save && var.save();
					if (save) {
						var.setBSC_ParameterLine_ID(varParam.getBSC_Parameter_ID());
					} else {
						varParam.delete(true);
						var.delete(true);
					}
				} else {
					save = false;
				}
			}
		}
		result  = save;
		return result;
	}
	
	protected static MParameter createParameter(String Name, String Description ,int C_BPartner_ID, int C_Period_ID){
		MParameter result = null;
		try {
			MParameter param = new MParameter(Env.getCtx(),0,null);
			param.setName(Name);
			param.setDescription(Description);
			param.setC_BPartner_ID(C_BPartner_ID);
			param.setPeriod(new MPeriod(Env.getCtx(),C_Period_ID,null));
			if (param.save()) { //TODO Переделать на Exception
				MParameterLine paramLine = new MParameterLine(Env.getCtx(),0,null);
				paramLine.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
				paramLine.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
				paramLine.setBSC_Parameter_ID(param.getBSC_Parameter_ID());
				paramLine.setIsFormula(false);
				paramLine.setC_Period_ID(C_Period_ID);
				paramLine.setGoal(true);
				paramLine.setIsActive(true);
				if (paramLine.save()) {
					result = param;
				} else {
					param.delete(true);
				}
			}
		} catch(Exception e) {
			sLog.log(Level.SEVERE, "createParameter: "+Name+" - ", e);
		}
		return result;
	}

	/**
	 * @return
	 */
	public String getValue() {
		String result = "";
		if (getCurrentParameterLine() != null) {
			result = getCurrentParameterLine().getValue(); 
		}
		return result;
	}

	public void setValue(String value) {
		getCurrentParameterLine().setValue(value);
	}
	
}
