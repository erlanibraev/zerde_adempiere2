package org.compiere.dsr;

public class DSR_Cell 
{
	private String columnName = "";
	private String rowName = "";
	private Object value = null;
	
	public DSR_Cell(String columnName, String rowName, Object value)
	{
		this.columnName = columnName;
		this.rowName = rowName;
		this.value = value;
	}

	public String getColumnName() 
	{
		return columnName;
	}

	public String getRowName() 
	{
		return rowName;
	}

	public Object getValue() 
	{
		return value;
	}
}
