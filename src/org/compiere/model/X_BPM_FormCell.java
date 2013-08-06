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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for BPM_FormCell
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_FormCell extends PO implements I_BPM_FormCell, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130806L;

    /** the default Constructor */
    public X_BPM_FormCell(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_FormCell (Properties ctx, int BPM_FormCell_ID, String trxName)
    {
      super (ctx, BPM_FormCell_ID, trxName);
      /** if (BPM_FormCell_ID == 0)
        {
			setBPM_FormCell_ID (0);
			setBPM_FormColumn_ID (0);
			setBPM_FormLine_ID (0);
			setOrderColumn (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_FormCell (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_FormCell[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_FormCell ID.
		@param BPM_FormCell_ID BPM_FormCell ID	  */
	public void setBPM_FormCell_ID (int BPM_FormCell_ID)
	{
		if (BPM_FormCell_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_FormCell_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_FormCell_ID, Integer.valueOf(BPM_FormCell_ID));
	}

	/** Get BPM_FormCell ID.
		@return BPM_FormCell ID	  */
	public int getBPM_FormCell_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_FormCell_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BPM_FormColumn getBPM_FormColumn() throws RuntimeException
    {
		return (I_BPM_FormColumn)MTable.get(getCtx(), I_BPM_FormColumn.Table_Name)
			.getPO(getBPM_FormColumn_ID(), get_TrxName());	}

	/** Set BPM_FormColumn ID.
		@param BPM_FormColumn_ID BPM_FormColumn ID	  */
	public void setBPM_FormColumn_ID (int BPM_FormColumn_ID)
	{
		if (BPM_FormColumn_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormColumn_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormColumn_ID, Integer.valueOf(BPM_FormColumn_ID));
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

	public I_BPM_FormLine getBPM_FormLine() throws RuntimeException
    {
		return (I_BPM_FormLine)MTable.get(getCtx(), I_BPM_FormLine.Table_Name)
			.getPO(getBPM_FormLine_ID(), get_TrxName());	}

	/** Set BPM_FormLine ID.
		@param BPM_FormLine_ID BPM_FormLine ID	  */
	public void setBPM_FormLine_ID (int BPM_FormLine_ID)
	{
		if (BPM_FormLine_ID < 1) 
			set_Value (COLUMNNAME_BPM_FormLine_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_FormLine_ID, Integer.valueOf(BPM_FormLine_ID));
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

	/** Set CellValue.
		@param CellValue CellValue	  */
	public void setCellValue (BigDecimal CellValue)
	{
		set_Value (COLUMNNAME_CellValue, CellValue);
	}

	/** Get CellValue.
		@return CellValue	  */
	public BigDecimal getCellValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CellValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
}