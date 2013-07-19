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
import org.compiere.util.KeyNamePair;

/** Generated Model for BSC_Coefficient
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Coefficient extends PO implements I_BSC_Coefficient, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130719L;

    /** the default Constructor */
    public X_BSC_Coefficient(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Coefficient (Properties ctx, int BSC_Coefficient_ID, String trxName)
    {
      super (ctx, BSC_Coefficient_ID, trxName);
      /** if (BSC_Coefficient_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_BSC_Coefficient (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_Coefficient[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BSC_Coefficient ID.
		@param BSC_Coefficient_ID BSC_Coefficient ID	  */
	public void setBSC_Coefficient_ID (int BSC_Coefficient_ID)
	{
		if (BSC_Coefficient_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Coefficient_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Coefficient_ID, Integer.valueOf(BSC_Coefficient_ID));
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

	/** Set quarter1.
		@param quarter1 
		quarter1
	  */
	public void setquarter1 (boolean quarter1)
	{
		set_Value (COLUMNNAME_quarter1, Boolean.valueOf(quarter1));
	}

	/** Get quarter1.
		@return quarter1
	  */
	public boolean isquarter1 () 
	{
		Object oo = get_Value(COLUMNNAME_quarter1);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set quarter2.
		@param quarter2 
		quarter2
	  */
	public void setquarter2 (boolean quarter2)
	{
		set_Value (COLUMNNAME_quarter2, Boolean.valueOf(quarter2));
	}

	/** Get quarter2.
		@return quarter2
	  */
	public boolean isquarter2 () 
	{
		Object oo = get_Value(COLUMNNAME_quarter2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set quarter3.
		@param quarter3 
		quarter3
	  */
	public void setquarter3 (boolean quarter3)
	{
		set_Value (COLUMNNAME_quarter3, Boolean.valueOf(quarter3));
	}

	/** Get quarter3.
		@return quarter3
	  */
	public boolean isquarter3 () 
	{
		Object oo = get_Value(COLUMNNAME_quarter3);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set quarter4.
		@param quarter4 
		quarter4
	  */
	public void setquarter4 (boolean quarter4)
	{
		set_Value (COLUMNNAME_quarter4, Boolean.valueOf(quarter4));
	}

	/** Get quarter4.
		@return quarter4
	  */
	public boolean isquarter4 () 
	{
		Object oo = get_Value(COLUMNNAME_quarter4);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}