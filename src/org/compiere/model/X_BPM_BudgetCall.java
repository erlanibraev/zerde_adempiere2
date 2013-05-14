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
import org.compiere.util.KeyNamePair;

/** Generated Model for BPM_BudgetCall
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_BudgetCall extends PO implements I_BPM_BudgetCall, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130514L;

    /** the default Constructor */
    public X_BPM_BudgetCall(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_BudgetCall (Properties ctx, int BPM_BudgetCall_ID, String trxName)
    {
      super (ctx, BPM_BudgetCall_ID, trxName);
      /** if (BPM_BudgetCall_ID == 0)
        {
			setBPM_ABP_ID (0);
			setBPM_BudgetCall_ID (0);
			setBPM_VersionBudgetLine_ID (0);
			setC_Project_ID (0);
			setC_ProjectParent_ID (0);
			setC_Year_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_BudgetCall (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_BudgetCall[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BPM_ABP getBPM_ABP() throws RuntimeException
    {
		return (I_BPM_ABP)MTable.get(getCtx(), I_BPM_ABP.Table_Name)
			.getPO(getBPM_ABP_ID(), get_TrxName());	}

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

	/** Set BPM_BudgetCall ID.
		@param BPM_BudgetCall_ID BPM_BudgetCall ID	  */
	public void setBPM_BudgetCall_ID (int BPM_BudgetCall_ID)
	{
		if (BPM_BudgetCall_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_BudgetCall_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_BudgetCall_ID, Integer.valueOf(BPM_BudgetCall_ID));
	}

	/** Get BPM_BudgetCall ID.
		@return BPM_BudgetCall ID	  */
	public int getBPM_BudgetCall_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_BudgetCall_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_VersionBudgetLine getBPM_VersionBudgetLine() throws RuntimeException
    {
		return (I_BPM_VersionBudgetLine)MTable.get(getCtx(), I_BPM_VersionBudgetLine.Table_Name)
			.getPO(getBPM_VersionBudgetLine_ID(), get_TrxName());	}

	/** Set BPM_VersionBudgetLine ID.
		@param BPM_VersionBudgetLine_ID BPM_VersionBudgetLine ID	  */
	public void setBPM_VersionBudgetLine_ID (int BPM_VersionBudgetLine_ID)
	{
		if (BPM_VersionBudgetLine_ID < 1) 
			set_Value (COLUMNNAME_BPM_VersionBudgetLine_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_VersionBudgetLine_ID, Integer.valueOf(BPM_VersionBudgetLine_ID));
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

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_ProjectParent() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_ProjectParent_ID(), get_TrxName());	}

	/** Set C_ProjectParent_ID.
		@param C_ProjectParent_ID C_ProjectParent_ID	  */
	public void setC_ProjectParent_ID (int C_ProjectParent_ID)
	{
		if (C_ProjectParent_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectParent_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectParent_ID, Integer.valueOf(C_ProjectParent_ID));
	}

	/** Get C_ProjectParent_ID.
		@return C_ProjectParent_ID	  */
	public int getC_ProjectParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectParent_ID);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}