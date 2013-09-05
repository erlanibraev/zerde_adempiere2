/**
 * 
 */
package main.org.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import main.org.model.Budget;
import main.org.model.ChargeCode;
import main.org.model.Utils;

/**
 * @author V.Sokolov
 *
 */
public class BudgetCall extends Budget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048083176947689878L;
	/**	Logger							*/
	protected CLogger			log = CLogger.getCLogger (getClass());
	ChargeCode[] chargeCode;
	

	public ChargeCode[] getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(ChargeCode[] chargeCode) {
		this.chargeCode = chargeCode;
	}

	@Override
	public String execute() throws Exception {
		
		Env.setContext(Env.getCtx(), "#AD_Language", getLang());
		
		setChargeCode(getValues(callID));
		setPage(Utils.TEMPLATE_FIRST);
		
		session.put("budgetcall", this);
		
		if(getCallID() != 0)
			return SUCCESS;
		else
			return NONE;
	}
	
	public ChargeCode[] getValues(int callID){
		
		ChargeCode values = null;
		List<ChargeCode> list = new ArrayList<ChargeCode>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select t.bpm_budgetCall_id, t.c_charge_id, r.name, r.fi_code from bpm_budgetcallLine t \n"+
				"join c_charge r on r.c_charge_id=t.c_charge_id \n"+
				"where t.bpm_budgetCall_id= "+callID+" \n"+
				"group by t.bpm_budgetCall_id, t.c_charge_id, r.name, r.fi_code";
		
		try
		{
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();	
			while (rs.next ())
			{
				values = new ChargeCode();
				values.setCallID(rs.getInt(1));
				values.setChargeID(rs.getInt(2));
				values.setName(rs.getString(3));
				values.setCode(rs.getString(4));
				list.add(values);
			}
			
		}
		catch (SQLException e)
		{
			log.log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		
		return list.toArray(new ChargeCode[list.size()]);
	}
	
	/** Query for BudgetCall.jsp */
	public String getSql(){
		
		return "select round(sum(t.amount),2)::text as amount from bpm_budgetcallLine t \n"+
				"where t.bpm_budgetCall_id=? and t.c_charge_id=? and t.c_period_id=? \n"+
				"group by t.bpm_budgetCall_id, t.c_charge_id";
	}
	
	public double getPeriodAmount(int callID, int chargeID, int periodID, LinkedHashMap<Integer, String> quarter, int keyQuarter){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sql_ = new StringBuilder();
		sql_.append("select round(sum(t.amount),2)::text as amount from bpm_budgetcallLine t \n");
		sql_.append("where t.bpm_budgetCall_id=? and t.c_charge_id=? \n");
		if(!quarter.containsKey(keyQuarter))
			sql_.append("and t.c_period_id=? \n");
		else
			sql_.append("and t.c_period_id in ("+quarter.get(keyQuarter)+") \n");
		sql_.append("group by t.bpm_budgetCall_id, t.c_charge_id");
		
		try
		{
			pstmt = DB.prepareStatement (sql_.toString(), null);
			pstmt.setInt(1, callID);
			pstmt.setInt(2, chargeID);
			if(keyQuarter == 0)
				pstmt.setInt(3, periodID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getDouble(1);
			}
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		
		return 0;
	}
	
	public boolean isQuarter(int month){
		
		return (month % 3 == 0) ? true : false;
	}

}
