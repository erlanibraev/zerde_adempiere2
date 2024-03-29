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

/** Generated Model for AGR_Stage
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_Stage extends PO implements I_AGR_Stage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130826L;

    /** the default Constructor */
    public X_AGR_Stage(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_AGR_Stage (Properties ctx, int AGR_Stage_ID, String trxName)
    {
      super (ctx, AGR_Stage_ID, trxName);
      /** if (AGR_Stage_ID == 0)
        {
			setAGR_Agreement_ID (0);
// @AGR_Agreement_ID@
			setAGR_Stage_ID (0);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM AGR_Stage WHERE AGR_Stage_ID=@AGR_Stage_ID@
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AGR_Stage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_Stage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Process)MTable.get(getCtx(), org.compiere.model.I_AD_Process.Table_Name)
			.getPO(getAD_Process_ID(), get_TrxName());	}

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Ref_List getAD_Ref_List() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Ref_List)MTable.get(getCtx(), org.compiere.model.I_AD_Ref_List.Table_Name)
			.getPO(getAD_Ref_List_ID(), get_TrxName());	}

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

	public org.compiere.model.I_AD_Ref_List getAD_Ref_List2() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Ref_List)MTable.get(getCtx(), org.compiere.model.I_AD_Ref_List.Table_Name)
			.getPO(getAD_Ref_List2_ID(), get_TrxName());	}

	/** Set AD_Ref_List2_ID.
		@param AD_Ref_List2_ID AD_Ref_List2_ID	  */
	public void setAD_Ref_List2_ID (int AD_Ref_List2_ID)
	{
		if (AD_Ref_List2_ID < 1) 
			set_Value (COLUMNNAME_AD_Ref_List2_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Ref_List2_ID, Integer.valueOf(AD_Ref_List2_ID));
	}

	/** Get AD_Ref_List2_ID.
		@return AD_Ref_List2_ID	  */
	public int getAD_Ref_List2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Ref_List2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AGR_Agreement getAGR_Agreement() throws RuntimeException
    {
		return (I_AGR_Agreement)MTable.get(getCtx(), I_AGR_Agreement.Table_Name)
			.getPO(getAGR_Agreement_ID(), get_TrxName());	}

	/** Set AGR_Agreement_ID.
		@param AGR_Agreement_ID AGR_Agreement_ID	  */
	public void setAGR_Agreement_ID (int AGR_Agreement_ID)
	{
		if (AGR_Agreement_ID < 1) 
			set_Value (COLUMNNAME_AGR_Agreement_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Agreement_ID, Integer.valueOf(AGR_Agreement_ID));
	}

	/** Get AGR_Agreement_ID.
		@return AGR_Agreement_ID	  */
	public int getAGR_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AGR_Stage ID.
		@param AGR_Stage_ID AGR_Stage ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID)
	{
		if (AGR_Stage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_Stage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_Stage_ID, Integer.valueOf(AGR_Stage_ID));
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

	/** Set isMultiStage.
		@param isMultiStage 
		Множественное участие
	  */
	public void setisMultiStage (boolean isMultiStage)
	{
		set_Value (COLUMNNAME_isMultiStage, Boolean.valueOf(isMultiStage));
	}

	/** Get isMultiStage.
		@return Множественное участие
	  */
	public boolean isMultiStage () 
	{
		Object oo = get_Value(COLUMNNAME_isMultiStage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** StageType AD_Reference_ID=1000146 */
	public static final int STAGETYPE_AD_Reference_ID=1000146;
	/** Initial = IN */
	public static final String STAGETYPE_Initial = "IN";
	/** Ordinary = OR */
	public static final String STAGETYPE_Ordinary = "OR";
	/** Final = FI */
	public static final String STAGETYPE_Final = "FI";
	/** Set StageType.
		@param StageType 
		Тип этапа
	  */
	public void setStageType (String StageType)
	{

		set_Value (COLUMNNAME_StageType, StageType);
	}

	/** Get StageType.
		@return Тип этапа
	  */
	public String getStageType () 
	{
		return (String)get_Value(COLUMNNAME_StageType);
	}
}