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

/** Generated Model for bcs_inputfinreports
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_bcs_inputfinreports extends PO implements I_bcs_inputfinreports, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130403L;

    /** Standard Constructor */
    public X_bcs_inputfinreports (Properties ctx, int bcs_inputfinreports_ID, String trxName)
    {
      super (ctx, bcs_inputfinreports_ID, trxName);
      /** if (bcs_inputfinreports_ID == 0)
        {
			setbcs_finreportspos_ID (0);
			setbcs_inputfinreports_ID (0);
			setDescription (null);
			setinputdate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_bcs_inputfinreports (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_bcs_inputfinreports[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_bcs_finreportspos getbcs_finreportspos() throws RuntimeException
    {
		return (I_bcs_finreportspos)MTable.get(getCtx(), I_bcs_finreportspos.Table_Name)
			.getPO(getbcs_finreportspos_ID(), get_TrxName());	}

	/** Set bcs_finreportspos ID.
		@param bcs_finreportspos_ID bcs_finreportspos ID	  */
	public void setbcs_finreportspos_ID (int bcs_finreportspos_ID)
	{
		if (bcs_finreportspos_ID < 1) 
			set_Value (COLUMNNAME_bcs_finreportspos_ID, null);
		else 
			set_Value (COLUMNNAME_bcs_finreportspos_ID, Integer.valueOf(bcs_finreportspos_ID));
	}

	/** Get bcs_finreportspos ID.
		@return bcs_finreportspos ID	  */
	public int getbcs_finreportspos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_finreportspos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set bcs_inputfinreports ID.
		@param bcs_inputfinreports_ID bcs_inputfinreports ID	  */
	public void setbcs_inputfinreports_ID (int bcs_inputfinreports_ID)
	{
		if (bcs_inputfinreports_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreports_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreports_ID, Integer.valueOf(bcs_inputfinreports_ID));
	}

	/** Get bcs_inputfinreports ID.
		@return bcs_inputfinreports ID	  */
	public int getbcs_inputfinreports_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_inputfinreports_ID);
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

	/** Set inputdate.
		@param inputdate inputdate	  */
	public void setinputdate (Timestamp inputdate)
	{
		set_Value (COLUMNNAME_inputdate, inputdate);
	}

	/** Get inputdate.
		@return inputdate	  */
	public Timestamp getinputdate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_inputdate);
	}
}