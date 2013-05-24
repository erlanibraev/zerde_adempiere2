package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;

public class MAGRAgreement extends X_AGR_Agreement 
{
	private static final long serialVersionUID = -704183934751993540L;

	public MAGRAgreement(Properties ctx, int AGR_Agreement_ID, String trxName) 
	{
		super(ctx, AGR_Agreement_ID, trxName);
	}

	 /** Load Constructor */
    public MAGRAgreement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    //Check for presence of process stages
  	private boolean stageCheck() throws SQLException
  	{
  		//X_AGR_Agreement agreement = new X_AGR_Agreement(Env.getCtx(), AGR_Agreement_ID, null);
  		
  		if(getAD_Reference() == null) return false;
  			
  		ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
  		
  		stages.addAll(MAGRStage.getOfAGR_StageList(Env.getCtx(), get_ID(), null));
  		
  		int stageCanBeAchived = 0;
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			if(stageCanBeAchived(stages.get(i), stages))
  				stageCanBeAchived++;
  		}
  		
  		return stageCanBeAchived == stages.size();
  	}
  	
  	private boolean hasExit()
  	{
  		ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
  		
  		stages.addAll(MAGRStage.getOfAGR_StageList(Env.getCtx(), get_ID(), null));
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			ArrayList<MAGRNode> nodes = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), stages.get(i).get_ID(), get_TrxName());
  			
  			if(nodes.size() == 0) 
  				return false;
  		}
  		
  		return true;
  	}
  	
  	public boolean checkAgreement() throws SQLException
  	{
  		if(!stageCheck() || !stageOptionCheck() || !hasExit()) return false;
  		
  		return true;
  	}
  	
  //Check for presence of next stages for each stage of this agreement
  	private boolean nodeCheck(MAGRStage stage)
  	{
  		List<MAGRNode> nodes = MAGRNode.getOfAGR_StageList(Env.getCtx(), stage.get_ID(), null);
  			  		
  		return nodes.size() != 0; 
  	}
  	
  	private boolean stageCanBeAchived(MAGRStage stage, ArrayList<MAGRStage> stages)
  	{
  		List<MAGRStage> stagesFromStages = new ArrayList<MAGRStage>();
  		
  		ArrayList<MAGRNode> nodes = new ArrayList<MAGRNode>();
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			ArrayList<MAGRNode> nodesFromCurrentStage = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), stages.get(i).get_ID(), get_TrxName());
  			
  			if(stages.get(i).get_ID() != stage.get_ID() && nodesFromCurrentStage.size() == 2)
  			{  			  
  				nodes.addAll(nodesFromCurrentStage);
  			}
  			else if(nodesFromCurrentStage.size() == 2)
  			{
  				if(nodesFromCurrentStage.get(0).getAGR_NextStage_ID() == stage.get_ID() || 
  						nodesFromCurrentStage.get(1).getAGR_NextStage_ID() == stage.get_ID())
  					return false;
  			}
  			else if(nodesFromCurrentStage.size() == 1 && stage.getName().equals(MAGRStage.STAGETYPE_Initial))
  				nodes.addAll(nodesFromCurrentStage);
  			else if(nodesFromCurrentStage.size() == 1 && nodesFromCurrentStage.get(0).isBack());
  			else return false;
  		}
  		
  		boolean flag = false;
  		
  		if(stage.getStageType().equals(MAGRStage.STAGETYPE_Initial)) flag = true;
  		
  		for(int i = 0; i < nodes.size(); i++)
  		{
  			if(nodes.get(i).getAGR_NextStage_ID() == stage.get_ID() && !nodes.get(i).isBack() && !stage.getName().equals(MAGRStage.STAGETYPE_Initial))
  			{
  				flag = true;
  			}
  		}
  		
  		return flag;
  	}
  	
  //Check for presence of persons
  	private boolean stageOptionCheck() throws SQLException
  	{	
  		ArrayList<MAGRStageList> stagesOptions = new ArrayList<MAGRStageList>();
  		
  		ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
  		
  		stages.addAll(MAGRStage.getOfAGR_StageList(Env.getCtx(), get_ID(), null));
  		
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
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			if(!isHasStatuses(stages.get(i)))
  				return false;
  		}
  	
  		return true;
  	}
  	
	//Check for presence of statuses in order to set document status in case of if document will be dissaproved
	private boolean isHasStatuses(MAGRStage AGR_Stage)
	{
		return AGR_Stage.getAD_Ref_List_ID() > 0;
	}
  	
  //Check for presence of alternate persons who can approve document instead of head of department
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
 	
}
