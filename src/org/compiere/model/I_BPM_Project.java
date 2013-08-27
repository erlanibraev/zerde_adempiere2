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

/** Generated Interface for BPM_Project
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BPM_Project 
{

    /** TableName=BPM_Project */
    public static final String Table_Name = "BPM_Project";

    /** AD_Table_ID=1000325 */
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

    /** Column name BPM_VersionBudget_ID */
    public static final String COLUMNNAME_BPM_VersionBudget_ID = "BPM_VersionBudget_ID";

	/** Set BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID);

	/** Get BPM_VersionBudget ID	  */
	public int getBPM_VersionBudget_ID();

	public I_BPM_VersionBudget getBPM_VersionBudget() throws RuntimeException;

    /** Column name Counter */
    public static final String COLUMNNAME_Counter = "Counter";

	/** Set Counter.
	  * Count Value
	  */
	public void setCounter (int Counter);

	/** Get Counter.
	  * Count Value
	  */
	public int getCounter();

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

    /** Column name isActual */
    public static final String COLUMNNAME_isActual = "isActual";

	/** Set isActual	  */
	public void setisActual (boolean isActual);

	/** Get isActual	  */
	public boolean isActual();

    /** Column name isWork */
    public static final String COLUMNNAME_isWork = "isWork";

	/** Set isWork	  */
	public void setisWork (boolean isWork);

	/** Get isWork	  */
	public boolean isWork();

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
