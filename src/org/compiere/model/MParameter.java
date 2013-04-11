/**
 * @autor: Ibrayev Y.A.
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MParameter extends X_BSC_Parameter {

	/**
	 * @class: Parameter for BSC  
	 */
	private static final long serialVersionUID = -909939771956323658L;

	public static boolean GOAL_max = true;
	public static boolean GOAL_min = false;
	public static String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
	
	private ArrayList<MParameterLine> parameterLine = new ArrayList<MParameterLine>();
	private MPeriod currentPeriod = null;
	private MParameterLine currentParameterLine = null;
	private BigDecimal C_BPartner_ID = null;

	public BigDecimal getC_BPartner_ID() {
		String sql = "SELECT C_BPartner_ID FROM BSC_BPartner_Parameter WHERE BSC_Parameter_ID = ? and C_Period_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_Parameter_ID());
			pstmt.setInt (2, getPeriod().getC_Period_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				C_BPartner_ID = rs.getBigDecimal(COLUMNNAME_C_BPartner_ID);
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
		return C_BPartner_ID;
	}

	public void setC_BPartner_ID(BigDecimal c_Bpartner_ID) {
		if (getC_BPartner_ID() != null && !getC_BPartner_ID().equals(c_Bpartner_ID)) {
			deleteC_BPartner_ID(c_Bpartner_ID.intValue(),getPeriod().getC_Period_ID());
			insertC_BPartner_ID(c_Bpartner_ID,getPeriod().getC_Period_ID());
		} else if(getC_BPartner_ID() == null && c_Bpartner_ID != null) {
			insertC_BPartner_ID(c_Bpartner_ID,getPeriod().getC_Period_ID());
		}
	}

	protected void insertC_BPartner_ID (BigDecimal c_Bpartner_ID, int c_Period_ID) {
		String sql = "INSERT INTO BSC_BPartner_Parameter (C_BPartner_ID,BSC_Parameter_ID, C_Period_ID) VALUE (?,?,?)";
		PreparedStatement pstmt = null;
		BigDecimal C_BPartner_ID_old = c_Bpartner_ID;
		int rs = 0;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_Parameter_ID());
			pstmt.setInt (2, c_Bpartner_ID.intValue());
			pstmt.setInt (3, c_Period_ID);
			rs = pstmt.executeUpdate();
			if (rs > 0) {
				C_BPartner_ID = c_Bpartner_ID;
				log.log(Level.INFO,"inserted BSC_Parameter_ID = " + Integer.toString(getBSC_Parameter_ID()).toString()+" and C_BPartner_ID = " + (C_BPartner_ID_old == null? " NULL": C_BPartner_ID_old.toString()));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "inserted BSC_BPartner_Parameter", e);
		}
		finally
		{
			DB.close(pstmt);
			pstmt = null;
		}	

	}
	
	protected void deleteC_BPartner_ID(int c_BPartner_ID, int c_Period_ID) {
		String sql = "DELETE BSC_BPartner_Parameter WHERE BSC_Parameter_ID = ? and C_BPartner_ID = ? and C_Period_ID = ?";
		PreparedStatement pstmt = null;
		BigDecimal C_BPartner_ID_old = getC_BPartner_ID();
		int rs = 0;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, getBSC_Parameter_ID());
			pstmt.setInt (2, c_BPartner_ID);
			pstmt.setInt (3, c_Period_ID);
			rs = pstmt.executeUpdate();
			if (rs > 0) {
				C_BPartner_ID = null;
				log.log(Level.INFO,"deleted in BSC_Parameter_ID = " + Integer.toString(getBSC_Parameter_ID()).toString()+" C_BPartner_ID = " + (C_BPartner_ID_old == null? " NULL": C_BPartner_ID_old.toString()));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "deleted  BSC_BPartner_Parameter", e);
		}
		finally
		{
			DB.close(pstmt);
			pstmt = null;
		}	
	}

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
				log.log(Level.SEVERE, "product", e);
			}
		}
	}
	
	public BigDecimal getValueNumber() {
		BigDecimal result = new BigDecimal(0);
		if (getCurrentParameterLine() != null) {
			result = getCurrentParameterLine().getValueNumber(); 
		}
		return result;
	}
	
	public void setValueNumber(BigDecimal value) {
		getCurrentParameterLine().setValueNumber(value);
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
	
	protected static BigDecimal runCalc(MParameter parameter, MPeriod period, Set<Integer> forCycle) throws Exception {
		BigDecimal result = null;
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
					// Здесь вызвать исключение
					throw new Exception("MParameter: detected cycle in "+parameter.getName()+"; ID - "+parameter.getBSC_Parameter_ID()+". "+param.getName()+" ID - "+param.getBSC_Parameter_ID()+" parameter exists");
				}
				BigDecimal value = runCalc(param, period, forCycle);
				param.getParameterLine(period).setValueNumber(value);
			}
			result = parameter.getParameterLine(period).calculate();
		} else {
			result = parameter.getParameterLine(period).getValueNumber();
		}
		return result;
	}
	
	protected void addParameterLine(int c_Period_ID) {
		if(getParameterLine(new MPeriod(Env.getCtx(),c_Period_ID,get_TrxName())) == null) {
			// ToDo Добавить Линию параметра на основе предыдущего периода.
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
			if (pl.isFormula()) {
				pl.addVariables(pl_prev);
			}
			loadParameterLine();
		}
	}

	/**
	 * @return
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
		return result;
	}
	
}
