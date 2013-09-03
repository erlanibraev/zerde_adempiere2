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

/** Generated Model for BPM_ColumnParameters
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_ColumnParameters extends PO implements I_BPM_ColumnParameters, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130903L;

    /** the default Constructor */
    public X_BPM_ColumnParameters(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_ColumnParameters (Properties ctx, int BPM_ColumnParameters_ID, String trxName)
    {
      super (ctx, BPM_ColumnParameters_ID, trxName);
      /** if (BPM_ColumnParameters_ID == 0)
        {
			setBPM_ColumnParameters_ID (0);
			setBPM_FormColumn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_ColumnParameters (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_ColumnParameters[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_ColumnParameters ID.
		@param BPM_ColumnParameters_ID BPM_ColumnParameters ID	  */
	public void setBPM_ColumnParameters_ID (int BPM_ColumnParameters_ID)
	{
		if (BPM_ColumnParameters_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_ColumnParameters_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_ColumnParameters_ID, Integer.valueOf(BPM_ColumnParameters_ID));
	}

	/** Get BPM_ColumnParameters ID.
		@return BPM_ColumnParameters ID	  */
	public int getBPM_ColumnParameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ColumnParameters_ID);
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

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
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

	/** ParameterType AD_Reference_ID=1000180 */
	public static final int PARAMETERTYPE_AD_Reference_ID=1000180;
	/** Project = BPM_Project_ID */
	public static final String PARAMETERTYPE_Project = "BPM_Project_ID";
	/** Charge = C_Charge_ID */
	public static final String PARAMETERTYPE_Charge = "C_Charge_ID";
	/** Period = C_Period_ID */
	public static final String PARAMETERTYPE_Period = "C_Period_ID";
	/** Set ParameterType.
		@param ParameterType 
		Тип параметра
	  */
	public void setParameterType (String ParameterType)
	{

		set_Value (COLUMNNAME_ParameterType, ParameterType);
	}

	/** Get ParameterType.
		@return Тип параметра
	  */
	public String getParameterType () 
	{
		return (String)get_Value(COLUMNNAME_ParameterType);
	}
}