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

/** Generated Model for TRM_Deposit
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Deposit extends PO implements I_TRM_Deposit, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130726L;

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
			setBeginningDate (new Timestamp( System.currentTimeMillis() ));
			setEndDate (new Timestamp( System.currentTimeMillis() ));
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

	/** Set BeginningDate.
		@param BeginningDate 
		Дата начала действия
	  */
	public void setBeginningDate (Timestamp BeginningDate)
	{
		set_Value (COLUMNNAME_BeginningDate, BeginningDate);
	}

	/** Get BeginningDate.
		@return Дата начала действия
	  */
	public Timestamp getBeginningDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BeginningDate);
	}

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException
    {
		return (org.compiere.model.I_C_Bank)MTable.get(getCtx(), org.compiere.model.I_C_Bank.Table_Name)
			.getPO(getC_Bank_ID(), get_TrxName());	}

	/** Set Bank.
		@param C_Bank_ID 
		Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1) 
			set_Value (COLUMNNAME_C_Bank_ID, null);
		else 
			set_Value (COLUMNNAME_C_Bank_ID, Integer.valueOf(C_Bank_ID));
	}

	/** Get Bank.
		@return Bank
	  */
	public int getC_Bank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Bank_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
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

	/** Set isForce.
		@param isForce 
		Принудительно
	  */
	public void setisForce (boolean isForce)
	{
		set_Value (COLUMNNAME_isForce, Boolean.valueOf(isForce));
	}

	/** Get isForce.
		@return Принудительно
	  */
	public boolean isForce () 
	{
		Object oo = get_Value(COLUMNNAME_isForce);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set RefreshLines.
		@param RefreshLines 
		Обновить строки
	  */
	public void setRefreshLines (String RefreshLines)
	{
		set_Value (COLUMNNAME_RefreshLines, RefreshLines);
	}

	/** Get RefreshLines.
		@return Обновить строки
	  */
	public String getRefreshLines () 
	{
		return (String)get_Value(COLUMNNAME_RefreshLines);
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
		@param TRM_Deposit_ID TRM_Deposit ID	  */
	public void setTRM_Deposit_ID (int TRM_Deposit_ID)
	{
		if (TRM_Deposit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Deposit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Deposit_ID, Integer.valueOf(TRM_Deposit_ID));
	}

	/** Get TRM_Deposit ID.
		@return TRM_Deposit ID	  */
	public int getTRM_Deposit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Deposit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}