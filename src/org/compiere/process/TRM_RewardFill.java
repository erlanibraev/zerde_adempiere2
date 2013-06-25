package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
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
		
		MTRMDeposit deposit = new MTRMDeposit(m_ctx, TRM_Deposit_ID, trxName);
		
		startPeriod = deposit.getBeginningDate();
		endPeriod = deposit.getEndDate();
		
		if(maxID <= 0)
			LoadRewardLines(TRM_Deposit_ID, startPeriod, endPeriod);//2013);
		
		UpdateReward(TRM_Deposit_ID);
		
		return null;
	}

	public Properties		m_ctx;
	public String trxName;
	@SuppressWarnings("deprecation")
	private void LoadRewardLines(int TRM_Deposit_ID, Timestamp startDate, Timestamp endDate)
	{
		int month = 0;
	
		Timestamp date = startDate;//new Timestamp(year - 1900, month, day + 1, 0, 0, 0, 0);
		
		MTRMDeposit deposit = new MTRMDeposit(m_ctx, TRM_Deposit_ID, trxName);
		
		BigDecimal depositSum = deposit.getSum();
		
		int index = 0;
		
		while(date.before(endDate))//while(date.getYear() == (year - 1900))
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
	
	private Timestamp startPeriod;
	private Timestamp endPeriod;
	
	private void loadWorkPeriod(int CMS_Contract_ID)
	{
		String sql = "SELECT BeginningDateExecution, EndDateExecution FROM CMS_Contract WHERE CMS_Contract_ID = " + CMS_Contract_ID;
		
		try
		{
			@SuppressWarnings("deprecation")
			PreparedStatement pstmt = DB.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				startPeriod = rs.getTimestamp("BeginningDateExecution");
				endPeriod = rs.getTimestamp("EndDateExecution");
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@SuppressWarnings("deprecation")
	private void UpdateReward(int TRM_Deposit_ID)
	{
		ArrayList<BigDecimal> lineSum = new ArrayList<BigDecimal>();
		ArrayList<Timestamp> dates = new ArrayList<Timestamp>();
		
		String sql = "SELECT DateAcct, Sum(LineSum) FROM TRM_DepositLine WHERE TRM_Deposit_ID = " + TRM_Deposit_ID + " GROUP BY DateAcct ORDER BY DateAcct ";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
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
			PreparedStatement pstmt = DB.prepareStatement("SELECT TRM_Reward_ID, DateReward FROM TRM_Reward WHERE isPrediction = 'Y' AND TRM_Deposit_ID = " + TRM_Deposit_ID + " ORDER BY DateReward", get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				rewardID.add(rs.getInt(1));
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception e){}
		/*
		for(int i = 0; i < dates.size(); i++)
		{			
			DB.executeUpdate("UPDATE TRM_Reward SET LineNetAmt = " + lineSum.get(i) + " WHERE TRM_Deposit_ID = " + TRM_Deposit_ID);
		}
		*/
		MTRMDeposit deposit = new MTRMDeposit(m_ctx, TRM_Deposit_ID, trxName);
		
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
