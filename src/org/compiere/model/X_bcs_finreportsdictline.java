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

/** Generated Model for bcs_finreportsdictline
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_bcs_finreportsdictline extends PO implements I_bcs_finreportsdictline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130404L;

    /** Standard Constructor */
    public X_bcs_finreportsdictline (Properties ctx, int bcs_finreportsdictline_ID, String trxName)
    {
      super (ctx, bcs_finreportsdictline_ID, trxName);
      /** if (bcs_finreportsdictline_ID == 0)
        {
			setbcs_finreportsdict_ID (0);
			setbcs_finreportsdictline_ID (0);
			setCode (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_bcs_finreportsdictline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_bcs_finreportsdictline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_bcs_finreportsdict getbcs_finreportsdict() throws RuntimeException
    {
		return (I_bcs_finreportsdict)MTable.get(getCtx(), I_bcs_finreportsdict.Table_Name)
			.getPO(getbcs_finreportsdict_ID(), get_TrxName());	}

	/** Set bcs_finreportsdict ID.
		@param bcs_finreportsdict_ID bcs_finreportsdict ID	  */
	public void setbcs_finreportsdict_ID (int bcs_finreportsdict_ID)
	{
		if (bcs_finreportsdict_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_bcs_finreportsdict_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_bcs_finreportsdict_ID, Integer.valueOf(bcs_finreportsdict_ID));
	}

	/** Get bcs_finreportsdict ID.
		@return bcs_finreportsdict ID	  */
	public int getbcs_finreportsdict_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_finreportsdict_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set bcs_finreportsdictline ID.
		@param bcs_finreportsdictline_ID bcs_finreportsdictline ID	  */
	public void setbcs_finreportsdictline_ID (int bcs_finreportsdictline_ID)
	{
		if (bcs_finreportsdictline_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_bcs_finreportsdictline_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_bcs_finreportsdictline_ID, Integer.valueOf(bcs_finreportsdictline_ID));
	}

	/** Get bcs_finreportsdictline ID.
		@return bcs_finreportsdictline ID	  */
	public int getbcs_finreportsdictline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_finreportsdictline_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (int Code)
	{
		set_Value (COLUMNNAME_Code, Integer.valueOf(Code));
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public int getCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Code);
		if (ii == null)
			 return 0;
		return ii.intValue();
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