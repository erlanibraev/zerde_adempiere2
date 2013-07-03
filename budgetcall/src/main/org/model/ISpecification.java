/**
 * 
 */
package main.org.model;

import org.apache.struts2.interceptor.SessionAware;

/**
 * @author V.Sokolov
 *
 */
public interface ISpecification extends SessionAware {
	
	public String getPage();
	
	public void setPage(String page);
	
	public int getProcessID();
	
	public void setProcessID(int processID);
	
	public int getCallID();
	
	public void setCallID(int callID);
	
	public int getChargeID();
	
	public void setChargeID(int chargeID);

}
