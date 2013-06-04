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

/** Generated Model for BSC_Responsible_Executor
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Responsible_Executor extends PO implements I_BSC_Responsible_Executor, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130604L;

    /** the default Constructor */
    public X_BSC_Responsible_Executor(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Responsible_Executor (Properties ctx, int BSC_Responsible_Executor_ID, String trxName)
    {
      super (ctx, BSC_Responsible_Executor_ID, trxName);
      /** if (BSC_Responsible_Executor_ID == 0)
        {
			setBSC_Responsible_Executor_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_Responsible_Executor (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_Responsible_Executor[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_BSC_Action getBSC_Action() throws RuntimeException
    {
		return (I_BSC_Action)MTable.get(getCtx(), I_BSC_Action.Table_Name)
			.getPO(getBSC_Action_ID(), get_TrxName());	}

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

	/** Set BSC Responsible Executor ID.
		@param BSC_Responsible_Executor_ID BSC Responsible Executor ID	  */
	public void setBSC_Responsible_Executor_ID (int BSC_Responsible_Executor_ID)
	{
		if (BSC_Responsible_Executor_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Responsible_Executor_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Responsible_Executor_ID, Integer.valueOf(BSC_Responsible_Executor_ID));
	}

	/** Get BSC Responsible Executor ID.
		@return BSC Responsible Executor ID	  */
	public int getBSC_Responsible_Executor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Responsible_Executor_ID);
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

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
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

	/** Set Subsidiary.
		@param SubSidiary 
		subsidiary and dependent company
	  */
	public void setSubSidiary (boolean SubSidiary)
	{
		set_Value (COLUMNNAME_SubSidiary, Boolean.valueOf(SubSidiary));
	}

	/** Get Subsidiary.
		@return subsidiary and dependent company
	  */
	public boolean isSubSidiary () 
	{
		Object oo = get_Value(COLUMNNAME_SubSidiary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}