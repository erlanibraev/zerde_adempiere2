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
import org.compiere.util.KeyNamePair;

/** Generated Model for TRM_Deposit
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Deposit extends PO implements I_TRM_Deposit, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130611L;

    /** the default Constructor */
    public X_TRM_Deposit(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_Deposit (Properties ctx, int TRM_Deposit_ID, String trxName)
    {
      super (ctx, TRM_Deposit_ID, trxName);
      /** if (TRM_Deposit_ID == 0)
        {
			setTRM_Deposit_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Deposit (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_Deposit[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_CMS_Contract getCMS_Contract() throws RuntimeException
    {
		return (I_CMS_Contract)MTable.get(getCtx(), I_CMS_Contract.Table_Name)
			.getPO(getCMS_Contract_ID(), get_TrxName());	}

	/** Set Contract.
		@param CMS_Contract_ID 
		Номер контракта (CMS)
	  */
	public void setCMS_Contract_ID (int CMS_Contract_ID)
	{
		if (CMS_Contract_ID < 1) 
			set_Value (COLUMNNAME_CMS_Contract_ID, null);
		else 
			set_Value (COLUMNNAME_CMS_Contract_ID, Integer.valueOf(CMS_Contract_ID));
	}

	/** Get Contract.
		@return Номер контракта (CMS)
	  */
	public int getCMS_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CMS_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DatePlacement.
		@param DatePlacement 
		Дата размещения
	  */
	public void setDatePlacement (Timestamp DatePlacement)
	{
		set_Value (COLUMNNAME_DatePlacement, DatePlacement);
	}

	/** Get DatePlacement.
		@return Дата размещения
	  */
	public Timestamp getDatePlacement () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePlacement);
	}

	/** Set DateSigning.
		@param DateSigning 
		Дата подписания
	  */
	public void setDateSigning (Timestamp DateSigning)
	{
		set_Value (COLUMNNAME_DateSigning, DateSigning);
	}

	/** Get DateSigning.
		@return Дата подписания
	  */
	public Timestamp getDateSigning () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateSigning);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set EstablishSubLimit.
		@param EstablishSubLimit 
		Установленные сублимиты
	  */
	public void setEstablishSubLimit (BigDecimal EstablishSubLimit)
	{
		set_Value (COLUMNNAME_EstablishSubLimit, EstablishSubLimit);
	}

	/** Get EstablishSubLimit.
		@return Установленные сублимиты
	  */
	public BigDecimal getEstablishSubLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EstablishSubLimit);
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

	/** Set isPartialWithdrawal.
		@param isPartialWithdrawal 
		Частичное изъятие (депозиты)
	  */
	public void setisPartialWithdrawal (boolean isPartialWithdrawal)
	{
		set_Value (COLUMNNAME_isPartialWithdrawal, Boolean.valueOf(isPartialWithdrawal));
	}

	/** Get isPartialWithdrawal.
		@return Частичное изъятие (депозиты)
	  */
	public boolean isPartialWithdrawal () 
	{
		Object oo = get_Value(COLUMNNAME_isPartialWithdrawal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MinBalance.
		@param MinBalance 
		Неснижаемый остаток
	  */
	public void setMinBalance (BigDecimal MinBalance)
	{
		set_Value (COLUMNNAME_MinBalance, MinBalance);
	}

	/** Get MinBalance.
		@return Неснижаемый остаток
	  */
	public BigDecimal getMinBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Sum.
		@param Sum 
		Сумма на депозите
	  */
	public void setSum (BigDecimal Sum)
	{
		set_Value (COLUMNNAME_Sum, Sum);
	}

	/** Get Sum.
		@return Сумма на депозите
	  */
	public BigDecimal getSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TRM_Deposit ID.
		@param TRM_Deposit_ID 
		Депозиты
	  */
	public void setTRM_Deposit_ID (int TRM_Deposit_ID)
	{
		if (TRM_Deposit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Deposit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Deposit_ID, Integer.valueOf(TRM_Deposit_ID));
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
}