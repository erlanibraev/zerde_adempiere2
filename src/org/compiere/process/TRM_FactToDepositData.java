package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankAccountAcct;
import org.compiere.model.MElementValue;
import org.compiere.model.MTRMDeposit;
import org.compiere.model.MTRMDepositLine;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_C_ValidCombination;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TRM_FactToDepositData extends SvrProcess implements ASyncProcess 
{
	private int TRM_Deposit_ID = 0;
	
	@Override
	protected void prepare() 
	{
		TRM_Deposit_ID = this.getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		int fact_acct_ID = getMaxFactAcct(TRM_Deposit_ID);
		
//		if(fact_acct_ID == -1) return "No fact_acct";		
		
		CreateLines(fact_acct_ID, TRM_Deposit_ID);
		
		//StartRewardFill();
		
		StartRewardFill();
		
		return null;
	}	
	
	private String StartRewardFill()
	{
		int AD_Process_ID = getProcess();
		
		if(AD_Process_ID == 0)
			return  "The process can not be found";
		//  Prepare Process
		ProcessInfo pi = new ProcessInfo ("The printing process of the order", AD_Process_ID);
		pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));

		List<ProcessInfoParameter> po = new ArrayList<ProcessInfoParameter>();		//
		po.add(new ProcessInfoParameter("TRM_Deposit_ID", new BigDecimal(TRM_Deposit_ID),null,"",""));
				
		ProcessInfoParameter[] pp = new ProcessInfoParameter[po.size()];
		po.toArray(pp);
		pi.setParameter(pp);
		//	Execute Process
		ProcessParameterPanel pu = new ProcessParameterPanel(0, pi);
		ProcessCtl.process(this, 0, (IProcessParameter) pu, pi, null);
		
		return "";
	}
	
	private int getProcess()
	{
		String sql = "select ad_process_id from ad_process where name like '%TRM_RewardFill%'";
		
		int ad_process_id = DB.getSQLValue(get_TrxName(), sql);
		
		return ad_process_id;
	}
	
	private void CreateLines(int fact_acct_ID, int TRM_Deposit_ID) throws SQLException
	{
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
		String accountsInDeposit = Accounts(deposit.getC_BankAccount_ID());
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.Value, f.DateAcct, f.AmtSourceCr, f.AmtSourceDr, f.Fact_Acct_ID, f.AD_Table_ID, f.Record_ID \n\t");
		sql.append("FROM Fact_Acct f INNER JOIN C_ElementValue e ON f.Account_ID = e.C_ElementValue_ID \n\t");
		sql.append("WHERE f.Fact_Acct_ID > " + fact_acct_ID + " \n\t");
		sql.append("AND e.Value LIKE '" + accountsInDeposit + "' ORDER BY DateAcct");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int replenishment = getC_DocType("DEP", "Replenishment");
		int withdrawal = getC_DocType("DEP", "Withdrawal");
		
		BigDecimal totalSum = fact_acct_ID == 0 ? new BigDecimal(0) : deposit.getSum();
		
		try		
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				if(accountsInDeposit.contains(rs.getString(1)))
				{
					if(deposit.getDatePlacement() == null)
						deposit.setDatePlacement(rs.getTimestamp("DateAcct"));
					
					MTRMDepositLine deposit_line = new MTRMDepositLine(getCtx(), 0, get_TrxName());
					deposit_line.setDateAcct(rs.getTimestamp(2));
					deposit_line.setLineSum(rs.getBigDecimal(4).subtract(rs.getBigDecimal(3)));
					deposit_line.setFact_Acct_ID(rs.getInt(5));
					deposit_line.setAD_Table_ID(rs.getInt(6));
					deposit_line.setRecord_ID(rs.getInt(7));
					
					deposit_line.setC_DocType_ID(deposit_line.getLineSum().doubleValue() > 0 ? replenishment : withdrawal);
					
					deposit_line.setTRM_Deposit_ID(TRM_Deposit_ID);
					
					MTable table = MTable.get(getCtx(), deposit_line.getAD_Table_ID());					
					PO po = table.getPO (deposit_line.getRecord_ID(), get_TrxName());
					
					totalSum = totalSum.add(deposit_line.getLineSum());
					
					try
					{					
						deposit_line.setDescription(po.get_ValueAsString("Description"));
					}
					catch(Exception ex){}
					
					deposit_line.saveEx();
				}
			}
			
			deposit.setSum(totalSum);
			deposit.saveEx();
		}
		catch(Exception ex){}
		finally
		{
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
	}
	
	private String Accounts(int C_BankAccount_ID)
	{
		String retValue = "";
		
		MBankAccount bankAccount = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
				
		MBankAccountAcct accountLine = MBankAccountAcct.getOfBankAccount(getCtx(), bankAccount.get_ID(), get_TrxName());
				
		if(accountLine != null)
		{
			X_C_ValidCombination validCombination = new X_C_ValidCombination(getCtx(), accountLine.getB_Asset_Acct(), get_TrxName());
			
			MElementValue elementValue = new MElementValue(getCtx(), validCombination.getAccount_ID(), get_TrxName());
			
			retValue = elementValue.getValue();
		}
		
		return retValue;
	}
	
	private int getMaxFactAcct(int TRM_Deposit_ID)
	{
		String sql = "SELECT MAX(Fact_Acct_ID) FROM TRM_DepositLine WHERE TRM_Deposit_ID = " + TRM_Deposit_ID;
		
		return DB.getSQLValue(get_TrxName(), sql);
	}
	
	private int getC_DocType(String baseType, String docName)
	{
		return DB.getSQLValue(get_TrxName(), "SELECT C_DocType_ID FROM C_DocType WHERE DocBaseType = '" + baseType + "' AND Name = '" + docName + "'");
	}

	@Override
	public void lockUI(ProcessInfo pi) 
	{
	}

	@Override
	public void unlockUI(ProcessInfo pi) 
	{
		
	}

	@Override
	public boolean isUILocked() 
	{
		return false;
	}

	@Override
	public void executeASync(ProcessInfo pi) 
	{
		
	}
	
	

}
