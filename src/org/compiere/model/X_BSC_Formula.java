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

/** Generated Model for BSC_Formula
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Formula extends PO implements I_BSC_Formula, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130212L;

    /** the default Constructor */
    public X_BSC_Formula(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Formula (Properties ctx, int BSC_Formula_ID, String trxName)
    {
      super (ctx, BSC_Formula_ID, trxName);
      /** if (BSC_Formula_ID == 0)
        {
			setBSC_Formula_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_Formula (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_BSC_Formula[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BSC_Formula ID.
		@param BSC_Formula_ID BSC_Formula ID	  */
	public void setBSC_Formula_ID (int BSC_Formula_ID)
	{
		if (BSC_Formula_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Formula_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Formula_ID, Integer.valueOf(BSC_Formula_ID));
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

	/** Set Formula.
		@param Formula 
		Formula
	  */
	public void setFormula (String Formula)
	{
		set_Value (COLUMNNAME_Formula, Formula);
	}

	/** Get Formula.
		@return Formula
	  */
	public String getFormula () 
	{
		return (String)get_Value(COLUMNNAME_Formula);
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
}