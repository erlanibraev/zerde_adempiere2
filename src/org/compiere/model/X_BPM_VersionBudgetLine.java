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

/** Generated Model for BPM_VersionBudgetLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_VersionBudgetLine extends PO implements I_BPM_VersionBudgetLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130823L;

    /** the default Constructor */
    public X_BPM_VersionBudgetLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_VersionBudgetLine (Properties ctx, int BPM_VersionBudgetLine_ID, String trxName)
    {
      super (ctx, BPM_VersionBudgetLine_ID, trxName);
      /** if (BPM_VersionBudgetLine_ID == 0)
        {
			setBPM_Version (null);
			setBPM_VersionBudget_ID (0);
			setBPM_VersionBudgetLine_ID (0);
			setBPM_VersionType (null);
        } */
    }

    /** Load Constructor */
    public X_BPM_VersionBudgetLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_VersionBudgetLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_Version.
		@param BPM_Version BPM_Version	  */
	public void setBPM_Version (String BPM_Version)
	{
		set_Value (COLUMNNAME_BPM_Version, BPM_Version);
	}

	/** Get BPM_Version.
		@return BPM_Version	  */
	public String getBPM_Version () 
	{
		return (String)get_Value(COLUMNNAME_BPM_Version);
	}

	/** Set BPM_VersionBudget ID.
		@param BPM_VersionBudget_ID BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID)
	{
		if (BPM_VersionBudget_ID < 1) 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, Integer.valueOf(BPM_VersionBudget_ID));
	}

	/** Get BPM_VersionBudget ID.
		@return BPM_VersionBudget ID	  */
	public int getBPM_VersionBudget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_VersionBudget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_VersionBudgetLine ID.
		@param BPM_VersionBudgetLine_ID BPM_VersionBudgetLine ID	  */
	public void setBPM_VersionBudgetLine_ID (int BPM_VersionBudgetLine_ID)
	{
		if (BPM_VersionBudgetLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_VersionBudgetLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_VersionBudgetLine_ID, Integer.valueOf(BPM_VersionBudgetLine_ID));
	}

	/** Get BPM_VersionBudgetLine ID.
		@return BPM_VersionBudgetLine ID	  */
	public int getBPM_VersionBudgetLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_VersionBudgetLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** BPM_VersionType AD_Reference_ID=1000138 */
	public static final int BPM_VERSIONTYPE_AD_Reference_ID=1000138;
	/** Approved = APPROVED */
	public static final String BPM_VERSIONTYPE_Approved = "APPROVED";
	/** Current = CURRENT */
	public static final String BPM_VERSIONTYPE_Current = "CURRENT";
	/** Correct = CORRECT */
	public static final String BPM_VERSIONTYPE_Correct = "CORRECT";
	/** NOT APPROVED = NOT APPROVED */
	public static final String BPM_VERSIONTYPE_NOTAPPROVED = "NOT APPROVED";
	/** Set BPM_VersionType.
		@param BPM_VersionType BPM_VersionType	  */
	public void setBPM_VersionType (String BPM_VersionType)
	{

		set_Value (COLUMNNAME_BPM_VersionType, BPM_VersionType);
	}

	/** Get BPM_VersionType.
		@return BPM_VersionType	  */
	public String getBPM_VersionType () 
	{
		return (String)get_Value(COLUMNNAME_BPM_VersionType);
	}
}