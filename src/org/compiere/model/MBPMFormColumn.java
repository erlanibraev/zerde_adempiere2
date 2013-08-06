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
public class MBPMFormColumn extends X_BPM_FormColumn {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3589392710095524154L;

	/**
	 * @param ctx
	 */
	public MBPMFormColumn(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormColumn_ID
	 * @param trxName
	 */
	public MBPMFormColumn(Properties ctx, int BPM_FormColumn_ID, String trxName) {
		super(ctx, BPM_FormColumn_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormColumn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MBPMFormColumn[] getLineFormCode(Properties ctx, int BPM_FormCode_ID, String trxName){
		
		List<MBPMFormColumn> list = new Query(ctx, I_BPM_FormColumn.Table_Name, "BPM_FormCode_ID=?", trxName)
		.setParameters(BPM_FormCode_ID).setOrderBy(I_BPM_FormColumn.COLUMNNAME_OrderColumn)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMFormColumn[] retValue = new MBPMFormColumn[list.size ()];
		list.toArray (retValue);
	
		return retValue;
	}
	
}
