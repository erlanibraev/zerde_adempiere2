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

/** Generated Model for bcs_inputfinreportsline
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_bcs_inputfinreportsline extends PO implements I_bcs_inputfinreportsline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130403L;

    /** Standard Constructor */
    public X_bcs_inputfinreportsline (Properties ctx, int bcs_inputfinreportsline_ID, String trxName)
    {
      super (ctx, bcs_inputfinreportsline_ID, trxName);
      /** if (bcs_inputfinreportsline_ID == 0)
        {
			setbcs_finreportsdictline_ID (0);
			setbcs_inputfinreports_ID (0);
			setbcs_inputfinreportsline_ID (0);
        } */
    }

    /** Load Constructor */
    public X_bcs_inputfinreportsline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_bcs_inputfinreportsline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set amtbegin.
		@param amtbegin amtbegin	  */
	public void setamtbegin (BigDecimal amtbegin)
	{
		set_Value (COLUMNNAME_amtbegin, amtbegin);
	}

	/** Get amtbegin.
		@return amtbegin	  */
	public BigDecimal getamtbegin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amtbegin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set amtcorrection.
		@param amtcorrection amtcorrection	  */
	public void setamtcorrection (BigDecimal amtcorrection)
	{
		set_Value (COLUMNNAME_amtcorrection, amtcorrection);
	}

	/** Get amtcorrection.
		@return amtcorrection	  */
	public BigDecimal getamtcorrection () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amtcorrection);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set amtend.
		@param amtend amtend	  */
	public void setamtend (BigDecimal amtend)
	{
		set_Value (COLUMNNAME_amtend, amtend);
	}

	/** Get amtend.
		@return amtend	  */
	public BigDecimal getamtend () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amtend);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_bcs_finreportsdictline getbcs_finreportsdictline() throws RuntimeException
    {
		return (I_bcs_finreportsdictline)MTable.get(getCtx(), I_bcs_finreportsdictline.Table_Name)
			.getPO(getbcs_finreportsdictline_ID(), get_TrxName());	}

	/** Set bcs_finreportsdictline ID.
		@param bcs_finreportsdictline_ID bcs_finreportsdictline ID	  */
	public void setbcs_finreportsdictline_ID (int bcs_finreportsdictline_ID)
	{
		if (bcs_finreportsdictline_ID < 1) 
			set_Value (COLUMNNAME_bcs_finreportsdictline_ID, null);
		else 
			set_Value (COLUMNNAME_bcs_finreportsdictline_ID, Integer.valueOf(bcs_finreportsdictline_ID));
	}

	/** Get bcs_finreportsdictline ID.
		@return bcs_finreportsdictline ID	  */
	public int getbcs_finreportsdictline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_finreportsdictline_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_bcs_inputfinreports getbcs_inputfinreports() throws RuntimeException
    {
		return (I_bcs_inputfinreports)MTable.get(getCtx(), I_bcs_inputfinreports.Table_Name)
			.getPO(getbcs_inputfinreports_ID(), get_TrxName());	}

	/** Set bcs_inputfinreports ID.
		@param bcs_inputfinreports_ID bcs_inputfinreports ID	  */
	public void setbcs_inputfinreports_ID (int bcs_inputfinreports_ID)
	{
		if (bcs_inputfinreports_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreports_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreports_ID, Integer.valueOf(bcs_inputfinreports_ID));
	}

	/** Get bcs_inputfinreports ID.
		@return bcs_inputfinreports ID	  */
	public int getbcs_inputfinreports_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_inputfinreports_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set bcs_inputfinreportsline ID.
		@param bcs_inputfinreportsline_ID bcs_inputfinreportsline ID	  */
	public void setbcs_inputfinreportsline_ID (int bcs_inputfinreportsline_ID)
	{
		if (bcs_inputfinreportsline_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreportsline_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_bcs_inputfinreportsline_ID, Integer.valueOf(bcs_inputfinreportsline_ID));
	}

	/** Get bcs_inputfinreportsline ID.
		@return bcs_inputfinreportsline ID	  */
	public int getbcs_inputfinreportsline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bcs_inputfinreportsline_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}