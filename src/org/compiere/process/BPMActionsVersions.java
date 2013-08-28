/**
 * 
 */
package org.compiere.process;

import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.MBPMBudgetCall;
import org.compiere.model.MBPMBudgetCallLine;
import org.compiere.model.MBPMProject;
import org.compiere.model.MRefList;
import org.compiere.model.MTable;
import org.compiere.model.X_AGR_AgreementList;
import org.compiere.model.X_BPM_Project;
import org.compiere.model.X_BPM_VersionBudgetLine;
import org.compiere.util.Msg;
import org.joda.time.DateTime;

/**
 * @author V.Sokolov
 *
 */
public class BPMActionsVersions extends SvrProcess {
	
	/** Current context		*/
	protected Properties m_ctx;
	/**	Optional Transaction 	*/
	protected String	m_trxName = null;
	/** */
	protected String TableName = "";
	/** */
	protected int RecordID = 0;
	/** */
	protected MBPMProject project = null;
	/** */
	protected boolean IsApproved = false;

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
			else if (name.compareTo(X_AGR_AgreementList.COLUMNNAME_IsApproved) == 0 && para[i].getParameter() != null)
				IsApproved = Boolean.parseBoolean(para[i].getParameter().toString());
			else
			{
				log.log(Level.INFO, "Unknown Parameter: " + name);
			}
		}
		
		if(TableName.equals(X_BPM_Project.Table_Name))
			project = new MBPMProject(m_ctx, RecordID, m_trxName);
		
	}

	/* 
	 */
	@Override
	protected String doIt() throws Exception {
		
		if(project != null)
			actionProject(project, IsApproved);
		
		return Msg.translate(m_ctx, "Success");
	}
	
	protected void actionProject(MBPMProject project, boolean isApproved){

		DateTime sysDate = new DateTime();
		X_BPM_VersionBudgetLine verLine = new X_BPM_VersionBudgetLine(getCtx(),0,get_TrxName());
		verLine.setBPM_VersionBudget_ID(project.getBPM_VersionBudget_ID());

		if(isApproved){
			verLine.setBPM_VersionType(X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_Approved);
			verLine.setBPM_Version("V-"+sysDate.getDayOfMonth()+"."+sysDate.getMonthOfYear()+"."+sysDate.getYear()+" -> "+ MRefList.getListName(m_ctx, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_AD_Reference_ID, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_Approved));
			
			setIsActualProject(project.getBPM_VersionBudget_ID(), false);
			project.setisActual(true);
		}else{
			verLine.setBPM_VersionType(X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_NOTAPPROVED);
			verLine.setBPM_Version("V-"+sysDate.getDayOfMonth()+"."+sysDate.getMonthOfYear()+"."+sysDate.getYear()+" -> "+ MRefList.getListName(m_ctx, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_AD_Reference_ID, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_NOTAPPROVED));
			
			project.setisWork(false);
		}
		verLine.saveEx();
		
		MBPMBudgetCall[] call = MBPMBudgetCall.getBudgetCallProject(project.getBPM_Project_ID());
		for(MBPMBudgetCall c: call){
			c.setBPM_VersionBudgetLine_ID(verLine.getBPM_VersionBudgetLine_ID());
			c.setProcessed(true);
			c.saveEx();
			
			MBPMBudgetCallLine[] callLine = MBPMBudgetCall.getLines(m_ctx, c.getBPM_BudgetCall_ID(), m_trxName);
			for(MBPMBudgetCallLine cl: callLine){
				cl.setProcessed(true);
				cl.saveEx();
			}
		}
		
		MBPMProject[] pr = MBPMProject.getProjectVersion(m_ctx, project.getBPM_VersionBudget_ID(), m_trxName);
		for(MBPMProject p: pr){
			p.setisActual(false);
			p.saveEx();
		}
		
		project.setProcessed(true);
		project.saveEx();
	}
	
	private void setIsActualProject(int BPM_VersionBudget_ID, boolean actual){
		
		MBPMProject[] pr = MBPMProject.getProjectVersion(m_ctx, BPM_VersionBudget_ID, m_trxName);
		for(MBPMProject p: pr){
			p.setisActual(actual);
			p.saveEx();
		}
	}
	
}
