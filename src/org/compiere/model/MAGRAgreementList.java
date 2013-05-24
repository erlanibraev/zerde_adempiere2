package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

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
    
    public static ArrayList<MAGRAgreementList> getOfStage(Properties ctx, String trxName, int AD_Table_ID, int Record_ID)
    {
    	ArrayList<Object> parameters = new ArrayList<Object>();
    	parameters.add(AD_Table_ID);
    	parameters.add(Record_ID);
    	parameters.add(false);
    	List<MAGRAgreementList> list = new Query(ctx, I_AGR_AgreementList.Table_Name, "AD_Table_ID=? AND Record_ID=? AND Processed=?", trxName)
		.setParameters(parameters)
		.setOnlyActiveRecords(true)
		.list();
    	
    	return (ArrayList<MAGRAgreementList>) list;
    }
    
    public static ArrayList<MAGRAgreementList> getOfStage(Properties ctx, String trxName, int AD_Table_ID, int Record_ID, int AGR_Stage_ID)
    {
    	ArrayList<Object> parameters = new ArrayList<Object>();
    	parameters.add(AD_Table_ID);
    	parameters.add(Record_ID);
    	parameters.add(AGR_Stage_ID);
    	parameters.add(false);
    	List<MAGRAgreementList> list = new Query(ctx, I_AGR_AgreementList.Table_Name, "AD_Table_ID=? AND Record_ID=? AND AGR_Stage_ID=? AND Processed=?", trxName)
		.setParameters(parameters)
		.setOnlyActiveRecords(true)
		.list();
    	
    	return (ArrayList<MAGRAgreementList>) list;
    }
    
    public boolean isNotApproved()
    {
    	return true;//!isSign_Y();
    }
}
