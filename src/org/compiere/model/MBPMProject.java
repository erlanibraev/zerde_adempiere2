/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

import extend.org.compiere.utils.Util;

/**
 * @author V.Sokolov
 *
 */
public class MBPMProject extends X_BPM_Project{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7041996758972564192L;
	/** */
	public static final int TempProjectID = 1000;  // TODO The temp project to preliminary calculations

	/**
	 * @param ctx
	 */
	public MBPMProject(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Project_ID
	 * @param trxName
	 */
	public MBPMProject(Properties ctx, int BPM_Project_ID, String trxName) {
		super(ctx, BPM_Project_ID, trxName);
	}
	
	/* 
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		String errmsg = "";
		if(getAGR_Dispatcher_ID() == 0) errmsg = Util.requiredField(COLUMNNAME_AGR_Dispatcher_ID)+"\n";
		Util.checkErrMsg(errmsg);
		
		if(newRecord && isWork()){
			MBPMProject[] pr = getProjectVersion(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
			if(pr.length != 0 && getBPM_Parent_ID() == 0)
				errmsg = Util.requiredField(COLUMNNAME_BPM_Parent_ID)+"\n";
			Util.checkErrMsg(errmsg);		
		}
		
		return true;
	}
	
	/* 
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		if(newRecord && success){
			
			MBPMForm[] formLine = MBPMFormLine.getLineForm(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
			if(formLine.length == 0)
				throw new  AdempiereException("Отсутствуют настройки по формам");
			
			for(MBPMForm f: formLine){
				MBPMProjectLine pLine = new MBPMProjectLine(getCtx(), 0, get_TrxName());
				pLine.setBPM_Form_ID(f.getBPM_Form_ID());
				pLine.setBPM_Project_ID(getBPM_Project_ID());
				pLine.saveEx();
			}
		}
		
		if(newRecord){
			copyBudgetCall(getBPM_Parent_ID());
		}
		
		return true;
	}
	
	/* 
	 */
	@Override
	protected boolean afterDelete(boolean success) {
	
		// Delete records Budget Call
		if(success && getBPM_Parent_ID() != 0){
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ");
			sql.append(I_BPM_BudgetCall.Table_Name);
			sql.append(" WHERE \n");
			sql.append(I_BPM_BudgetCall.COLUMNNAME_BPM_Project_ID).append("=").append(getBPM_Project_ID());
			
			DB.executeUpdate(sql.toString(), get_TrxName());
		}
		
		return true;
	}
	
	private void copyBudgetCall(int BPM_Parent_ID){
		
		MBPMBudgetCall[] call = MBPMBudgetCall.getBudgetCallProject(BPM_Parent_ID); 
		for(MBPMBudgetCall c: call){
			MBPMBudgetCall newCall = new MBPMBudgetCall(getCtx(), 0, get_TrxName());
			PO.copyValues(c, newCall);
			newCall.setBPM_Parent_ID(BPM_Parent_ID);
			newCall.setBPM_Project_ID(getBPM_Project_ID());
			newCall.setBPM_VersionBudgetLine_ID(MBPMVersionBudget.getCurrentVersion());
			newCall.setProcessed(false);
			newCall.setIsActive(true);
			newCall.save();
			
			MBPMBudgetCallLine[] callLine = MBPMBudgetCall.getLines(getCtx(), c.getBPM_BudgetCall_ID(), get_TrxName());
			for(MBPMBudgetCallLine cl: callLine){
				MBPMBudgetCallLine newCallLine = new MBPMBudgetCallLine(getCtx(), 0, get_TrxName());
				PO.copyValues(cl, newCallLine);
				newCallLine.setBPM_BudgetCall_ID(newCall.getBPM_BudgetCall_ID());
				newCallLine.setProcessed(false);
				newCallLine.setIsActive(true);
				newCallLine.saveEx();
			}
		}
		
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMProject(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MBPMProjectLine[] getLines(Properties ctx, int BPM_Project_ID, String trxName){
		
		List<MBPMProjectLine> list = new Query(ctx, I_BPM_ProjectLine.Table_Name, I_BPM_ProjectLine.COLUMNNAME_BPM_Project_ID+"=?", trxName)
		.setParameters(BPM_Project_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMProjectLine[] retValue = new MBPMProjectLine[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	public static boolean isLineForm(Properties ctx, int BPM_Project_ID, int BPM_Form_ID, String trxName){
		
		List<MBPMProjectLine> line = new Query(ctx, I_BPM_ProjectLine.Table_Name, I_BPM_Project.COLUMNNAME_BPM_Project_ID+"=? AND "
																				 +I_BPM_ProjectLine.COLUMNNAME_BPM_Form_ID+"=?", trxName)
		.setParameters(BPM_Project_ID, BPM_Form_ID).list();
		
		return (line.size() != 0);
	}
	
	public static MBPMProject[] getProjectVersion(Properties ctx, int BPM_VersionBudget_ID, String trxName){
		
		List<MBPMProject> list = new Query(ctx, I_BPM_Project.Table_Name, I_BPM_Project.COLUMNNAME_BPM_VersionBudget_ID+"=? AND "
																		  +I_BPM_Project.COLUMNNAME_isActual+"=?", trxName)
		.setParameters(BPM_VersionBudget_ID, "Y")
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMProject[] retValue = new MBPMProject[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}

}
