package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.apps.DialogAgreement;
import org.compiere.dsr.DSR_DataCollection;

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
		DialogAgreement.dialogOK("Result", collection.toString(), 0);
		System.out.println(collection);
		
		return null;
	}

}
