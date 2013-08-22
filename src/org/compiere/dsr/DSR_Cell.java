package org.compiere.dsr;

public class DSR_Cell 
{
	private String columnName = "";
	private String rowName = "";
	private Object value = null;
	
	public boolean isHeader = false;
	public boolean isRow = false;
	
	public int LevelIndex = 0;
	
	public DSR_Cell(String columnName, String rowName, Object value)
	{
		this.columnName = columnName;
		this.rowName = rowName;
		this.value = value;
	}
	
	private String getColumnName() 
	{
		return columnName;
	}

	private String getRowName() 
	{
		return rowName;
	}
	
	public Object getValue() 
	{
		if(isHeader)
			return getColumnName();
		else if(isRow)
			return getRowName();
		else
			return value;
	}
	
	public String toString()
	{
		return getValue().toString();
	}

	public void setHeader(boolean isHeader) 
	{
		this.isHeader = isHeader;
	}

	public void setRow(boolean isRow) 
	{
		this.isRow = isRow;
	}
}
