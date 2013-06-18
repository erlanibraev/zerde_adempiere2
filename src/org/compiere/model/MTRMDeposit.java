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
    
    public void CloseLines(MBankStatement bankStatement)
    {
    	MTRMDepositLine[] lines = MTRMDepositLine.getOfBankStatement(getCtx(), bankStatement.get_ID(), get_TrxName());
    	
    	if(lines.length == 0) return;
    	
    	for(int i = 0; i < lines.length; i++)
    	{
    		//lines[i].setProcessed(false);
    		//lines[i].setDocStatus(MTRMDepositLine.DOCSTATUS_Closed);
    		//lines[i].setProcessed(true);
    		lines[i].saveEx();
    	}
    }
    
    public void CreateLines(MBankStatement bankStatement)
    {
    	MBankStatementLine[] lines = bankStatement.getLines(false);
    	
    	MDocType[] docTypes = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_Deposit);
    	
    	int withdrawal = docTypes[0].getName().equals("Withdrawal") ? 0 : 1;
    	int replenishment = withdrawal == 0 ? 1 : 0;
    	
    	for(int i = 0; i < lines.length; i++)
    	{
    		MTRMDepositLine depositLine = new MTRMDepositLine(getCtx(), 0, get_TrxName());
    		
    		BigDecimal sum = lines[i].getStmtAmt();
    		
    		depositLine.setAD_Client_ID(this.getAD_Client_ID());
    		depositLine.setAD_Org_ID(this.getAD_Org_ID());
    		depositLine.setTRM_Deposit_ID(this.get_ID());
    		depositLine.setDateOperation(lines[i].getDateAcct());
    		
    		depositLine.setBeginningBalance(this.getSum());
    		depositLine.setLineSum(sum);
    		
    		//Пополнение счета
    		//if(sum.doubleValue() >= 0.0d)
    			this.setSum(this.getSum().add(sum));
    		//Изъятие со счета
    		//else
    		//	this.setSum(this.getSum().subtract(sum));
    		
    		depositLine.setEndingBalance(this.getSum());
    		
    		depositLine.setC_DocType_ID(sum.doubleValue() >= 0.0d ? docTypes[replenishment].get_ID() : docTypes[withdrawal].get_ID());
    		//depositLine.setC_BankStatement_ID(bankStatement.get_ID());
    		//depositLine.setDocStatus(MTRMDepositLine.DOCSTATUS_Complete);
    		//depositLine.setProcessed(true);
    		
    		this.saveEx();
    		depositLine.save();
    	}    	
    }
    
    public void VoidLines(MBankStatement bankStatement)
    {
     	MTRMDepositLine[] lines = MTRMDepositLine.getOfBankStatement(getCtx(), bankStatement.get_ID(), get_TrxName());
    	
    	if(lines.length == 0) return;
    	
    	BigDecimal sum = new BigDecimal(0.0);
    	
    	for(int i = 0; i < lines.length; i++)
    	{
    		//lines[i].setProcessed(false);
    		sum = lines[i].getLineSum();
    		
    		//if(sum.doubleValue() >= 0)    		
    		this.setSum(this.getSum().subtract(sum));
    		//else
    		//	this.setSum(this.getSum().add(sum.multiply(new BigDecimal(1))));
    		
    		//lines[i].setDocStatus(MTRMDepositLine.DOCSTATUS_Voided);
    		//lines[i].setProcessed(true);
    		lines[i].saveEx();
    	}
    	
    	this.saveEx();
    }
}
