package org.compiere.process;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.dsr.DSR_DataCollection;
import org.compiere.dsr.DSR_ExcelImport;
import org.compiere.model.I_BPM_Form;
import org.compiere.model.I_BPM_Project;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MBPMProject;
import org.compiere.util.Msg;
import org.joda.time.DateTime;

import extend.org.compiere.utils.Util;

public class DSR_GenerateReport extends SvrProcess 
{
	/** Current context			*/
	private Properties m_ctx;
	/** */
	private int BPM_Form_ID = 0;
	private int BPM_Project_ID = MBPMProject.TempProjectID;  
	
	@Override
	protected void prepare() 
	{
		m_ctx = getCtx();
		
		ProcessInfoParameter[] para = getParameter();
		for(int i=0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if(para[i].getParameter() == null );
			else if(name.equals(I_BPM_Form.COLUMNNAME_BPM_Form_ID)) 
			{
				BigDecimal tmp = new BigDecimal(Integer.parseInt(para[i].getParameter().toString()));
				BPM_Form_ID = tmp.intValue();
				
			} 
			else if (name.compareTo(I_BPM_Project.COLUMNNAME_BPM_Project_ID) == 0 && para[i].getParameter() != null)
				BPM_Project_ID = new BigDecimal(Integer.parseInt(para[i].getParameter().toString())).intValue();
			else 
			{
				log.log(Level.SEVERE,"BPM_Form: Unknown parameter - "+name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception 
	{
		DSR_DataCollection collection = new DSR_DataCollection(BPM_Form_ID, BPM_Project_ID);
		String path = Util.localFilePath + "pattern" + new DateTime() + ".xls";
		MAttachmentEntry entry = Util.getAttachment(getProcessInfo(), getCtx(), path);
		DSR_ExcelImport.collectionImport(entry, collection);
		
		return Msg.translate(m_ctx, "Success");
	}

}
