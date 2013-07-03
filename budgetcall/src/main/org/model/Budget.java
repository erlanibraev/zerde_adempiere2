/**
 * 
 */
package main.org.model;

import java.util.Map;

import org.compiere.util.Language;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author V.Sokolov
 *
 */
public abstract class Budget extends ActionSupport implements ISpecification {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1483376841331475745L;
	
	protected int callID;
	protected int chargeID;
	protected int processID;
	protected int periodID;
	protected int tableID;
	protected int recordID;
	protected String page;
	protected Map<String, Object> session; 
	protected String lang = Language.AD_Language_ru_RU; // default language
	
	public int getCallID() {
		return callID;
	}
	public void setCallID(int callID) {
		this.callID = callID;
		session.put("callID", getCallID());
	}
	public int getChargeID() {
		return chargeID;
	}
	public void setChargeID(int chargeID) {
		this.chargeID = chargeID;
	}
	public int getProcessID() {
		return processID;
	}
	@Override
	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public int getPeriodID() {
		return periodID;
	}
	public void setPeriodID(int periodID) {
		this.periodID = periodID;
	}
	
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
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	/* 
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session; 
	}

}
