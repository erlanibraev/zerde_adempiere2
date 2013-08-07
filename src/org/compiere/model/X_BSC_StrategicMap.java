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

/** Generated Model for BSC_StrategicMap
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_StrategicMap extends PO implements I_BSC_StrategicMap, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130806L;

    /** the default Constructor */
    public X_BSC_StrategicMap(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_StrategicMap (Properties ctx, int BSC_StrategicMap_ID, String trxName)
    {
      super (ctx, BSC_StrategicMap_ID, trxName);
      /** if (BSC_StrategicMap_ID == 0)
        {
			setBSC_StrategicMap_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_StrategicMap (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_BSC_StrategicMap[")
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

	/** Set BSC_StrategicMap ID.
		@param BSC_StrategicMap_ID BSC_StrategicMap ID	  */
	public void setBSC_StrategicMap_ID (int BSC_StrategicMap_ID)
	{
		if (BSC_StrategicMap_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_StrategicMap_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_StrategicMap_ID, Integer.valueOf(BSC_StrategicMap_ID));
	}

	/** Get BSC_StrategicMap ID.
		@return BSC_StrategicMap ID	  */
	public int getBSC_StrategicMap_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_StrategicMap_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Height.
		@param Height 
		Height
	  */
	public void setHeight (int Height)
	{
		set_Value (COLUMNNAME_Height, Integer.valueOf(Height));
	}

	/** Get Height.
		@return Height
	  */
	public int getHeight () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Height);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Width.
		@param Width 
		Width
	  */
	public void setWidth (int Width)
	{
		set_Value (COLUMNNAME_Width, Integer.valueOf(Width));
	}

	/** Get Width.
		@return Width
	  */
	public int getWidth () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Width);
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