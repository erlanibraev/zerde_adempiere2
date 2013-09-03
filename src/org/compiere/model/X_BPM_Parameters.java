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

/** Generated Model for BPM_Parameters
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_Parameters extends PO implements I_BPM_Parameters, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130903L;

    /** the default Constructor */
    public X_BPM_Parameters(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_Parameters (Properties ctx, int BPM_Parameters_ID, String trxName)
    {
      super (ctx, BPM_Parameters_ID, trxName);
      /** if (BPM_Parameters_ID == 0)
        {
			setAD_Table_ID (0);
			setBPM_Parameters_ID (0);
			setColumnName (null);
			setRecord_ID (0);
			setValue1 (null);
        } */
    }

    /** Load Constructor */
    public X_BPM_Parameters (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_Parameters[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_Parameters ID.
		@param BPM_Parameters_ID BPM_Parameters ID	  */
	public void setBPM_Parameters_ID (int BPM_Parameters_ID)
	{
		if (BPM_Parameters_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_Parameters_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_Parameters_ID, Integer.valueOf(BPM_Parameters_ID));
	}

	/** Get BPM_Parameters ID.
		@return BPM_Parameters ID	  */
	public int getBPM_Parameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Parameters_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value1.
		@param Value1 Value1	  */
	public void setValue1 (String Value1)
	{
		set_Value (COLUMNNAME_Value1, Value1);
	}

	/** Get Value1.
		@return Value1	  */
	public String getValue1 () 
	{
		return (String)get_Value(COLUMNNAME_Value1);
	}

	/** Set Value To.
		@param Value2 
		Value To
	  */
	public void setValue2 (String Value2)
	{
		set_Value (COLUMNNAME_Value2, Value2);
	}

	/** Get Value To.
		@return Value To
	  */
	public String getValue2 () 
	{
		return (String)get_Value(COLUMNNAME_Value2);
	}
}