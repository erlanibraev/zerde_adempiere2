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

/** Generated Model for BPM_FormColumn
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_FormColumn extends PO implements I_BPM_FormColumn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130906L;

    /** the default Constructor */
    public X_BPM_FormColumn(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_FormColumn (Properties ctx, int BPM_FormColumn_ID, String trxName)
    {
      super (ctx, BPM_FormColumn_ID, trxName);
      /** if (BPM_FormColumn_ID == 0)
        {
			setBPM_DataType (null);
// S
			setBPM_FormCode_ID (0);
			setBPM_FormColumn_ID (0);
			setisParameter (false);
// N
			setName (null);
			setOrderColumn (0);
// @SQL=SELECT coalesce(MAX(OrderColumn),0)+10 AS DefaultValue FROM BPM_FormColumn WHERE BPM_FormCode_ID=@BPM_FormCode_ID@
			setParamDlg (null);
// N
        } */
    }

    /** Load Constructor */
    public X_BPM_FormColumn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_FormColumn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BPM_DataType AD_Reference_ID=1000181 */
	public static final int BPM_DATATYPE_AD_Reference_ID=1000181;
	/** String = S */
	public static final String BPM_DATATYPE_String = "S";
	/** Date = D */
	public static final String BPM_DATATYPE_Date = "D";
	/** Amount = A */
	public static final String BPM_DATATYPE_Amount = "A";
	/** Set BPM_DataType.
		@param BPM_DataType 
		Тип данных
	  */
	public void setBPM_DataType (String BPM_DataType)
	{

		set_Value (COLUMNNAME_BPM_DataType, BPM_DataType);
	}

	/** Get BPM_DataType.
		@return Тип данных
	  */
	public String getBPM_DataType () 
	{
		return (String)get_Value(COLUMNNAME_BPM_DataType);
	}

	public I_BPM_FormCode getBPM_FormCode() throws RuntimeException
    {
		return (I_BPM_FormCode)MTable.get(getCtx(), I_BPM_FormCode.Table_Name)
			.getPO(getBPM_FormCode_ID(), get_TrxName());	}

	/** Set BPM_FormCode ID.
		@param BPM_FormCode_ID BPM_FormCode ID	  */
	public void setBPM_FormCode_ID (int BPM_FormCode_ID)
	{
		if (BPM_FormCode_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormCode_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormCode_ID, Integer.valueOf(BPM_FormCode_ID));
	}

	/** Get BPM_FormCode ID.
		@return BPM_FormCode ID	  */
	public int getBPM_FormCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_FormColumn ID.
		@param BPM_FormColumn_ID BPM_FormColumn ID	  */
	public void setBPM_FormColumn_ID (int BPM_FormColumn_ID)
	{
		if (BPM_FormColumn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_FormColumn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_FormColumn_ID, Integer.valueOf(BPM_FormColumn_ID));
	}

	/** Get BPM_FormColumn ID.
		@return BPM_FormColumn ID	  */
	public int getBPM_FormColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormColumn_ID);
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

	/** Set OrderColumn.
		@param OrderColumn OrderColumn	  */
	public void setOrderColumn (int OrderColumn)
	{
		set_Value (COLUMNNAME_OrderColumn, Integer.valueOf(OrderColumn));
	}

	/** Get OrderColumn.
		@return OrderColumn	  */
	public int getOrderColumn () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OrderColumn);
		if (ii == null)
			 return 0;
		return ii.intValue();
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