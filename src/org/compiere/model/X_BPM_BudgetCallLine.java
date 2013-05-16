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
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for BPM_BudgetCallLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_BudgetCallLine extends PO implements I_BPM_BudgetCallLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130515L;

    /** the default Constructor */
    public X_BPM_BudgetCallLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_BudgetCallLine (Properties ctx, int BPM_BudgetCallLine_ID, String trxName)
    {
      super (ctx, BPM_BudgetCallLine_ID, trxName);
      /** if (BPM_BudgetCallLine_ID == 0)
        {
			setAmount (Env.ZERO);
			setAmountUnit (Env.ZERO);
			setBPM_BudgetCall_ID (0);
			setBPM_BudgetCallLine_ID (0);
			setC_Charge_ID (0);
			setC_Period_ID (0);
			setC_UOM_ID (0);
			setNumber (0);
			setPaymentMonth (null);
        } */
    }

    /** Load Constructor */
    public X_BPM_BudgetCallLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_BudgetCallLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AmountUnit.
		@param AmountUnit AmountUnit	  */
	public void setAmountUnit (BigDecimal AmountUnit)
	{
		set_Value (COLUMNNAME_AmountUnit, AmountUnit);
	}

	/** Get AmountUnit.
		@return AmountUnit	  */
	public BigDecimal getAmountUnit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountUnit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set BPM_BudgetCall ID.
		@param BPM_BudgetCall_ID BPM_BudgetCall ID	  */
	public void setBPM_BudgetCall_ID (int BPM_BudgetCall_ID)
	{
		if (BPM_BudgetCall_ID < 1) 
			set_Value (COLUMNNAME_BPM_BudgetCall_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_BudgetCall_ID, Integer.valueOf(BPM_BudgetCall_ID));
	}

	/** Get BPM_BudgetCall ID.
		@return BPM_BudgetCall ID	  */
	public int getBPM_BudgetCall_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_BudgetCall_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_BudgetCallLine ID.
		@param BPM_BudgetCallLine_ID BPM_BudgetCallLine ID	  */
	public void setBPM_BudgetCallLine_ID (int BPM_BudgetCallLine_ID)
	{
		if (BPM_BudgetCallLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_BudgetCallLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_BudgetCallLine_ID, Integer.valueOf(BPM_BudgetCallLine_ID));
	}

	/** Get BPM_BudgetCallLine ID.
		@return BPM_BudgetCallLine ID	  */
	public int getBPM_BudgetCallLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_BudgetCallLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number.
		@param Number Number	  */
	public void setNumber (int Number)
	{
		set_Value (COLUMNNAME_Number, Integer.valueOf(Number));
	}

	/** Get Number.
		@return Number	  */
	public int getNumber () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Number);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PaymentMonth AD_Reference_ID=1000139 */
	public static final int PAYMENTMONTH_AD_Reference_ID=1000139;
	/** Current = CURRENT */
	public static final String PAYMENTMONTH_Current = "CURRENT";
	/** Next = NEXT */
	public static final String PAYMENTMONTH_Next = "NEXT";
	/** Set PaymentMonth.
		@param PaymentMonth PaymentMonth	  */
	public void setPaymentMonth (String PaymentMonth)
	{

		set_Value (COLUMNNAME_PaymentMonth, PaymentMonth);
	}

	/** Get PaymentMonth.
		@return PaymentMonth	  */
	public String getPaymentMonth () 
	{
		return (String)get_Value(COLUMNNAME_PaymentMonth);
	}
}