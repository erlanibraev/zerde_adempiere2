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
	private static final long serialVersionUID = 20130524L;

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
			setAD_Table_ID (0);
// @SQL=Select AD_Table_ID From AD_Table where tablename='TRM_Application'
			setApplAmount (Env.ZERO);
			setCMS_Contract_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setInitiator_ID (0);
// @AD_User_ID@
			setTRM_Application_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TRM_Application (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TRM_Application[")
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

	public I_AGR_Dispatcher getAGR_Dispatcher() throws RuntimeException
    {
		return (I_AGR_Dispatcher)MTable.get(getCtx(), I_AGR_Dispatcher.Table_Name)
			.getPO(getAGR_Dispatcher_ID(), get_TrxName());	}

	/** Set AGR_Dispatcher ID.
		@param AGR_Dispatcher_ID AGR_Dispatcher ID	  */
	public void setAGR_Dispatcher_ID (int AGR_Dispatcher_ID)
	{
		if (AGR_Dispatcher_ID < 1) 
			set_Value (COLUMNNAME_AGR_Dispatcher_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Dispatcher_ID, Integer.valueOf(AGR_Dispatcher_ID));
	}

	/** Get AGR_Dispatcher ID.
		@return AGR_Dispatcher ID	  */
	public int getAGR_Dispatcher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Dispatcher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AGR_Stage getAGR_Stage() throws RuntimeException
    {
		return (I_AGR_Stage)MTable.get(getCtx(), I_AGR_Stage.Table_Name)
			.getPO(getAGR_Stage_ID(), get_TrxName());	}

	/** Set AGR_Stage ID.
		@param AGR_Stage_ID AGR_Stage ID	  */
	public void setAGR_Stage_ID (int AGR_Stage_ID)
	{
		if (AGR_Stage_ID < 1) 
			set_Value (COLUMNNAME_AGR_Stage_ID, null);
		else 
			set_Value (COLUMNNAME_AGR_Stage_ID, Integer.valueOf(AGR_Stage_ID));
	}

	/** Get AGR_Stage ID.
		@return AGR_Stage ID	  */
	public int getAGR_Stage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AGR_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approve.
		@param AGRApprove 
		Одобрить
	  */
	public void setAGRApprove (String AGRApprove)
	{
		set_Value (COLUMNNAME_AGRApprove, AGRApprove);
	}

	/** Get Approve.
		@return Одобрить
	  */
	public String getAGRApprove () 
	{
		return (String)get_Value(COLUMNNAME_AGRApprove);
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

	/** DocStatus AD_Reference_ID=1000141 */
	public static final int DOCSTATUS_AD_Reference_ID=1000141;
	/** On approval = AP */
	public static final String DOCSTATUS_OnApproval = "AP";
	/** Refused = RE */
	public static final String DOCSTATUS_Refused = "RE";
	/** Approved = AD */
	public static final String DOCSTATUS_Approved = "AD";
	/** To pay = TP */
	public static final String DOCSTATUS_ToPay = "TP";
	/** Clear = CL */
	public static final String DOCSTATUS_Clear = "CL";
	/** Paid = PA */
	public static final String DOCSTATUS_Paid = "PA";
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
		@param TRM_Application_ID 
		Финансовая заявка
	  */
	public void setTRM_Application_ID (int TRM_Application_ID)
	{
		if (TRM_Application_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TRM_Application_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TRM_Application_ID, Integer.valueOf(TRM_Application_ID));
	}

	/** Get Financial Application ID.
		@return Финансовая заявка
	  */
	public int getTRM_Application_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TRM_Application_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}