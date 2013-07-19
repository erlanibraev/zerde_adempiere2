/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class MBSCCoefficent extends X_BSC_Coefficient {

	protected static CLogger sLog = CLogger.getCLogger(MBSCCardLine.class); 
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
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	protected MBSCCoefficent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 */
	protected MBSCCoefficent(Properties ctx) {
		super(ctx);
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
			map.put("Value", value.trim()); // Magic name variable
			map.put("Max", max); // Magic name variable
			map.put("Min", min); // Magic name variable
			getFormula().setArguments(map);
			result = new BigDecimal(getFormula().calculate()); 
		}
		return result;
	}
	
	public static MBSCCoefficent getCoefficentByUnit(Properties ctx  ,String unit, int C_Period_ID) {
		String[] quarterField = {"quarter1","quarter2","quarter3","quarter4"};
		MBSCCoefficent result = null;
		int quarter = getQuarter(ctx,C_Period_ID);
		if (quarter > 0) {
			int AD_Org_ID = Env.getAD_Org_ID(ctx);
			int AD_Client_ID = Env.getAD_Client_ID(ctx);
			
			String sql = "SELECT * FROM BSC_Coefficient \n"
					   + "WHERE AD_Org_ID = ? \n"
					   + "  AND AD_Client_ID = ? \n"
					   + "  AND Unit = ? \n"
					   + "  AND " + quarterField[quarter-1] + " = 'Y'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;		
			try {
				pstmt = DB.prepareStatement(sql,null);
				pstmt.setInt(1, AD_Org_ID);
				pstmt.setInt(2, AD_Client_ID);
				pstmt.setString (3, unit);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new MBSCCoefficent(ctx, rs, null);
				}
			} catch (SQLException e) {
				sLog.log(Level.SEVERE, "getCoefficentByUnit", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}	
		}
		return result;
	}

	/**
	 * @param c_Period_ID
	 * @return
	 */
	private static int getQuarter(Properties ctx, int c_Period_ID) {
		int result = 0;
		MPeriod period = new MPeriod(ctx,c_Period_ID,null);
		if (period != null) {
			Timestamp startDate = period.getStartDate();
			if (startDate != null) {
				int month = startDate.getMonth() + 1;
				result = month / 3 + 1;
			}
		}
		return result;
	}
}
