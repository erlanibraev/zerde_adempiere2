/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.naming.factory.SendMailFactory;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.apps.ADialog;
import org.compiere.process.DocAction;
import org.compiere.process.BSCDocumentEngine;

import extend.org.compiere.utils.Util;


/**
 * @author Y.Ibrayev
 *
 */
public class MBSCCard extends X_BSC_Card implements DocAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3366362516386767291L;
	
	/*
	 * Константы ролей для работы с картами ССП
	 * --------------------------------------------------------------
	 */
	private static int AD_Role_ID_Ввод_Карт_ССП = 1000035;
	private static int AD_Role_ID_Редактирование_Карт_ССП = 1000036;
	private static int AD_Role_ID_Директор_ДКУР = 1000039;
	private static int AD_Role_ID_Директор_ДСПК = 1000037;
	private static int AD_Role_ID_Менеджер_ДКУР = 1000040;
	private static int AD_Role_ID_Менеджер_ДСПК = 1000038;
	private static int[] allRoles = {AD_Role_ID_Редактирование_Карт_ССП
		                            ,AD_Role_ID_Директор_ДКУР
		                            ,AD_Role_ID_Директор_ДСПК
		                            ,AD_Role_ID_Менеджер_ДКУР
		                            ,AD_Role_ID_Менеджер_ДСПК};
	private static int[] ДКУРRoles = {AD_Role_ID_Директор_ДКУР
        							 ,AD_Role_ID_Менеджер_ДКУР};
	private static int[] ДСПКRoles = {AD_Role_ID_Директор_ДСПК
		 							 ,AD_Role_ID_Менеджер_ДСПК};
	//---------------------------------------------------------------
	
	MPeriod period = null;

	private ArrayList<MBSCCardLine> cardLine = new ArrayList<MBSCCardLine>(); 
	private MParameter parameter = null; 
	
	public MParameter getParameter() {
		if (parameter == null && getBSC_Parameter_ID() > 0) {
			parameter = new MParameter(Env.getCtx(),getBSC_Parameter_ID(),get_TrxName());
			parameter.setPeriod(getPeriod());
		}
		return parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
		if (parameter != null && parameter.getBSC_Parameter_ID() != getBSC_Parameter_ID()) {
			setBSC_Parameter_ID(parameter.getBSC_Parameter_ID());
		}
	}

	public ArrayList<MBSCCardLine> getCardLine() {
		cardLine.clear();
		String sql = "SELECT * FROM BSC_CardLine WHERE BSC_Card_ID = ?  AND isActive = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, getBSC_Card_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MBSCCardLine line = new MBSCCardLine(Env.getCtx(), rs.getInt(X_BSC_CardLine.COLUMNNAME_BSC_CardLine_ID), null); 
				cardLine.add(line);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return cardLine;
	}

	public MPeriod getPeriod() {
		if (period == null || period.getC_Period_ID() != getC_Period_ID()) {
			setPeriod(new MPeriod(Env.getCtx(),getC_Period_ID(),get_TrxName()));
		}
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

	/**
	 * @param ctx
	 * @param BSC_Card_ID
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, int BSC_Card_ID, String trxName) {
		super(ctx, BSC_Card_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCCard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 */
	public MBSCCard(Properties ctx) {
		super(ctx);
	}
	
	public BigDecimal calculate() {
		BigDecimal result = new BigDecimal(0);
		BigDecimal weightSum = new BigDecimal(0);
		for(MBSCCardLine line:getCardLine()) {
			BigDecimal calculate  = line.calculate();
			BigDecimal weight = line.getWeight();
			weightSum = (weight != null ? weightSum.add(weight): weightSum);
			calculate = (weight != null ? calculate.multiply(weight): calculate);
			result = (calculate != null ? result.add(calculate) : result);
		}
		if (getBSC_Parameter_ID() <= 0 ) {
			createParameter();
		} 
		setValueNumber(result);
		save();
		if (weightSum.doubleValue() > 100.00) {
			String msg = "Сумма весов больше 100 (%,6.2f)";
			ADialog.info(25, null,  String.format(msg, weightSum.doubleValue()));
		}
		return result;
	}

	/**
	 * 
	 */
	private MParameter createParameter() {
		MParameter param = getParameter();
		if (param == null) { 
			param = MParameter.createParameter(getName(), getDescription(), getC_BPartner_ID(), getC_Period_ID());
			param.setPeriod(getPeriod());
			setParameter(param);
		}
		return param;
	}

	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	//private boolean		m_justPrepared = false;
	
	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#processIt(java.lang.String)
	 */
	@Override
	public boolean processIt(String action) throws Exception {
		log.severe("BSC_Card: processIt('" +action+"')");
		m_processMsg = null;
		BSCDocumentEngine engine = new BSCDocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#unlockIt()
	 */
	@Override
	public boolean unlockIt() {
		log.severe("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#invalidateIt()
	 */
	@Override
	public boolean invalidateIt() {
		log.severe("invalidateIt - " + toString());
		setDocAction(DocAction.ACTION_Prepare);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#prepareIt()
	 */
	@Override
	public String prepareIt() {
		log.severe("BSC_Card: prepareIt()");
		String result = (STATUS_WaitingConfirmation.equals(getDocStatus())  ? STATUS_WaitingConfirmation : DocAction.STATUS_Invalid);
		if (STATUS_NotApproved.equals(getDocStatus()) || STATUS_Drafted.equals(getDocStatus())) {
			setDocStatus(STATUS_WaitingConfirmation);
			result = STATUS_WaitingConfirmation;
			sendMailPrepare();
		}
		return result;
	}

	/**
	 * 
	 */
	private void sendMailPrepare() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userToID = getUserID(getC_BPartner_ID());
		int userFromID = getCreatedBy();
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")");
		message.append("Уважаемый(ая) ")
		       .append(getC_BPartner().getName())
		       .append(".\n")
		       .append("Необходимо начать ввод данных для ССП за ")
		       .append(getC_Period().getName());
		try {
			if (userToID > 0 && userFromID > 0)
				Util.sendMail(userToID, userFromID, label.toString(), message.toString(), false);
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/**
	 * @param c_BPartner_ID
	 * @return
	 */
	private int getUserID(int c_BPartner_ID) {
		int result = 0;
		MUser[] user = MUser.getOfBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		if(user[0].getAD_User_ID() != 0 || user[0].getEMail() != null || user[0].getEMail().length() != 0)
			result = user[0].getAD_User_ID();
		return result;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#approveIt()
	 */
	@Override
	public boolean approveIt() {
		log.severe("BSC_Card: approveIt()");
		setDocStatus(STATUS_Approved);
		SendMailApprove();
		return true;
	}

	/**
	 * 
	 */
	private void SendMailApprove() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userFromID = Env.getAD_User_ID(Env.getCtx());; 
		HashSet<Integer> userToID = new HashSet<Integer>();
		userToID.add(new Integer(getCreatedBy()));
		userToID.add(getUserID(getC_BPartner_ID()));
		
		int[] roles = (isDSPKCard() ? ДСПКRoles : ДКУРRoles);
		
		for(Integer item:getUserAtRole(roles)) {
			userToID.add(item);
		}
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")")
		     ;
		message.append("Данные по ССП за ")
		       .append(getC_Period().getName())
		       .append(" утверждены.\n\n")
		       ;
		try {
			for(Integer item:userToID) {
				if(item.intValue() > 0 && userFromID > 0)
					Util.sendMail(item.intValue(), userFromID, label.toString(), message.toString(), false);
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#rejectIt()
	 */
	@Override
	public boolean rejectIt() {
		log.severe("BSC_Card: rejectIt()");
		SendMailReject();
		setDocStatus(STATUS_Drafted);
		return true;
	}

	/**
	 * 
	 */
	private void SendMailReject() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userFromID = Env.getAD_User_ID(Env.getCtx());; 
		HashSet<Integer> userToID = new HashSet<Integer>();
		userToID.add(new Integer(getCreatedBy()));
		userToID.add(getUserID(getC_BPartner_ID()));
		int[] roles = (isDSPKCard() ? ДСПКRoles : ДКУРRoles);
		for(Integer item:getUserAtRole(roles)) {
			userToID.add(item);
		}
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")");
		message.append("Данные по ССП за ")
		       .append(getC_Period().getName())
		       .append(" отклонены.\n\n")
		       .append(getDescription());
		try {
			for(Integer item:userToID) {
				Util.sendMail(item.intValue(), userFromID, label.toString(), message.toString(), false);
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#completeIt()
	 */
	@Override
	public String completeIt() {
		log.severe("BSC_Card: completeIt()");
		String result = (STATUS_InProgress.equals(getDocStatus()) ? STATUS_InProgress : DocAction.STATUS_Invalid);
		if (STATUS_WaitingConfirmation.equals(getDocStatus())) {
			setDocStatus(STATUS_InProgress);
			result = STATUS_InProgress;
			SendMailComplete();
		}
		return result;
	}

	/**
	 * 
	 */
	private void SendMailComplete() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userFromID = getUserID(getC_BPartner_ID());
		HashSet<Integer> userToID = new HashSet<Integer>();
		userToID.add(new Integer(getCreatedBy()));
		int[] roles = (isDSPKCard() ? ДСПКRoles : ДКУРRoles);
		for(Integer item:getUserAtRole(roles)) {
			userToID.add(item);
		}
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")");
		message.append("Ввод данных по ССП за ")
		       .append(getC_Period().getName())
		       .append(" завершен.");
		try {
			for(Integer item:userToID) {
				Util.sendMail(item.intValue(), userFromID, label.toString(), message.toString(), false);
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/**
	 * @return
	 */
	private List<Integer> getUserAtRole(int[] roles) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String roles_id = "0";
		for(int AD_Role_ID:roles) {
			roles_id += ","+AD_Role_ID;
		}
		String sql = "SELECT AD_User_ID FROM AD_User_Roles WHERE AD_Role_ID IN ("+roles_id+")";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int item = rs.getInt(1); 
				result.add(new Integer(item));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#voidIt()
	 */
	@Override
	public boolean voidIt() {
		log.severe("BSC_Card: voidIt()");
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;

		if (STATUS_Closed.equals(getDocStatus())
			|| STATUS_Reversed.equals(getDocStatus())
			|| STATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DocAction.ACTION_None);
			return false;
		}

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DocAction.ACTION_None);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#closeIt()
	 */
	@Override
	public boolean closeIt() {
		log.severe("BSC_Card: closeIt()");
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DocAction.ACTION_None);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		setDocStatus(STATUS_Closed);
		SendMailClose();
		return true;
	}

	/**
	 * 
	 */
	private void SendMailClose() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userFromID = getUserID(getC_BPartner_ID());
		HashSet<Integer> userToID = new HashSet<Integer>();
		userToID.add(new Integer(getCreatedBy()));
		int[] roles = (isDSPKCard() ? ДСПКRoles : ДКУРRoles);
		
		for(Integer item:getUserAtRole(roles)) {
			userToID.add(item);
		}
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")");
		message.append("Ввод данных по ССП за ")
		       .append(getC_Period().getName())
		       .append(" не доступен (закрыт).");
		try {
			for(Integer item:userToID) {
				Util.sendMail(item.intValue(), userFromID, label.toString(), message.toString(), false);
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reverseCorrectIt()
	 */
	@Override
	public boolean reverseCorrectIt() {
		log.severe("BSC_Card: reverseCorrectIt()");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reverseAccrualIt()
	 */
	@Override
	public boolean reverseAccrualIt() {
		log.severe("BSC_Card: reverseAccrualIt()");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#reActivateIt()
	 */
	@Override
	public boolean reActivateIt() {
		log.severe("BSC_Card: reActivateIt()");
		setDocStatus(STATUS_Drafted);
		setProcessed(false);
		setDocAction(DocAction.ACTION_None);
		SendMailReactivate();
		return true;
	}

	/**
	 * 
	 */
	private void SendMailReactivate() {
		StringBuffer message = new StringBuffer();
		StringBuffer label = new StringBuffer();
		int userFromID = getUserID(getC_BPartner_ID());
		HashSet<Integer> userToID = new HashSet<Integer>();
		userToID.add(new Integer(getCreatedBy()));
		int[] roles = (isDSPKCard() ? ДСПКRoles : ДКУРRoles);
		
		for(Integer item:getUserAtRole(roles)) {
			userToID.add(item);
		}
		label.append("Ввод данных карты ССП за ")
			 .append(getC_Period().getName())
		     .append("(")
		     .append(getC_BPartner().getName())
		     .append(")");
		message.append("Ввод данных по ССП за ")
		       .append(getC_Period().getName())
		       .append(" открыт снова.");
		try {
			for(Integer item:userToID) {
				Util.sendMail(item.intValue(), userFromID, label.toString(), message.toString(), false);
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"MBSCCard.sendMailPrepare: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getSummary()
	 */
	@Override
	public String getSummary() {
		log.severe("BSC_Card: getSummary()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getDocumentInfo()
	 */
	@Override
	public String getDocumentInfo() {
		log.severe("BSC_Card: getDocumentInfo()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#createPDF()
	 */
	@Override
	public File createPDF() {
		log.severe("BSC_Card: createPDF()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getProcessMsg()
	 */
	@Override
	public String getProcessMsg() {
		log.severe("BSC_Card: getProcessMsg()");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getDoc_User_ID()
	 */
	@Override
	public int getDoc_User_ID() {
		log.severe("BSC_Card: getDoc_User_ID()");
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getC_Currency_ID()
	 */
	@Override
	public int getC_Currency_ID() {
		log.severe("BSC_Card: getC_Currency_ID()");
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.DocAction#getApprovalAmt()
	 */
	@Override
	public BigDecimal getApprovalAmt() {
		log.severe("BSC_Card: getApprovalAmt()");
		return null;
	}
	
	public MBSCCardLine[] getLines(boolean requery) {
		ArrayList<MBSCCardLine> cardLine = getCardLine(); 
		MBSCCardLine[] result = ( cardLine != null ? cardLine.toArray( new MBSCCardLine[cardLine.size()]) :null);
		return result;
	}
	
	@Override
	public Timestamp getDateAcct() {
		Timestamp result = super.getDateAcct();
		if (result == null) {
			result = new Timestamp(System.currentTimeMillis());
			setDateAcct(result);
		}
		return result;
	}
	
	@Override
	public void setValueNumber (BigDecimal ValueNumber)	{
		super.setValueNumber(ValueNumber);
		getParameter().setPeriod(getPeriod());
		getParameter().setValueNumber(ValueNumber);
		getParameter().save();
	}

	
	protected String getPosition() {
		String result = "";
		if(getC_BPartner_ID() > 0) {
			String sql = "select name from HR_Job where HR_Job_ID in (select HR_Job_ID from HR_Employee where C_BPartner_ID = ? and IsActive = 'Y')";
			PreparedStatement pstmt = null;
			ResultSet rs = null;		
			try {
				pstmt = DB.prepareStatement(sql,null);
				pstmt.setInt(1, getC_BPartner_ID());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString(1);
				}
				result = (result == null ? "" : result);
			} catch (SQLException e) {
				log.log(Level.SEVERE, "product", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}	
		}
		return result;
	}
	
	protected String getCardName() {
		String result = getName();
		if (getName() == null || getName().trim().equals("")) {
			result = "Карта ССП на ";
			if (getC_Period_ID() > 0) {
				result += getC_Period().getName() + " для ";
				if(getC_BPartner_ID() > 0) {
					MBPartner bp = new MBPartner(Env.getCtx(),getC_BPartner_ID(),get_TrxName());
					result += "" + getPosition();
					result += " (" + bp.getName()+ ")";
				} else {
					result = null;
				}
			} else {
				result = null;
			}
		}
		return result;
	}
	
	@Override 
	protected boolean beforeSave(boolean newRecord) {
		boolean b = getCardName() != null;
		if (b) {
			setName(getCardName());
		}
		boolean b1 = createParameter() != null;
		if(getC_Period_ID() > 0) {
			setDateFrom(getC_Period().getStartDate());
			setDateTo(getC_Period().getEndDate());
		}
		if(getDateAcct() == null) {
			setDateAcct(getC_Period().getEndDate());
		}
		return super.beforeSave(newRecord) && b && b1;
	}
	
	public void copyCard(MPeriod period) {
		if(period != null && period.getC_Period_ID() > 0) {
			int new_BSC_Card_ID = createNewCard(period.getC_Period_ID());
			if(new_BSC_Card_ID > 0) {
				copyLines(new_BSC_Card_ID, period.getC_Period_ID());
			}
		}
	}
	
	private int createNewCard(int C_Period_ID) {
		int result = 0;
		MBSCCard newCard = new MBSCCard(Env.getCtx(),0,get_TrxName());
		copyValues(this, newCard);
		newCard.setC_Period_ID(C_Period_ID);
		newCard.set_ValueNoCheck (MBSCCard.COLUMNNAME_BSC_Card_ID, I_ZERO);	// new
		
		newCard.getParameter().createNewLine(getC_Period_ID(),C_Period_ID);
		
		if (newCard.save()) {
			result = newCard.getBSC_Card_ID();
		}
		return result;
	}
	
	private void copyLines(int BSC_Card_ID, int C_Period_ID) {
		for(MBSCCardLine cardLine:getCardLine()) {
			cardLine.copyCardLine(BSC_Card_ID, C_Period_ID);
		}
	}

	/**
	 * @param c_Period_ID
	 */
	public void closePeriod(int c_Period_ID) {
		if (c_Period_ID == getC_Period_ID()) {
			setDocStatus(DOCSTATUS_Закрыт);
			setDocAction(DOCACTION_НЕТ);
			setPosted(true);
			save();
		}
	}
	
//	private static String mainBoss = "Председатель правления";
	public boolean isMainCard() {
		boolean result = false;
		String sql = "SELECT C_BPartner_ID FROM BSC_Dashboard WHERE AD_Cleint_ID = "+Env.getAD_Client_ID(getCtx())+" AND IsActive = 'Y' AND NumberOfRuns IN (SELECT MIN(NumberOfRuns) FROM BSC_Dashboard WHERE IsActive = 'Y')"; 
		int C_BPartner_ID = DB.getSQLValue(get_TrxName(), sql);
		result = getC_BPartner_ID() == C_BPartner_ID;
		return result;
	}
	
	public boolean isDSPKCard() {
		boolean result = false;
		String sql = "SELECT C_BPartner_ID FROM BSC_Dashboard WHERE AD_Cleint_ID = "+Env.getAD_Client_ID(getCtx())+" AND IsActive = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = result || (rs.getInt(1) == getC_BPartner_ID());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "product", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		return result;
	}
}
