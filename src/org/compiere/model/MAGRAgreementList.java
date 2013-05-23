package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MAGRAgreementList extends X_AGR_AgreementList 
{
	private static final long serialVersionUID = 741804315537684787L;

	public MAGRAgreementList(Properties ctx, int AGR_AgreementList_ID,
			String trxName) 
	{
		super(ctx, AGR_AgreementList_ID, trxName);
	}

    /** Load Constructor */
    public MAGRAgreementList (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
}
