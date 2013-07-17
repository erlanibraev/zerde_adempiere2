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

/** Generated Model for BSC_CardLine_Link
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_CardLine_Link extends PO implements I_BSC_CardLine_Link, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130717L;

    /** the default Constructor */
    public X_BSC_CardLine_Link(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_CardLine_Link (Properties ctx, int BSC_CardLine_Link_ID, String trxName)
    {
      super (ctx, BSC_CardLine_Link_ID, trxName);
      /** if (BSC_CardLine_Link_ID == 0)
        {
			setBSC_CardLine_Link_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_CardLine_Link (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_CardLine_Link[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BSC_CardLine getBSC_CardLine() throws RuntimeException
    {
		return (I_BSC_CardLine)MTable.get(getCtx(), I_BSC_CardLine.Table_Name)
			.getPO(getBSC_CardLine_ID(), get_TrxName());	}

	/** Set BSC_CardLine ID.
		@param BSC_CardLine_ID BSC_CardLine ID	  */
	public void setBSC_CardLine_ID (int BSC_CardLine_ID)
	{
		if (BSC_CardLine_ID < 1) 
			set_Value (COLUMNNAME_BSC_CardLine_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_CardLine_ID, Integer.valueOf(BSC_CardLine_ID));
	}

	/** Get BSC_CardLine ID.
		@return BSC_CardLine ID	  */
	public int getBSC_CardLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_CardLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BSC_CardLine_Link ID.
		@param BSC_CardLine_Link_ID BSC_CardLine_Link ID	  */
	public void setBSC_CardLine_Link_ID (int BSC_CardLine_Link_ID)
	{
		if (BSC_CardLine_Link_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_CardLine_Link_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_CardLine_Link_ID, Integer.valueOf(BSC_CardLine_Link_ID));
	}

	/** Get BSC_CardLine_Link ID.
		@return BSC_CardLine_Link ID	  */
	public int getBSC_CardLine_Link_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_CardLine_Link_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_CardLine getlink_BSC_CardLine() throws RuntimeException
    {
		return (I_BSC_CardLine)MTable.get(getCtx(), I_BSC_CardLine.Table_Name)
			.getPO(getlink_BSC_CardLine_ID(), get_TrxName());	}

	/** Set link_BSC_CardLine_ID.
		@param link_BSC_CardLine_ID 
		link_BSC_CardLine_ID
	  */
	public void setlink_BSC_CardLine_ID (int link_BSC_CardLine_ID)
	{
		if (link_BSC_CardLine_ID < 1) 
			set_Value (COLUMNNAME_link_BSC_CardLine_ID, null);
		else 
			set_Value (COLUMNNAME_link_BSC_CardLine_ID, Integer.valueOf(link_BSC_CardLine_ID));
	}

	/** Get link_BSC_CardLine_ID.
		@return link_BSC_CardLine_ID
	  */
	public int getlink_BSC_CardLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_link_BSC_CardLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}