package org.compiere.dsr;

import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormValues;
import org.compiere.util.Env;

public class DSR_DataCollection
{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4320055593999100229L;
	
	private ArrayList<DSR_Row> elementData = new ArrayList<DSR_Row>();
	
	private MBPMForm form;
	
	public DSR_DataCollection(int BPM_Form_ID) 
	{
		form = new MBPMForm(Env.getCtx(), BPM_Form_ID, null);
		getValues();
	}
	
	private void getValues()
	{		
		try 
		{
			Integer[] rows = MBPMFormValues.getRows(Env.getCtx(), form.get_ID(), null);
			
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
	
	public DSR_Row getRow(int index)
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
}
