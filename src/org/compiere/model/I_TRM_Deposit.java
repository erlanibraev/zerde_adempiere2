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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TRM_Deposit
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_TRM_Deposit 
{

    /** TableName=TRM_Deposit */
    public static final String Table_Name = "TRM_Deposit";

    /** AD_Table_ID=1000259 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_Bank_ID */
    public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";

	/** Set Bank.
	  * Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID);

	/** Get Bank.
	  * Bank
	  */
	public int getC_Bank_ID();

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException;

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name CMS_Contract_ID */
    public static final String COLUMNNAME_CMS_Contract_ID = "CMS_Contract_ID";

	/** Set Contract.
	  * Номер контракта (CMS)
	  */
	public void setCMS_Contract_ID (int CMS_Contract_ID);

	/** Get Contract.
	  * Номер контракта (CMS)
	  */
	public int getCMS_Contract_ID();

	public I_CMS_Contract getCMS_Contract() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DatePlacement */
    public static final String COLUMNNAME_DatePlacement = "DatePlacement";

	/** Set DatePlacement.
	  * Дата размещения
	  */
	public void setDatePlacement (Timestamp DatePlacement);

	/** Get DatePlacement.
	  * Дата размещения
	  */
	public Timestamp getDatePlacement();

    /** Column name DateSigning */
    public static final String COLUMNNAME_DateSigning = "DateSigning";

	/** Set DateSigning.
	  * Дата подписания
	  */
	public void setDateSigning (Timestamp DateSigning);

	/** Get DateSigning.
	  * Дата подписания
	  */
	public Timestamp getDateSigning();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EstablishSubLimit */
    public static final String COLUMNNAME_EstablishSubLimit = "EstablishSubLimit";

	/** Set EstablishSubLimit.
	  * Установленные сублимиты
	  */
	public void setEstablishSubLimit (BigDecimal EstablishSubLimit);

	/** Get EstablishSubLimit.
	  * Установленные сублимиты
	  */
	public BigDecimal getEstablishSubLimit();

    /** Column name InterestRate */
    public static final String COLUMNNAME_InterestRate = "InterestRate";

	/** Set InterestRate.
	  * Процентная ставка
	  */
	public void setInterestRate (BigDecimal InterestRate);

	/** Get InterestRate.
	  * Процентная ставка
	  */
	public BigDecimal getInterestRate();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name isPartialWithdrawal */
    public static final String COLUMNNAME_isPartialWithdrawal = "isPartialWithdrawal";

	/** Set isPartialWithdrawal.
	  * Частичное изъятие (депозиты)
	  */
	public void setisPartialWithdrawal (boolean isPartialWithdrawal);

	/** Get isPartialWithdrawal.
	  * Частичное изъятие (депозиты)
	  */
	public boolean isPartialWithdrawal();

    /** Column name MinBalance */
    public static final String COLUMNNAME_MinBalance = "MinBalance";

	/** Set MinBalance.
	  * Неснижаемый остаток
	  */
	public void setMinBalance (BigDecimal MinBalance);

	/** Get MinBalance.
	  * Неснижаемый остаток
	  */
	public BigDecimal getMinBalance();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name RefreshLines */
    public static final String COLUMNNAME_RefreshLines = "RefreshLines";

	/** Set RefreshLines.
	  * Обновить строки
	  */
	public void setRefreshLines (String RefreshLines);

	/** Get RefreshLines.
	  * Обновить строки
	  */
	public String getRefreshLines();

    /** Column name Sum */
    public static final String COLUMNNAME_Sum = "Sum";

	/** Set Sum.
	  * Сумма на депозите
	  */
	public void setSum (BigDecimal Sum);

	/** Get Sum.
	  * Сумма на депозите
	  */
	public BigDecimal getSum();

    /** Column name TRM_Deposit_ID */
    public static final String COLUMNNAME_TRM_Deposit_ID = "TRM_Deposit_ID";

	/** Set TRM_Deposit ID	  */
	public void setTRM_Deposit_ID (int TRM_Deposit_ID);

	/** Get TRM_Deposit ID	  */
	public int getTRM_Deposit_ID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
