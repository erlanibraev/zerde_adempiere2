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

/** Generated Model for BSC_KeySuccessFactorLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_KeySuccessFactorLine extends PO implements I_BSC_KeySuccessFactorLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130908L;

    /** the default Constructor */
    public X_BSC_KeySuccessFactorLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_KeySuccessFactorLine (Properties ctx, int BSC_KeySuccessFactorLine_ID, String trxName)
    {
      super (ctx, BSC_KeySuccessFactorLine_ID, trxName);
      /** if (BSC_KeySuccessFactorLine_ID == 0)
        {
			setBSC_KeySuccessFactorLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_KeySuccessFactorLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_KeySuccessFactorLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BSC_KeySuccessFactor getBSC_KeySuccessFactor() throws RuntimeException
    {
		return (I_BSC_KeySuccessFactor)MTable.get(getCtx(), I_BSC_KeySuccessFactor.Table_Name)
			.getPO(getBSC_KeySuccessFactor_ID(), get_TrxName());	}

	/** Set BSC_KeySuccessFactor ID.
		@param BSC_KeySuccessFactor_ID BSC_KeySuccessFactor ID	  */
	public void setBSC_KeySuccessFactor_ID (int BSC_KeySuccessFactor_ID)
	{
		if (BSC_KeySuccessFactor_ID < 1) 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_ID, null);
		else 
			set_Value (COLUMNNAME_BSC_KeySuccessFactor_ID, Integer.valueOf(BSC_KeySuccessFactor_ID));
	}

	/** Get BSC_KeySuccessFactor ID.
		@return BSC_KeySuccessFactor ID	  */
	public int getBSC_KeySuccessFactor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_KeySuccessFactor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BSC_KeySuccessFactorLine ID.
		@param BSC_KeySuccessFactorLine_ID BSC_KeySuccessFactorLine ID	  */
	public void setBSC_KeySuccessFactorLine_ID (int BSC_KeySuccessFactorLine_ID)
	{
		if (BSC_KeySuccessFactorLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_KeySuccessFactorLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_KeySuccessFactorLine_ID, Integer.valueOf(BSC_KeySuccessFactorLine_ID));
	}

	/** Get BSC_KeySuccessFactorLine ID.
		@return BSC_KeySuccessFactorLine ID	  */
	public int getBSC_KeySuccessFactorLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_KeySuccessFactorLine_ID);
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

	/** Set X Position.
		@param XPosition 
		Absolute X (horizontal) position in 1/72 of an inch
	  */
	public void setXPosition (int XPosition)
	{
		set_Value (COLUMNNAME_XPosition, Integer.valueOf(XPosition));
	}

	/** Get X Position.
		@return Absolute X (horizontal) position in 1/72 of an inch
	  */
	public int getXPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_XPosition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Y Position.
		@param YPosition 
		Absolute Y (vertical) position in 1/72 of an inch
	  */
	public void setYPosition (int YPosition)
	{
		set_Value (COLUMNNAME_YPosition, Integer.valueOf(YPosition));
	}

	/** Get Y Position.
		@return Absolute Y (vertical) position in 1/72 of an inch
	  */
	public int getYPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_YPosition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}