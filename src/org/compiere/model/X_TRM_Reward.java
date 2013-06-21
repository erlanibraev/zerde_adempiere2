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

/** Generated Model for TRM_Reward
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Reward extends PO implements I_TRM_Reward, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130621L;

    /** the default Constructor */
    public X_TRM_Reward(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_Reward (Properties ctx, int TRM_Reward_ID, String trxName)
    {
      super (ctx, TRM_Reward_ID, trxName);
      /** if (TRM_Reward_ID == 0)
        {
			setTRM_Reward_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Reward (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_Reward[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DateReward.
		@param DateReward 
		Дата начисления вознаграждения
	  */
	public void setDateReward (Timestamp DateReward)
	{
		set_Value (COLUMNNAME_DateReward, DateReward);
	}

	/** Get DateReward.
		@return Дата начисления вознаграждения
	  */
	public Timestamp getDateReward () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReward);
	}

	/** Set IncomingBalance.
		@param IncomingBalance 
		Входящий остаток
	  */
	public void setIncomingBalance (BigDecimal IncomingBalance)
	{
		set_Value (COLUMNNAME_IncomingBalance, IncomingBalance);
	}

	/** Get IncomingBalance.
		@return Входящий остаток
	  */
	public BigDecimal getIncomingBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IncomingBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Index.
		@param Index Index	  */
	public void setIndex (BigDecimal Index)
	{
		set_Value (COLUMNNAME_Index, Index);
	}

	/** Get Index.
		@return Index	  */
	public BigDecimal getIndex () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Index);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set InterestRate.
		@param InterestRate 
		Процентная ставка
	  */
	public void setInterestRate (BigDecimal InterestRate)
	{
		set_Value (COLUMNNAME_InterestRate, InterestRate);
	}

	/** Get InterestRate.
		@return Процентная ставка
	  */
	public BigDecimal getInterestRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set isPrediction.
		@param isPrediction 
		Прогноз
	  */
	public void setisPrediction (boolean isPrediction)
	{
		set_Value (COLUMNNAME_isPrediction, Boolean.valueOf(isPrediction));
	}

	/** Get isPrediction.
		@return Прогноз
	  */
	public boolean isPrediction () 
	{
		Object oo = get_Value(COLUMNNAME_isPrediction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_Value (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Total.
		@param LineTotalAmt 
		Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt)
	{
		set_Value (COLUMNNAME_LineTotalAmt, LineTotalAmt);
	}

	/** Get Line Total.
		@return Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineTotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Premium.
		@param Premium 
		Вознаграждение по депозиту
	  */
	public void setPremium (BigDecimal Premium)
	{
		set_Value (COLUMNNAME_Premium, Premium);
	}

	/** Get Premium.
		@return Вознаграждение по депозиту
	  */
	public BigDecimal getPremium () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Premium);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
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

	/** Set TRM_Reward ID.
		@param TRM_Reward_ID TRM_Reward ID	  */
	public void setTRM_Reward_ID (int TRM_Reward_ID)
	{
		if (TRM_Reward_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Reward_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Reward_ID, Integer.valueOf(TRM_Reward_ID));
	}

	/** Get TRM_Reward ID.
		@return TRM_Reward ID	  */
	public int getTRM_Reward_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Reward_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set withdrawal.
		@param withdrawal 
		Частичное изъятие
	  */
	public void setwithdrawal (BigDecimal withdrawal)
	{
		set_Value (COLUMNNAME_withdrawal, withdrawal);
	}

	/** Get withdrawal.
		@return Частичное изъятие
	  */
	public BigDecimal getwithdrawal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_withdrawal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}