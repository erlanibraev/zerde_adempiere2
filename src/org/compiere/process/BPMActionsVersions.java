/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.MBPMBudgetCall;
import org.compiere.model.MBPMBudgetCallLine;
import org.compiere.model.MBPMProject;
import org.compiere.model.MRefList;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AGR_AgreementList;
import org.compiere.model.X_BPM_BudgetCall;
import org.compiere.model.X_BPM_Project;
import org.compiere.model.X_BPM_VersionBudgetLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
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
		}else{
			verLine.setBPM_VersionType(X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_NOTAPPROVED);
			verLine.setBPM_Version("V-"+sysDate.getDayOfMonth()+"."+sysDate.getMonthOfYear()+"."+sysDate.getYear()+" -> "+ MRefList.getListName(m_ctx, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_AD_Reference_ID, X_BPM_VersionBudgetLine.BPM_VERSIONTYPE_NOTAPPROVED));
		}
		verLine.saveEx();
		
		MBPMBudgetCall[] call = getBudgetCallProject(project.getBPM_Project_ID());
		for(MBPMBudgetCall c: call){
			
			if(isApproved){
				MBPMBudgetCall newCall = new MBPMBudgetCall(m_ctx, 0, m_trxName);
				PO.copyValues(c, newCall);
				newCall.setBPM_Project_ID(MBPMProject.TempProjectID);
				newCall.saveEx();
			}
			
			c.setBPM_VersionBudgetLine_ID(verLine.getBPM_VersionBudgetLine_ID());
			c.setProcessed(true);
			c.saveEx();
			
			MBPMBudgetCallLine[] callLine = MBPMBudgetCall.getLines(m_ctx, c.getBPM_BudgetCall_ID(), m_trxName);
			for(MBPMBudgetCallLine cl: callLine){
				
				if(isApproved){
					MBPMBudgetCallLine newCallLine = new MBPMBudgetCallLine(m_ctx, 0, m_trxName);
					PO.copyValues(cl, newCallLine);
					newCallLine.saveEx();
				}
				
				cl.setIsActive(false);
				cl.setProcessed(true);
				cl.saveEx();
			}
		}
		
		project.setProcessed(true);
		project.setIsActive(false);
		project.saveEx();
	}
	
	private MBPMBudgetCall[] getBudgetCallProject(int BPM_Project_ID){
		
		//
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		MBPMBudgetCall result = null;
		
		ArrayList<MBPMBudgetCall> list = new ArrayList<MBPMBudgetCall>();
		
		// 
		String sql_ = "SELECT * FROM "+X_BPM_BudgetCall.Table_Name+" \n " +
				" WHERE "+X_BPM_BudgetCall.COLUMNNAME_BPM_Project_ID+"="+BPM_Project_ID;
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				result = new MBPMBudgetCall(Env.getCtx(), rs, null);
				list.add(result);
			}				
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		
		return list.toArray(new MBPMBudgetCall[list.size()]);
		
	}
	
}
