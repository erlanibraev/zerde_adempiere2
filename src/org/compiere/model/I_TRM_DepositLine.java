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

/** Generated Interface for TRM_DepositLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_TRM_DepositLine 
{

    /** TableName=TRM_DepositLine */
    public static final String Table_Name = "TRM_DepositLine";

    /** AD_Table_ID=1000260 */
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

    /** Column name BeginningBalance */
    public static final String COLUMNNAME_BeginningBalance = "BeginningBalance";

	/** Set Beginning Balance.
	  * Balance prior to any transactions
	  */
	public void setBeginningBalance (BigDecimal BeginningBalance);

	/** Get Beginning Balance.
	  * Balance prior to any transactions
	  */
	public BigDecimal getBeginningBalance();

    /** Column name C_BankStatement_ID */
    public static final String COLUMNNAME_C_BankStatement_ID = "C_BankStatement_ID";

	/** Set Bank Statement.
	  * Bank Statement of account
	  */
	public void setC_BankStatement_ID (int C_BankStatement_ID);

	/** Get Bank Statement.
	  * Bank Statement of account
	  */
	public int getC_BankStatement_ID();

	public org.compiere.model.I_C_BankStatement getC_BankStatement() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

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

    /** Column name DateOperation */
    public static final String COLUMNNAME_DateOperation = "DateOperation";

	/** Set DateOperation.
	  * Дата операции
	  */
	public void setDateOperation (Timestamp DateOperation);

	/** Get DateOperation.
	  * Дата операции
	  */
	public Timestamp getDateOperation();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name EndingBalance */
    public static final String COLUMNNAME_EndingBalance = "EndingBalance";

	/** Set Ending balance.
	  * Ending  or closing balance
	  */
	public void setEndingBalance (BigDecimal EndingBalance);

	/** Get Ending balance.
	  * Ending  or closing balance
	  */
	public BigDecimal getEndingBalance();

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

    /** Column name LineSum */
    public static final String COLUMNNAME_LineSum = "LineSum";

	/** Set LineSum.
	  * Сумма строки
	  */
	public void setLineSum (BigDecimal LineSum);

	/** Get LineSum.
	  * Сумма строки
	  */
	public BigDecimal getLineSum();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name TRM_Deposit_ID */
    public static final String COLUMNNAME_TRM_Deposit_ID = "TRM_Deposit_ID";

	/** Set TRM_Deposit ID.
	  * Депозиты
	  */
	public void setTRM_Deposit_ID (int TRM_Deposit_ID);

	/** Get TRM_Deposit ID.
	  * Депозиты
	  */
	public int getTRM_Deposit_ID();

	public I_TRM_Deposit getTRM_Deposit() throws RuntimeException;

    /** Column name TRM_DepositLine_ID */
    public static final String COLUMNNAME_TRM_DepositLine_ID = "TRM_DepositLine_ID";

	/** Set TRM_DepositLine ID	  */
	public void setTRM_DepositLine_ID (int TRM_DepositLine_ID);

	/** Get TRM_DepositLine ID	  */
	public int getTRM_DepositLine_ID();

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
