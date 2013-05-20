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

/** Generated Model for TRM_Agreement
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Agreement extends PO implements I_TRM_Agreement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_TRM_Agreement(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_Agreement (Properties ctx, int TRM_Agreement_ID, String trxName)
    {
      super (ctx, TRM_Agreement_ID, trxName);
      /** if (TRM_Agreement_ID == 0)
        {
			setTRM_Agreement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Agreement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_Agreement[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Sign_N.
		@param Sign_N 
		Disapprove
	  */
	public void setSign_N (boolean Sign_N)
	{
		set_Value (COLUMNNAME_Sign_N, Boolean.valueOf(Sign_N));
	}

	/** Get Sign_N.
		@return Disapprove
	  */
	public boolean isSign_N () 
	{
		Object oo = get_Value(COLUMNNAME_Sign_N);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sign_Y.
		@param Sign_Y Sign_Y	  */
	public void setSign_Y (boolean Sign_Y)
	{
		set_Value (COLUMNNAME_Sign_Y, Boolean.valueOf(Sign_Y));
	}

	/** Get Sign_Y.
		@return Sign_Y	  */
	public boolean isSign_Y () 
	{
		Object oo = get_Value(COLUMNNAME_Sign_Y);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_AD_User getSigner() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSigner_ID(), get_TrxName());	}

	/** Set Signer_ID.
		@param Signer_ID 
		Согласовывающий
	  */
	public void setSigner_ID (int Signer_ID)
	{
		if (Signer_ID < 1) 
			set_Value (COLUMNNAME_Signer_ID, null);
		else 
			set_Value (COLUMNNAME_Signer_ID, Integer.valueOf(Signer_ID));
	}

	/** Get Signer_ID.
		@return Согласовывающий
	  */
	public int getSigner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Signer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TRM_Agreement ID.
		@param TRM_Agreement_ID TRM_Agreement ID	  */
	public void setTRM_Agreement_ID (int TRM_Agreement_ID)
	{
		if (TRM_Agreement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Agreement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Agreement_ID, Integer.valueOf(TRM_Agreement_ID));
	}

	/** Get TRM_Agreement ID.
		@return TRM_Agreement ID	  */
	public int getTRM_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Application ID.
		@param TRM_Application_ID 
		Финансовая заявка
	  */
	public void setTRM_Application_ID (int TRM_Application_ID)
	{
		if (TRM_Application_ID < 1) 
			set_Value (COLUMNNAME_TRM_Application_ID, null);
		else 
			set_Value (COLUMNNAME_TRM_Application_ID, Integer.valueOf(TRM_Application_ID));
	}

	/** Get Financial Application ID.
		@return Финансовая заявка
	  */
	public int getTRM_Application_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Application_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}