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

/** Generated Model for AGR_Dispatcher
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_Dispatcher extends PO implements I_AGR_Dispatcher, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130523L;

    /** the default Constructor */
    public X_AGR_Dispatcher(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_AGR_Dispatcher (Properties ctx, int AGR_Dispatcher_ID, String trxName)
    {
      super (ctx, AGR_Dispatcher_ID, trxName);
      /** if (AGR_Dispatcher_ID == 0)
        {
			setAD_Table_ID (0);
			setAGR_Agreement_ID (0);
			setAGR_Dispatcher_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AGR_Dispatcher (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_Dispatcher[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AGR_Agreement getAGR_Agreement() throws RuntimeException
    {
		return (I_AGR_Agreement)MTable.get(getCtx(), I_AGR_Agreement.Table_Name)
			.getPO(getAGR_Agreement_ID(), get_TrxName());	}

	/** Set AGR_Agreement_ID.
		@param AGR_Agreement_ID AGR_Agreement_ID	  */
	public void setAGR_Agreement_ID (int AGR_Agreement_ID)
	{
		if (AGR_Agreement_ID < 1) 
			set_Value (COLUMNNAME_AGR_Agreement_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Agreement_ID, Integer.valueOf(AGR_Agreement_ID));
	}

	/** Get AGR_Agreement_ID.
		@return AGR_Agreement_ID	  */
	public int getAGR_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AGR_Dispatcher ID.
		@param AGR_Dispatcher_ID AGR_Dispatcher ID	  */
	public void setAGR_Dispatcher_ID (int AGR_Dispatcher_ID)
	{
		if (AGR_Dispatcher_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_Dispatcher_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_Dispatcher_ID, Integer.valueOf(AGR_Dispatcher_ID));
	}

	/** Get AGR_Dispatcher ID.
		@return AGR_Dispatcher ID	  */
	public int getAGR_Dispatcher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Dispatcher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}