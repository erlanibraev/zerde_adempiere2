/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for TRM_DepositLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_DepositLine extends PO implements I_TRM_DepositLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130619L;

    /** the default Constructor */
    public X_TRM_DepositLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_DepositLine (Properties ctx, int TRM_DepositLine_ID, String trxName)
    {
      super (ctx, TRM_DepositLine_ID, trxName);
      /** if (TRM_DepositLine_ID == 0)
        {
			setAD_Table_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setDocStatus (null);
// PR
			setFact_Acct_ID (0);
			setRecord_ID (0);
			setTRM_Deposit_ID (0);
			setTRM_DepositLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_DepositLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_TRM_DepositLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocStatus AD_Reference_ID=1000151 */
	public static final int DOCSTATUS_AD_Reference_ID=1000151;
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Prepare = PR */
	public static final String DOCSTATUS_Prepare = "PR";
	/** Complete = CO */
	public static final String DOCSTATUS_Complete = "CO";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	public org.compiere.model.I_Fact_Acct getFact_Acct() throws RuntimeException
    {
		return (org.compiere.model.I_Fact_Acct)MTable.get(getCtx(), org.compiere.model.I_Fact_Acct.Table_Name)
			.getPO(getFact_Acct_ID(), get_TrxName());	}

	/** Set Accounting Fact.
		@param Fact_Acct_ID Accounting Fact	  */
	public void setFact_Acct_ID (int Fact_Acct_ID)
	{
		if (Fact_Acct_ID < 1) 
			set_Value (COLUMNNAME_Fact_Acct_ID, null);
		else 
			set_Value (COLUMNNAME_Fact_Acct_ID, Integer.valueOf(Fact_Acct_ID));
	}

	/** Get Accounting Fact.
		@return Accounting Fact	  */
	public int getFact_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fact_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LineSum.
		@param LineSum 
		Сумма строки
	  */
	public void setLineSum (BigDecimal LineSum)
	{
		set_Value (COLUMNNAME_LineSum, LineSum);
	}

	/** Get LineSum.
		@return Сумма строки
	  */
	public BigDecimal getLineSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TRM_Deposit getTRM_Deposit() throws RuntimeException
    {
		return (I_TRM_Deposit)MTable.get(getCtx(), I_TRM_Deposit.Table_Name)
			.getPO(getTRM_Deposit_ID(), get_TrxName());	}

	/** Set TRM_Deposit ID.
		@param TRM_Deposit_ID 
		Депозиты
	  */
	public void setTRM_Deposit_ID (int TRM_Deposit_ID)
	{
		if (TRM_Deposit_ID < 1) 
			set_Value (COLUMNNAME_TRM_Deposit_ID, null);
		else 
			set_Value (COLUMNNAME_TRM_Deposit_ID, Integer.valueOf(TRM_Deposit_ID));
	}

	/** Get TRM_Deposit ID.
		@return Депозиты
	  */
	public int getTRM_Deposit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Deposit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TRM_DepositLine ID.
		@param TRM_DepositLine_ID TRM_DepositLine ID	  */
	public void setTRM_DepositLine_ID (int TRM_DepositLine_ID)
	{
		if (TRM_DepositLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_DepositLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_DepositLine_ID, Integer.valueOf(TRM_DepositLine_ID));
	}

	/** Get TRM_DepositLine ID.
		@return TRM_DepositLine ID	  */
	public int getTRM_DepositLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_DepositLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}