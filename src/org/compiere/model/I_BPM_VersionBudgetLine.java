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

/** Generated Interface for BPM_VersionBudgetLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BPM_VersionBudgetLine 
{

    /** TableName=BPM_VersionBudgetLine */
    public static final String Table_Name = "BPM_VersionBudgetLine";

    /** AD_Table_ID=1000231 */
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

    /** Column name BPM_Version */
    public static final String COLUMNNAME_BPM_Version = "BPM_Version";

	/** Set BPM_Version	  */
	public void setBPM_Version (String BPM_Version);

	/** Get BPM_Version	  */
	public String getBPM_Version();

    /** Column name BPM_VersionBudget_ID */
    public static final String COLUMNNAME_BPM_VersionBudget_ID = "BPM_VersionBudget_ID";

	/** Set BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID);

	/** Get BPM_VersionBudget ID	  */
	public int getBPM_VersionBudget_ID();

    /** Column name BPM_VersionBudgetLine_ID */
    public static final String COLUMNNAME_BPM_VersionBudgetLine_ID = "BPM_VersionBudgetLine_ID";

	/** Set BPM_VersionBudgetLine ID	  */
	public void setBPM_VersionBudgetLine_ID (int BPM_VersionBudgetLine_ID);

	/** Get BPM_VersionBudgetLine ID	  */
	public int getBPM_VersionBudgetLine_ID();

    /** Column name BPM_VersionType */
    public static final String COLUMNNAME_BPM_VersionType = "BPM_VersionType";

	/** Set BPM_VersionType	  */
	public void setBPM_VersionType (String BPM_VersionType);

	/** Get BPM_VersionType	  */
	public String getBPM_VersionType();

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
