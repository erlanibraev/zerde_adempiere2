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
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for PFR_Storage
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_PFR_Storage extends PO implements I_PFR_Storage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130806L;

    /** the default Constructor */
    public X_PFR_Storage(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_PFR_Storage (Properties ctx, int PFR_Storage_ID, String trxName)
    {
      super (ctx, PFR_Storage_ID, trxName);
      /** if (PFR_Storage_ID == 0)
        {
			setPFR_Storage_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PFR_Storage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PFR_Storage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CalculatedValue.
		@param CalculatedValue 
		Рассчитанное значение
	  */
	public void setCalculatedValue (String CalculatedValue)
	{
		set_Value (COLUMNNAME_CalculatedValue, CalculatedValue);
	}

	/** Get CalculatedValue.
		@return Рассчитанное значение
	  */
	public String getCalculatedValue () 
	{
		return (String)get_Value(COLUMNNAME_CalculatedValue);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
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

	/** Set PFR_Storage ID.
		@param PFR_Storage_ID PFR_Storage ID	  */
	public void setPFR_Storage_ID (int PFR_Storage_ID)
	{
		if (PFR_Storage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PFR_Storage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PFR_Storage_ID, Integer.valueOf(PFR_Storage_ID));
	}

	/** Get PFR_Storage ID.
		@return PFR_Storage ID	  */
	public int getPFR_Storage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PFR_Storage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}