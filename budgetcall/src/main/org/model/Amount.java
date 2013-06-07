/**
 * 
 */
package main.org.model;

import org.compiere.model.MRefList;
import org.compiere.model.X_BPM_BudgetCallLine;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class Amount {

	private int tableID;
	private int recordID;
	private String name;
	private String quantity;
	private String amountUnit;
	private String amount;
	private String payment;
	
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = Msg.translate(Env.getCtx(), MRefList.getListName(Env.getCtx(), X_BPM_BudgetCallLine.PAYMENTMONTH_AD_Reference_ID, payment));
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getAmountUnit() {
		return amountUnit;
	}
	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
