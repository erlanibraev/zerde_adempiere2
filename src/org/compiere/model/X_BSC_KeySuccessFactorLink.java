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

/** Generated Model for BSC_KeySuccessFactorLink
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_KeySuccessFactorLink extends PO implements I_BSC_KeySuccessFactorLink, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130802L;

    /** the default Constructor */
    public X_BSC_KeySuccessFactorLink(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_KeySuccessFactorLink (Properties ctx, int BSC_KeySuccessFactorLink_ID, String trxName)
    {
      super (ctx, BSC_KeySuccessFactorLink_ID, trxName);
      /** if (BSC_KeySuccessFactorLink_ID == 0)
        {
			setBSC_KeySuccessFactorLink_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_KeySuccessFactorLink (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_KeySuccessFactorLink[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BSC_KeySuccessFactor getBSC_KeySuccessFactor() throws RuntimeException
    {
		return (I_BSC_KeySuccessFactor)MTable.get(getCtx(), I_BSC_KeySuccessFactor.Table_Name)
			.getPO(getBSC_KeySuccessFactor_ID(), get_TrxName());	}

	/** Set BSC_KeySuccessFactor ID.
		@param BSC_KeySuccessFactor_ID BSC_KeySuccessFactor ID	  */
	public void setBSC_KeySuccessFactor_ID (int BSC_KeySuccessFactor_ID)
	{
		if (BSC_KeySuccessFactor_ID < 1) 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_ID, Integer.valueOf(BSC_KeySuccessFactor_ID));
	}

	/** Get BSC_KeySuccessFactor ID.
		@return BSC_KeySuccessFactor ID	  */
	public int getBSC_KeySuccessFactor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_KeySuccessFactor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_KeySuccessFactor getBSC_KeySuccessFactor_Link() throws RuntimeException
    {
		return (I_BSC_KeySuccessFactor)MTable.get(getCtx(), I_BSC_KeySuccessFactor.Table_Name)
			.getPO(getBSC_KeySuccessFactor_Link_ID(), get_TrxName());	}

	/** Set BSC_KeySuccessFactor_Link_ID.
		@param BSC_KeySuccessFactor_Link_ID 
		BSC_KeySuccessFactor_Link_ID
	  */
	public void setBSC_KeySuccessFactor_Link_ID (int BSC_KeySuccessFactor_Link_ID)
	{
		if (BSC_KeySuccessFactor_Link_ID < 1) 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_Link_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_Link_ID, Integer.valueOf(BSC_KeySuccessFactor_Link_ID));
	}

	/** Get BSC_KeySuccessFactor_Link_ID.
		@return BSC_KeySuccessFactor_Link_ID
	  */
	public int getBSC_KeySuccessFactor_Link_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_KeySuccessFactor_Link_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BSC_KeySuccessFactorLink ID.
		@param BSC_KeySuccessFactorLink_ID BSC_KeySuccessFactorLink ID	  */
	public void setBSC_KeySuccessFactorLink_ID (int BSC_KeySuccessFactorLink_ID)
	{
		if (BSC_KeySuccessFactorLink_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_KeySuccessFactorLink_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_KeySuccessFactorLink_ID, Integer.valueOf(BSC_KeySuccessFactorLink_ID));
	}

	/** Get BSC_KeySuccessFactorLink ID.
		@return BSC_KeySuccessFactorLink ID	  */
	public int getBSC_KeySuccessFactorLink_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_KeySuccessFactorLink_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}