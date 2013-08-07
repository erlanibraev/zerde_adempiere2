/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.compiere.apps.DialogAgreement;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class MBPMFormLine extends X_BPM_FormLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8467103165656931102L;

	/**
	 * @param ctx
	 */
	public MBPMFormLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormLine_ID
	 * @param trxName
	 */
	public MBPMFormLine(Properties ctx, int BPM_FormLine_ID, String trxName) {
		super(ctx, BPM_FormLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	MBPMForm form = null;
	MBPMFormColumn[] listColumn = null;
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		form = new MBPMForm(getCtx(), getBPM_Form_ID(), get_TrxName());
		listColumn = MBPMFormColumn.getLineFormCode(getCtx(), form.getBPM_FormCode_ID(), get_TrxName());
		
		if(listColumn.length == 0){
			DialogAgreement.dialogOK(Msg.translate(Env.getCtx(), "Error"), "Отсутствуют колонки для [ "+form.getBPM_FormCode().getName()+" ]", 0);
			return false;
		}
			
		return true;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		for(MBPMFormColumn column: listColumn){
			
			MBPMFormCell cell = new MBPMFormCell(getCtx(), 0, get_TrxName());
			cell.setBPM_FormColumn_ID(column.getBPM_FormColumn_ID());
			cell.setBPM_FormLine_ID(getBPM_FormLine_ID());
			cell.setOrderColumn(column.getOrderColumn());
			cell.saveEx();
			
		}
		
		return true;
	}
	
	public static MBPMFormCell[] getLines(Properties ctx, int BPM_FormLine_ID, String trxName){
		
		List<MBPMFormCell> list = new Query(ctx, I_BPM_FormCell.Table_Name, "BPM_FormLine_ID=?", trxName)
		.setParameters(BPM_FormLine_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMFormCell[] retValue = new MBPMFormCell[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}
	
}
