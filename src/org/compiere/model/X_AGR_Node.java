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

/** Generated Model for AGR_Node
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AGR_Node extends PO implements I_AGR_Node, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130523L;

    /** the default Constructor */
    public X_AGR_Node(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_AGR_Node (Properties ctx, int AGR_Node_ID, String trxName)
    {
      super (ctx, AGR_Node_ID, trxName);
      /** if (AGR_Node_ID == 0)
        {
			setAGR_NextStage_ID (0);
			setAGR_Node_ID (0);
			setAGR_Stage_ID (0);
// @AGR_Stage_ID@
        } */
    }

    /** Load Constructor */
    public X_AGR_Node (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AGR_Node[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AGR_Stage getAGR_NextStage() throws RuntimeException
    {
		return (I_AGR_Stage)MTable.get(getCtx(), I_AGR_Stage.Table_Name)
			.getPO(getAGR_NextStage_ID(), get_TrxName());	}

	/** Set AGR_NextStage_ID.
		@param AGR_NextStage_ID 
		Следующий этап
	  */
	public void setAGR_NextStage_ID (int AGR_NextStage_ID)
	{
		if (AGR_NextStage_ID < 1) 
			set_Value (COLUMNNAME_AGR_NextStage_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_NextStage_ID, Integer.valueOf(AGR_NextStage_ID));
	}

	/** Get AGR_NextStage_ID.
		@return Следующий этап
	  */
	public int getAGR_NextStage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_NextStage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AGR_Node ID.
		@param AGR_Node_ID AGR_Node ID	  */
	public void setAGR_Node_ID (int AGR_Node_ID)
	{
		if (AGR_Node_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AGR_Node_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AGR_Node_ID, Integer.valueOf(AGR_Node_ID));
	}

	/** Get AGR_Node ID.
		@return AGR_Node ID	  */
	public int getAGR_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set isBack.
		@param isBack 
		Направление назад
	  */
	public void setisBack (boolean isBack)
	{
		set_Value (COLUMNNAME_isBack, Boolean.valueOf(isBack));
	}

	/** Get isBack.
		@return Направление назад
	  */
	public boolean isBack () 
	{
		Object oo = get_Value(COLUMNNAME_isBack);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}