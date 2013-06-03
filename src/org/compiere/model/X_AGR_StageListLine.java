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

/** Generated Model for AGR_StageListLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_StageListLine extends PO implements I_AGR_StageListLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130531L;

    /** Standard Constructor */
    public X_AGR_StageListLine (Properties ctx, int AGR_StageListLine_ID, String trxName)
    {
      super (ctx, AGR_StageListLine_ID, trxName);
      /** if (AGR_StageListLine_ID == 0)
        {
			setAGR_StageList_ID (0);
			setAGR_StageListLine_ID (0);
			setC_BPartner_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AGR_StageListLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_StageListLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AGR_StageList getAGR_StageList() throws RuntimeException
    {
		return (I_AGR_StageList)MTable.get(getCtx(), I_AGR_StageList.Table_Name)
			.getPO(getAGR_StageList_ID(), get_TrxName());	}

	/** Set AGR_StageList ID.
		@param AGR_StageList_ID 
		Связь с инициатором
	  */
	public void setAGR_StageList_ID (int AGR_StageList_ID)
	{
		if (AGR_StageList_ID < 1) 
			set_Value (COLUMNNAME_AGR_StageList_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_StageList_ID, Integer.valueOf(AGR_StageList_ID));
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

	/** Set AGR_StageListLine ID.
		@param AGR_StageListLine_ID AGR_StageListLine ID	  */
	public void setAGR_StageListLine_ID (int AGR_StageListLine_ID)
	{
		if (AGR_StageListLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_StageListLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_StageListLine_ID, Integer.valueOf(AGR_StageListLine_ID));
	}

	/** Get AGR_StageListLine ID.
		@return AGR_StageListLine ID	  */
	public int getAGR_StageListLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_StageListLine_ID);
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

	/** Set isAlsoInclude.
		@param isAlsoInclude 
		Также включая
	  */
	public void setisAlsoInclude (boolean isAlsoInclude)
	{
		set_Value (COLUMNNAME_isAlsoInclude, Boolean.valueOf(isAlsoInclude));
	}

	/** Get isAlsoInclude.
		@return Также включая
	  */
	public boolean isAlsoInclude () 
	{
		Object oo = get_Value(COLUMNNAME_isAlsoInclude);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isExceptFor.
		@param isExceptFor 
		За исключением
	  */
	public void setisExceptFor (boolean isExceptFor)
	{
		set_Value (COLUMNNAME_isExceptFor, Boolean.valueOf(isExceptFor));
	}

	/** Get isExceptFor.
		@return За исключением
	  */
	public boolean isExceptFor () 
	{
		Object oo = get_Value(COLUMNNAME_isExceptFor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}