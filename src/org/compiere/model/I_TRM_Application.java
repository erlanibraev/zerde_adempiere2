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

/** Generated Interface for TRM_Application
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_TRM_Application 
{

    /** TableName=TRM_Application */
    public static final String Table_Name = "TRM_Application";

    /** AD_Table_ID=1000241 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AGR_Dispatcher_ID */
    public static final String COLUMNNAME_AGR_Dispatcher_ID = "AGR_Dispatcher_ID";

	/** Set AGR_Dispatcher ID	  */
	public void setAGR_Dispatcher_ID (int AGR_Dispatcher_ID);

	/** Get AGR_Dispatcher ID	  */
	public int getAGR_Dispatcher_ID();

	public I_AGR_Dispatcher getAGR_Dispatcher() throws RuntimeException;

    /** Column name AGR_Stage_ID */
    public static final String COLUMNNAME_AGR_Stage_ID = "AGR_Stage_ID";

	/** Set AGR_Stage ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID);

	/** Get AGR_Stage ID	  */
	public int getAGR_Stage_ID();

	public I_AGR_Stage getAGR_Stage() throws RuntimeException;

    /** Column name AGRApprove */
    public static final String COLUMNNAME_AGRApprove = "AGRApprove";

	/** Set Approve.
	  * Одобрить
	  */
	public void setAGRApprove (String AGRApprove);

	/** Get Approve.
	  * Одобрить
	  */
	public String getAGRApprove();

    /** Column name ApplAmount */
    public static final String COLUMNNAME_ApplAmount = "ApplAmount";

	/** Set ApplAmount.
	  * Сумма финансовой заявки
	  */
	public void setApplAmount (BigDecimal ApplAmount);

	/** Get ApplAmount.
	  * Сумма финансовой заявки
	  */
	public BigDecimal getApplAmount();

    /** Column name Apps */
    public static final String COLUMNNAME_Apps = "Apps";

	/** Set Apps.
	  * Приложения
	  */
	public void setApps (String Apps);

	/** Get Apps.
	  * Приложения
	  */
	public String getApps();

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name GenerateApplication */
    public static final String COLUMNNAME_GenerateApplication = "GenerateApplication";

	/** Set GenerateApplication.
	  * Сформировать заявку
	  */
	public void setGenerateApplication (String GenerateApplication);

	/** Get GenerateApplication.
	  * Сформировать заявку
	  */
	public String getGenerateApplication();

    /** Column name Initiator_ID */
    public static final String COLUMNNAME_Initiator_ID = "Initiator_ID";

	/** Set Initiator_ID.
	  * Сотрудник, создавший заявку
	  */
	public void setInitiator_ID (int Initiator_ID);

	/** Get Initiator_ID.
	  * Сотрудник, создавший заявку
	  */
	public int getInitiator_ID();

	public org.compiere.model.I_AD_User getInitiator() throws RuntimeException;

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

    /** Column name PaymentTerms */
    public static final String COLUMNNAME_PaymentTerms = "PaymentTerms";

	/** Set PaymentTerms.
	  * Условия оплаты
	  */
	public void setPaymentTerms (String PaymentTerms);

	/** Get PaymentTerms.
	  * Условия оплаты
	  */
	public String getPaymentTerms();

    /** Column name TRM_Application_ID */
    public static final String COLUMNNAME_TRM_Application_ID = "TRM_Application_ID";

	/** Set Financial Application ID.
	  * Финансовая заявка
	  */
	public void setTRM_Application_ID (int TRM_Application_ID);

	/** Get Financial Application ID.
	  * Финансовая заявка
	  */
	public int getTRM_Application_ID();

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
