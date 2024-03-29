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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PFR_Calculation
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_PFR_Calculation extends PO implements I_PFR_Calculation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130813L;

    /** the default Constructor */
    public X_PFR_Calculation(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_PFR_Calculation (Properties ctx, int PFR_Calculation_ID, String trxName)
    {
      super (ctx, PFR_Calculation_ID, trxName);
      /** if (PFR_Calculation_ID == 0)
        {
			setAD_Column_ID (0);
			setAD_Table_ID (0);
			setisSubQuery (false);
// N
			setName (null);
			setPFR_CalcType_ID (0);
			setPFR_Calculation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PFR_Calculation (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PFR_Calculation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set isSubQuery.
		@param isSubQuery isSubQuery	  */
	public void setisSubQuery (boolean isSubQuery)
	{
		set_Value (COLUMNNAME_isSubQuery, Boolean.valueOf(isSubQuery));
	}

	/** Get isSubQuery.
		@return isSubQuery	  */
	public boolean isSubQuery () 
	{
		Object oo = get_Value(COLUMNNAME_isSubQuery);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Koeff.
		@param Koeff 
		Коэффициент
	  */
	public void setKoeff (BigDecimal Koeff)
	{
		set_Value (COLUMNNAME_Koeff, Koeff);
	}

	/** Get Koeff.
		@return Коэффициент
	  */
	public BigDecimal getKoeff () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Koeff);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Operator AD_Reference_ID=1000150 */
	public static final int OPERATOR_AD_Reference_ID=1000150;
	/** Division = / */
	public static final String OPERATOR_Division = "/";
	/** Multiplication = * */
	public static final String OPERATOR_Multiplication = "*";
	/** Plus = + */
	public static final String OPERATOR_Plus = "+";
	/** Minus = - */
	public static final String OPERATOR_Minus = "-";
	/** Percent = % */
	public static final String OPERATOR_Percent = "%";
	/** Set Operator.
		@param Operator 
		Арифметический оператор
	  */
	public void setOperator (String Operator)
	{

		set_Value (COLUMNNAME_Operator, Operator);
	}

	/** Get Operator.
		@return Арифметический оператор
	  */
	public String getOperator () 
	{
		return (String)get_Value(COLUMNNAME_Operator);
	}

	public I_PFR_CalcType getPFR_CalcType() throws RuntimeException
    {
		return (I_PFR_CalcType)MTable.get(getCtx(), I_PFR_CalcType.Table_Name)
			.getPO(getPFR_CalcType_ID(), get_TrxName());	}

	/** Set PFR_CalcType ID.
		@param PFR_CalcType_ID 
		Вид вычисления
	  */
	public void setPFR_CalcType_ID (int PFR_CalcType_ID)
	{
		if (PFR_CalcType_ID < 1) 
			set_Value (COLUMNNAME_PFR_CalcType_ID, null);
		else 
			set_Value (COLUMNNAME_PFR_CalcType_ID, Integer.valueOf(PFR_CalcType_ID));
	}

	/** Get PFR_CalcType ID.
		@return Вид вычисления
	  */
	public int getPFR_CalcType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PFR_CalcType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PFR_Calculation ID.
		@param PFR_Calculation_ID PFR_Calculation ID	  */
	public void setPFR_Calculation_ID (int PFR_Calculation_ID)
	{
		if (PFR_Calculation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PFR_Calculation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PFR_Calculation_ID, Integer.valueOf(PFR_Calculation_ID));
	}

	/** Get PFR_Calculation ID.
		@return PFR_Calculation ID	  */
	public int getPFR_Calculation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PFR_Calculation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Settings.
		@param Settings 
		Настройки
	  */
	public void setSettings (String Settings)
	{
		set_Value (COLUMNNAME_Settings, Settings);
	}

	/** Get Settings.
		@return Настройки
	  */
	public String getSettings () 
	{
		return (String)get_Value(COLUMNNAME_Settings);
	}
}