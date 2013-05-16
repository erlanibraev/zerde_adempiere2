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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for TRM_Application
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_TRM_Application extends PO implements I_TRM_Application, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130514L;

    /** the default Constructor */
    public X_TRM_Application(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_TRM_Application (Properties ctx, int TRM_Application_ID, String trxName)
    {
      super (ctx, TRM_Application_ID, trxName);
      /** if (TRM_Application_ID == 0)
        {
			setApplAmount (Env.ZERO);
			setCMS_Contract_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setInitiator_ID (0);
// @AD_User_ID@
			settrs_application_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Application (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_TRM_Application[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ApplAmount.
		@param ApplAmount 
		Сумма финансовой заявки
	  */
	public void setApplAmount (BigDecimal ApplAmount)
	{
		set_Value (COLUMNNAME_ApplAmount, ApplAmount);
	}

	/** Get ApplAmount.
		@return Сумма финансовой заявки
	  */
	public BigDecimal getApplAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ApplAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Apps.
		@param Apps 
		Приложения
	  */
	public void setApps (String Apps)
	{
		set_Value (COLUMNNAME_Apps, Apps);
	}

	/** Get Apps.
		@return Приложения
	  */
	public String getApps () 
	{
		return (String)get_Value(COLUMNNAME_Apps);
	}

	public I_CMS_Contract getCMS_Contract() throws RuntimeException
    {
		return (I_CMS_Contract)MTable.get(getCtx(), I_CMS_Contract.Table_Name)
			.getPO(getCMS_Contract_ID(), get_TrxName());	}

	/** Set Contract.
		@param CMS_Contract_ID 
		Номер контракта (CMS)
	  */
	public void setCMS_Contract_ID (int CMS_Contract_ID)
	{
		if (CMS_Contract_ID < 1) 
			set_Value (COLUMNNAME_CMS_Contract_ID, null);
		else 
			set_Value (COLUMNNAME_CMS_Contract_ID, Integer.valueOf(CMS_Contract_ID));
	}

	/** Get Contract.
		@return Номер контракта (CMS)
	  */
	public int getCMS_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CMS_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDocumentNo());
    }

	/** Set GenerateApplication.
		@param GenerateApplication 
		Сформировать заявку
	  */
	public void setGenerateApplication (String GenerateApplication)
	{
		set_Value (COLUMNNAME_GenerateApplication, GenerateApplication);
	}

	/** Get GenerateApplication.
		@return Сформировать заявку
	  */
	public String getGenerateApplication () 
	{
		return (String)get_Value(COLUMNNAME_GenerateApplication);
	}

	public org.compiere.model.I_AD_User getInitiator() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getInitiator_ID(), get_TrxName());	}

	/** Set Initiator_ID.
		@param Initiator_ID 
		Сотрудник, создавший заявку
	  */
	public void setInitiator_ID (int Initiator_ID)
	{
		if (Initiator_ID < 1) 
			set_Value (COLUMNNAME_Initiator_ID, null);
		else 
			set_Value (COLUMNNAME_Initiator_ID, Integer.valueOf(Initiator_ID));
	}

	/** Get Initiator_ID.
		@return Сотрудник, создавший заявку
	  */
	public int getInitiator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Initiator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PaymentTerms.
		@param PaymentTerms 
		Условия оплаты
	  */
	public void setPaymentTerms (String PaymentTerms)
	{
		set_Value (COLUMNNAME_PaymentTerms, PaymentTerms);
	}

	/** Get PaymentTerms.
		@return Условия оплаты
	  */
	public String getPaymentTerms () 
	{
		return (String)get_Value(COLUMNNAME_PaymentTerms);
	}

	/** Set Financial Application ID.
		@param trs_application_ID Financial Application ID	  */
	public void settrs_application_ID (int trs_application_ID)
	{
		if (trs_application_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_trs_application_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_trs_application_ID, Integer.valueOf(trs_application_ID));
	}

	/** Get Financial Application ID.
		@return Financial Application ID	  */
	public int gettrs_application_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_trs_application_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}