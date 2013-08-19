package org.compiere.dsr;

import java.util.ArrayList;

import org.compiere.model.MBPMFormColumn;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MBPMFormValues;
import org.compiere.util.Env;

public class DSR_Row 
{
	private ArrayList<DSR_Cell> elementData = new ArrayList<DSR_Cell>();
	private int BMP_Project_ID;
	private int BPM_FormLine_ID;
	
	public DSR_Row(int BPM_FormLine_ID, int BMP_Project_ID)
	{
		this.BMP_Project_ID = BMP_Project_ID;
		this.BPM_FormLine_ID = BPM_FormLine_ID;
		getCells();		
	}
	
	public DSR_Row()
	{
	}
	
	public void add(DSR_Cell cell)
	{
		if(cell != null)
			elementData.add(cell);
	}
	
	private void getCells()
	{
		MBPMFormValues[] lines = MBPMFormValues.getValuesOrdered(Env.getCtx(), BPM_FormLine_ID, BMP_Project_ID, null);
		
		MBPMFormLine rowLine = new MBPMFormLine(Env.getCtx(), BPM_FormLine_ID, null);
		
		//The row's name
		DSR_Cell lineCell = new DSR_Cell("", rowLine.getName(), null);
		lineCell.setRow(true);
		lineCell.Level_Type = rowLine.getLevelType();
		elementData.add(lineCell);
		
		for(MBPMFormValues line : lines)
		{
			MBPMFormLine formLine = new MBPMFormLine(Env.getCtx(), line.getBPM_FormLine_ID(), null);
			MBPMFormColumn formColumn = new MBPMFormColumn(Env.getCtx(), line.getBPM_FormColumn_ID(), null);
			
			Object cellValue = line.getAlternateValue() == null || line.getAlternateValue().toString().isEmpty() ? line.getCellValue() : line.getAlternateValue();
			
			DSR_Cell cell = new DSR_Cell(formColumn.getName(), formLine.getName(), cellValue);
			elementData.add(cell);
		}
	}
	
	public DSR_Cell getCell(int index)
	{
		if(index >= 0 && index < elementData.size())
			return elementData.get(index);
		else 
			return null;
	}
	
	public int size()
	{
		return elementData.size();
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < elementData.size(); i++)
		{
			String value = String.format("%-20s", elementData.get(i));
			builder.append(value);
		}
		
		return builder.toString();
	}
}
