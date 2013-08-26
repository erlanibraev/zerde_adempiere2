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

/** Generated Interface for AGR_Stage
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_AGR_Stage 
{

    /** TableName=AGR_Stage */
    public static final String Table_Name = "AGR_Stage";

    /** AD_Table_ID=1000242 */
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

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException;

    /** Column name AD_Ref_List_ID */
    public static final String COLUMNNAME_AD_Ref_List_ID = "AD_Ref_List_ID";

	/** Set Reference List.
	  * Reference List based on Table
	  */
	public void setAD_Ref_List_ID (int AD_Ref_List_ID);

	/** Get Reference List.
	  * Reference List based on Table
	  */
	public int getAD_Ref_List_ID();

	public org.compiere.model.I_AD_Ref_List getAD_Ref_List() throws RuntimeException;

    /** Column name AD_Ref_List_ID2 */
    public static final String COLUMNNAME_AD_Ref_List_ID2 = "AD_Ref_List_ID2";

	/** Set Reference List.
	  * Reference List based on Table
	  */
	public void setAD_Ref_List_ID2 (int AD_Ref_List_ID2);

	/** Get Reference List.
	  * Reference List based on Table
	  */
	public int getAD_Ref_List_ID2();

	public org.compiere.model.I_AD_Ref_List getAD_Ref_List_() throws RuntimeException;

    /** Column name AGR_Agreement_ID */
    public static final String COLUMNNAME_AGR_Agreement_ID = "AGR_Agreement_ID";

	/** Set AGR_Agreement_ID	  */
	public void setAGR_Agreement_ID (int AGR_Agreement_ID);

	/** Get AGR_Agreement_ID	  */
	public int getAGR_Agreement_ID();

	public I_AGR_Agreement getAGR_Agreement() throws RuntimeException;

    /** Column name AGR_Stage_ID */
    public static final String COLUMNNAME_AGR_Stage_ID = "AGR_Stage_ID";

	/** Set AGR_Stage ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID);

	/** Get AGR_Stage ID	  */
	public int getAGR_Stage_ID();

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

    /** Column name isMultiStage */
    public static final String COLUMNNAME_isMultiStage = "isMultiStage";

	/** Set isMultiStage.
	  * Множественное участие
	  */
	public void setisMultiStage (boolean isMultiStage);

	/** Get isMultiStage.
	  * Множественное участие
	  */
	public boolean isMultiStage();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

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

    /** Column name StageType */
    public static final String COLUMNNAME_StageType = "StageType";

	/** Set StageType.
	  * Тип этапа
	  */
	public void setStageType (String StageType);

	/** Get StageType.
	  * Тип этапа
	  */
	public String getStageType();

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
