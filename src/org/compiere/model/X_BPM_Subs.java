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
import org.compiere.util.KeyNamePair;

/** Generated Model for BPM_Subs
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_Subs extends PO implements I_BPM_Subs, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130422L;

    /** the default Constructor */
    public X_BPM_Subs(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_Subs (Properties ctx, int BPM_Subs_ID, String trxName)
    {
      super (ctx, BPM_Subs_ID, trxName);
      /** if (BPM_Subs_ID == 0)
        {
			setBPM_Share (Env.ZERO);
			setBPM_Subs_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_BPM_Subs (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_Subs[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_Share.
		@param BPM_Share BPM_Share	  */
	public void setBPM_Share (BigDecimal BPM_Share)
	{
		set_Value (COLUMNNAME_BPM_Share, BPM_Share);
	}

	/** Get BPM_Share.
		@return BPM_Share	  */
	public BigDecimal getBPM_Share () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BPM_Share);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set BPM_Subs ID.
		@param BPM_Subs_ID BPM_Subs ID	  */
	public void setBPM_Subs_ID (int BPM_Subs_ID)
	{
		if (BPM_Subs_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_Subs_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_Subs_ID, Integer.valueOf(BPM_Subs_ID));
	}

	/** Get BPM_Subs ID.
		@return BPM_Subs ID	  */
	public int getBPM_Subs_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Subs_ID);
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