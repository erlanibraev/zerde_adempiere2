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

/** Generated Model for BPM_ServicesLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_ServicesLine extends PO implements I_BPM_ServicesLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130419L;

    /** the default Constructor */
    public X_BPM_ServicesLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_ServicesLine (Properties ctx, int BPM_ServicesLine_ID, String trxName)
    {
      super (ctx, BPM_ServicesLine_ID, trxName);
      /** if (BPM_ServicesLine_ID == 0)
        {
			setBPM_ABP_ID (0);
			setBPM_Services_ID (0);
			setBPM_ServicesLine_ID (0);
			setBPM_ServicesWork_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_ServicesLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_ServicesLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_ABP ID.
		@param BPM_ABP_ID BPM_ABP ID	  */
	public void setBPM_ABP_ID (int BPM_ABP_ID)
	{
		if (BPM_ABP_ID < 1) 
			set_Value (COLUMNNAME_BPM_ABP_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_ABP_ID, Integer.valueOf(BPM_ABP_ID));
	}

	/** Get BPM_ABP ID.
		@return BPM_ABP ID	  */
	public int getBPM_ABP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ABP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_ServicesWork getBPM_Services() throws RuntimeException
    {
		return (I_BPM_ServicesWork)MTable.get(getCtx(), I_BPM_ServicesWork.Table_Name)
			.getPO(getBPM_Services_ID(), get_TrxName());	}

	/** Set BPM_Services_ID.
		@param BPM_Services_ID BPM_Services_ID	  */
	public void setBPM_Services_ID (int BPM_Services_ID)
	{
		if (BPM_Services_ID < 1) 
			set_Value (COLUMNNAME_BPM_Services_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Services_ID, Integer.valueOf(BPM_Services_ID));
	}

	/** Get BPM_Services_ID.
		@return BPM_Services_ID	  */
	public int getBPM_Services_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Services_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_ServicesLine ID.
		@param BPM_ServicesLine_ID BPM_ServicesLine ID	  */
	public void setBPM_ServicesLine_ID (int BPM_ServicesLine_ID)
	{
		if (BPM_ServicesLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_ServicesLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_ServicesLine_ID, Integer.valueOf(BPM_ServicesLine_ID));
	}

	/** Get BPM_ServicesLine ID.
		@return BPM_ServicesLine ID	  */
	public int getBPM_ServicesLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ServicesLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_ServicesWork getBPM_ServicesWork() throws RuntimeException
    {
		return (I_BPM_ServicesWork)MTable.get(getCtx(), I_BPM_ServicesWork.Table_Name)
			.getPO(getBPM_ServicesWork_ID(), get_TrxName());	}

	/** Set BPM_ServicesWork ID.
		@param BPM_ServicesWork_ID BPM_ServicesWork ID	  */
	public void setBPM_ServicesWork_ID (int BPM_ServicesWork_ID)
	{
		if (BPM_ServicesWork_ID < 1) 
			set_Value (COLUMNNAME_BPM_ServicesWork_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_ServicesWork_ID, Integer.valueOf(BPM_ServicesWork_ID));
	}

	/** Get BPM_ServicesWork ID.
		@return BPM_ServicesWork ID	  */
	public int getBPM_ServicesWork_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ServicesWork_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}