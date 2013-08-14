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

/** Generated Model for BPM_ProjectLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_ProjectLine extends PO implements I_BPM_ProjectLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130814L;

    /** the default Constructor */
    public X_BPM_ProjectLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_ProjectLine (Properties ctx, int BPM_ProjectLine_ID, String trxName)
    {
      super (ctx, BPM_ProjectLine_ID, trxName);
      /** if (BPM_ProjectLine_ID == 0)
        {
			setBPM_Form_ID (0);
			setBPM_Project_ID (0);
			setBPM_ProjectLine_ID (0);
			setProcessed (false);
// N
			setProcessing (false);
// N
        } */
    }

    /** Load Constructor */
    public X_BPM_ProjectLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_ProjectLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_BPM_Project getBPM_Project() throws RuntimeException
    {
		return (I_BPM_Project)MTable.get(getCtx(), I_BPM_Project.Table_Name)
			.getPO(getBPM_Project_ID(), get_TrxName());	}

	/** Set BPM_Project ID.
		@param BPM_Project_ID BPM_Project ID	  */
	public void setBPM_Project_ID (int BPM_Project_ID)
	{
		if (BPM_Project_ID < 1) 
			set_Value (COLUMNNAME_BPM_Project_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_Project_ID, Integer.valueOf(BPM_Project_ID));
	}

	/** Get BPM_Project ID.
		@return BPM_Project ID	  */
	public int getBPM_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_ProjectLine ID.
		@param BPM_ProjectLine_ID BPM_ProjectLine ID	  */
	public void setBPM_ProjectLine_ID (int BPM_ProjectLine_ID)
	{
		if (BPM_ProjectLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_ProjectLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_ProjectLine_ID, Integer.valueOf(BPM_ProjectLine_ID));
	}

	/** Get BPM_ProjectLine ID.
		@return BPM_ProjectLine ID	  */
	public int getBPM_ProjectLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ProjectLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}