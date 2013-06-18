package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MTRMDepositLine;
import org.compiere.util.DB;

public class TRM_FactToDepositData extends SvrProcess {

	@Override
	protected void prepare() 
	{
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		int fact_acct_ID = getMaxFactAcct();
		
		if(fact_acct_ID == -1) return "No fact_acct";		
		
		return null;
	}
	
	private void CreateLines(int fact_acct_ID)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.Value, f.DateAcct, f.AmtSourceCr, f.AmtSourceDr, f.Fact_Acct_ID, f.AD_Table_ID, f.Record_ID \n\t");
		sql.append("Fact_Acct f INNER JOIN C_ElementValue e ON f.Account_ID = e.C_ElementValue_ID \n\t");
		sql.append("WHERE f.Fact_Acct_ID > " + fact_acct_ID + " \n\t");
		sql.append("AND e.Value LIKE '1060%'");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try		
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.getResultSet();
			
			while(rs.next())
			{
				MTRMDepositLine deposit_line = new MTRMDepositLine(getCtx(), 0, get_TrxName());
			}
		}
		catch(Exception ex){}
	}
	
	private ArrayList<String> Accounts()
	{
		ArrayList<String> accounts = new ArrayList<String>();
		
		return accounts;
	}
	
	private int getMaxFactAcct()
	{
		String sql = "SELECT MAX(Fact_Acct_ID) FROM DepositLine";
		
		return DB.getSQLValue(get_TrxName(), sql);
	}

}
