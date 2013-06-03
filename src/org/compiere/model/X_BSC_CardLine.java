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

/** Generated Model for BSC_CardLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_CardLine extends PO implements I_BSC_CardLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130530L;

    /** the default Constructor */
    public X_BSC_CardLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_CardLine (Properties ctx, int BSC_CardLine_ID, String trxName)
    {
      super (ctx, BSC_CardLine_ID, trxName);
      /** if (BSC_CardLine_ID == 0)
        {
			setBSC_CardLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_CardLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_CardLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BSC_Card getBSC_Card() throws RuntimeException
    {
		return (I_BSC_Card)MTable.get(getCtx(), I_BSC_Card.Table_Name)
			.getPO(getBSC_Card_ID(), get_TrxName());	}

	/** Set BSC_Card ID.
		@param BSC_Card_ID BSC_Card ID	  */
	public void setBSC_Card_ID (int BSC_Card_ID)
	{
		if (BSC_Card_ID < 1) 
			set_Value (COLUMNNAME_BSC_Card_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Card_ID, Integer.valueOf(BSC_Card_ID));
	}

	/** Get BSC_Card ID.
		@return BSC_Card ID	  */
	public int getBSC_Card_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Card_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BSC_CardLine ID.
		@param BSC_CardLine_ID BSC_CardLine ID	  */
	public void setBSC_CardLine_ID (int BSC_CardLine_ID)
	{
		if (BSC_CardLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_CardLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_CardLine_ID, Integer.valueOf(BSC_CardLine_ID));
	}

	/** Get BSC_CardLine ID.
		@return BSC_CardLine ID	  */
	public int getBSC_CardLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_CardLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Coefficient getBSC_Coefficient() throws RuntimeException
    {
		return (I_BSC_Coefficient)MTable.get(getCtx(), I_BSC_Coefficient.Table_Name)
			.getPO(getBSC_Coefficient_ID(), get_TrxName());	}

	/** Set BSC_Coefficient ID.
		@param BSC_Coefficient_ID BSC_Coefficient ID	  */
	public void setBSC_Coefficient_ID (int BSC_Coefficient_ID)
	{
		if (BSC_Coefficient_ID < 1) 
			set_Value (COLUMNNAME_BSC_Coefficient_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Coefficient_ID, Integer.valueOf(BSC_Coefficient_ID));
	}

	/** Get BSC_Coefficient ID.
		@return BSC_Coefficient ID	  */
	public int getBSC_Coefficient_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Coefficient_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Formula getBSC_Formula() throws RuntimeException
    {
		return (I_BSC_Formula)MTable.get(getCtx(), I_BSC_Formula.Table_Name)
			.getPO(getBSC_Formula_ID(), get_TrxName());	}

	/** Set BSC_Formula ID.
		@param BSC_Formula_ID BSC_Formula ID	  */
	public void setBSC_Formula_ID (int BSC_Formula_ID)
	{
		if (BSC_Formula_ID < 1) 
			set_Value (COLUMNNAME_BSC_Formula_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Formula_ID, Integer.valueOf(BSC_Formula_ID));
	}

	/** Get BSC_Formula ID.
		@return BSC_Formula ID	  */
	public int getBSC_Formula_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Formula_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Parameter getBSC_Parameter() throws RuntimeException
    {
		return (I_BSC_Parameter)MTable.get(getCtx(), I_BSC_Parameter.Table_Name)
			.getPO(getBSC_Parameter_ID(), get_TrxName());	}

	/** Set BSC_Parameter ID.
		@param BSC_Parameter_ID BSC_Parameter ID	  */
	public void setBSC_Parameter_ID (int BSC_Parameter_ID)
	{
		if (BSC_Parameter_ID < 1) 
			set_Value (COLUMNNAME_BSC_Parameter_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Parameter_ID, Integer.valueOf(BSC_Parameter_ID));
	}

	/** Get BSC_Parameter ID.
		@return BSC_Parameter ID	  */
	public int getBSC_Parameter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Parameter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Parameter getBSC_Parameter_Out() throws RuntimeException
    {
		return (I_BSC_Parameter)MTable.get(getCtx(), I_BSC_Parameter.Table_Name)
			.getPO(getBSC_Parameter_Out_ID(), get_TrxName());	}

	/** Set BSC_Parameter_Out_ID.
		@param BSC_Parameter_Out_ID 
		BSC_Parameter_Out_ID
	  */
	public void setBSC_Parameter_Out_ID (int BSC_Parameter_Out_ID)
	{
		if (BSC_Parameter_Out_ID < 1) 
			set_Value (COLUMNNAME_BSC_Parameter_Out_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Parameter_Out_ID, Integer.valueOf(BSC_Parameter_Out_ID));
	}

	/** Get BSC_Parameter_Out_ID.
		@return BSC_Parameter_Out_ID
	  */
	public int getBSC_Parameter_Out_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Parameter_Out_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Perspective getBSC_Perspective() throws RuntimeException
    {
		return (I_BSC_Perspective)MTable.get(getCtx(), I_BSC_Perspective.Table_Name)
			.getPO(getBSC_Perspective_ID(), get_TrxName());	}

	/** Set BSC_Perspective ID.
		@param BSC_Perspective_ID BSC_Perspective ID	  */
	public void setBSC_Perspective_ID (int BSC_Perspective_ID)
	{
		if (BSC_Perspective_ID < 1) 
			set_Value (COLUMNNAME_BSC_Perspective_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Perspective_ID, Integer.valueOf(BSC_Perspective_ID));
	}

	/** Get BSC_Perspective ID.
		@return BSC_Perspective ID	  */
	public int getBSC_Perspective_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Perspective_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CalcButton.
		@param CalcButton 
		CalcButton
	  */
	public void setCalcButton (String CalcButton)
	{
		set_Value (COLUMNNAME_CalcButton, CalcButton);
	}

	/** Get CalcButton.
		@return CalcButton
	  */
	public String getCalcButton () 
	{
		return (String)get_Value(COLUMNNAME_CalcButton);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set IsFormula.
		@param IsFormula 
		IsFormula
	  */
	public void setIsFormula (boolean IsFormula)
	{
		throw new IllegalArgumentException ("IsFormula is virtual column");	}

	/** Get IsFormula.
		@return IsFormula
	  */
	public boolean isFormula () 
	{
		Object oo = get_Value(COLUMNNAME_IsFormula);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Unit AD_Reference_ID=1000135 */
	public static final int UNIT_AD_Reference_ID=1000135;
	/** % = % */
	public static final String UNIT_ = "%";
	/** in_time = in_time */
	public static final String UNIT_In_time = "in_time";
	/** pcs = pcs */
	public static final String UNIT_Pcs = "pcs";
	/** qty = qty */
	public static final String UNIT_Qty = "qty";
	/** units = units */
	public static final String UNIT_Units = "units";
	/** Yes/No = Yes/No */
	public static final String UNIT_YesNo = "Yes/No";
	/** Set Unit.
		@param Unit 
		Unit
	  */
	public void setUnit (String Unit)
	{

		set_Value (COLUMNNAME_Unit, Unit);
	}

	/** Get Unit.
		@return Unit
	  */
	public String getUnit () 
	{
		return (String)get_Value(COLUMNNAME_Unit);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Max. Value.
		@param ValueMax 
		Maximum Value for a field
	  */
	public void setValueMax (BigDecimal ValueMax)
	{
		set_Value (COLUMNNAME_ValueMax, ValueMax);
	}

	/** Get Max. Value.
		@return Maximum Value for a field
	  */
	public BigDecimal getValueMax () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min. Value.
		@param ValueMin 
		Minimum Value for a field
	  */
	public void setValueMin (BigDecimal ValueMin)
	{
		set_Value (COLUMNNAME_ValueMin, ValueMin);
	}

	/** Get Min. Value.
		@return Minimum Value for a field
	  */
	public BigDecimal getValueMin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Value.
		@param ValueNumber 
		Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber)
	{
		set_Value (COLUMNNAME_ValueNumber, ValueNumber);
	}

	/** Get Value.
		@return Numeric Value
	  */
	public BigDecimal getValueNumber () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueNumber);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_Value (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}