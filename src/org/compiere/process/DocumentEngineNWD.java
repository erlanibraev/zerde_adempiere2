/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBSCNetWorkDiag;
import org.compiere.model.MUser;
import org.compiere.model.X_BSC_NetWorkDiag;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author A.Kulantayev
 *
 */
public class DocumentEngineNWD extends DocumentEngine {

	/**
	 * @param po
	 */
	private int m_BSCNetWorkDiag_ID = 0;
	protected CLogger log = CLogger.getCLogger (getClass());
	/**
	 * @param po
	 * @param docStatus
	 */
	public DocumentEngineNWD(DocAction po, String docStatus, int p_BSCNetWorkDiag_ID) {
		super(po, docStatus);
		m_BSCNetWorkDiag_ID = p_BSCNetWorkDiag_ID;
	}
	
	@Override
	public boolean processIt (String processAction, String docAction) {
		boolean result = true;
		int p_AD_User_ID = 0;
		String emailtempTitle = "Информация о согласований (Сетевого графика)";
		if (processAction != null) {
			//TODO Что делать с процессами
		} 
		
		if(ACTION_Prepare.equals(docAction)){
			result = STATUS_WaitingConfirmation.equals(prepareIt());
			if(result) {
				try {
					p_AD_User_ID = new X_BSC_NetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_ValueAsInt("Director_ID");
					if(p_AD_User_ID > 0) 
						extend.org.compiere.utils.Util.sendMail(p_AD_User_ID, Env.getAD_Client_ID(getCtx()), emailtempTitle
						, "Уважаемый(ая), "+getRecipientName(p_AD_User_ID)+ ", Вам надо согласовать документ №"	
						+ new MBSCNetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_Value("DocumentNo"), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		else if(ACTION_Approve.equals(docAction)){
			result = approveIt();
			if(result){
				try{
					p_AD_User_ID = new X_BSC_NetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_ValueAsInt("Resposible_Manager_ID");
					if(p_AD_User_ID > 0) 
						extend.org.compiere.utils.Util.sendMail(p_AD_User_ID, Env.getAD_Client_ID(getCtx()), emailtempTitle, "Уважаемый(ая), "
							+ getRecipientName(p_AD_User_ID)+", документ №"
							+ new MBSCNetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_Value("DocumentNo")+" одобрен.", false);
				}catch(Exception e){
					e.printStackTrace();
				}
				//---Рассылка для исполнителей
				try {
					sendMailAction();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//---
			}
		} 
		
		else if (ACTION_Reject.equals(docAction)) { 
			result = rejectIt();
			if(result){
				try{
					p_AD_User_ID = new X_BSC_NetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_ValueAsInt("Resposible_Manager_ID");
					if(p_AD_User_ID > 0) 
						extend.org.compiere.utils.Util.sendMail(p_AD_User_ID, Env.getAD_Client_ID(getCtx()), emailtempTitle, "Уважаемый(ая), "
							+ getRecipientName(p_AD_User_ID)+", документ №"
							+ new MBSCNetWorkDiag(getCtx(), m_BSCNetWorkDiag_ID, get_TrxName()).get_Value("DocumentNo")+" отклонен.", false);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		else if (ACTION_Complete.equals(docAction)) { 
			result = STATUS_Completed.equals(completeIt());} 
		
		else if (ACTION_Close.equals(docAction)){
			result = closeIt();} 
		
		else if (ACTION_ReActivate.equals(docAction)){
			result = reActivateIt();} 
		else { result = false;}
		
		return result;
	}
	
	/**************************************************************************
	 * 	Get Action Options based on current Status
	 *	@return array of actions
	 */
	@Override
	public String[] getActionOptions() {
		return new String[] {ACTION_Approve, ACTION_Close, ACTION_Complete, ACTION_Invalidate, ACTION_None, ACTION_Post, ACTION_Prepare,
				ACTION_ReActivate, ACTION_Reject, ACTION_Reverse_Accrual, ACTION_Reverse_Correct, ACTION_Unlock, ACTION_Void, ACTION_WaitComplete};
	}
	
	private boolean sendMailAction() throws Exception{
	    
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT AD_User_ID "+ 
					" FROM bsc_responsible_executor \n"+
					" WHERE AD_User_ID IS NOT NULL AND bsc_action_id IN (SELECT bsc_action_id FROM bsc_action WHERE bsc_networkdiagsubline_id IN \n"+ 
					" (SELECT bsc_networkdiagsubline_id FROM bsc_networkdiagsubline WHERE bsc_networkdiagline_id IN \n"+
			        " (SELECT bsc_networkdiagline_id FROM bsc_networkdiagline WHERE bsc_networkdiag_id ="+ m_BSCNetWorkDiag_ID+"))) \n GROUP BY AD_User_ID";
		
		ArrayList<Integer> sendList = new ArrayList<Integer>();
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				sendList.add(rs.getInt(1));
			}
		}
		catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		 if(sendList.size()==0)
			 throw new Exception("Unable to load list of Executors");
			
		String sqlGetZerdeAdmin = " SELECT MIN(AD_User_ID) FROM ad_user WHERE LOWER(name) LIKE '%admin%' \n" +
								  " AND IsActive = 'Y' AND AD_Client_ID = "+Env.getAD_Client_ID(getCtx());
		int p_AD_User_ID = DB.getSQLValue(get_TrxName(), sqlGetZerdeAdmin);		
		if(p_AD_User_ID < 0)
			throw new Exception("User_ID not found");
		for(int i = 0; i < sendList.size(); i++){
			extend.org.compiere.utils.Util.sendMail(sendList.get(i).intValue(), p_AD_User_ID, "Мероприятия", "Список мероприятии для "
					+getRecipientName(sendList.get(i).intValue()), false);
		}
		return true;
	}
	private String getRecipientName(int r_AD_User_ID){
		
		String recipientName = null;
		MUser recipientUser = new MUser(getCtx(), r_AD_User_ID, get_TrxName());
		if(recipientUser.getC_BPartner_ID()!=0)
			recipientName = new MBPartner(getCtx(), (new MUser(getCtx(), r_AD_User_ID, get_TrxName()).getC_BPartner_ID()), get_TrxName()).getName();
		else if (recipientUser.getDescription().length()!=0)
				recipientName = recipientUser.getDescription();
		else
				recipientName = recipientUser.getName();
		
		return recipientName;
	}
}
