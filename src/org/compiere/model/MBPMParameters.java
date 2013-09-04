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
public class MBPMParameters extends X_BPM_Parameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7981959326246489981L;

	/**
	 * @param ctx
	 */
	public MBPMParameters(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_Parameters_ID
	 * @param trxName
	 */
	public MBPMParameters(Properties ctx, int BPM_Parameters_ID, String trxName) {
		super(ctx, BPM_Parameters_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMParameters(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	public static MBPMParameters[] getLines(Properties ctx, int AD_Table_ID, int Record_ID, String trxName)
	{
		List<MBPMParameters> list = new Query(ctx, I_BPM_Parameters.Table_Name, "AD_Table_ID=? AND Record_ID=?", trxName)
		.setParameters(AD_Table_ID, Record_ID)
		.setOrderBy(I_BPM_Parameters.COLUMNNAME_ColumnName)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMParameters[] retValue = new MBPMParameters[list.size ()];
		list.toArray (retValue);
		return retValue;
	}

}
