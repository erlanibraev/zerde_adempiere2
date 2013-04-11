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

/** Generated Model for BSC_UnitFormula
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_UnitFormula extends PO implements I_BSC_UnitFormula, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130410L;

    /** the default Constructor */
    public X_BSC_UnitFormula(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_UnitFormula (Properties ctx, int BSC_UnitFormula_ID, String trxName)
    {
      super (ctx, BSC_UnitFormula_ID, trxName);
      /** if (BSC_UnitFormula_ID == 0)
        {
			setBSC_UnitFormula_ID (0);
			setUnit (null);
        } */
    }

    /** Load Constructor */
    public X_BSC_UnitFormula (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_UnitFormula[")
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

	/** Set BSC_UnitFormula ID.
		@param BSC_UnitFormula_ID BSC_UnitFormula ID	  */
	public void setBSC_UnitFormula_ID (int BSC_UnitFormula_ID)
	{
		if (BSC_UnitFormula_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_UnitFormula_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_UnitFormula_ID, Integer.valueOf(BSC_UnitFormula_ID));
	}

	/** Get BSC_UnitFormula ID.
		@return BSC_UnitFormula ID	  */
	public int getBSC_UnitFormula_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_UnitFormula_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Unit AD_Reference_ID=1000132 */
	public static final int UNIT_AD_Reference_ID=1000132;
	/** Yes/No = Yes/No */
	public static final String UNIT_YesNo = "Yes/No";
	/** in_time = in_time */
	public static final String UNIT_In_time = "in_time";
	/** % = % */
	public static final String UNIT_ = "%";
	/** qty = qty */
	public static final String UNIT_Qty = "qty";
	/** pcs = pcs */
	public static final String UNIT_Pcs = "pcs";
	/** units = units */
	public static final String UNIT_Units = "units";
	/** Set Unit.
		@param Unit 
		Unit
	  */
	public void setUnit (String Unit)
	{

		set_ValueNoCheck (COLUMNNAME_Unit, Unit);
	}

	/** Get Unit.
		@return Unit
	  */
	public String getUnit () 
	{
		return (String)get_Value(COLUMNNAME_Unit);
	}
}