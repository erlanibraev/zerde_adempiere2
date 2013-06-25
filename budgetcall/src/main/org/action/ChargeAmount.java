/**
 * 
 */
package main.org.action;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import main.org.model.Amount;
import main.org.model.Budget;
import main.org.model.Utils;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 * @author V.Sokolov
 *
 */
public class ChargeAmount extends Budget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -823450726985416943L;
	/**	Logger							*/
	protected CLogger			log = CLogger.getCLogger (getClass());
	
	protected Amount[] amt;
	private int sQuantity;
	private String sAmount;
	
	
	public Amount[] getAmt() {
		return amt;
	}
	public void setAmt(Amount[] amt) {
		this.amt = amt;
	}

	public int getsQuantity() {
		return sQuantity;
	}
	public void setsQuantity(int sQuantity) {
		this.sQuantity = sQuantity;
	}
	
	public String getsAmount() {
		return sAmount;
	}
	public void setsAmount(String sAmount) {
		this.sAmount = sAmount;
	}
	
	public Amount getLabelValue(int index){
		return amt[index];		
	}
	
	public void setLabelValue(){
		this.amt = getValues(callID, chargeID, periodID);
	}
	
	/* 
	 */
	@Override
	public String execute() throws Exception {
		
		setAmt(getValues(callID, chargeID, periodID));
		setPage(Utils.TEMPLATE_SECOND);
		
		session.put("chargeamount", this); 
	
		if(amt.length > 0)
			return SUCCESS;
		else
			return	NONE;
	}
	
	public Amount[] getValues(int callID, int chargeID, int periodID){
		
		Amount values = null;
		List<Amount> list = new ArrayList<Amount>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String month = "";
		String having = "";

		if(periodID != 0){
			month = " and t.c_period_id="+periodID;
			having = "having sum(t.amount) > 0";
		}
		
		String sql = "select t.ad_table_id, t.record_id, r.name, sum(t.quantity)::text as quantity, round(sum(t.amountUnit),2)::text as amountUnit, round(sum(t.amount),2) as amount \n" +
				"from bpm_budgetCallLine t \n" +
				"join bpm_budget_v r on r.bpm_budgetCall_id=t.bpm_budgetCall_id and r.ad_table_id=t.ad_table_id and r.record_id=t.record_id \n" +
				"where t.bpm_budgetCall_id=? and t.c_charge_id=? " + month+ " \n" +
				"group by t.ad_table_id, t.record_id, r.name \n" + having + " \n" +
				"order by t.ad_table_id, t.record_id";
		
		try
		{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, callID);
			pstmt.setInt(2, chargeID);
			rs = pstmt.executeQuery();	
					
			int sQuantity = 0;
			BigDecimal sAmount = new BigDecimal(0);
			while (rs.next ())
			{
				values = new Amount();
				values.setTableID(rs.getInt("ad_table_id"));
				values.setRecordID(rs.getInt("record_id"));
				values.setName(rs.getString("name"));
				values.setQuantity(rs.getString("quantity"));
				values.setAmountUnit(rs.getString("amountunit"));
				values.setAmount(String.valueOf(rs.getBigDecimal("amount")));
				sQuantity = sQuantity + Integer.parseInt(rs.getString("quantity"));
				sAmount = sAmount.add(rs.getBigDecimal("amount"));
				list.add(values);
			}
			
			setsQuantity(sQuantity);
			setsAmount(String.valueOf(sAmount));
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
		
		return list.toArray(new Amount[list.size()]);
	}

}
