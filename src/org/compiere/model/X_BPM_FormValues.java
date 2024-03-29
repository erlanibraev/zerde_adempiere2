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

/** Generated Model for BPM_FormValues
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_FormValues extends PO implements I_BPM_FormValues, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130815L;

    /** the default Constructor */
    public X_BPM_FormValues(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_FormValues (Properties ctx, int BPM_FormValues_ID, String trxName)
    {
      super (ctx, BPM_FormValues_ID, trxName);
      /** if (BPM_FormValues_ID == 0)
        {
			setBPM_Form_ID (0);
			setBPM_FormColumn_ID (0);
			setBPM_FormLine_ID (0);
			setBPM_FormValues_ID (0);
			setBPM_Project_ID (0);
			setBPM_VersionBudget_ID (0);
			setCellValue (Env.ZERO);
// 0
        } */
    }

    /** Load Constructor */
    public X_BPM_FormValues (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_FormValues[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AlternateValue.
		@param AlternateValue AlternateValue	  */
	public void setAlternateValue (String AlternateValue)
	{
		set_Value (COLUMNNAME_AlternateValue, AlternateValue);
	}

	/** Get AlternateValue.
		@return AlternateValue	  */
	public String getAlternateValue () 
	{
		return (String)get_Value(COLUMNNAME_AlternateValue);
	}

	public I_BPM_Form getBPM_Form() throws RuntimeException
    {
		return (I_BPM_Form)MTable.get(getCtx(), I_BPM_Form.Table_Name)
			.getPO(getBPM_Form_ID(), get_TrxName());	}

	/** Set BPM_Form ID.
		@param BPM_Form_ID BPM_Form ID	  */
	public void setBPM_Form_ID (int BPM_Form_ID)
	{
		if (BPM_Form_ID < 1) 
			set_Value (COLUMNNAME_BPM_Form_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Form_ID, Integer.valueOf(BPM_Form_ID));
	}

	/** Get BPM_Form ID.
		@return BPM_Form ID	  */
	public int getBPM_Form_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Form_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_FormColumn getBPM_FormColumn() throws RuntimeException
    {
		return (I_BPM_FormColumn)MTable.get(getCtx(), I_BPM_FormColumn.Table_Name)
			.getPO(getBPM_FormColumn_ID(), get_TrxName());	}

	/** Set BPM_FormColumn ID.
		@param BPM_FormColumn_ID BPM_FormColumn ID	  */
	public void setBPM_FormColumn_ID (int BPM_FormColumn_ID)
	{
		if (BPM_FormColumn_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormColumn_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormColumn_ID, Integer.valueOf(BPM_FormColumn_ID));
	}

	/** Get BPM_FormColumn ID.
		@return BPM_FormColumn ID	  */
	public int getBPM_FormColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_FormLine getBPM_FormLine() throws RuntimeException
    {
		return (I_BPM_FormLine)MTable.get(getCtx(), I_BPM_FormLine.Table_Name)
			.getPO(getBPM_FormLine_ID(), get_TrxName());	}

	/** Set BPM_FormLine ID.
		@param BPM_FormLine_ID BPM_FormLine ID	  */
	public void setBPM_FormLine_ID (int BPM_FormLine_ID)
	{
		if (BPM_FormLine_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormLine_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormLine_ID, Integer.valueOf(BPM_FormLine_ID));
	}

	/** Get BPM_FormLine ID.
		@return BPM_FormLine ID	  */
	public int getBPM_FormLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_FormValues ID.
		@param BPM_FormValues_ID BPM_FormValues ID	  */
	public void setBPM_FormValues_ID (int BPM_FormValues_ID)
	{
		if (BPM_FormValues_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_FormValues_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_FormValues_ID, Integer.valueOf(BPM_FormValues_ID));
	}

	/** Get BPM_FormValues ID.
		@return BPM_FormValues ID	  */
	public int getBPM_FormValues_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormValues_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_Project getBPM_Project() throws RuntimeException
    {
		return (I_BPM_Project)MTable.get(getCtx(), I_BPM_Project.Table_Name)
			.getPO(getBPM_Project_ID(), get_TrxName());	}

	/** Set BPM_Project ID.
		@param BPM_Project_ID BPM_Project ID	  */
	public void setBPM_Project_ID (int BPM_Project_ID)
	{
		if (BPM_Project_ID < 1) 
			set_Value (COLUMNNAME_BPM_Project_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Project_ID, Integer.valueOf(BPM_Project_ID));
	}

	/** Get BPM_Project ID.
		@return BPM_Project ID	  */
	public int getBPM_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_VersionBudget getBPM_VersionBudget() throws RuntimeException
    {
		return (I_BPM_VersionBudget)MTable.get(getCtx(), I_BPM_VersionBudget.Table_Name)
			.getPO(getBPM_VersionBudget_ID(), get_TrxName());	}

	/** Set BPM_VersionBudget ID.
		@param BPM_VersionBudget_ID BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID)
	{
		if (BPM_VersionBudget_ID < 1) 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, Integer.valueOf(BPM_VersionBudget_ID));
	}

	/** Get BPM_VersionBudget ID.
		@return BPM_VersionBudget ID	  */
	public int getBPM_VersionBudget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_VersionBudget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CellValue.
		@param CellValue CellValue	  */
	public void setCellValue (BigDecimal CellValue)
	{
		set_Value (COLUMNNAME_CellValue, CellValue);
	}

	/** Get CellValue.
		@return CellValue	  */
	public BigDecimal getCellValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CellValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}