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

/** Generated Interface for PFR_Calculation
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_PFR_Calculation 
{

    /** TableName=PFR_Calculation */
    public static final String Table_Name = "PFR_Calculation";

    /** AD_Table_ID=1000304 */
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

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException;

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

    /** Column name columnDate */
    public static final String COLUMNNAME_columnDate = "columnDate";

	/** Set columnDate.
	  * Дата
	  */
	public void setcolumnDate (Timestamp columnDate);

	/** Get columnDate.
	  * Дата
	  */
	public Timestamp getcolumnDate();

    /** Column name columnString */
    public static final String COLUMNNAME_columnString = "columnString";

	/** Set columnString.
	  * Строка
	  */
	public void setcolumnString (String columnString);

	/** Get columnString.
	  * Строка
	  */
	public String getcolumnString();

    /** Column name columnSum */
    public static final String COLUMNNAME_columnSum = "columnSum";

	/** Set columnSum.
	  * Сумма. Число с плавающей точкой
	  */
	public void setcolumnSum (BigDecimal columnSum);

	/** Get columnSum.
	  * Сумма. Число с плавающей точкой
	  */
	public BigDecimal getcolumnSum();

    /** Column name columnYesNo */
    public static final String COLUMNNAME_columnYesNo = "columnYesNo";

	/** Set columnYesNo.
	  * Логическая переменная
	  */
	public void setcolumnYesNo (String columnYesNo);

	/** Get columnYesNo.
	  * Логическая переменная
	  */
	public String getcolumnYesNo();

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

    /** Column name dstColumnType */
    public static final String COLUMNNAME_dstColumnType = "dstColumnType";

	/** Set dstColumnType.
	  * Тип данных поля
	  */
	public void setdstColumnType (String dstColumnType);

	/** Get dstColumnType.
	  * Тип данных поля
	  */
	public String getdstColumnType();

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

    /** Column name PFR_CalcType_ID */
    public static final String COLUMNNAME_PFR_CalcType_ID = "PFR_CalcType_ID";

	/** Set PFR_CalcType ID.
	  * Вид вычисления
	  */
	public void setPFR_CalcType_ID (int PFR_CalcType_ID);

	/** Get PFR_CalcType ID.
	  * Вид вычисления
	  */
	public int getPFR_CalcType_ID();

	public I_PFR_CalcType getPFR_CalcType() throws RuntimeException;

    /** Column name PFR_Calculation_ID */
    public static final String COLUMNNAME_PFR_Calculation_ID = "PFR_Calculation_ID";

	/** Set PFR_Calculation ID	  */
	public void setPFR_Calculation_ID (int PFR_Calculation_ID);

	/** Get PFR_Calculation ID	  */
	public int getPFR_Calculation_ID();

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
