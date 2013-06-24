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
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import main.org.model.Budget;
import main.org.model.Period;
import main.org.model.Utils;

/**
 * @author V.Sokolov
 *
 */
public class PeriodAmount extends Budget implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8588479221768360808L;
	/**	Logger							*/
	protected CLogger			log = CLogger.getCLogger (getClass());
	
	private String name;
	private Period[] period;
	private Period[] periodBean;
	private int periodID;
	private int sQuantity;
	private String sAmount;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Period[] getPeriod() {
		return period;
	}
	public void setPeriod(Period[] period) {
		this.period = period;
	}
	
	public int getPeriodID() {
		return periodID;
	}
	public void setPeriodID(int periodID) {
		this.periodID = periodID;
	}
	
	public Period[] getPeriodBean() {
		return periodBean;
	}
	public void setPeriodBean(Period[] periodBean) {
		if(periodBean == null)
			this.periodBean = getValues(callID, tableID, recordID);
		else
			this.periodBean = periodBean;
	}

	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest(){
		return request;
	}

	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}
	
	/* 
	 */
	@Override
	public String execute() throws Exception {
		
		setPeriod(getValues(callID, tableID, recordID));
		setPage(Utils.TEMPLATE_THREE);
		getClass().getDeclaredFields();
		session.put("periodamount", this);
		
		return SUCCESS;
	}
	
	/*
	 */
	@Override
	public String input() throws Exception {
		
		Period[] pp = getValues(callID, tableID, recordID);
		String upd = "update bpm_budgetCallLine ";
		String set = "";
		String where = " where bpm_budgetCall_id="+callID
				+"\n and c_charge_ID="+chargeID
				+"\n and ad_table_ID="+tableID
				+"\n and record_ID="+recordID;
		String periodSql = "";
		
		int idx = 1;
		int quentity = 0;
		double amountUnit = 0.;
		double amount = 0.;
		for(Period p: pp){

			periodSql = "\n and c_period_ID="+Integer.valueOf(getServletRequest().getParameter("period_IDX_"+idx));
			set = " set ";
			quentity = Integer.valueOf(getServletRequest().getParameter("quantity_IDX_"+idx));
			set += "\n quantity="+quentity;
			amountUnit = Double.valueOf(getServletRequest().getParameter("amountUnit_IDX_"+idx));
			set += "\n ,amountUnit="+amountUnit;
			amount = Double.valueOf(getServletRequest().getParameter("amount_IDX_"+idx));
			set += "\n ,amount="+amount;
			
			DB.executeUpdate(upd+set+where+periodSql, null);
			periodSql = "";
			idx++;
		}
		
		return INPUT;
	}
	
	public Period[] getValues(int callID, int tableID, int recordID){
		
		Period values = null;
		List<Period> list = new ArrayList<Period>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select bv.name, u.name as uom, p.c_period_id as periodID, p.name as mth, t.quantity, round(t.amountUnit,2) as amountUnit, round(t.amount,2) as amount, t.paymentMonth as payment, bv.description \n" +
				"from bpm_budgetcallLine t \n" +
				"join c_uom u on u.c_uom_id=t.c_uom_id \n" +
				"join c_period p on p.c_period_id=t.c_period_id \n" +
				"join bpm_budget_v bv on bv.bpm_budgetCall_id=t.bpm_budgetCall_id and bv.ad_table_id=t.ad_table_id and bv.record_id=t.record_id \n" +
				"where t.bpm_budgetCall_id=? and t.ad_table_id=? and t.record_id=? \n" +
				"order by t.C_period_id";
		
		try
		{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, callID);
			pstmt.setInt(2, tableID);
			pstmt.setInt(3, recordID);
			rs = pstmt.executeQuery();	
					
			int sQuantity = 0;
			BigDecimal sAmount = new BigDecimal(0);
			while (rs.next ())
			{
				values = new Period();
				values.setName(rs.getString("name"));
				values.setUom(rs.getString("uom"));
				values.setPeriodID(rs.getInt("periodid"));
				values.setMonth(rs.getString("mth"));
				values.setQuantity(rs.getString("quantity"));
				values.setAmountUnit(rs.getString("amountunit"));
				values.setAmount(String.valueOf(rs.getBigDecimal("amount")));
				values.setPayment(rs.getString("payment"));
				values.setDescription(rs.getString("description"));
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
		
		return list.toArray(new Period[list.size()]);
	}

	
}
