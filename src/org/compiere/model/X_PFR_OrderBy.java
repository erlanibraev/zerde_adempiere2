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

/** Generated Model for PFR_OrderBy
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_PFR_OrderBy extends PO implements I_PFR_OrderBy, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130730L;

    /** the default Constructor */
    public X_PFR_OrderBy(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_PFR_OrderBy (Properties ctx, int PFR_OrderBy_ID, String trxName)
    {
      super (ctx, PFR_OrderBy_ID, trxName);
      /** if (PFR_OrderBy_ID == 0)
        {
			setPFR_OrderBy_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PFR_OrderBy (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PFR_OrderBy[")
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

	/** Set PFR_OrderBy ID.
		@param PFR_OrderBy_ID PFR_OrderBy ID	  */
	public void setPFR_OrderBy_ID (int PFR_OrderBy_ID)
	{
		if (PFR_OrderBy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PFR_OrderBy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PFR_OrderBy_ID, Integer.valueOf(PFR_OrderBy_ID));
	}

	/** Get PFR_OrderBy ID.
		@return PFR_OrderBy ID	  */
	public int getPFR_OrderBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PFR_OrderBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}