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
public class MBPMFormParameters extends X_BPM_FormParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5451912416308626117L;

	/**
	 * @param ctx
	 */
	public MBPMFormParameters(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_FormParameters_ID
	 * @param trxName
	 */
	public MBPMFormParameters(Properties ctx, int BPM_FormParameters_ID,
			String trxName) {
		super(ctx, BPM_FormParameters_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMFormParameters(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MBPMFormParameters[] getLines(Properties ctx, int BPM_FormCell_ID, String trxName){
		
		List<MBPMFormParameters> list = new Query(ctx, I_BPM_FormParameters.Table_Name, "BPM_FormCell_ID=?", trxName)
		.setParameters(BPM_FormCell_ID)
		.setOnlyActiveRecords(true)
		.setOrderBy(MBPMFormParameters.COLUMNNAME_ParameterType)
		.list();
		
		MBPMFormParameters[] retValue = new MBPMFormParameters[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}
	
}
