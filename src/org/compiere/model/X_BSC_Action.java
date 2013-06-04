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

/** Generated Model for BSC_Action
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Action extends PO implements I_BSC_Action, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130604L;

    /** the default Constructor */
    public X_BSC_Action(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Action (Properties ctx, int BSC_Action_ID, String trxName)
    {
      super (ctx, BSC_Action_ID, trxName);
      /** if (BSC_Action_ID == 0)
        {
			setBSC_Action_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_Action (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_Action[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BSC NetWork Diagram Sub Line Action ID.
		@param BSC_Action_ID BSC NetWork Diagram Sub Line Action ID	  */
	public void setBSC_Action_ID (int BSC_Action_ID)
	{
		if (BSC_Action_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Action_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Action_ID, Integer.valueOf(BSC_Action_ID));
	}

	/** Get BSC NetWork Diagram Sub Line Action ID.
		@return BSC NetWork Diagram Sub Line Action ID	  */
	public int getBSC_Action_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Action_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_BSC_NetWorkDiagSubLine getBSC_NetWorkDiagSubLine() throws RuntimeException
    {
		return (I_BSC_NetWorkDiagSubLine)MTable.get(getCtx(), I_BSC_NetWorkDiagSubLine.Table_Name)
			.getPO(getBSC_NetWorkDiagSubLine_ID(), get_TrxName());	}

	/** Set BSC NetWork Diagram Sub Line  ID.
		@param BSC_NetWorkDiagSubLine_ID BSC NetWork Diagram Sub Line  ID	  */
	public void setBSC_NetWorkDiagSubLine_ID (int BSC_NetWorkDiagSubLine_ID)
	{
		if (BSC_NetWorkDiagSubLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_NetWorkDiagSubLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_NetWorkDiagSubLine_ID, Integer.valueOf(BSC_NetWorkDiagSubLine_ID));
	}

	/** Get BSC NetWork Diagram Sub Line  ID.
		@return BSC NetWork Diagram Sub Line  ID	  */
	public int getBSC_NetWorkDiagSubLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_NetWorkDiagSubLine_ID);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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
}