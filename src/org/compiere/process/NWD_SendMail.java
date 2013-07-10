/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBSCNetWorkDiag;
import org.compiere.model.MUser;
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
	/** */
	MBSCNetWorkDiag m_netNetWorkDiag = null;
	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {
			sendMailAction();
		return Msg.translate(m_ctx, "Success");
	}
	private boolean sendMailAction() throws Exception{
		
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
			extend.org.compiere.utils.Util.sendMail(sendList.get(i).intValue(), p_AD_User_ID, "Мероприятия", "Список мероприятии для "
					+ new MBPartner(m_ctx, (Integer)(MUser.get(m_ctx, sendList.get(i).intValue()).get_Value("C_BPartner_ID")), 
					  get_TrxName()).getName(), false);
		}
		return true;
	}

}
