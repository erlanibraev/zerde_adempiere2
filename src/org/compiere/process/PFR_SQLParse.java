package org.compiere.process;

import java.awt.Dialog;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.logging.Level;

import org.compiere.apps.ADialog;
import org.compiere.model.MPFRCalculation;
import org.compiere.util.Msg;

public class PFR_SQLParse extends SvrProcess 
{
	private MPFRCalculation calc;
	
	@Override
	protected void prepare() 
	{
		int PFR_Calculation_ID = 0;
		ProcessInfoParameter[] para = getParameter();
		
		for(int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();			
			if (para[i].getParameter() == null);
			else if (name.equals("PFR_Calculation_ID"))
				PFR_Calculation_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
			{
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}				
		}
		
		calc = new MPFRCalculation(getCtx(), PFR_Calculation_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put("C_Charge_ID", "1000087");
		Object obj = MPFRCalculation.getValueFromSQL(calc.get_ID(), parameters);
		
		return "Return Value = " + String.valueOf(obj);
	}

}
