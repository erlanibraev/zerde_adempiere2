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

/** Generated Interface for AGR_StageList
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_AGR_StageList 
{

    /** TableName=AGR_StageList */
    public static final String Table_Name = "AGR_StageList";

    /** AD_Table_ID=1000249 */
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

    /** Column name AGR_Stage_ID */
    public static final String COLUMNNAME_AGR_Stage_ID = "AGR_Stage_ID";

	/** Set AGR_Stage_ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID);

	/** Get AGR_Stage_ID	  */
	public int getAGR_Stage_ID();

	public I_AGR_Stage getAGR_Stage() throws RuntimeException;

    /** Column name AGR_StageList_ID */
    public static final String COLUMNNAME_AGR_StageList_ID = "AGR_StageList_ID";

	/** Set AGR_StageList ID	  */
	public void setAGR_StageList_ID (int AGR_StageList_ID);

	/** Get AGR_StageList ID	  */
	public int getAGR_StageList_ID();

    /** Column name Alternate_ID */
    public static final String COLUMNNAME_Alternate_ID = "Alternate_ID";

	/** Set Alternate_ID.
	  * Альтернативное ответственное лицо
	  */
	public void setAlternate_ID (int Alternate_ID);

	/** Get Alternate_ID.
	  * Альтернативное ответственное лицо
	  */
	public int getAlternate_ID();

	public org.compiere.model.I_C_BPartner getAlternate() throws RuntimeException;

    /** Column name Alternate2_ID */
    public static final String COLUMNNAME_Alternate2_ID = "Alternate2_ID";

	/** Set Alternate2_ID.
	  * Альтернативное ответственное лицо (2)
	  */
	public void setAlternate2_ID (int Alternate2_ID);

	/** Get Alternate2_ID.
	  * Альтернативное ответственное лицо (2)
	  */
	public int getAlternate2_ID();

	public org.compiere.model.I_C_BPartner getAlternate2() throws RuntimeException;

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

    /** Column name IsAlternate2Active */
    public static final String COLUMNNAME_IsAlternate2Active = "IsAlternate2Active";

	/** Set IsAlternate2Active.
	  * Активно
	  */
	public void setIsAlternate2Active (boolean IsAlternate2Active);

	/** Get IsAlternate2Active.
	  * Активно
	  */
	public boolean isAlternate2Active();

    /** Column name IsAlternateActive */
    public static final String COLUMNNAME_IsAlternateActive = "IsAlternateActive";

	/** Set IsAlternateActive.
	  * Активно
	  */
	public void setIsAlternateActive (boolean IsAlternateActive);

	/** Get IsAlternateActive.
	  * Активно
	  */
	public boolean isAlternateActive();

    /** Column name IsHeaderActive */
    public static final String COLUMNNAME_IsHeaderActive = "IsHeaderActive";

	/** Set IsHeaderActive.
	  * Активно
	  */
	public void setIsHeaderActive (boolean IsHeaderActive);

	/** Get IsHeaderActive.
	  * Активно
	  */
	public boolean isHeaderActive();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (BigDecimal Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public BigDecimal getPriority();

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
