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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for TRM_StageOptions
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_StageOptions extends PO implements I_TRM_StageOptions, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130520L;

    /** the default Constructor */
    public X_TRM_StageOptions(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_StageOptions (Properties ctx, int TRM_StageOptions_ID, String trxName)
    {
      super (ctx, TRM_StageOptions_ID, trxName);
      /** if (TRM_StageOptions_ID == 0)
        {
			setIsAlternate2Active (true);
// Y
			setIsAlternateActive (true);
// Y
			setIsHeaderActive (true);
// Y
			setTRM_Stage_ID (0);
// @TRM_Stage_ID@
			setTRM_StageOptions_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_StageOptions (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_StageOptions[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getAlternate() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAlternate_ID(), get_TrxName());	}

	/** Set Alternate_ID.
		@param Alternate_ID 
		Альтернативное ответственное лицо
	  */
	public void setAlternate_ID (int Alternate_ID)
	{
		if (Alternate_ID < 1) 
			set_Value (COLUMNNAME_Alternate_ID, null);
		else 
			set_Value (COLUMNNAME_Alternate_ID, Integer.valueOf(Alternate_ID));
	}

	/** Get Alternate_ID.
		@return Альтернативное ответственное лицо
	  */
	public int getAlternate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Alternate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getAlternate2() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAlternate2_ID(), get_TrxName());	}

	/** Set Alternate2_ID.
		@param Alternate2_ID 
		Альтернативное ответственное лицо (2)
	  */
	public void setAlternate2_ID (int Alternate2_ID)
	{
		if (Alternate2_ID < 1) 
			set_Value (COLUMNNAME_Alternate2_ID, null);
		else 
			set_Value (COLUMNNAME_Alternate2_ID, Integer.valueOf(Alternate2_ID));
	}

	/** Get Alternate2_ID.
		@return Альтернативное ответственное лицо (2)
	  */
	public int getAlternate2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Alternate2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getHR_Header() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getHR_Header_ID(), get_TrxName());	}

	/** Set HR_Header_ID.
		@param HR_Header_ID 
		Руководитель департамента
	  */
	public void setHR_Header_ID (int HR_Header_ID)
	{
		if (HR_Header_ID < 1) 
			set_Value (COLUMNNAME_HR_Header_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Header_ID, Integer.valueOf(HR_Header_ID));
	}

	/** Get HR_Header_ID.
		@return Руководитель департамента
	  */
	public int getHR_Header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsAlternate2Active.
		@param IsAlternate2Active 
		Активно
	  */
	public void setIsAlternate2Active (boolean IsAlternate2Active)
	{
		set_Value (COLUMNNAME_IsAlternate2Active, Boolean.valueOf(IsAlternate2Active));
	}

	/** Get IsAlternate2Active.
		@return Активно
	  */
	public boolean isAlternate2Active () 
	{
		Object oo = get_Value(COLUMNNAME_IsAlternate2Active);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsAlternateActive.
		@param IsAlternateActive 
		Активно
	  */
	public void setIsAlternateActive (boolean IsAlternateActive)
	{
		set_Value (COLUMNNAME_IsAlternateActive, Boolean.valueOf(IsAlternateActive));
	}

	/** Get IsAlternateActive.
		@return Активно
	  */
	public boolean isAlternateActive () 
	{
		Object oo = get_Value(COLUMNNAME_IsAlternateActive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsHeaderActive.
		@param IsHeaderActive 
		Активно
	  */
	public void setIsHeaderActive (boolean IsHeaderActive)
	{
		set_Value (COLUMNNAME_IsHeaderActive, Boolean.valueOf(IsHeaderActive));
	}

	/** Get IsHeaderActive.
		@return Активно
	  */
	public boolean isHeaderActive () 
	{
		Object oo = get_Value(COLUMNNAME_IsHeaderActive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (BigDecimal Priority)
	{
		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public BigDecimal getPriority () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Priority);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_TRM_Stage getTRM_Stage() throws RuntimeException
    {
		return (I_TRM_Stage)MTable.get(getCtx(), I_TRM_Stage.Table_Name)
			.getPO(getTRM_Stage_ID(), get_TrxName());	}

	/** Set TRM_Stage_ID.
		@param TRM_Stage_ID TRM_Stage_ID	  */
	public void setTRM_Stage_ID (int TRM_Stage_ID)
	{
		if (TRM_Stage_ID < 1) 
			set_Value (COLUMNNAME_TRM_Stage_ID, null);
		else 
			set_Value (COLUMNNAME_TRM_Stage_ID, Integer.valueOf(TRM_Stage_ID));
	}

	/** Get TRM_Stage_ID.
		@return TRM_Stage_ID	  */
	public int getTRM_Stage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TRM_StageOptions_ID.
		@param TRM_StageOptions_ID TRM_StageOptions_ID	  */
	public void setTRM_StageOptions_ID (int TRM_StageOptions_ID)
	{
		if (TRM_StageOptions_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_StageOptions_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_StageOptions_ID, Integer.valueOf(TRM_StageOptions_ID));
	}

	/** Get TRM_StageOptions_ID.
		@return TRM_StageOptions_ID	  */
	public int getTRM_StageOptions_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_StageOptions_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}