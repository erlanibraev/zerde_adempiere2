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

/** Generated Interface for TRM_Reward
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_TRM_Reward 
{

    /** TableName=TRM_Reward */
    public static final String Table_Name = "TRM_Reward";

    /** AD_Table_ID=1000261 */
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

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException;

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

    /** Column name DateReward */
    public static final String COLUMNNAME_DateReward = "DateReward";

	/** Set DateReward.
	  * Дата начисления вознаграждения
	  */
	public void setDateReward (Timestamp DateReward);

	/** Get DateReward.
	  * Дата начисления вознаграждения
	  */
	public Timestamp getDateReward();

    /** Column name IncomingBalance */
    public static final String COLUMNNAME_IncomingBalance = "IncomingBalance";

	/** Set IncomingBalance.
	  * Входящий остаток
	  */
	public void setIncomingBalance (BigDecimal IncomingBalance);

	/** Get IncomingBalance.
	  * Входящий остаток
	  */
	public BigDecimal getIncomingBalance();

    /** Column name Index */
    public static final String COLUMNNAME_Index = "Index";

	/** Set Index	  */
	public void setIndex (BigDecimal Index);

	/** Get Index	  */
	public BigDecimal getIndex();

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

    /** Column name isPrediction */
    public static final String COLUMNNAME_isPrediction = "isPrediction";

	/** Set isPrediction.
	  * Прогноз
	  */
	public void setisPrediction (boolean isPrediction);

	/** Get isPrediction.
	  * Прогноз
	  */
	public boolean isPrediction();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

    /** Column name LineTotalAmt */
    public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";

	/** Set Line Total.
	  * Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt);

	/** Get Line Total.
	  * Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt();

    /** Column name Premium */
    public static final String COLUMNNAME_Premium = "Premium";

	/** Set Premium.
	  * Вознаграждение по депозиту
	  */
	public void setPremium (BigDecimal Premium);

	/** Get Premium.
	  * Вознаграждение по депозиту
	  */
	public BigDecimal getPremium();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

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

    /** Column name TRM_Reward_ID */
    public static final String COLUMNNAME_TRM_Reward_ID = "TRM_Reward_ID";

	/** Set TRM_Reward ID	  */
	public void setTRM_Reward_ID (int TRM_Reward_ID);

	/** Get TRM_Reward ID	  */
	public int getTRM_Reward_ID();

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

    /** Column name withdrawal */
    public static final String COLUMNNAME_withdrawal = "withdrawal";

	/** Set withdrawal.
	  * Частичное изъятие
	  */
	public void setwithdrawal (BigDecimal withdrawal);

	/** Get withdrawal.
	  * Частичное изъятие
	  */
	public BigDecimal getwithdrawal();
}
