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

/** Generated Model for AGR_StageList
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_StageList extends PO implements I_AGR_StageList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130603L;

    /** Standard Constructor */
    public X_AGR_StageList (Properties ctx, int AGR_StageList_ID, String trxName)
    {
      super (ctx, AGR_StageList_ID, trxName);
      /** if (AGR_StageList_ID == 0)
        {
			setAGR_Stage_ID (0);
// @AGR_Stage_ID@
			setAGR_StageList_ID (0);
			setIsAlternate2Active (true);
// Y
			setIsAlternateActive (true);
// Y
			setIsHeaderActive (true);
// Y
        } */
    }

    /** Load Constructor */
    public X_AGR_StageList (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_StageList[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AGR_Stage getAGR_Stage() throws RuntimeException
    {
		return (I_AGR_Stage)MTable.get(getCtx(), I_AGR_Stage.Table_Name)
			.getPO(getAGR_Stage_ID(), get_TrxName());	}

	/** Set AGR_Stage_ID.
		@param AGR_Stage_ID AGR_Stage_ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID)
	{
		if (AGR_Stage_ID < 1) 
			set_Value (COLUMNNAME_AGR_Stage_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Stage_ID, Integer.valueOf(AGR_Stage_ID));
	}

	/** Get AGR_Stage_ID.
		@return AGR_Stage_ID	  */
	public int getAGR_Stage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AGR_StageList ID.
		@param AGR_StageList_ID 
		Связь с инициатором
	  */
	public void setAGR_StageList_ID (int AGR_StageList_ID)
	{
		if (AGR_StageList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_StageList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_StageList_ID, Integer.valueOf(AGR_StageList_ID));
	}

	/** Get AGR_StageList ID.
		@return Связь с инициатором
	  */
	public int getAGR_StageList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_StageList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getAlternate() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAlternate_ID(), get_TrxName());	}

	/** Set Alternate_ID.
		@param Alternate_ID 
		Альтернативное ответственное лицо
	  */
	public void setAlternate_ID (int Alternate_ID)
	{
		if (Alternate_ID < 1) 
			set_Value (COLUMNNAME_Alternate_ID, null);
		else 
			set_Value (COLUMNNAME_Alternate_ID, Integer.valueOf(Alternate_ID));
	}

	/** Get Alternate_ID.
		@return Альтернативное ответственное лицо
	  */
	public int getAlternate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Alternate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getAlternate2() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAlternate2_ID(), get_TrxName());	}

	/** Set Alternate2_ID.
		@param Alternate2_ID 
		Альтернативное ответственное лицо (2)
	  */
	public void setAlternate2_ID (int Alternate2_ID)
	{
		if (Alternate2_ID < 1) 
			set_Value (COLUMNNAME_Alternate2_ID, null);
		else 
			set_Value (COLUMNNAME_Alternate2_ID, Integer.valueOf(Alternate2_ID));
	}

	/** Get Alternate2_ID.
		@return Альтернативное ответственное лицо (2)
	  */
	public int getAlternate2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Alternate2_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_BPartner_ID()));
    }

	/** Set IsAlternate2Active.
		@param IsAlternate2Active 
		Активно
	  */
	public void setIsAlternate2Active (boolean IsAlternate2Active)
	{
		set_Value (COLUMNNAME_IsAlternate2Active, Boolean.valueOf(IsAlternate2Active));
	}

	/** Get IsAlternate2Active.
		@return Активно
	  */
	public boolean isAlternate2Active () 
	{
		Object oo = get_Value(COLUMNNAME_IsAlternate2Active);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsAlternateActive.
		@param IsAlternateActive 
		Активно
	  */
	public void setIsAlternateActive (boolean IsAlternateActive)
	{
		set_Value (COLUMNNAME_IsAlternateActive, Boolean.valueOf(IsAlternateActive));
	}

	/** Get IsAlternateActive.
		@return Активно
	  */
	public boolean isAlternateActive () 
	{
		Object oo = get_Value(COLUMNNAME_IsAlternateActive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsHeaderActive.
		@param IsHeaderActive 
		Активно
	  */
	public void setIsHeaderActive (boolean IsHeaderActive)
	{
		set_Value (COLUMNNAME_IsHeaderActive, Boolean.valueOf(IsHeaderActive));
	}

	/** Get IsHeaderActive.
		@return Активно
	  */
	public boolean isHeaderActive () 
	{
		Object oo = get_Value(COLUMNNAME_IsHeaderActive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}