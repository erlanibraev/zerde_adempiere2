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

/** Generated Model for cms_proposal
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_cms_proposal extends PO implements I_cms_proposal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20130606L;

    /** the default Constructor */
    public X_cms_proposal(Properties ctx)
    { 
      super (ctx, null, null);
    } 

    /** Standard Constructor */
    public X_cms_proposal (Properties ctx, int cms_proposal_ID, String trxName)
    {
      super (ctx, cms_proposal_ID, trxName);
      /** if (cms_proposal_ID == 0)
        {
			setcms_contractstype_ID (0);
			setcms_proposal_ID (0);
			setDescription (null);
			setHR_Department_ID (0);
// @SQL=select h.hr_department_id from hr_employee e 
				inner join hr_job j on e.hr_job_id = j.hr_job_id
				inner join hr_department h on e.hr_department_id = h.hr_department_id
				inner join c_bpartner b on e.c_bpartner_id = b.c_bpartner_id
				inner join ad_user u on u.c_bpartner_id = b.c_bpartner_id
where e.isactive = 'Y' and ad_user_id = @AD_User_ID@
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_cms_proposal (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_cms_proposal[")
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

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set Usuario.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get Usuario.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
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

	/** Set BeginningDateExecution.
		@param BeginningDateExecution 
		BeginningDateExecution
	  */
	public void setBeginningDateExecution (Timestamp BeginningDateExecution)
	{
		set_Value (COLUMNNAME_BeginningDateExecution, BeginningDateExecution);
	}

	/** Get BeginningDateExecution.
		@return BeginningDateExecution
	  */
	public Timestamp getBeginningDateExecution () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BeginningDateExecution);
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

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contract Number.
		@param CMS_Contract_ContractNum Contract Number	  */
	public void setCMS_Contract_ContractNum (String CMS_Contract_ContractNum)
	{
		set_Value (COLUMNNAME_CMS_Contract_ContractNum, CMS_Contract_ContractNum);
	}

	/** Get Contract Number.
		@return Contract Number	  */
	public String getCMS_Contract_ContractNum () 
	{
		return (String)get_Value(COLUMNNAME_CMS_Contract_ContractNum);
	}

	/** Set Contract Number BP.
		@param CMS_Contract_ContractNumBP Contract Number BP	  */
	public void setCMS_Contract_ContractNumBP (String CMS_Contract_ContractNumBP)
	{
		set_Value (COLUMNNAME_CMS_Contract_ContractNumBP, CMS_Contract_ContractNumBP);
	}

	/** Get Contract Number BP.
		@return Contract Number BP	  */
	public String getCMS_Contract_ContractNumBP () 
	{
		return (String)get_Value(COLUMNNAME_CMS_Contract_ContractNumBP);
	}

	/** Set Contract Creation Date.
		@param CMS_Contract_CreatDate Contract Creation Date	  */
	public void setCMS_Contract_CreatDate (Timestamp CMS_Contract_CreatDate)
	{
		set_Value (COLUMNNAME_CMS_Contract_CreatDate, CMS_Contract_CreatDate);
	}

	/** Get Contract Creation Date.
		@return Contract Creation Date	  */
	public Timestamp getCMS_Contract_CreatDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CMS_Contract_CreatDate);
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
			set_ValueNoCheck (COLUMNNAME_CMS_Contract_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CMS_Contract_ID, Integer.valueOf(CMS_Contract_ID));
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

	public I_cms_contractstype getcms_contractstype() throws RuntimeException
    {
		return (I_cms_contractstype)MTable.get(getCtx(), I_cms_contractstype.Table_Name)
			.getPO(getcms_contractstype_ID(), get_TrxName());	}

	/** Set cms_contractstype.
		@param cms_contractstype_ID cms_contractstype	  */
	public void setcms_contractstype_ID (int cms_contractstype_ID)
	{
		if (cms_contractstype_ID < 1) 
			set_Value (COLUMNNAME_cms_contractstype_ID, null);
		else 
			set_Value (COLUMNNAME_cms_contractstype_ID, Integer.valueOf(cms_contractstype_ID));
	}

	/** Get cms_contractstype.
		@return cms_contractstype	  */
	public int getcms_contractstype_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_cms_contractstype_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_cms_incotermstype getcms_incotermstype() throws RuntimeException
    {
		return (I_cms_incotermstype)MTable.get(getCtx(), I_cms_incotermstype.Table_Name)
			.getPO(getcms_incotermstype_ID(), get_TrxName());	}

	/** Set cms_incotermstype.
		@param cms_incotermstype_ID cms_incotermstype	  */
	public void setcms_incotermstype_ID (int cms_incotermstype_ID)
	{
		if (cms_incotermstype_ID < 1) 
			set_Value (COLUMNNAME_cms_incotermstype_ID, null);
		else 
			set_Value (COLUMNNAME_cms_incotermstype_ID, Integer.valueOf(cms_incotermstype_ID));
	}

	/** Get cms_incotermstype.
		@return cms_incotermstype	  */
	public int getcms_incotermstype_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_cms_incotermstype_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_cms_paymentstype getcms_paymentstype() throws RuntimeException
    {
		return (I_cms_paymentstype)MTable.get(getCtx(), I_cms_paymentstype.Table_Name)
			.getPO(getcms_paymentstype_ID(), get_TrxName());	}

	/** Set cms_paymentstype.
		@param cms_paymentstype_ID cms_paymentstype	  */
	public void setcms_paymentstype_ID (int cms_paymentstype_ID)
	{
		if (cms_paymentstype_ID < 1) 
			set_Value (COLUMNNAME_cms_paymentstype_ID, null);
		else 
			set_Value (COLUMNNAME_cms_paymentstype_ID, Integer.valueOf(cms_paymentstype_ID));
	}

	/** Get cms_paymentstype.
		@return cms_paymentstype	  */
	public int getcms_paymentstype_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_cms_paymentstype_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set cms_proposal.
		@param cms_proposal_ID cms_proposal	  */
	public void setcms_proposal_ID (int cms_proposal_ID)
	{
		if (cms_proposal_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_cms_proposal_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_cms_proposal_ID, Integer.valueOf(cms_proposal_ID));
	}

	/** Get cms_proposal.
		@return cms_proposal	  */
	public int getcms_proposal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_cms_proposal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set comment.
		@param comment 
		Примечание
	  */
	public void setcomment (String comment)
	{
		set_Value (COLUMNNAME_comment, comment);
	}

	/** Get comment.
		@return Примечание
	  */
	public String getcomment () 
	{
		return (String)get_Value(COLUMNNAME_comment);
	}

	/** Set Contragent.
		@param Contragent 
		Contragent
	  */
	public void setContragent (String Contragent)
	{
		set_Value (COLUMNNAME_Contragent, Contragent);
	}

	/** Get Contragent.
		@return Contragent
	  */
	public String getContragent () 
	{
		return (String)get_Value(COLUMNNAME_Contragent);
	}

	/** Set contragent_contacts.
		@param contragent_contacts 
		contragent_contacts
	  */
	public void setcontragent_contacts (String contragent_contacts)
	{
		set_Value (COLUMNNAME_contragent_contacts, contragent_contacts);
	}

	/** Get contragent_contacts.
		@return contragent_contacts
	  */
	public String getcontragent_contacts () 
	{
		return (String)get_Value(COLUMNNAME_contragent_contacts);
	}

	public org.compiere.model.I_AD_User getCreator() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getCreator_ID(), get_TrxName());	}

	/** Set Creator_ID.
		@param Creator_ID 
		Создатель заявки
	  */
	public void setCreator_ID (int Creator_ID)
	{
		if (Creator_ID < 1) 
			set_Value (COLUMNNAME_Creator_ID, null);
		else 
			set_Value (COLUMNNAME_Creator_ID, Integer.valueOf(Creator_ID));
	}

	/** Get Creator_ID.
		@return Создатель заявки
	  */
	public int getCreator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Creator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set datefiling.
		@param datefiling 
		Дата подачи заявки
	  */
	public void setdatefiling (Timestamp datefiling)
	{
		set_Value (COLUMNNAME_datefiling, datefiling);
	}

	/** Get datefiling.
		@return Дата подачи заявки
	  */
	public Timestamp getdatefiling () 
	{
		return (Timestamp)get_Value(COLUMNNAME_datefiling);
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

	/** DocStatus AD_Reference_ID=1000149 */
	public static final int DOCSTATUS_AD_Reference_ID=1000149;
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** On approval = OA */
	public static final String DOCSTATUS_OnApproval = "OA";
	/** Rejected = 10000005 */
	public static final String DOCSTATUS_Rejected = "10000005";
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

	/** Set EndDateExecution.
		@param EndDateExecution 
		EndDateExecution
	  */
	public void setEndDateExecution (Timestamp EndDateExecution)
	{
		set_Value (COLUMNNAME_EndDateExecution, EndDateExecution);
	}

	/** Get EndDateExecution.
		@return EndDateExecution
	  */
	public Timestamp getEndDateExecution () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDateExecution);
	}

	public org.eevolution.model.I_HR_Department getHR_Block() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Block_ID(), get_TrxName());	}

	/** Set HR Block.
		@param HR_Block_ID HR Block	  */
	public void setHR_Block_ID (int HR_Block_ID)
	{
		if (HR_Block_ID < 1) 
			set_Value (COLUMNNAME_HR_Block_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Block_ID, Integer.valueOf(HR_Block_ID));
	}

	/** Get HR Block.
		@return HR Block	  */
	public int getHR_Block_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Block_ID);
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
		@param HR_Header_ID HR_Header_ID	  */
	public void setHR_Header_ID (int HR_Header_ID)
	{
		if (HR_Header_ID < 1) 
			set_Value (COLUMNNAME_HR_Header_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Header_ID, Integer.valueOf(HR_Header_ID));
	}

	/** Get HR_Header_ID.
		@return HR_Header_ID	  */
	public int getHR_Header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set paymentarrangements.
		@param paymentarrangements 
		Порядок расчетов
	  */
	public void setpaymentarrangements (String paymentarrangements)
	{
		set_Value (COLUMNNAME_paymentarrangements, paymentarrangements);
	}

	/** Get paymentarrangements.
		@return Порядок расчетов
	  */
	public String getpaymentarrangements () 
	{
		return (String)get_Value(COLUMNNAME_paymentarrangements);
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

	/** Set quantityqualitygoods.
		@param quantityqualitygoods 
		Количество и качество товаров и услуг
	  */
	public void setquantityqualitygoods (String quantityqualitygoods)
	{
		set_Value (COLUMNNAME_quantityqualitygoods, quantityqualitygoods);
	}

	/** Get quantityqualitygoods.
		@return Количество и качество товаров и услуг
	  */
	public String getquantityqualitygoods () 
	{
		return (String)get_Value(COLUMNNAME_quantityqualitygoods);
	}

	/** Set SpecialConditions.
		@param SpecialConditions 
		Особые условия договора
	  */
	public void setSpecialConditions (String SpecialConditions)
	{
		set_Value (COLUMNNAME_SpecialConditions, SpecialConditions);
	}

	/** Get SpecialConditions.
		@return Особые условия договора
	  */
	public String getSpecialConditions () 
	{
		return (String)get_Value(COLUMNNAME_SpecialConditions);
	}

	/** Set Summary.
		@param Summary 
		Textual summary of this request
	  */
	public void setSummary (BigDecimal Summary)
	{
		set_Value (COLUMNNAME_Summary, Summary);
	}

	/** Get Summary.
		@return Textual summary of this request
	  */
	public BigDecimal getSummary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Summary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Version No.
		@param VersionNo 
		Version Number
	  */
	public void setVersionNo (String VersionNo)
	{
		set_Value (COLUMNNAME_VersionNo, VersionNo);
	}

	/** Get Version No.
		@return Version Number
	  */
	public String getVersionNo () 
	{
		return (String)get_Value(COLUMNNAME_VersionNo);
	}
}