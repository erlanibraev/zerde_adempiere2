package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MBPMColumnParameters extends X_BPM_ColumnParameters {
	private static final long serialVersionUID = 1414630575933208886L;

	public MBPMColumnParameters(Properties ctx) {
		super(ctx);
	}
	
	/** Standard Constructor */
    public MBPMColumnParameters (Properties ctx, int BPM_ColumnParameters_ID, String trxName) {
      super (ctx, BPM_ColumnParameters_ID, trxName);
    }

    /** Load Constructor */
    public MBPMColumnParameters (Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }

    public static MBPMColumnParameters[] getLines(Properties ctx, int BPM_FormColumn_ID, String trxName){
		
		List<MBPMColumnParameters> list = new Query(ctx, I_BPM_ColumnParameters.Table_Name, "BPM_FormColumn_ID=?", trxName)
		.setParameters(BPM_FormColumn_ID)
		.setOnlyActiveRecords(true)
		.setOrderBy(MBPMColumnParameters.COLUMNNAME_ParameterType)
		.list();
		
		MBPMColumnParameters[] retValue = new MBPMColumnParameters[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}
}
