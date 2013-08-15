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

/** Generated Model for BPM_Project
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_Project extends PO implements I_BPM_Project, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130815L;

    /** the default Constructor */
    public X_BPM_Project(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_Project (Properties ctx, int BPM_Project_ID, String trxName)
    {
      super (ctx, BPM_Project_ID, trxName);
      /** if (BPM_Project_ID == 0)
        {
			setBPM_Project_ID (0);
			setBPM_VersionBudget_ID (0);
			setName (null);
			setProcessed (false);
// N
			setProcessing (false);
// N
        } */
    }

    /** Load Constructor */
    public X_BPM_Project (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_Project[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_Project ID.
		@param BPM_Project_ID BPM_Project ID	  */
	public void setBPM_Project_ID (int BPM_Project_ID)
	{
		if (BPM_Project_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_Project_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_Project_ID, Integer.valueOf(BPM_Project_ID));
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

	public I_BPM_VersionBudget getBPM_VersionBudget() throws RuntimeException
    {
		return (I_BPM_VersionBudget)MTable.get(getCtx(), I_BPM_VersionBudget.Table_Name)
			.getPO(getBPM_VersionBudget_ID(), get_TrxName());	}

	/** Set BPM_VersionBudget ID.
		@param BPM_VersionBudget_ID BPM_VersionBudget ID	  */
	public void setBPM_VersionBudget_ID (int BPM_VersionBudget_ID)
	{
		if (BPM_VersionBudget_ID < 1) 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_VersionBudget_ID, Integer.valueOf(BPM_VersionBudget_ID));
	}

	/** Get BPM_VersionBudget ID.
		@return BPM_VersionBudget ID	  */
	public int getBPM_VersionBudget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_VersionBudget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DocAction AD_Reference_ID=1000151 */
	public static final int DOCACTION_AD_Reference_ID=1000151;
	/** Approved = AP */
	public static final String DOCACTION_Approved = "AP";
	/** Closed = CL */
	public static final String DOCACTION_Closed = "CL";
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Project = PR */
	public static final String DOCACTION_Project = "PR";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=1000151 */
	public static final int DOCSTATUS_AD_Reference_ID=1000151;
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Complete = CO */
	public static final String DOCSTATUS_Complete = "CO";
	/** Project = PR */
	public static final String DOCSTATUS_Project = "PR";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
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