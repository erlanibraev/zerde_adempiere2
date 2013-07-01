/**
 * 
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.apps.ADialog;
import org.compiere.model.MBSCCard;
import org.compiere.model.MCalendar;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MPrepaidExpenses;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 * Close period for BSC Card
 */
public class BSCClosePeriod extends SvrProcess {

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	
	private int C_Period_ID = 0;
	private MPeriod period = null;
	private List<MBSCCard> cards = new ArrayList<MBSCCard>();;
	private int AD_Client_ID = Env.getAD_Client_ID(getCtx());
	private int AD_Org_ID = Env.getAD_Org_ID(getCtx());
	private int C_DocType_ID = 0;
	private String resultIt = null;
	private int c_doctype_id = getC_DocType_ID();;
	private int p_AD_Client_ID;  
//---------------------------------------------------------------------------
	@Override
	protected void prepare() {
		getProcessInfo();
		
	 	Env.getAD_User_ID(getCtx());
		
		initParameters();
		
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		if (BSCClose()) {
			resultIt = "Период отчета по КПИ и ССП закрыт";
		} 
		return resultIt;
	}
//---------------------------------------------------------------------------
	public int getC_DocType_ID() {
		StringBuffer sql = new StringBuffer();
		String clientCheck = " AND AD_Client_ID=" + getAD_Client_ID();
		sql.setLength(0);
        sql.append("SELECT C_DocType_ID FROM C_DocType"
		+ " WHERE Name='BSC_Card' AND isActive ='Y'"+clientCheck);        
        C_DocType_ID = DB.getSQLValue(get_TrxName(), sql.toString());
		return C_DocType_ID;
	}

	public int getC_Period_ID() {
		return C_Period_ID;
	}

	public void setC_Period_ID(int c_Period_ID) {
		C_Period_ID = c_Period_ID;
		MPeriod p = MPeriod.get(getCtx(),C_Period_ID);
		if (p != null) {
			setPeriod(p);
		}
	}

	public MPeriod getPeriod() {
		if (period == null && getC_Period_ID() > 0) {
			period = MPeriod.get(getCtx(),getC_Period_ID());;
		}
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

	public List<MBSCCard> getCards() {
		return cards;
	}

	public void setCards(List<MBSCCard> cards) {
		this.cards = cards;
	}
//---------------------------------------------------------------------------
	/**
	 * 
	 */
	private void initParameters() {
		for(ProcessInfoParameter param: getParameter()) {
			if (param.getParameterName().equals("C_Period_ID")) {
				setC_Period_ID(Integer.parseInt(param.getParameter().toString()));
			}
		}
		loadCards();
	}
	
	/**
	 * 
	 */
	private void loadCards() {
		cards.clear();
		if (getC_Period_ID() > 0) {
			String sql = " SELECT * " +
					     " FROM BSC_Card " +
					     " WHERE AD_Org_ID = ? " +
					     "   AND AD_Client_ID = ? " +
					     "   AND isActive = 'Y' " +
					     "   AND C_Period_ID = ?";
		    PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql,null);
				pstmt.setInt(1, getAD_Client_ID());
				pstmt.setInt(2, Env.getAD_Org_ID(getCtx()));
				pstmt.setInt(3, C_Period_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MBSCCard card = new MBSCCard(getCtx(), rs, null);
					cards.add(card);
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "product", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}	
		}
	}

	/**
	 * @return
	 */
	private boolean BSCClose() {
		boolean result = false;
		if (c_doctype_id<0) {
			log.info("doctype is not found");
			return false;
		}

		ADialog dialog = new ADialog();
		boolean isWork = true;
		if (isBSCClosedPeriod(getC_Period_ID())) {
			if(!dialog.ask(25, null, "Процесс \"Закрытие периода ССП и КПИ.\" уже был успешно завершен. Вы хотите запустить данный процесс повторно?")) {
				isWork = false;
				result = true;
			}
		}
		if (isWork) {
			MPeriod nextPeriod = getNextPeriod();
			if(nextPeriod != null && nextPeriod.getC_Period_ID() > 0) {
				//TODO Хоть что-нибудь сделать!!!!!!!!!!!!!!!
				try {
					copyBSCCardToNextPeriod(nextPeriod);
					closeCurrentBSCCard();
				} catch(Exception e) {
					log.log(Level.SEVERE,"BSCClosePeriod: ",e);
					result = false;
				}
			} else {
				dialog.error(30, null, "Нет следующего периода! Создайте следующий период перед запуском процесса");
				result  = false;
			}
		}
		return result;
	}

