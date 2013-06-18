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
	private static final long serialVersionUID = 20130618L;

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
			setDateOperation (new Timestamp( System.currentTimeMillis() ));
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

	/** Set Beginning Balance.
		@param BeginningBalance 
		Balance prior to any transactions
	  */
	public void setBeginningBalance (BigDecimal BeginningBalance)
	{
		set_Value (COLUMNNAME_BeginningBalance, BeginningBalance);
	}

	/** Get Beginning Balance.
		@return Balance prior to any transactions
	  */
	public BigDecimal getBeginningBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BeginningBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set DateOperation.
		@param DateOperation 
		Дата операции
	  */
	public void setDateOperation (Timestamp DateOperation)
	{
		set_Value (COLUMNNAME_DateOperation, DateOperation);
	}

	/** Get DateOperation.
		@return Дата операции
	  */
	public Timestamp getDateOperation () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOperation);
	}

	/** Set Ending balance.
		@param EndingBalance 
		Ending  or closing balance
	  */
	public void setEndingBalance (BigDecimal EndingBalance)
	{
		set_Value (COLUMNNAME_EndingBalance, EndingBalance);
	}

	/** Get Ending balance.
		@return Ending  or closing balance
	  */
	public BigDecimal getEndingBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EndingBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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