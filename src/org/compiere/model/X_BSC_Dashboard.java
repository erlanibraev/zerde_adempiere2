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

/** Generated Model for BSC_Dashboard
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_BSC_Dashboard extends PO implements I_BSC_Dashboard, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130828L;

    /** the default Constructor */
    public X_BSC_Dashboard(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_BSC_Dashboard (Properties ctx, int BSC_Dashboard_ID, String trxName)
    {
      super (ctx, BSC_Dashboard_ID, trxName);
      /** if (BSC_Dashboard_ID == 0)
        {
			setBSC_Dashboard_ID (0);
			setC_BPartner_ID (0);
			setNumberOfRuns (0);
        } */
    }

    /** Load Constructor */
    public X_BSC_Dashboard (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_BSC_Dashboard[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BSC_Dashboard ID.
		@param BSC_Dashboard_ID BSC_Dashboard ID	  */
	public void setBSC_Dashboard_ID (int BSC_Dashboard_ID)
	{
		if (BSC_Dashboard_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BSC_Dashboard_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BSC_Dashboard_ID, Integer.valueOf(BSC_Dashboard_ID));
	}

	/** Get BSC_Dashboard ID.
		@return BSC_Dashboard ID	  */
	public int getBSC_Dashboard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSC_Dashboard_ID);
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_Name)
			.getPO(getHR_Job_ID(), get_TrxName());	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) 
			set_Value (COLUMNNAME_HR_Job_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of runs.
		@param NumberOfRuns 
		Frequency of processing Perpetual Inventory
	  */
	public void setNumberOfRuns (int NumberOfRuns)
	{
		set_Value (COLUMNNAME_NumberOfRuns, Integer.valueOf(NumberOfRuns));
	}

	/** Get Number of runs.
		@return Frequency of processing Perpetual Inventory
	  */
	public int getNumberOfRuns () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberOfRuns);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}