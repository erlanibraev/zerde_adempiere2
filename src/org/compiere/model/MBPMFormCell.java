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
public class MBPMFormCell extends X_BPM_FormCell {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -899362471081511560L;

	/**
	 * @param ctx
	 */
	public MBPMFormCell(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormCell_ID
	 * @param trxName
	 */
	public MBPMFormCell(Properties ctx, int BPM_FormCell_ID, String trxName) {
		super(ctx, BPM_FormCell_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormCell(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/* 
	 */
	@Override
	protected boolean beforeDelete() {
		// You can not delete records
		return false;
	}
	
	public static MBPMFormCell[] getLinesParameter(Properties ctx, int BSC_Parameter_ID, String trxName){
		
		List<MBPMFormCell> list = new Query(ctx, I_BPM_FormCell.Table_Name, "BSC_Parameter_ID=?", trxName)
		.setParameters(BSC_Parameter_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMFormCell[] retValue = new MBPMFormCell[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}


}
