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

/** Generated Interface for TRM_Agreement
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_TRM_Agreement 
{

    /** TableName=TRM_Agreement */
    public static final String Table_Name = "TRM_Agreement";

    /** AD_Table_ID=1000240 */
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

    /** Column name Sign_N */
    public static final String COLUMNNAME_Sign_N = "Sign_N";

	/** Set Sign_N.
	  * Disapprove
	  */
	public void setSign_N (boolean Sign_N);

	/** Get Sign_N.
	  * Disapprove
	  */
	public boolean isSign_N();

    /** Column name Sign_Y */
    public static final String COLUMNNAME_Sign_Y = "Sign_Y";

	/** Set Sign_Y	  */
	public void setSign_Y (boolean Sign_Y);

	/** Get Sign_Y	  */
	public boolean isSign_Y();

    /** Column name Signer_ID */
    public static final String COLUMNNAME_Signer_ID = "Signer_ID";

	/** Set Signer_ID.
	  * Согласовывающий
	  */
	public void setSigner_ID (int Signer_ID);

	/** Get Signer_ID.
	  * Согласовывающий
	  */
	public int getSigner_ID();

	public org.compiere.model.I_AD_User getSigner() throws RuntimeException;

    /** Column name TRM_Agreement_ID */
    public static final String COLUMNNAME_TRM_Agreement_ID = "TRM_Agreement_ID";

	/** Set TRM_Agreement ID	  */
	public void setTRM_Agreement_ID (int TRM_Agreement_ID);

	/** Get TRM_Agreement ID	  */
	public int getTRM_Agreement_ID();

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