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

/** Generated Interface for BPM_FormCell
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BPM_FormCell 
{

    /** TableName=BPM_FormCell */
    public static final String Table_Name = "BPM_FormCell";

    /** AD_Table_ID=1000322 */
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

    /** Column name BPM_FormCell_ID */
    public static final String COLUMNNAME_BPM_FormCell_ID = "BPM_FormCell_ID";

	/** Set BPM_FormCell ID	  */
	public void setBPM_FormCell_ID (int BPM_FormCell_ID);

	/** Get BPM_FormCell ID	  */
	public int getBPM_FormCell_ID();

    /** Column name BPM_FormColumn_ID */
    public static final String COLUMNNAME_BPM_FormColumn_ID = "BPM_FormColumn_ID";

	/** Set BPM_FormColumn ID	  */
	public void setBPM_FormColumn_ID (int BPM_FormColumn_ID);

	/** Get BPM_FormColumn ID	  */
	public int getBPM_FormColumn_ID();

	public I_BPM_FormColumn getBPM_FormColumn() throws RuntimeException;

    /** Column name BPM_FormLine_ID */
    public static final String COLUMNNAME_BPM_FormLine_ID = "BPM_FormLine_ID";

	/** Set BPM_FormLine ID	  */
	public void setBPM_FormLine_ID (int BPM_FormLine_ID);

	/** Get BPM_FormLine ID	  */
	public int getBPM_FormLine_ID();

	public I_BPM_FormLine getBPM_FormLine() throws RuntimeException;

    /** Column name BSC_Parameter_ID */
    public static final String COLUMNNAME_BSC_Parameter_ID = "BSC_Parameter_ID";

	/** Set BSC_Parameter ID	  */
	public void setBSC_Parameter_ID (int BSC_Parameter_ID);

	/** Get BSC_Parameter ID	  */
	public int getBSC_Parameter_ID();

	public I_BSC_Parameter getBSC_Parameter() throws RuntimeException;

    /** Column name CellValue */
    public static final String COLUMNNAME_CellValue = "CellValue";

	/** Set CellValue	  */
	public void setCellValue (BigDecimal CellValue);

	/** Get CellValue	  */
	public BigDecimal getCellValue();

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

    /** Column name isParameter */
    public static final String COLUMNNAME_isParameter = "isParameter";

	/** Set isParameter.
	  * Параметр
	  */
	public void setisParameter (boolean isParameter);

	/** Get isParameter.
	  * Параметр
	  */
	public boolean isParameter();

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
