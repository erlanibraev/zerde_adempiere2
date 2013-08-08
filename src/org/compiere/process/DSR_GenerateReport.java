package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.dsr.DSR_Cell;
import org.compiere.dsr.DSR_DataCollection;
import org.compiere.dsr.DSR_Row;

public class DSR_GenerateReport extends SvrProcess 
{
	private int BPM_Form_ID = 0;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for(int i=0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if(para[i].getParameter() == null );
			else if(name.equals("BPM_Form_ID")) 
			{
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				BPM_Form_ID = tmp.intValue();
				
			} 
			else 
			{
				log.log(Level.SEVERE,"BPM_Form: Unknown parameter - "+name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception 
	{
		DSR_DataCollection collection = new DSR_DataCollection(BPM_Form_ID);
		
		int size = collection.size();
		int cellSize = 0;
		
		StringBuilder builder = new StringBuilder();
		
		DSR_Row header = collection.getHeader();
		
		builder.append(header);
		builder.append("\n");
		
		for(int i = 0; i < size; i++)
		{			
			DSR_Row row = collection.getRow(i);
			cellSize = row.size();
			
			for(int j = 0; j < cellSize; j++)
			{
				DSR_Cell cell = row.getCell(j);
				
				String formatValue = String.format("%-20s", cell.getValue());
				
				builder.append(formatValue);
			}
			
			builder.append("\n");
		}
		
		System.out.println(builder);
		
		return null;
	}

}
