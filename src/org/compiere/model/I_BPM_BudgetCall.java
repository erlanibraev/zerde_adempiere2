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

/** Generated Interface for BPM_BudgetCall
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BPM_BudgetCall 
{

    /** TableName=BPM_BudgetCall */
    public static final String Table_Name = "BPM_BudgetCall";

    /** AD_Table_ID=1000234 */
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

	/** Set AGRApprove	  */
	public void setAGRApprove (String AGRApprove);

	/** Get AGRApprove	  */
	public String getAGRApprove();

    /** Column name BPM_ABP_ID */
    public static final String COLUMNNAME_BPM_ABP_ID = "BPM_ABP_ID";

	/** Set BPM_ABP ID	  */
	public void setBPM_ABP_ID (int BPM_ABP_ID);

	/** Get BPM_ABP ID	  */
	public int getBPM_ABP_ID();

	public I_BPM_ABP getBPM_ABP() throws RuntimeException;

    /** Column name BPM_BudgetCall_ID */
    public static final String COLUMNNAME_BPM_BudgetCall_ID = "BPM_BudgetCall_ID";

	/** Set BPM_BudgetCall ID	  */
	public void setBPM_BudgetCall_ID (int BPM_BudgetCall_ID);

	/** Get BPM_BudgetCall ID	  */
	public int getBPM_BudgetCall_ID();

    /** Column name BPM_Parent_ID */
    public static final String COLUMNNAME_BPM_Parent_ID = "BPM_Parent_ID";

	/** Set BPM_Parent_ID	  */
	public void setBPM_Parent_ID (int BPM_Parent_ID);

	/** Get BPM_Parent_ID	  */
	public int getBPM_Parent_ID();

	public I_BPM_Project getBPM_Parent() throws RuntimeException;

    /** Column name BPM_Project_ID */
    public static final String COLUMNNAME_BPM_Project_ID = "BPM_Project_ID";

	/** Set BPM_Project ID	  */
	public void setBPM_Project_ID (int BPM_Project_ID);

	/** Get BPM_Project ID	  */
	public int getBPM_Project_ID();

	public I_BPM_Project getBPM_Project() throws RuntimeException;

    /** Column name BPM_VersionBudgetLine_ID */
    public static final String COLUMNNAME_BPM_VersionBudgetLine_ID = "BPM_VersionBudgetLine_ID";

	/** Set BPM_VersionBudgetLine ID	  */
	public void setBPM_VersionBudgetLine_ID (int BPM_VersionBudgetLine_ID);

	/** Get BPM_VersionBudgetLine ID	  */
	public int getBPM_VersionBudgetLine_ID();

	public I_BPM_VersionBudgetLine getBPM_VersionBudgetLine() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_ProjectParent_ID */
    public static final String COLUMNNAME_C_ProjectParent_ID = "C_ProjectParent_ID";

	/** Set C_ProjectParent_ID	  */
	public void setC_ProjectParent_ID (int C_ProjectParent_ID);

	/** Get C_ProjectParent_ID	  */
	public int getC_ProjectParent_ID();

	public org.compiere.model.I_C_Project getC_ProjectParent() throws RuntimeException;

    /** Column name C_Year_ID */
    public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

	/** Set Year.
	  * Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID);

	/** Get Year.
	  * Calendar Year
	  */
	public int getC_Year_ID();

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException;

    /** Column name CategoryName */
    public static final String COLUMNNAME_CategoryName = "CategoryName";

	/** Set Category Name.
	  * Name of the Category
	  */
	public void setCategoryName (String CategoryName);

	/** Get Category Name.
	  * Name of the Category
	  */
	public String getCategoryName();

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
}
