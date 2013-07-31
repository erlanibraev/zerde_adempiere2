package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MTRMDeposit;
import org.compiere.model.MTRMReward;
import org.compiere.model.X_TRM_Reward;
import org.compiere.util.DB;

public class TRM_RewardFill extends SvrProcess {

	public int TRM_Deposit_ID = 0;
	public int CMS_Contract_ID = 0;
	
	public Properties m_ctx;
	public String trxName;
	
	private MTRMDeposit deposit;
	
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		
		for(int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();			
			if (para[i].getParameter() == null);
			else if (name.equals("TRM_Deposit_ID"))
				TRM_Deposit_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
			{
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}				
		}
			
		deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
		CMS_Contract_ID = deposit.getCMS_Contract_ID();
		
		m_ctx = getCtx();
		trxName = get_TrxName();
	}

	@Override
	protected String doIt() throws Exception 
	{ 
		//loadWorkPeriod(CMS_Contract_ID);
		
		String sql = "SELECT MAX(TRM_Reward_ID) FROM TRM_Reward WHERE TRM_Deposit_ID = " + TRM_Deposit_ID;
		
		int maxID = DB.getSQLValue(trxName, sql);
		
		if(maxID <= 0 || deposit.isForce())
		{
			ClearLines();
			LoadRewardLines(TRM_Deposit_ID, deposit.getBeginningDate(), deposit.getEndDate());
		}
		
		UpdateReward(TRM_Deposit_ID);
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private void ClearLines() throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TRM_Reward WHERE TRM_Deposit_ID = ?");
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString());
			pstmt.setInt(1, TRM_Deposit_ID);
			pstmt.executeUpdate();
		}
		catch(Exception ex) {}
		finally
		{
			pstmt.close();
			pstmt = null;
		}
	}
	
	@SuppressWarnings("deprecation")
	private void LoadRewardLines(int TRM_Deposit_ID, Timestamp startDate, Timestamp endDate)
	{
		int month = 0;
	
		Timestamp date = startDate;
		
		BigDecimal depositSum = deposit.getSum();
		
		int index = 0;
		
		while(date.before(endDate))
		{
			X_TRM_Reward reward = new X_TRM_Reward(m_ctx, null, trxName);
			reward.setTRM_Deposit_ID(TRM_Deposit_ID);
			reward.setIncomingBalance(depositSum);
			reward.setDateReward(date);
			reward.setIndex(new BigDecimal(index));
			reward.setInterestRate(deposit.getInterestRate());
			reward.setisPrediction(true);
			reward.save();
			
			date.setDate(date.getDate() + 1);
			
			index++;
			
			if(date.getMonth() != month) 
			{
				month = date.getMonth();
				index = 0;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void UpdateReward(int TRM_Deposit_ID)
	{
		ArrayList<BigDecimal> lineSum = new ArrayList<BigDecimal>();
		ArrayList<Timestamp> dates = new ArrayList<Timestamp>();
		
		String sql = "SELECT DateAcct, Sum(LineSum) FROM TRM_DepositLine WHERE TRM_Deposit_ID = ? GROUP BY DateAcct ORDER BY DateAcct ";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, TRM_Deposit_ID);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dates.add(rs.getTimestamp(1));
				lineSum.add(rs.getBigDecimal(2));
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception e){}
		
		ArrayList<Integer> rewardID = new ArrayList<Integer>();
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement("SELECT TRM_Reward_ID FROM TRM_Reward WHERE isPrediction = 'Y' AND TRM_Deposit_ID = ? ORDER BY DateReward", trxName);
			pstmt.setInt(1, TRM_Deposit_ID);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				rewardID.add(rs.getInt(1));
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception e){}
		
		BigDecimal totalSum = deposit.getSum();
		
		int rewardIndex = 0;
		
		int month = 0;
		
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		for(int i = 0; i < rewardID.size(); i++)
		{
			MTRMReward reward = new MTRMReward(m_ctx, rewardID.get(i), trxName);
			
			reward.setIncomingBalance(totalSum);
			reward.setIndex(new BigDecimal(rewardIndex));
			
			rewardIndex++;
			
			if(month != reward.getDateReward().getMonth())
			{
				rewardIndex = 1;
				month = reward.getDateReward().getMonth();
			}
			
			Timestamp rewardDate = reward.getDateReward();
			rewardDate.setDate(rewardDate.getDate() + 1);
			
			if(dates.contains(reward.getDateReward()))
			{
				int index = dates.indexOf(reward.getDateReward());
				
				if(lineSum.get(index).doubleValue() != 0)
				{
					reward.setwithdrawal(lineSum.get(index));
					
					rewardIndex = 1;
					
					Timestamp currentDate = reward.getDateReward();
					currentDate.setDate(currentDate.getDate() + 1);
					
					if(currentDate.getMonth() != month)
						month = currentDate.getMonth();
				}
				lineSum.remove(index);
				dates.remove(index);
			}
			
			else if(rewardDate.getMonth() != month)
			{
				rewardIndex = 1;
				month = rewardDate.getMonth();
			}
			
			
			totalSum = totalSum.add(reward.getwithdrawal());
			
			BigDecimal rewardSum = reward.getIncomingBalance().multiply(reward.getInterestRate());
			rewardSum = rewardSum.divide(new BigDecimal(36000), 3, RoundingMode.HALF_UP).multiply(reward.getIndex());
			
			BigDecimal premiumSum = reward.getIncomingBalance().multiply(reward.getInterestRate()).divide(new BigDecimal(36000), 3, RoundingMode.HALF_UP);
			
			if(reward.getIndex().intValue() == 0)
				rewardSum = new BigDecimal(0);
			
			reward.setLineNetAmt(rewardSum);
			reward.setPremium(premiumSum);
			
			if(reward.getDateReward().before((currentTime)))
				reward.setisPrediction(false);

			reward.saveEx();
		}
	}

	
}