	/**
	 * 
	 */
	private void closeCurrentBSCCard() {
		if (cards.size() > 0) {
			for(MBSCCard card: cards) {
				card.closePeriod(C_Period_ID);
			}
		}
//		getPeriod().isOpen(DocBaseType);
	}

	/**
	 * @param nextPeriod
	 * @throws Exception 
	 */
	private void copyBSCCardToNextPeriod(MPeriod nextPeriod) throws Exception {
		// TODO Auto-generated method stub
		if (nextPeriod == null || nextPeriod.getC_Period_ID() == 0) {
			return;
		}
		if (cards.size() > 0 && haveBSCCard(nextPeriod.getC_Period_ID())) {
			for(MBSCCard card: cards) {
				card.copyCard(nextPeriod);
			}
		} else {
			throw new Exception("Can not copy BSCCard for the next period"); 
		}
	}
	
	/**
	 * @param next_C_Period_ID
	 * @return
	 */
	private boolean haveBSCCard(int next_C_Period_ID) {
		boolean result = false;
		String sql = " SELECT count(*) as count " +
			     " FROM BSC_Card " +
			     " WHERE AD_Org_ID = " + Env.getAD_Org_ID(getCtx()) +
			     "   AND AD_Client_ID = " + Env.getAD_Client_ID(getCtx()) +
			     "   AND isActive = 'Y' " +
			     "   AND C_Period_ID = " + next_C_Period_ID;
		int count = DB.getSQLValue(get_TrxName(), sql);
		result = count > 0;
		return result;
	}

	public static boolean isBSCClosedPeriod(int C_Period_ID) {
		boolean result = false;
		if (C_Period_ID <=0) {
			return result;
		}
		
		MPeriodControl pc = new MPeriodControl(new MPeriod(Env.getCtx(),C_Period_ID,null),"BSC");
		if (pc != null) {
			result = !pc.isOpen();
		}
		
		return result;
	}
	
	protected MPeriod getNextPeriod() {
		MPeriod result = null;
		if(getPeriod() != null && getPeriod().getC_Period_ID() > 0) {
			int currentNo = getPeriod().getPeriodNo();
			int C_Calendar_ID = getPeriod().getC_Calendar_ID();
			int C_Year_ID = getPeriod().getC_Year_ID();
			
			int next_CPeriod_ID = getNextPeriod(C_Year_ID,currentNo+1);
			
			if (next_CPeriod_ID == 0) {
				C_Year_ID  = getNextYear(C_Calendar_ID, C_Year_ID);
				if (C_Year_ID > 0) {
					next_CPeriod_ID = getNextPeriod(C_Year_ID,0);
				}
			}
			if (next_CPeriod_ID > 0) {
				result = MPeriod.get(getCtx(), next_CPeriod_ID);
			}
		}
		return result;
	}
	
	/**
	 * @param c_Calendar_ID
	 * @param c_Year_ID
	 * @return
	 */
	private int getNextYear(int C_Calendar_ID, int C_Year_ID) {
		int result = 0;
		try {
			String query = "SELECT C_Year_ID " +
					       "FROM C_Year " +
					       "WHERE isActive = 'Y' " +
					       "  AND C_Calendar_ID = " + C_Calendar_ID +" " +
					       "  AND FiscalYear > (" +
					       "                    SELECT FiscalYear " +
					       "                    FROM C_Year " +
					       "                    WHERE C_Year_ID = "+ C_Year_ID +" LIMIT 1 " +
					       "                   ) " +
					       "ORDER BY FiscalYear LIMIT 1";
			result = DB.getSQLValue(get_TrxName(), query);
		} catch (Exception e) {
			log.log(Level.SEVERE, "BSCClosePeriod: ", e);
		} 
		return result;
	}

	private int getNextPeriod(int C_Year_ID, int currentNo) {
		int result = 0;
		try {
			String query = " SELECT C_Period_ID " +
					       " FROM C_Period " +
					       " WHERE isActive = 'Y' " +
					       "   AND C_Year_ID = " + C_Year_ID +" " +
					       "   AND PeriodNo >= "+currentNo+" " +
					       " ORDER BY PeriodNo LIMIT 1";
			result = DB.getSQLValue(get_TrxName(), query);
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "BSCClosePeriod: ", e);
		} 
		return result;
	}
	
}
