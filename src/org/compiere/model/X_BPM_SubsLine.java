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

/** Generated Model for BPM_SubsLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_SubsLine extends PO implements I_BPM_SubsLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130618L;

    /** the default Constructor */
    public X_BPM_SubsLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_SubsLine (Properties ctx, int BPM_SubsLine_ID, String trxName)
    {
      super (ctx, BPM_SubsLine_ID, trxName);
      /** if (BPM_SubsLine_ID == 0)
        {
			setAD_User_ID (0);
			setBPM_Subs_ID (0);
			setBPM_SubsLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_SubsLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_SubsLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set Usuario.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get Usuario.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_Subs getBPM_Subs() throws RuntimeException
    {
		return (I_BPM_Subs)MTable.get(getCtx(), I_BPM_Subs.Table_Name)
			.getPO(getBPM_Subs_ID(), get_TrxName());	}

	/** Set BPM_Subs ID.
		@param BPM_Subs_ID BPM_Subs ID	  */
	public void setBPM_Subs_ID (int BPM_Subs_ID)
	{
		if (BPM_Subs_ID < 1) 
			set_Value (COLUMNNAME_BPM_Subs_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Subs_ID, Integer.valueOf(BPM_Subs_ID));
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

	/** Set BPM_SubsLine ID.
		@param BPM_SubsLine_ID BPM_SubsLine ID	  */
	public void setBPM_SubsLine_ID (int BPM_SubsLine_ID)
	{
		if (BPM_SubsLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_SubsLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_SubsLine_ID, Integer.valueOf(BPM_SubsLine_ID));
	}

	/** Get BPM_SubsLine ID.
		@return BPM_SubsLine ID	  */
	public int getBPM_SubsLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_SubsLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}