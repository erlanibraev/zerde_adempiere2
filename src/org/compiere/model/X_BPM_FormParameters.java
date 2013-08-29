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

/** Generated Model for BPM_FormParameters
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_FormParameters extends PO implements I_BPM_FormParameters, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130829L;

    /** the default Constructor */
    public X_BPM_FormParameters(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_FormParameters (Properties ctx, int BPM_FormParameters_ID, String trxName)
    {
      super (ctx, BPM_FormParameters_ID, trxName);
      /** if (BPM_FormParameters_ID == 0)
        {
			setBPM_FormCell_ID (0);
			setBPM_FormParameters_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_FormParameters (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_FormParameters[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BPM_FormCell getBPM_FormCell() throws RuntimeException
    {
		return (I_BPM_FormCell)MTable.get(getCtx(), I_BPM_FormCell.Table_Name)
			.getPO(getBPM_FormCell_ID(), get_TrxName());	}

	/** Set BPM_FormCell ID.
		@param BPM_FormCell_ID BPM_FormCell ID	  */
	public void setBPM_FormCell_ID (int BPM_FormCell_ID)
	{
		if (BPM_FormCell_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormCell_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormCell_ID, Integer.valueOf(BPM_FormCell_ID));
	}

	/** Get BPM_FormCell ID.
		@return BPM_FormCell ID	  */
	public int getBPM_FormCell_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormCell_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_FormParameters ID.
		@param BPM_FormParameters_ID BPM_FormParameters ID	  */
	public void setBPM_FormParameters_ID (int BPM_FormParameters_ID)
	{
		if (BPM_FormParameters_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_FormParameters_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_FormParameters_ID, Integer.valueOf(BPM_FormParameters_ID));
	}

	/** Get BPM_FormParameters ID.
		@return BPM_FormParameters ID	  */
	public int getBPM_FormParameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormParameters_ID);
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
	/** Charge = C_Charge_ID */
	public static final String PARAMETERTYPE_Charge = "C_Charge_ID";
	/** Period = C_Period_ID */
	public static final String PARAMETERTYPE_Period = "C_Period_ID";
	/** Project = BPM_Project_ID */
	public static final String PARAMETERTYPE_Project = "BPM_Project_ID";
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