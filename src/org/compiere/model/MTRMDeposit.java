package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MTRMDeposit extends X_TRM_Deposit 
{
	private static final long serialVersionUID = -8860470031117295336L;

	public MTRMDeposit(Properties ctx, int TRM_Deposit_ID, String trxName) 
	{
		super(ctx, TRM_Deposit_ID, trxName);
	}

    /** Load Constructor */
    public MTRMDeposit (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public static MTRMDeposit[] getOfCMS_Contract(Properties ctx, int CMS_Contract_ID, String trxName)
	{
		List<MTRMDeposit> list = new Query(ctx, I_TRM_Deposit.Table_Name, "CMS_Contract_ID=?", trxName)
		.setParameters(CMS_Contract_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MTRMDeposit[] retValue = new MTRMDeposit[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
    
    public static MTRMDeposit[] getOfAccount(Properties ctx, int C_BankAccount_ID, String trxName)
    {
    	List<MTRMDeposit> list = new Query(ctx, I_TRM_Deposit.Table_Name, "C_BankAccount_ID=?", trxName)
		.setParameters(C_BankAccount_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MTRMDeposit[] retValue = new MTRMDeposit[list.size ()];
		list.toArray (retValue);
		return retValue;
    }
    
    public void CreateLines(MBankStatement bankStatement)
    {
    	MBankStatementLine[] lines = bankStatement.getLines(false);
    	
    	MDocType[] docTypes = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_Deposit);
    	
    	int withdrawal = docTypes[0].getName().equals("Withdrawal") ? 0 : 1;
    	int replenishment = withdrawal == 0 ? 1 : 0;
    	
    	for(int i = 0; i < lines.length; i++)
    	{
    		MTRMDepositLine depositLine = new MTRMDepositLine(getCtx(), this.get_ID(), get_TrxName());
    		
    		BigDecimal sum = lines[i].getStmtAmt();
    		
    		depositLine.setAD_Client_ID(this.getAD_Client_ID());
    		depositLine.setAD_Org_ID(this.getAD_Org_ID());
    		depositLine.setDateOperation(lines[i].getDateAcct());
    		depositLine.setBeginningBalance(this.getSum());
    		depositLine.setLineSum(lines[i].getStmtAmt());
    		
    		this.setSum(this.getSum().subtract(depositLine.getLineSum()));
    		
    		depositLine.setEndingBalance(this.getSum());
    		
    		depositLine.setC_DocType_ID(sum.doubleValue() >= 0.0d ? docTypes[replenishment].get_ID() : docTypes[withdrawal].get_ID());
    		
    		this.saveEx();
    		depositLine.save();
    	}
    	
    	
    }
}
