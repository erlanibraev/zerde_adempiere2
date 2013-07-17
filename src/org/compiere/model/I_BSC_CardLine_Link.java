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

/** Generated Interface for BSC_CardLine_Link
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BSC_CardLine_Link 
{

    /** TableName=BSC_CardLine_Link */
    public static final String Table_Name = "BSC_CardLine_Link";

    /** AD_Table_ID=1000235 */
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

    /** Column name BSC_CardLine_ID */
    public static final String COLUMNNAME_BSC_CardLine_ID = "BSC_CardLine_ID";

	/** Set BSC_CardLine ID	  */
	public void setBSC_CardLine_ID (int BSC_CardLine_ID);

	/** Get BSC_CardLine ID	  */
	public int getBSC_CardLine_ID();

	public I_BSC_CardLine getBSC_CardLine() throws RuntimeException;

    /** Column name BSC_CardLine_Link_ID */
    public static final String COLUMNNAME_BSC_CardLine_Link_ID = "BSC_CardLine_Link_ID";

	/** Set BSC_CardLine_Link ID	  */
	public void setBSC_CardLine_Link_ID (int BSC_CardLine_Link_ID);

	/** Get BSC_CardLine_Link ID	  */
	public int getBSC_CardLine_Link_ID();

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

    /** Column name link_BSC_CardLine_ID */
    public static final String COLUMNNAME_link_BSC_CardLine_ID = "link_BSC_CardLine_ID";

	/** Set link_BSC_CardLine_ID.
	  * link_BSC_CardLine_ID
	  */
	public void setlink_BSC_CardLine_ID (int link_BSC_CardLine_ID);

	/** Get link_BSC_CardLine_ID.
	  * link_BSC_CardLine_ID
	  */
	public int getlink_BSC_CardLine_ID();

	public I_BSC_CardLine getlink_BSC_CardLine() throws RuntimeException;

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
