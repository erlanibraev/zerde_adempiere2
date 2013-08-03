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

/** Generated Interface for BSC_ParameterLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_BSC_ParameterLine 
{

    /** TableName=BSC_ParameterLine */
    public static final String Table_Name = "BSC_ParameterLine";

    /** AD_Table_ID=1000218 */
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

    /** Column name BSC_ParameterLine_ID */
    public static final String COLUMNNAME_BSC_ParameterLine_ID = "BSC_ParameterLine_ID";

	/** Set BSC_ParameterLine ID	  */
	public void setBSC_ParameterLine_ID (int BSC_ParameterLine_ID);

	/** Get BSC_ParameterLine ID	  */
	public int getBSC_ParameterLine_ID();

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException;

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

    /** Column name Goal */
    public static final String COLUMNNAME_Goal = "Goal";

	/** Set Goal.
	  * Goal
	  */
	public void setGoal (boolean Goal);

	/** Get Goal.
	  * Goal
	  */
	public boolean isGoal();

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

    /** Column name IsImported */
    public static final String COLUMNNAME_IsImported = "IsImported";

	/** Set IsImported.
	  * IsImported
	  */
	public void setIsImported (boolean IsImported);

	/** Get IsImported.
	  * IsImported
	  */
	public boolean isImported();

    /** Column name PFR_Calculation_ID */
    public static final String COLUMNNAME_PFR_Calculation_ID = "PFR_Calculation_ID";

	/** Set PFR_Calculation ID	  */
	public void setPFR_Calculation_ID (int PFR_Calculation_ID);

	/** Get PFR_Calculation ID	  */
	public int getPFR_Calculation_ID();

	public I_PFR_Calculation getPFR_Calculation() throws RuntimeException;

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
	public void setValueNumber (String ValueNumber);

	/** Get Value.
	  * Numeric Value
	  */
	public String getValueNumber();
}
