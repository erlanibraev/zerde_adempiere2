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

/** Generated Model for BPM_FormLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_FormLine extends PO implements I_BPM_FormLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130904L;

    /** the default Constructor */
    public X_BPM_FormLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_FormLine (Properties ctx, int BPM_FormLine_ID, String trxName)
    {
      super (ctx, BPM_FormLine_ID, trxName);
      /** if (BPM_FormLine_ID == 0)
        {
			setBPM_Form_ID (0);
			setBPM_FormLine_ID (0);
			setisParameter (false);
// N
			setName (null);
			setParamDlg (null);
// N
        } */
    }

    /** Load Constructor */
    public X_BPM_FormLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_FormLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BPM_Category getBPM_Category() throws RuntimeException
    {
		return (I_BPM_Category)MTable.get(getCtx(), I_BPM_Category.Table_Name)
			.getPO(getBPM_Category_ID(), get_TrxName());	}

	/** Set BPM_Category ID.
		@param BPM_Category_ID BPM_Category ID	  */
	public void setBPM_Category_ID (int BPM_Category_ID)
	{
		if (BPM_Category_ID < 1) 
			set_Value (COLUMNNAME_BPM_Category_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Category_ID, Integer.valueOf(BPM_Category_ID));
	}

	/** Get BPM_Category ID.
		@return BPM_Category ID	  */
	public int getBPM_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_Form getBPM_Form() throws RuntimeException
    {
		return (I_BPM_Form)MTable.get(getCtx(), I_BPM_Form.Table_Name)
			.getPO(getBPM_Form_ID(), get_TrxName());	}

	/** Set BPM_Form ID.
		@param BPM_Form_ID BPM_Form ID	  */
	public void setBPM_Form_ID (int BPM_Form_ID)
	{
		if (BPM_Form_ID < 1) 
			set_Value (COLUMNNAME_BPM_Form_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Form_ID, Integer.valueOf(BPM_Form_ID));
	}

	/** Get BPM_Form ID.
		@return BPM_Form ID	  */
	public int getBPM_Form_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Form_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_FormLine ID.
		@param BPM_FormLine_ID BPM_FormLine ID	  */
	public void setBPM_FormLine_ID (int BPM_FormLine_ID)
	{
		if (BPM_FormLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_FormLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_FormLine_ID, Integer.valueOf(BPM_FormLine_ID));
	}

	/** Get BPM_FormLine ID.
		@return BPM_FormLine ID	  */
	public int getBPM_FormLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_Parameter getBSC_Parameter() throws RuntimeException
    {
		return (I_BSC_Parameter)MTable.get(getCtx(), I_BSC_Parameter.Table_Name)
			.getPO(getBSC_Parameter_ID(), get_TrxName());	}

	/** Set BSC_Parameter ID.
		@param BSC_Parameter_ID BSC_Parameter ID	  */
	public void setBSC_Parameter_ID (int BSC_Parameter_ID)
	{
		if (BSC_Parameter_ID < 1) 
			set_Value (COLUMNNAME_BSC_Parameter_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_Parameter_ID, Integer.valueOf(BSC_Parameter_ID));
	}

	/** Get BSC_Parameter ID.
		@return BSC_Parameter ID	  */
	public int getBSC_Parameter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Parameter_ID);
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

	/** Set isParameter.
		@param isParameter 
		Параметр
	  */
	public void setisParameter (boolean isParameter)
	{
		set_Value (COLUMNNAME_isParameter, Boolean.valueOf(isParameter));
	}

	/** Get isParameter.
		@return Параметр
	  */
	public boolean isParameter () 
	{
		Object oo = get_Value(COLUMNNAME_isParameter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
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

	/** Set ParamDlg.
		@param ParamDlg 
		Настройка параметров
	  */
	public void setParamDlg (String ParamDlg)
	{
		set_Value (COLUMNNAME_ParamDlg, ParamDlg);
	}

	/** Get ParamDlg.
		@return Настройка параметров
	  */
	public String getParamDlg () 
	{
		return (String)get_Value(COLUMNNAME_ParamDlg);
	}
}