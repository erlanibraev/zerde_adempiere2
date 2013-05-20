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

/** Generated Model for BPM_VersionBudget
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_VersionBudget extends PO implements I_BPM_VersionBudget, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_BPM_VersionBudget(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_VersionBudget (Properties ctx, int BPM_VersionBudget_ID, String trxName)
    {
      super (ctx, BPM_VersionBudget_ID, trxName);
      /** if (BPM_VersionBudget_ID == 0)
        {
			setBPM_VersionBudget_ID (0);
			setC_Year_ID (0);
			setNumberSend (0);
// 0
			setSendEMail (null);
// N
        } */
    }

    /** Load Constructor */
    public X_BPM_VersionBudget (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_VersionBudget[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_VersionBudget ID.
		@param BPM_VersionBudget_ID BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID)
	{
		if (BPM_VersionBudget_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_VersionBudget_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_VersionBudget_ID, Integer.valueOf(BPM_VersionBudget_ID));
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

	public org.compiere.model.I_C_Calendar getC_Calendar() throws RuntimeException
    {
		return (org.compiere.model.I_C_Calendar)MTable.get(getCtx(), org.compiere.model.I_C_Calendar.Table_Name)
			.getPO(getC_Calendar_ID(), get_TrxName());	}

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1) 
			set_Value (COLUMNNAME_C_Calendar_ID, null);
		else 
			set_Value (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException
    {
		return (org.compiere.model.I_C_Year)MTable.get(getCtx(), org.compiere.model.I_C_Year.Table_Name)
			.getPO(getC_Year_ID(), get_TrxName());	}

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1) 
			set_Value (COLUMNNAME_C_Year_ID, null);
		else 
			set_Value (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
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

	/** Set NumberSend.
		@param NumberSend NumberSend	  */
	public void setNumberSend (int NumberSend)
	{
		set_Value (COLUMNNAME_NumberSend, Integer.valueOf(NumberSend));
	}

	/** Get NumberSend.
		@return NumberSend	  */
	public int getNumberSend () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberSend);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Send EMail.
		@param SendEMail 
		Enable sending Document EMail
	  */
	public void setSendEMail (String SendEMail)
	{
		set_Value (COLUMNNAME_SendEMail, SendEMail);
	}

	/** Get Send EMail.
		@return Enable sending Document EMail
	  */
	public String getSendEMail () 
	{
		return (String)get_Value(COLUMNNAME_SendEMail);
	}
}