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

/** Generated Interface for cms_proposal
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_cms_proposal 
{

    /** TableName=cms_proposal */
    public static final String Table_Name = "cms_proposal";

    /** AD_Table_ID=1000109 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set Usuario.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get Usuario.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name BeginningDateExecution */
    public static final String COLUMNNAME_BeginningDateExecution = "BeginningDateExecution";

	/** Set BeginningDateExecution.
	  * BeginningDateExecution
	  */
	public void setBeginningDateExecution (Timestamp BeginningDateExecution);

	/** Get BeginningDateExecution.
	  * BeginningDateExecution
	  */
	public Timestamp getBeginningDateExecution();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name CMS_Contract_ContractNum */
    public static final String COLUMNNAME_CMS_Contract_ContractNum = "CMS_Contract_ContractNum";

	/** Set Contract Number	  */
	public void setCMS_Contract_ContractNum (String CMS_Contract_ContractNum);

	/** Get Contract Number	  */
	public String getCMS_Contract_ContractNum();

    /** Column name CMS_Contract_ContractNumBP */
    public static final String COLUMNNAME_CMS_Contract_ContractNumBP = "CMS_Contract_ContractNumBP";

	/** Set Contract Number BP	  */
	public void setCMS_Contract_ContractNumBP (String CMS_Contract_ContractNumBP);

	/** Get Contract Number BP	  */
	public String getCMS_Contract_ContractNumBP();

    /** Column name CMS_Contract_CreatDate */
    public static final String COLUMNNAME_CMS_Contract_CreatDate = "CMS_Contract_CreatDate";

	/** Set Contract Creation Date	  */
	public void setCMS_Contract_CreatDate (Timestamp CMS_Contract_CreatDate);

	/** Get Contract Creation Date	  */
	public Timestamp getCMS_Contract_CreatDate();

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

    /** Column name cms_contractstype_ID */
    public static final String COLUMNNAME_cms_contractstype_ID = "cms_contractstype_ID";

	/** Set cms_contractstype	  */
	public void setcms_contractstype_ID (int cms_contractstype_ID);

	/** Get cms_contractstype	  */
	public int getcms_contractstype_ID();

	public I_cms_contractstype getcms_contractstype() throws RuntimeException;

    /** Column name cms_incotermstype_ID */
    public static final String COLUMNNAME_cms_incotermstype_ID = "cms_incotermstype_ID";

	/** Set cms_incotermstype	  */
	public void setcms_incotermstype_ID (int cms_incotermstype_ID);

	/** Get cms_incotermstype	  */
	public int getcms_incotermstype_ID();

	public I_cms_incotermstype getcms_incotermstype() throws RuntimeException;

    /** Column name cms_paymentstype_ID */
    public static final String COLUMNNAME_cms_paymentstype_ID = "cms_paymentstype_ID";

	/** Set cms_paymentstype	  */
	public void setcms_paymentstype_ID (int cms_paymentstype_ID);

	/** Get cms_paymentstype	  */
	public int getcms_paymentstype_ID();

	public I_cms_paymentstype getcms_paymentstype() throws RuntimeException;

    /** Column name cms_proposal_ID */
    public static final String COLUMNNAME_cms_proposal_ID = "cms_proposal_ID";

	/** Set cms_proposal	  */
	public void setcms_proposal_ID (int cms_proposal_ID);

	/** Get cms_proposal	  */
	public int getcms_proposal_ID();

    /** Column name comment */
    public static final String COLUMNNAME_comment = "comment";

	/** Set comment.
	  * Примечание
	  */
	public void setcomment (String comment);

	/** Get comment.
	  * Примечание
	  */
	public String getcomment();

    /** Column name Contragent */
    public static final String COLUMNNAME_Contragent = "Contragent";

	/** Set Contragent.
	  * Contragent
	  */
	public void setContragent (String Contragent);

	/** Get Contragent.
	  * Contragent
	  */
	public String getContragent();

    /** Column name contragent_contacts */
    public static final String COLUMNNAME_contragent_contacts = "contragent_contacts";

	/** Set contragent_contacts.
	  * contragent_contacts
	  */
	public void setcontragent_contacts (String contragent_contacts);

	/** Get contragent_contacts.
	  * contragent_contacts
	  */
	public String getcontragent_contacts();

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

    /** Column name Creator_ID */
    public static final String COLUMNNAME_Creator_ID = "Creator_ID";

	/** Set Creator_ID.
	  * Создатель заявки
	  */
	public void setCreator_ID (int Creator_ID);

	/** Get Creator_ID.
	  * Создатель заявки
	  */
	public int getCreator_ID();

	public org.compiere.model.I_AD_User getCreator() throws RuntimeException;

    /** Column name datefiling */
    public static final String COLUMNNAME_datefiling = "datefiling";

	/** Set datefiling.
	  * Дата подачи заявки
	  */
	public void setdatefiling (Timestamp datefiling);

	/** Get datefiling.
	  * Дата подачи заявки
	  */
	public Timestamp getdatefiling();

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

    /** Column name EndDateExecution */
    public static final String COLUMNNAME_EndDateExecution = "EndDateExecution";

	/** Set EndDateExecution.
	  * EndDateExecution
	  */
	public void setEndDateExecution (Timestamp EndDateExecution);

	/** Get EndDateExecution.
	  * EndDateExecution
	  */
	public Timestamp getEndDateExecution();

    /** Column name HR_Block_ID */
    public static final String COLUMNNAME_HR_Block_ID = "HR_Block_ID";

	/** Set HR Block	  */
	public void setHR_Block_ID (int HR_Block_ID);

	/** Get HR Block	  */
	public int getHR_Block_ID();

	public org.eevolution.model.I_HR_Department getHR_Block() throws RuntimeException;

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

    /** Column name HR_Header_ID */
    public static final String COLUMNNAME_HR_Header_ID = "HR_Header_ID";

	/** Set HR_Header_ID.
	  * Руководитель департамента
	  */
	public void setHR_Header_ID (int HR_Header_ID);

	/** Get HR_Header_ID.
	  * Руководитель департамента
	  */
	public int getHR_Header_ID();

	public org.compiere.model.I_C_BPartner getHR_Header() throws RuntimeException;

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

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name paymentarrangements */
    public static final String COLUMNNAME_paymentarrangements = "paymentarrangements";

	/** Set paymentarrangements.
	  * Порядок расчетов
	  */
	public void setpaymentarrangements (String paymentarrangements);

	/** Get paymentarrangements.
	  * Порядок расчетов
	  */
	public String getpaymentarrangements();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name quantityqualitygoods */
    public static final String COLUMNNAME_quantityqualitygoods = "quantityqualitygoods";

	/** Set quantityqualitygoods.
	  * Количество и качество товаров и услуг
	  */
	public void setquantityqualitygoods (String quantityqualitygoods);

	/** Get quantityqualitygoods.
	  * Количество и качество товаров и услуг
	  */
	public String getquantityqualitygoods();

    /** Column name SpecialConditions */
    public static final String COLUMNNAME_SpecialConditions = "SpecialConditions";

	/** Set SpecialConditions.
	  * Особые условия договора
	  */
	public void setSpecialConditions (String SpecialConditions);

	/** Get SpecialConditions.
	  * Особые условия договора
	  */
	public String getSpecialConditions();

    /** Column name Summary */
    public static final String COLUMNNAME_Summary = "Summary";

	/** Set Summary.
	  * Textual summary of this request
	  */
	public void setSummary (BigDecimal Summary);

	/** Get Summary.
	  * Textual summary of this request
	  */
	public BigDecimal getSummary();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name VersionNo */
    public static final String COLUMNNAME_VersionNo = "VersionNo";

	/** Set Version No.
	  * Version Number
	  */
	public void setVersionNo (String VersionNo);

	/** Get Version No.
	  * Version Number
	  */
	public String getVersionNo();
}
