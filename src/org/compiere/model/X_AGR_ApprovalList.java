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

/** Generated Model for AGR_ApprovalList
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_ApprovalList extends PO implements I_AGR_ApprovalList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130531L;

    /** Standard Constructor */
    public X_AGR_ApprovalList (Properties ctx, int AGR_ApprovalList_ID, String trxName)
    {
      super (ctx, AGR_ApprovalList_ID, trxName);
      /** if (AGR_ApprovalList_ID == 0)
        {
			setAD_Table_ID (0);
			setAGR_Agreement_ID (0);
			setAGR_ApprovalList_ID (0);
			setAGR_Stage_ID (0);
			setC_BPartner_ID (0);
			setRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AGR_ApprovalList (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_ApprovalList[")
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

	public I_AGR_Agreement getAGR_Agreement() throws RuntimeException
    {
		return (I_AGR_Agreement)MTable.get(getCtx(), I_AGR_Agreement.Table_Name)
			.getPO(getAGR_Agreement_ID(), get_TrxName());	}

	/** Set AGR_Agreement ID.
		@param AGR_Agreement_ID AGR_Agreement ID	  */
	public void setAGR_Agreement_ID (int AGR_Agreement_ID)
	{
		if (AGR_Agreement_ID < 1) 
			set_Value (COLUMNNAME_AGR_Agreement_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Agreement_ID, Integer.valueOf(AGR_Agreement_ID));
	}

	/** Get AGR_Agreement ID.
		@return AGR_Agreement ID	  */
	public int getAGR_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AGR_ApprovalList ID.
		@param AGR_ApprovalList_ID AGR_ApprovalList ID	  */
	public void setAGR_ApprovalList_ID (int AGR_ApprovalList_ID)
	{
		if (AGR_ApprovalList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_ApprovalList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_ApprovalList_ID, Integer.valueOf(AGR_ApprovalList_ID));
	}

	/** Get AGR_ApprovalList ID.
		@return AGR_ApprovalList ID	  */
	public int getAGR_ApprovalList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_ApprovalList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AGR_Stage getAGR_Stage() throws RuntimeException
    {
		return (I_AGR_Stage)MTable.get(getCtx(), I_AGR_Stage.Table_Name)
			.getPO(getAGR_Stage_ID(), get_TrxName());	}

	/** Set AGR_Stage ID.
		@param AGR_Stage_ID AGR_Stage ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID)
	{
		if (AGR_Stage_ID < 1) 
			set_Value (COLUMNNAME_AGR_Stage_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Stage_ID, Integer.valueOf(AGR_Stage_ID));
	}

	/** Get AGR_Stage ID.
		@return AGR_Stage ID	  */
	public int getAGR_Stage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}