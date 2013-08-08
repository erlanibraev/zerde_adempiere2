package org.compiere.dsr;

import java.util.ArrayList;

import org.compiere.model.MBPMFormColumn;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MBPMFormValues;
import org.compiere.util.Env;

public class DSR_Row 
{
	private ArrayList<DSR_Cell> elementData = new ArrayList<DSR_Cell>();
	
	public DSR_Row(int BPM_FormLine_ID)
	{
		getCells(BPM_FormLine_ID);
	}
	
	private void getCells(int BPM_FormLine_ID)
	{
		MBPMFormValues[] lines = MBPMFormValues.getValues(Env.getCtx(), BPM_FormLine_ID, null);
		
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
		if(index > 0 && index < elementData.size())
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
			builder.append(elementData.get(i));
			builder.append("|");
			builder.append("\t\t");
		}
		
		return builder.toString();
	}
}
