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

/** Generated Model for BPM_EmployeeLine
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BPM_EmployeeLine extends PO implements I_BPM_EmployeeLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130514L;

    /** the default Constructor */
    public X_BPM_EmployeeLine(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BPM_EmployeeLine (Properties ctx, int BPM_EmployeeLine_ID, String trxName)
    {
      super (ctx, BPM_EmployeeLine_ID, trxName);
      /** if (BPM_EmployeeLine_ID == 0)
        {
			setBPM_ABP_ID (0);
			setBPM_EmployeeLine_ID (0);
			setC_BPartner_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BPM_EmployeeLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BPM_EmployeeLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPM_ABP ID.
		@param BPM_ABP_ID BPM_ABP ID	  */
	public void setBPM_ABP_ID (int BPM_ABP_ID)
	{
		if (BPM_ABP_ID < 1) 
			set_Value (COLUMNNAME_BPM_ABP_ID, null);
		else 
			set_Value (COLUMNNAME_BPM_ABP_ID, Integer.valueOf(BPM_ABP_ID));
	}

	/** Get BPM_ABP ID.
		@return BPM_ABP ID	  */
	public int getBPM_ABP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_ABP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BPM_EmployeeLine ID.
		@param BPM_EmployeeLine_ID BPM_EmployeeLine ID	  */
	public void setBPM_EmployeeLine_ID (int BPM_EmployeeLine_ID)
	{
		if (BPM_EmployeeLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BPM_EmployeeLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BPM_EmployeeLine_ID, Integer.valueOf(BPM_EmployeeLine_ID));
	}

	/** Get BPM_EmployeeLine ID.
		@return BPM_EmployeeLine ID	  */
	public int getBPM_EmployeeLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPM_EmployeeLine_ID);
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
}