package org.compiere.agreement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MAGRNode;
import org.compiere.model.MAGRStage;
import org.compiere.model.MAGRStageList;
import org.compiere.model.X_AGR_Agreement;
import org.compiere.model.X_AGR_StageList;
import org.compiere.util.Env;

public class Agreement_Dispatcher 
{
	
	ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
	
	private int AGR_Agreement_ID = 0;
	private int AGR_Stage_ID = 0;
	private int AD_User_ID = 0;
	
	private ArrayList<Integer> signers;
	
	public Agreement_Dispatcher(Object document)
	{
		AGR_Stage_ID = ((IAgreement)document).getAGR_Stage_ID();
		AGR_Agreement_ID = AGR_Agreement();
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());		
	}
	
	public boolean startAgreement()
	{
		try
		{
			if(!validation()) return false;
		}
		catch(Exception ex) 
		{
			
		}
		
		getNextStage();
		
		return true;
	}
	
	private int AGR_Agreement()
	{
		return 1000000;
	}
	
	//Проверка наличия этапов согласования
	private boolean stageCheck() throws SQLException
	{
		X_AGR_Agreement stage = new X_AGR_Agreement(Env.getCtx(), AGR_Agreement_ID, null);
		
		if(stage == null || stage.getAD_Reference() == null) return false;
			
		stages.addAll(MAGRStage.getOfAGR_StageList(Env.getCtx(), stage.get_ID(), null));
		
		return stages.size() > 0;
	}
	//Проверка наличия лиц, согласовывающих документ
	private boolean stageOptionCheck() throws SQLException
	{	
		ArrayList<MAGRStageList> stagesOptions = new ArrayList<MAGRStageList>();
		
		for(int i = 0; i < stages.size(); i++)
		{
			List<MAGRStageList> options = MAGRStageList.getOfAGR_StageList(Env.getCtx(), stages.get(i).get_ID(), null);
			
			if(options.size() == 0) return false;
			else stagesOptions.addAll(options);
		}
		
		for(int i = 0; i < stagesOptions.size(); i++)
		{
			X_AGR_StageList stageOption = stagesOptions.get(i);
			
			if(stageOption.getHR_Department_ID() == -1 || !isHasAlternates(stageOption)) return false;			
		}
		
		for(int i = 0; i < stagesOptions.size(); i++)
		{
			if(!isHasStatuses(stagesOptions.get(i).get_ID()))
				return false;
		}
	
		return true;
	}

	private boolean nodeCheck()
	{
		for(int i = 0; i < stages.size(); i++)
		{
			List<MAGRNode> nodes = MAGRNode.getOfAGR_StageList(Env.getCtx(), stages.get(i).get_ID(), null);
			
			if(nodes.size() == 0) return false;
		}
		
		return true;
	}
	
	private boolean isHasAlternates(X_AGR_StageList option)
	{
		boolean flag = false;
		
		if(option.getHR_Header_ID() != -1 && option.isHeaderActive())
			flag = true;
		
		if(!flag && option.getAlternate_ID() != -1 && option.isAlternateActive())
			flag = true;
		
		if(!flag && option.getAlternate2_ID() != -1 && option.isAlternate2Active())
			flag = true;
		
		return flag;
	}
	
	private boolean isHasStatuses(int TRM_StageOptions)
	{
		return true;
	}
	
	private boolean validation() throws SQLException
	{
		if(!stageCheck() || !stageOptionCheck() || !nodeCheck()) return false;
						
		return true;		
	}
	
	private void loadSigners(MAGRStage stage)
	{
		signers = stage.getSigners();
	}
	
	private void getNextStage()
	{
		MAGRStage stage = null;
		if(AGR_Stage_ID == 0)
		{
			stage = MAGRStage.getFirstStage(Env.getCtx(), AGR_Agreement_ID, null);
		}
		else
		{
			stage = new MAGRStage(Env.getCtx(), AGR_Stage_ID, null);
		}
		
		if(stage == null) return;
		
		loadSigners(stage);
		
		if(!signers.contains(AD_User_ID)) return;
		
		
	}
	
	private void loadNextStage()
	{
		
	}

}
