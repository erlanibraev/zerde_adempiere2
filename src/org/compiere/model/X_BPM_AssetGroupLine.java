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

/** Generated Model for BPM_AssetGroupLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_AssetGroupLine extends PO implements I_BPM_AssetGroupLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130419L;

    /** the default Constructor */
    public X_BPM_AssetGroupLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_AssetGroupLine (Properties ctx, int BPM_AssetGroupLine_ID, String trxName)
    {
      super (ctx, BPM_AssetGroupLine_ID, trxName);
      /** if (BPM_AssetGroupLine_ID == 0)
        {
			setA_Asset_Group_ID (0);
			setBPM_ABP_ID (0);
			setBPM_AssetGroupLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_AssetGroupLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_AssetGroupLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset_Group getA_Asset_Group() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Group)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Group.Table_Name)
			.getPO(getA_Asset_Group_ID(), get_TrxName());	}

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Group_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set BPM_AssetGroupLine ID.
		@param BPM_AssetGroupLine_ID BPM_AssetGroupLine ID	  */
	public void setBPM_AssetGroupLine_ID (int BPM_AssetGroupLine_ID)
	{
		if (BPM_AssetGroupLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_AssetGroupLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_AssetGroupLine_ID, Integer.valueOf(BPM_AssetGroupLine_ID));
	}

	/** Get BPM_AssetGroupLine ID.
		@return BPM_AssetGroupLine ID	  */
	public int getBPM_AssetGroupLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_AssetGroupLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}