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

/** Generated Model for TRM_Stage
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Stage extends PO implements I_TRM_Stage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_TRM_Stage(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_Stage (Properties ctx, int TRM_Stage_ID, String trxName)
    {
      super (ctx, TRM_Stage_ID, trxName);
      /** if (TRM_Stage_ID == 0)
        {
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM TRM_Stage WHERE TRM_AgreementStages_ID=@TRM_AgreementStages_ID@
			setName (null);
			setTRM_Stage_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Stage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_Stage[")
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

	public I_TRM_AgreementStages getTRM_AgreementStages() throws RuntimeException
    {
		return (I_TRM_AgreementStages)MTable.get(getCtx(), I_TRM_AgreementStages.Table_Name)
			.getPO(getTRM_AgreementStages_ID(), get_TrxName());	}

	/** Set TRM_AgreementStages ID.
		@param TRM_AgreementStages_ID TRM_AgreementStages ID	  */
	public void setTRM_AgreementStages_ID (int TRM_AgreementStages_ID)
	{
		if (TRM_AgreementStages_ID < 1) 
			set_Value (COLUMNNAME_TRM_AgreementStages_ID, null);
		else 
			set_Value (COLUMNNAME_TRM_AgreementStages_ID, Integer.valueOf(TRM_AgreementStages_ID));
	}

	/** Get TRM_AgreementStages ID.
		@return TRM_AgreementStages ID	  */
	public int getTRM_AgreementStages_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_AgreementStages_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TRM_Stage ID.
		@param TRM_Stage_ID TRM_Stage ID	  */
	public void setTRM_Stage_ID (int TRM_Stage_ID)
	{
		if (TRM_Stage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Stage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Stage_ID, Integer.valueOf(TRM_Stage_ID));
	}

	/** Get TRM_Stage ID.
		@return TRM_Stage ID	  */
	public int getTRM_Stage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}