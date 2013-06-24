package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.compiere.model.MTRMDeposit;
import org.compiere.model.MTRMReward;
import org.compiere.util.DB;

public class TRM_RewardFill extends SvrProcess {

	private int TRM_Deposit_ID = 0;
	
	@Override
	protected void prepare() 
	{
		TRM_Deposit_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		//int currentYear = 
		LoadRewardLines(TRM_Deposit_ID, 2013);
		
		UpdateReward(TRM_Deposit_ID);
		
		return null;
	}

	@SuppressWarnings("deprecation")
	private void LoadRewardLines(int TRM_Deposit_ID, int year)
	{
		int month = 0;
		int day = 0;
		
		@SuppressWarnings("deprecation")
		Timestamp date = new Timestamp(year - 1900, month, day + 1, 0, 0, 0, 0);
		
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
		BigDecimal depositSum = deposit.getSum();
		
		int index = 0;
		
		while(date.getYear() == (year - 1900))
		{
			MTRMReward reward = new MTRMReward(getCtx(), 0, get_TrxName());
			reward.setTRM_Deposit_ID(TRM_Deposit_ID);
			reward.setIncomingBalance(depositSum);
			reward.setDateReward(date);
			reward.setIndex(new BigDecimal(index));
			reward.setInterestRate(deposit.getInterestRate());
			reward.setisPrediction(true);
			reward.saveEx();
			
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
		
		String sql = "SELECT DateAcct, LineSum FROM TRM_DepositLine WHERE TRM_Deposit_ID = " + TRM_Deposit_ID;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
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
			PreparedStatement pstmt = DB.prepareStatement("SELECT TRM_Reward_ID FROM TRM_Reward WHERE TRM_Deposit_ID = " + TRM_Deposit_ID + " ORDER BY DateReward", get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				rewardID.add(rs.getInt(1));
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception e){}
		
		StringBuffer update = new StringBuffer();
		
		for(int i = 0; i < dates.size(); i++)
		{			
			DB.executeUpdate("UPDATE TRM_Reward SET LineNetAmt = " + lineSum.get(i) + " WHERE TRM_Deposit_ID = " + TRM_Deposit_ID);
		}
		
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
		BigDecimal totalSum = deposit.getSum();
		
		int rewardIndex = 0;
		
		int month = 0;
		
		Timestamp currentTime = new Timestamp((new Date()).getDate());
		
		for(int i = 0; i < rewardID.size(); i++)
		{
			MTRMReward reward = new MTRMReward(getCtx(), rewardID.get(i), get_TrxName());
			
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
				
				reward.setwithdrawal(lineSum.get(index));
				
				rewardIndex = 1;
				
				Timestamp currentDate = reward.getDateReward();
				currentDate.setDate(currentDate.getDate() + 1);
				
				if(currentDate.getMonth() != month)
					month = currentDate.getMonth();
			}
			
			totalSum = totalSum.add(reward.getwithdrawal());
			
			BigDecimal rewardSum = reward.getIncomingBalance().multiply(reward.getInterestRate());
			rewardSum = rewardSum.divide(new BigDecimal(36000), 3, RoundingMode.HALF_UP).multiply(reward.getIndex());
			
			BigDecimal premiumSum = reward.getIncomingBalance().multiply(reward.getInterestRate()).divide(new BigDecimal(36000), 3, RoundingMode.HALF_UP);
			
			if(reward.getIndex().intValue() == 0)
				rewardSum = new BigDecimal(0);
			
			reward.setLineNetAmt(rewardSum);
			reward.setPremium(premiumSum);
			
			if(reward.getDateReward().before(currentTime))
				reward.setisPrediction(false);

			reward.saveEx();
		}
	}

	
}
