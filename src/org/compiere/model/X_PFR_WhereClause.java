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

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for PFR_WhereClause
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_PFR_WhereClause extends PO implements I_PFR_WhereClause, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130808L;

    /** the default Constructor */
    public X_PFR_WhereClause(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_PFR_WhereClause (Properties ctx, int PFR_WhereClause_ID, String trxName)
    {
      super (ctx, PFR_WhereClause_ID, trxName);
      /** if (PFR_WhereClause_ID == 0)
        {
			setColumnName (null);
			setisStatic (false);
// N
			setOperation (null);
			setPFR_WhereClause_ID (0);
			setValue1 (null);
        } */
    }

    /** Load Constructor */
    public X_PFR_WhereClause (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PFR_WhereClause[")
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

	/** AndOr AD_Reference_ID=204 */
	public static final int ANDOR_AD_Reference_ID=204;
	/** And = A */
	public static final String ANDOR_And = "A";
	/** Or = O */
	public static final String ANDOR_Or = "O";
	/** Set And/Or.
		@param AndOr 
		Logical operation: AND or OR
	  */
	public void setAndOr (String AndOr)
	{

		set_Value (COLUMNNAME_AndOr, AndOr);
	}

	/** Get And/Or.
		@return Logical operation: AND or OR
	  */
	public String getAndOr () 
	{
		return (String)get_Value(COLUMNNAME_AndOr);
	}

	/** Set CloseBracket.
		@param CloseBracket 
		Закрывающая скобка
	  */
	public void setCloseBracket (String CloseBracket)
	{
		set_Value (COLUMNNAME_CloseBracket, CloseBracket);
	}

	/** Get CloseBracket.
		@return Закрывающая скобка
	  */
	public String getCloseBracket () 
	{
		return (String)get_Value(COLUMNNAME_CloseBracket);
	}

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
	}

	/** Set isStatic.
		@param isStatic isStatic	  */
	public void setisStatic (boolean isStatic)
	{
		set_Value (COLUMNNAME_isStatic, Boolean.valueOf(isStatic));
	}

	/** Get isStatic.
		@return isStatic	  */
	public boolean isStatic () 
	{
		Object oo = get_Value(COLUMNNAME_isStatic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set OpenBracket.
		@param OpenBracket 
		Открывающая скобка
	  */
	public void setOpenBracket (String OpenBracket)
	{
		set_Value (COLUMNNAME_OpenBracket, OpenBracket);
	}

	/** Get OpenBracket.
		@return Открывающая скобка
	  */
	public String getOpenBracket () 
	{
		return (String)get_Value(COLUMNNAME_OpenBracket);
	}

	/** Operation AD_Reference_ID=205 */
	public static final int OPERATION_AD_Reference_ID=205;
	/**  = = == */
	public static final String OPERATION_Eq = "==";
	/** >= = >= */
	public static final String OPERATION_GtEq = ">=";
	/** > = >> */
	public static final String OPERATION_Gt = ">>";
	/** < = << */
	public static final String OPERATION_Le = "<<";
	/**  ~ = ~~ */
	public static final String OPERATION_Like = "~~";
	/** <= = <= */
	public static final String OPERATION_LeEq = "<=";
	/** |<x>| = AB */
	public static final String OPERATION_X = "AB";
	/** sql = SQ */
	public static final String OPERATION_Sql = "SQ";
	/** != = != */
	public static final String OPERATION_NotEq = "!=";
	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{

		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
	}

	public I_PFR_Calculation getPFR_Calculation() throws RuntimeException
    {
		return (I_PFR_Calculation)MTable.get(getCtx(), I_PFR_Calculation.Table_Name)
			.getPO(getPFR_Calculation_ID(), get_TrxName());	}

	/** Set PFR_Calculation ID.
		@param PFR_Calculation_ID PFR_Calculation ID	  */
	public void setPFR_Calculation_ID (int PFR_Calculation_ID)
	{
		if (PFR_Calculation_ID < 1) 
			set_Value (COLUMNNAME_PFR_Calculation_ID, null);
		else 
			set_Value (COLUMNNAME_PFR_Calculation_ID, Integer.valueOf(PFR_Calculation_ID));
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

	/** Set PFR_WhereClause ID.
		@param PFR_WhereClause_ID PFR_WhereClause ID	  */
	public void setPFR_WhereClause_ID (int PFR_WhereClause_ID)
	{
		if (PFR_WhereClause_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PFR_WhereClause_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PFR_WhereClause_ID, Integer.valueOf(PFR_WhereClause_ID));
	}

	/** Get PFR_WhereClause ID.
		@return PFR_WhereClause ID	  */
	public int getPFR_WhereClause_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PFR_WhereClause_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value1.
		@param Value1 Value1	  */
	public void setValue1 (String Value1)
	{
		set_Value (COLUMNNAME_Value1, Value1);
	}

	/** Get Value1.
		@return Value1	  */
	public String getValue1 () 
	{
		return (String)get_Value(COLUMNNAME_Value1);
	}

	/** Set Value To.
		@param Value2 
		Value To
	  */
	public void setValue2 (String Value2)
	{
		set_Value (COLUMNNAME_Value2, Value2);
	}

	/** Get Value To.
		@return Value To
	  */
	public String getValue2 () 
	{
		return (String)get_Value(COLUMNNAME_Value2);
	}
}