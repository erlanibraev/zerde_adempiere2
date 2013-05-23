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

/** Generated Model for AGR_AgreementList
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_AgreementList extends PO implements I_AGR_AgreementList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130523L;

    /** the default Constructor */
    public X_AGR_AgreementList(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_AGR_AgreementList (Properties ctx, int AGR_AgreementList_ID, String trxName)
    {
      super (ctx, AGR_AgreementList_ID, trxName);
      /** if (AGR_AgreementList_ID == 0)
        {
			setAD_Table_ID (0);
			setAGR_AgreementList_ID (0);
			setRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AGR_AgreementList (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_AgreementList[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

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

	/** Set AGR_AgreementList ID.
		@param AGR_AgreementList_ID AGR_AgreementList ID	  */
	public void setAGR_AgreementList_ID (int AGR_AgreementList_ID)
	{
		if (AGR_AgreementList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_AgreementList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_AgreementList_ID, Integer.valueOf(AGR_AgreementList_ID));
	}

	/** Get AGR_AgreementList ID.
		@return AGR_AgreementList ID	  */
	public int getAGR_AgreementList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_AgreementList_ID);
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

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}