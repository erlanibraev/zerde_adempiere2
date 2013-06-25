package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTRMReward extends X_TRM_Reward 
{
	private static final long serialVersionUID = -5199076433683955386L;

	public MTRMReward(Properties ctx, int TRM_Reward_ID, String trxName) 
	{
		super(ctx, TRM_Reward_ID, trxName);
	}
	
    public MTRMReward (Properties ctx, ResultSet rs, String trxName)
    {
    	super (ctx, rs, trxName);
    }


}
