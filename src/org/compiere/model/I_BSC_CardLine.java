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

/** Generated Interface for BSC_CardLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BSC_CardLine 
{

    /** TableName=BSC_CardLine */
    public static final String Table_Name = "BSC_CardLine";

    /** AD_Table_ID=1000222 */
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

    /** Column name BSC_Card_ID */
    public static final String COLUMNNAME_BSC_Card_ID = "BSC_Card_ID";

	/** Set BSC_Card ID	  */
	public void setBSC_Card_ID (int BSC_Card_ID);

	/** Get BSC_Card ID	  */
	public int getBSC_Card_ID();

	public I_BSC_Card getBSC_Card() throws RuntimeException;

    /** Column name BSC_CardLine_ID */
    public static final String COLUMNNAME_BSC_CardLine_ID = "BSC_CardLine_ID";

	/** Set BSC_CardLine ID	  */
	public void setBSC_CardLine_ID (int BSC_CardLine_ID);

	/** Get BSC_CardLine ID	  */
	public int getBSC_CardLine_ID();

    /** Column name BSC_Coefficient_ID */
    public static final String COLUMNNAME_BSC_Coefficient_ID = "BSC_Coefficient_ID";

	/** Set BSC_Coefficient ID	  */
	public void setBSC_Coefficient_ID (int BSC_Coefficient_ID);

	/** Get BSC_Coefficient ID	  */
	public int getBSC_Coefficient_ID();

	public I_BSC_Coefficient getBSC_Coefficient() throws RuntimeException;

    /** Column name BSC_Formula_ID */
    public static final String COLUMNNAME_BSC_Formula_ID = "BSC_Formula_ID";

	/** Set BSC_Formula ID	  */
	public void setBSC_Formula_ID (int BSC_Formula_ID);

	/** Get BSC_Formula ID	  */
	public int getBSC_Formula_ID();

	public I_BSC_Formula getBSC_Formula() throws RuntimeException;

    /** Column name BSC_Parameter_ID */
    public static final String COLUMNNAME_BSC_Parameter_ID = "BSC_Parameter_ID";

	/** Set BSC_Parameter ID	  */
	public void setBSC_Parameter_ID (int BSC_Parameter_ID);

	/** Get BSC_Parameter ID	  */
	public int getBSC_Parameter_ID();

	public I_BSC_Parameter getBSC_Parameter() throws RuntimeException;

    /** Column name BSC_Parameter_Out_ID */
    public static final String COLUMNNAME_BSC_Parameter_Out_ID = "BSC_Parameter_Out_ID";

	/** Set BSC_Parameter_Out_ID.
	  * BSC_Parameter_Out_ID
	  */
	public void setBSC_Parameter_Out_ID (int BSC_Parameter_Out_ID);

	/** Get BSC_Parameter_Out_ID.
	  * BSC_Parameter_Out_ID
	  */
	public int getBSC_Parameter_Out_ID();

	public I_BSC_Parameter getBSC_Parameter_Out() throws RuntimeException;

    /** Column name BSC_Perspective_ID */
    public static final String COLUMNNAME_BSC_Perspective_ID = "BSC_Perspective_ID";

	/** Set BSC_Perspective ID	  */
	public void setBSC_Perspective_ID (int BSC_Perspective_ID);

	/** Get BSC_Perspective ID	  */
	public int getBSC_Perspective_ID();

	public I_BSC_Perspective getBSC_Perspective() throws RuntimeException;

    /** Column name CalcButton */
    public static final String COLUMNNAME_CalcButton = "CalcButton";

	/** Set CalcButton.
	  * CalcButton
	  */
	public void setCalcButton (String CalcButton);

	/** Get CalcButton.
	  * CalcButton
	  */
	public String getCalcButton();

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

    /** Column name IsFormula */
    public static final String COLUMNNAME_IsFormula = "IsFormula";

	/** Set IsFormula.
	  * IsFormula
	  */
	public void setIsFormula (boolean IsFormula);

	/** Get IsFormula.
	  * IsFormula
	  */
	public boolean isFormula();

    /** Column name LineValue */
    public static final String COLUMNNAME_LineValue = "LineValue";

	/** Set LineValue.
	  * LineValue
	  */
	public void setLineValue (BigDecimal LineValue);

	/** Get LineValue.
	  * LineValue
	  */
	public BigDecimal getLineValue();

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

    /** Column name Q */
    public static final String COLUMNNAME_Q = "Q";

	/** Set Q.
	  * Q
	  */
	public void setQ (BigDecimal Q);

	/** Get Q.
	  * Q
	  */
	public BigDecimal getQ();

    /** Column name Unit */
    public static final String COLUMNNAME_Unit = "Unit";

	/** Set Unit.
	  * Unit
	  */
	public void setUnit (String Unit);

	/** Get Unit.
	  * Unit
	  */
	public String getUnit();

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

    /** Column name ValueMax */
    public static final String COLUMNNAME_ValueMax = "ValueMax";

	/** Set Max. Value.
	  * Maximum Value for a field
	  */
	public void setValueMax (BigDecimal ValueMax);

	/** Get Max. Value.
	  * Maximum Value for a field
	  */
	public BigDecimal getValueMax();

    /** Column name ValueMin */
    public static final String COLUMNNAME_ValueMin = "ValueMin";

	/** Set Min. Value.
	  * Minimum Value for a field
	  */
	public void setValueMin (BigDecimal ValueMin);

	/** Get Min. Value.
	  * Minimum Value for a field
	  */
	public BigDecimal getValueMin();

    /** Column name ValueNumber */
    public static final String COLUMNNAME_ValueNumber = "ValueNumber";

	/** Set Value.
	  * Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber);

	/** Get Value.
	  * Numeric Value
	  */
	public BigDecimal getValueNumber();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
