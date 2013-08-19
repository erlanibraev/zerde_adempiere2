/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

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
	
	private MBPMProject currentProject = null;
	
	/* 
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		currentProject = getCurrentProject(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
		
		return true;
	}
	
	/* 
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		if(newRecord && success){
			
			MBPMForm[] formLine = getLineForm(getCtx(), getBPM_VersionBudget_ID(), get_TrxName());
			for(MBPMForm f: formLine){
				MBPMProjectLine pLine = new MBPMProjectLine(getCtx(), 0, get_TrxName());
				pLine.setBPM_Form_ID(f.getBPM_Form_ID());
				pLine.setBPM_Project_ID(getBPM_Project_ID());
				pLine.saveEx();
			}
			
			if(currentProject != null){
				currentProject.setProcessed(true);
				currentProject.setIsActive(false);
				currentProject.saveEx();
				
				MBPMProjectLine[] pLine = MBPMProject.getLines(getCtx(), currentProject.getBPM_Project_ID(), get_TrxName());
				for(MBPMProjectLine p : pLine){
					p.setProcessed(true);
					p.setIsActive(false);
					p.saveEx();
				}
			}
		}
		
		return true;
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
		
		List<MBPMProjectLine> list = new Query(ctx, I_BPM_ProjectLine.Table_Name, "BPM_Project_ID=?", trxName)
		.setParameters(BPM_Project_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMProjectLine[] retValue = new MBPMProjectLine[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	private MBPMForm[] getLineForm(Properties ctx, int BPM_VersionBudget_ID, String trxName){
		
		List<MBPMForm> list = new Query(ctx, I_BPM_Form.Table_Name, "BPM_VersionBudget_ID=?", trxName)
		.setParameters(BPM_VersionBudget_ID).setOnlyActiveRecords(true)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMForm[] retValue = new MBPMForm[list.size ()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	private MBPMProject getCurrentProject(Properties ctx, int BPM_VersionBudget_ID, String trxName){
		
		MBPMProject project = null;
		
		project = new Query(ctx, I_BPM_Project.Table_Name, "BPM_VersionBudget_ID=?", trxName)
		.setParameters(BPM_VersionBudget_ID).setOnlyActiveRecords(true)
		.setOnlyActiveRecords(true)
		.first();
		
		return project;
	}
}
