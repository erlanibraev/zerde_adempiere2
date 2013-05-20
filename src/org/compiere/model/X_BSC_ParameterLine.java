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

/** Generated Model for BSC_ParameterLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_ParameterLine extends PO implements I_BSC_ParameterLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_BSC_ParameterLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_ParameterLine (Properties ctx, int BSC_ParameterLine_ID, String trxName)
    {
      super (ctx, BSC_ParameterLine_ID, trxName);
      /** if (BSC_ParameterLine_ID == 0)
        {
			setBSC_ParameterLine_ID (0);
			setCalcButton (null);
// N
        } */
    }

    /** Load Constructor */
    public X_BSC_ParameterLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_ParameterLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set BSC_ParameterLine ID.
		@param BSC_ParameterLine_ID BSC_ParameterLine ID	  */
	public void setBSC_ParameterLine_ID (int BSC_ParameterLine_ID)
	{
		if (BSC_ParameterLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_ParameterLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_ParameterLine_ID, Integer.valueOf(BSC_ParameterLine_ID));
	}

	/** Get BSC_ParameterLine ID.
		@return BSC_ParameterLine ID	  */
	public int getBSC_ParameterLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_ParameterLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
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

	/** Set Goal.
		@param Goal 
		Goal
	  */
	public void setGoal (boolean Goal)
	{
		set_Value (COLUMNNAME_Goal, Boolean.valueOf(Goal));
	}

	/** Get Goal.
		@return Goal
	  */
	public boolean isGoal () 
	{
		Object oo = get_Value(COLUMNNAME_Goal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsFormula.
		@param IsFormula 
		IsFormula
	  */
	public void setIsFormula (boolean IsFormula)
	{
		set_Value (COLUMNNAME_IsFormula, Boolean.valueOf(IsFormula));
	}

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
	public void setValueNumber (String ValueNumber)
	{
		set_Value (COLUMNNAME_ValueNumber, ValueNumber);
	}

	/** Get Value.
		@return Numeric Value
	  */
	public String getValueNumber () 
	{
		return (String)get_Value(COLUMNNAME_ValueNumber);
	}
}