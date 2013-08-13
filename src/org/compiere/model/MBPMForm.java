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
public class MBPMForm extends X_BPM_Form {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5236262513637596250L;

	/**
	 * @param ctx
	 */
	public MBPMForm(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Form_ID
	 * @param trxName
	 */
	public MBPMForm(Properties ctx, int BPM_Form_ID, String trxName) {
		super(ctx, BPM_Form_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMForm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MBPMFormLine[] getLines(Properties ctx, int BPM_Form_ID, String trxName){
		
		List<MBPMFormLine> list = new Query(ctx, I_BPM_FormLine.Table_Name, "BPM_Form_ID=?", trxName)
		.setParameters(BPM_Form_ID).setOrderBy(I_BPM_FormLine.COLUMNNAME_LineNo)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMFormLine[] retValue = new MBPMFormLine[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}



}
