package org.compiere.dsr;

/**
 * @author E.Maimanov
 * 
 * This class generates 2-dimension array-report from BPM_Form Settings
 */

import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormColumn;
import org.compiere.model.MBPMFormValues;
import org.compiere.util.Env;

public class DSR_DataCollection
{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4320055593999100229L;
	
	private ArrayList<DSR_Row> elementData = new ArrayList<DSR_Row>();
	private DSR_Row header;
	private MBPMForm form;
	private int BMP_Project_ID;
	
	public DSR_DataCollection(int BPM_Form_ID, int BPM_Project_ID) 
	{
		form = new MBPMForm(Env.getCtx(), BPM_Form_ID, null);
		this.BMP_Project_ID = BPM_Project_ID;
		setHeader();
		getValues();
	}
	
	public DSR_Row getHeader()
	{
		return header;
	}
	
	public DSR_Row getRow(int index)
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
		int size = this.size();
		int cellSize = 0;
		
		StringBuilder builder = new StringBuilder();
		
		DSR_Row header = this.getHeader();
		
		builder.append(header);
		builder.append("\n");
		
		for(int i = 0; i < size; i++)
		{			
			DSR_Row row = this.getRow(i);
			cellSize = row.size();
			
			for(int j = 0; j < cellSize; j++)
			{
				DSR_Cell cell = row.getCell(j);
				
				String formatValue = String.format("%-20s", cell.getValue());
				
				builder.append(formatValue);
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	/**
	 * Get header of current report
	 * Header is a set of column's name
	 * First row in elementData (index = 0)
	 */
	private void setHeader()
	{
		MBPMFormColumn[] columns = MBPMFormColumn.getLineFormCode(Env.getCtx(), form.getBPM_FormCode_ID(), null);
		
		header = new DSR_Row();
		
		DSR_Cell nullCell = new DSR_Cell("", "", null);
		nullCell.setHeader(true);
		header.add(nullCell);
		
		for(MBPMFormColumn column : columns)
		{
			DSR_Cell cell = new DSR_Cell(column.getName(), "", null);
			cell.setHeader(true);
			
			header.add(cell);
		}
	}
	
	/**
	 * Get values for current report
	 * and store them in elementData ArrayList<DSR_Row>()
	 */
	private void getValues()
	{		
		try 
		{
			Integer[] rows = MBPMFormValues.getRows(Env.getCtx(), form.get_ID(), BMP_Project_ID, null);
			
			for(int row : rows)
			{
				DSR_Row dsrRow = new DSR_Row(row);
				
				elementData.add(dsrRow);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
