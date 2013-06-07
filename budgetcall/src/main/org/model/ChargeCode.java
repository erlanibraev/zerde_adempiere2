/**
 * 
 */
package main.org.model;

/**
 * @author V.Sokolov
 *
 */
public class ChargeCode {
	
	private String name;
	private int callID;
	private int chargeID;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCallID() {
		return callID;
	}
	public void setCallID(int callID) {
		this.callID = callID;
	}
	public int getChargeID() {
		return chargeID;
	}
	public void setChargeID(int chargeID) {
		this.chargeID = chargeID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
