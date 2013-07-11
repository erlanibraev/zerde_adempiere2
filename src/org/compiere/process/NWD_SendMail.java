/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MUser;
import org.compiere.model.X_BSC_NetWorkDiag;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author A.Kulantayev
 *
 */
public class NWD_SendMail extends SvrProcess {

	/** Current context */
	private Properties m_ctx;
	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {
		String result="";
		m_ctx = Env.getCtx();
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		int currentDay = new org.joda.time.DateTime(currentDate.getTime()).getDayOfMonth();
		int currentMonth = new org.joda.time.DateTime(currentDate.getTime()).getMonthOfYear();
		int currentYear = new org.joda.time.DateTime(currentDate.getTime()).getYear();
		Timestamp closePeriod1 = Timestamp.valueOf(String.valueOf(currentYear)+"-06-26 10:10:10.0");
		Timestamp closePeriod2 = Timestamp.valueOf(String.valueOf(currentYear)+"-10-14 10:10:10.0");
		Timestamp closePeriod3 = Timestamp.valueOf(String.valueOf(currentYear)+"-01-04 10:10:10.0");
		
		int cp1Day = new org.joda.time.DateTime(closePeriod1.getTime()).getDayOfMonth(),
			cp2Day = new org.joda.time.DateTime(closePeriod2.getTime()).getDayOfMonth(), 
			cp3Day = new org.joda.time.DateTime(closePeriod3.getTime()).getDayOfMonth();
		
		int cp1Month = new org.joda.time.DateTime(closePeriod1.getTime()).getMonthOfYear(), 
			cp2Month = new org.joda.time.DateTime(closePeriod2.getTime()).getMonthOfYear(), 
			cp3Month = new org.joda.time.DateTime(closePeriod3.getTime()).getMonthOfYear();
		
		int cp1Year = new org.joda.time.DateTime(closePeriod1.getTime()).getYear(), 
			cp2Year = new org.joda.time.DateTime(closePeriod2.getTime()).getYear(), 
			cp3Year = new org.joda.time.DateTime(closePeriod3.getTime()).getYear();
		
		//Проверяем не пришло ли время первого периода
		if((currentYear == cp1Year && currentMonth == cp1Month && currentDay == cp1Day)
				||(currentYear == cp2Year && currentMonth == cp2Month && currentDay == cp2Day)
				||(currentYear == cp3Year && currentMonth == cp3Month && currentDay == cp3Day)){
			sendAlertMail();
			result = Msg.translate(m_ctx, "Success");
			
		} /*//Проверяем не пришло ли время  второго периода
		else if (currentYear == cp2Year && currentMonth == cp2Month && currentDay == cp2Day){
			
			result = Msg.translate(m_ctx, "Success");
			
		}//Проверяем не пришло ли время третьего периода 
		else if (currentYear == cp3Year && currentMonth == cp3Month && currentDay == cp3Day){
			
			result = Msg.translate(m_ctx, "Success");
			
		}*/ //Понятно то что не время еще для закрытия периода 
		else result = "Проверка прошла успешно(время закрытия периода еще не пришло)";
		
	return result;
	}

	private boolean sendAlertMail(){
		
		X_BSC_NetWorkDiag[] bsc_NetWorkDiags = getNetWorkDiag();
		for(int i = 0; i < bsc_NetWorkDiags.length; i++){
			try{
				sendMailAction(bsc_NetWorkDiags[i]);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return true;
	}
	private boolean sendMailAction(X_BSC_NetWorkDiag x_BSC_NetWorkDiag) throws Exception{
		
		X_BSC_NetWorkDiag m_netNetWorkDiag = x_BSC_NetWorkDiag;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT AD_User_ID "+ 
					" FROM bsc_responsible_executor \n"+
					" WHERE AD_User_ID IS NOT NULL AND bsc_action_id IN (SELECT bsc_action_id FROM bsc_action WHERE bsc_networkdiagsubline_id IN \n"+ 
					" (SELECT bsc_networkdiagsubline_id FROM bsc_networkdiagsubline WHERE bsc_networkdiagline_id IN \n"+
			        " (SELECT bsc_networkdiagline_id FROM bsc_networkdiagline WHERE bsc_networkdiag_id ="+ m_netNetWorkDiag.getBSC_NetWorkDiag_ID()+"))) \n GROUP BY AD_User_ID";
		
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
								  " AND IsActive = 'Y' AND AD_Client_ID = "+Env.getAD_Client_ID(m_ctx);
		int p_AD_User_ID = DB.getSQLValue(get_TrxName(), sqlGetZerdeAdmin);		
		if(p_AD_User_ID < 0)
			throw new Exception("User_ID not found");
		for(int i = 0; i < sendList.size(); i++){
			extend.org.compiere.utils.Util.sendMail(sendList.get(i).intValue(), p_AD_User_ID, "Напоминание об отчетном периоде"
					,"Уважаемый(ая), "+getRecipientName(sendList.get(i).intValue())
					+ ". Через несколько дней будет закрытие отчетного периода, не забудьте проставить статусы исполнения мероприятий в документе №"
					+ m_netNetWorkDiag.get_Value("DocumentNo")
					, false);
		}
		return true;
	}
	
	private X_BSC_NetWorkDiag[] getNetWorkDiag(){
		
		X_BSC_NetWorkDiag bsc_NetWorkDiag = null;
		List<X_BSC_NetWorkDiag> list = new ArrayList<X_BSC_NetWorkDiag>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT bsc_networkdiag_id FROM bsc_networkdiag " +
				     "\n WHERE AD_Client_ID = " + Env.getAD_Client_ID(getCtx())+
				     "\n AND IsActive = 'Y' AND DocStatus = 'AP'";
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				bsc_NetWorkDiag = new X_BSC_NetWorkDiag(getCtx(), rs.getInt(1), get_TrxName());
				list.add(bsc_NetWorkDiag);
				bsc_NetWorkDiag = null;
			}
			
		}
		catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return list.toArray(new X_BSC_NetWorkDiag[list.size()]);
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
