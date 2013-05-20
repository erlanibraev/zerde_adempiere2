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

/** Generated Model for TRM_StatusAccess
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_StatusAccess extends PO implements I_TRM_StatusAccess, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_TRM_StatusAccess(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_StatusAccess (Properties ctx, int TRM_StatusAccess_ID, String trxName)
    {
      super (ctx, TRM_StatusAccess_ID, trxName);
      /** if (TRM_StatusAccess_ID == 0)
        {
			setAD_Ref_List_ID (0);
			setTRM_StageOptions_ID (0);
// @TRM_StageOptions_ID@
			setTRM_StatusAccess_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_StatusAccess (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_StatusAccess[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Reference List.
		@param AD_Ref_List_ID 
		Reference List based on Table
	  */
	public void setAD_Ref_List_ID (int AD_Ref_List_ID)
	{
		if (AD_Ref_List_ID < 1) 
			set_Value (COLUMNNAME_AD_Ref_List_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Ref_List_ID, Integer.valueOf(AD_Ref_List_ID));
	}

	/** Get Reference List.
		@return Reference List based on Table
	  */
	public int getAD_Ref_List_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Ref_List_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_TRM_StageOptions getTRM_StageOptions() throws RuntimeException
    {
		return (I_TRM_StageOptions)MTable.get(getCtx(), I_TRM_StageOptions.Table_Name)
			.getPO(getTRM_StageOptions_ID(), get_TrxName());	}

	/** Set TRM_StageOptions_ID.
		@param TRM_StageOptions_ID TRM_StageOptions_ID	  */
	public void setTRM_StageOptions_ID (int TRM_StageOptions_ID)
	{
		if (TRM_StageOptions_ID < 1) 
			set_Value (COLUMNNAME_TRM_StageOptions_ID, null);
		else 
			set_Value (COLUMNNAME_TRM_StageOptions_ID, Integer.valueOf(TRM_StageOptions_ID));
	}

	/** Get TRM_StageOptions_ID.
		@return TRM_StageOptions_ID	  */
	public int getTRM_StageOptions_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_StageOptions_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TRM_StatusAccess ID.
		@param TRM_StatusAccess_ID TRM_StatusAccess ID	  */
	public void setTRM_StatusAccess_ID (int TRM_StatusAccess_ID)
	{
		if (TRM_StatusAccess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_StatusAccess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_StatusAccess_ID, Integer.valueOf(TRM_StatusAccess_ID));
	}

	/** Get TRM_StatusAccess ID.
		@return TRM_StatusAccess ID	  */
	public int getTRM_StatusAccess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_StatusAccess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}