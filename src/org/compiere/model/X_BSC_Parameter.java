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

/** Generated Model for BSC_Parameter
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Parameter extends PO implements I_BSC_Parameter, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130822L;

    /** the default Constructor */
    public X_BSC_Parameter(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Parameter (Properties ctx, int BSC_Parameter_ID, String trxName)
    {
      super (ctx, BSC_Parameter_ID, trxName);
      /** if (BSC_Parameter_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_BSC_Parameter (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_Parameter[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set b_graph.
		@param b_graph b_graph	  */
	public void setb_graph (String b_graph)
	{
		set_Value (COLUMNNAME_b_graph, b_graph);
	}

	/** Get b_graph.
		@return b_graph	  */
	public String getb_graph () 
	{
		return (String)get_Value(COLUMNNAME_b_graph);
	}

	/** Set BSC_Parameter ID.
		@param BSC_Parameter_ID BSC_Parameter ID	  */
	public void setBSC_Parameter_ID (int BSC_Parameter_ID)
	{
		if (BSC_Parameter_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Parameter_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Parameter_ID, Integer.valueOf(BSC_Parameter_ID));
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

	/** Set IsExports.
		@param IsExports 
		IsExports
	  */
	public void setIsExports (boolean IsExports)
	{
		set_Value (COLUMNNAME_IsExports, Boolean.valueOf(IsExports));
	}

	/** Get IsExports.
		@return IsExports
	  */
	public boolean isExports () 
	{
		Object oo = get_Value(COLUMNNAME_IsExports);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Modules AD_Reference_ID=1000171 */
	public static final int MODULES_AD_Reference_ID=1000171;
	/** BPM = BPM */
	public static final String MODULES_BPM = "BPM";
	/** BSC = BSC */
	public static final String MODULES_BSC = "BSC";
	/** FI = FI */
	public static final String MODULES_FI = "FI";
	/** HRM = HRM */
	public static final String MODULES_HRM = "HRM";
	/** MM = MM */
	public static final String MODULES_MM = "MM";
	/** Set Modules.
		@param Modules 
		Modules
	  */
	public void setModules (String Modules)
	{

		set_Value (COLUMNNAME_Modules, Modules);
	}

	/** Get Modules.
		@return Modules
	  */
	public String getModules () 
	{
		return (String)get_Value(COLUMNNAME_Modules);
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

	/** Set without_period.
		@param without_period 
		without_period
	  */
	public void setwithout_period (boolean without_period)
	{
		set_Value (COLUMNNAME_without_period, Boolean.valueOf(without_period));
	}

	/** Get without_period.
		@return without_period
	  */
	public boolean iswithout_period () 
	{
		Object oo = get_Value(COLUMNNAME_without_period);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}