/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.MAGRStage;
import org.compiere.model.MBPMProject;
import org.compiere.model.MTable;
import org.compiere.model.X_AGR_Stage;
import org.compiere.model.X_BPM_Project;
import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class BPMActionsVversions extends SvrProcess {
	
	/** Current context		*/
	private Properties m_ctx;
	/**	Optional Transaction 	*/
	private String	m_trxName = null;
	/** */
	private String TableName = "";
	/** */
	private int RecordID = 0;
	/** */
	private int AGR_Stage_ID = 0;
	/** */
	private MBPMProject project = null;
	/** */
	private MAGRStage stage = null;

	/* 
	 */
	@Override
	protected void prepare() {
		
		log.info(" Actions with versions (BPM) ");
		m_ctx = getCtx();
		m_trxName = get_TrxName();
		TableName = (MTable.getTableName(m_ctx, getTable_ID()) == null) ? "": MTable.getTableName(m_ctx, getTable_ID());
		RecordID = getRecord_ID();
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)	{
			String name = para[i].getParameterName();
			if (name == null); 
				//
			else if (name.compareTo(X_AGR_Stage.COLUMNNAME_AGR_Stage_ID) == 0 && para[i].getParameter() != null)
				AGR_Stage_ID = new BigDecimal(Integer.parseInt(para[i].getParameter().toString())).intValue();
			else
			{
				log.log(Level.INFO, "Unknown Parameter: " + name);
			}
		}
		
		if(TableName.equals(X_BPM_Project.Table_Name))
			project = new MBPMProject(m_ctx, RecordID, m_trxName);
		
		if(AGR_Stage_ID != 0)
			stage = new MAGRStage(m_ctx, AGR_Stage_ID, m_trxName);

	}

	/* 
	 */
	@Override
	protected String doIt() throws Exception {
		
		if(project != null){
			
			//////
			
		}
		
		return Msg.translate(m_ctx, "Success");
	}
	
}
