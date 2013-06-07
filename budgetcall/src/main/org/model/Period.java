/**
 * 
 */
package main.org.model;

/**
 * @author V.Sokolov
 *
 */
public class Period extends Amount{
	
	private String uom;
	private String month;
	private String description;
	

	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
