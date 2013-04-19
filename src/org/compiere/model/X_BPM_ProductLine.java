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

/** Generated Model for BPM_ProductLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_ProductLine extends PO implements I_BPM_ProductLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130419L;

    /** the default Constructor */
    public X_BPM_ProductLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_ProductLine (Properties ctx, int BPM_ProductLine_ID, String trxName)
    {
      super (ctx, BPM_ProductLine_ID, trxName);
      /** if (BPM_ProductLine_ID == 0)
        {
			setBPM_ABP_ID (0);
			setBPM_ProductLine_ID (0);
			setM_Product_Category_ID (0);
			setM_ProductCat_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_ProductLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_ProductLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set BPM_ProductLine.
		@param BPM_ProductLine_ID BPM_ProductLine	  */
	public void setBPM_ProductLine_ID (int BPM_ProductLine_ID)
	{
		if (BPM_ProductLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_ProductLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_ProductLine_ID, Integer.valueOf(BPM_ProductLine_ID));
	}

	/** Get BPM_ProductLine.
		@return BPM_ProductLine	  */
	public int getBPM_ProductLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ProductLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_ProductCat() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_ProductCat_ID(), get_TrxName());	}

	/** Set M_ProductCat_ID.
		@param M_ProductCat_ID M_ProductCat_ID	  */
	public void setM_ProductCat_ID (int M_ProductCat_ID)
	{
		if (M_ProductCat_ID < 1) 
			set_Value (COLUMNNAME_M_ProductCat_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductCat_ID, Integer.valueOf(M_ProductCat_ID));
	}

	/** Get M_ProductCat_ID.
		@return M_ProductCat_ID	  */
	public int getM_ProductCat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductCat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}