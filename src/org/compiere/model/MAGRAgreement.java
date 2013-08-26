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
	
	private ArrayList<String> errorsList = new ArrayList<String>();

	public MAGRAgreement(Properties ctx, int AGR_Agreement_ID, String trxName) 
	{
		super(ctx, AGR_Agreement_ID, trxName);
	}

	 /** Load Constructor */
    public MAGRAgreement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public ArrayList<String> getErrors()
    {
    	return errorsList;
    }
    
    protected boolean afterSave (boolean newRecord, boolean success)
	{
    	if(newRecord && success){
			MAGRStage stage = new MAGRStage(getCtx(), null, get_TrxName());
			stage.setAGR_Agreement_ID(get_ID());
			stage.setStageType(MAGRStage.STAGETYPE_Initial);
			stage.setName("---");
			stage.setLine(10);
			stage.saveEx();
    	}
    	
		return success;
	}	//	afterSave
    
    //Check for presence of process stages
  	private boolean stageCheck() throws SQLException
  	{  		
  		if(getAD_Reference() == null) 
  		{
  			errorsList.add("Agreement does not contain AD_Reference_ID");
  			return false;
  		}
  		
  		if(getAD_Process() == null) 
  		{
  			errorsList.add("Agreement does not contain AD_Process_ID");
  			return false;
  		}
  		
  		ArrayList<MAGRStage> stages = (ArrayList<MAGRStage>) MAGRStage.getOfAGR_Agreement(Env.getCtx(), get_ID(), null);
  		
  		if(!stageCanBeAchived(stages))
  			return false;
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			if(!hasNodes(stages.get(i)))
  			{
  				errorsList.add("Stage \"" + stages.get(i).getName() + "\" has invalid nodes");
  				return false;
  			}
  		}
  		return true;
  	}
  	
  	private boolean hasExit()
  	{
  		ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
  		
  		stages.addAll(MAGRStage.getOfAGR_Agreement(Env.getCtx(), get_ID(), null));
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			//ArrayList<MAGRNode> nodes = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), stages.get(i).get_ID(), get_TrxName());
  			
/*  			if(nodes.size() == 0 && !stages.get(i).getStageType().equals(MAGRStage.STAGETYPE_Final))
  			{
  				errorsList.add(stages.get(i).getName() + " has no any nodes");
  				return false;
  			}*/
  		}
  		
  		return true;
  	}
  	
  	public boolean checkAgreement() throws SQLException
  	{
  		errorsList = new ArrayList<String>();
  		
  		if(		!stageCheck() || 
  				!stageOptionCheck() || 
  				!hasExit())
  			return false;
  		
  		return true;
  	}
  	
  	public boolean hasNodes(MAGRStage stage)
  	{
  		boolean retValue = true;
  		
/*  		ArrayList<MAGRNode> nodes = (ArrayList<MAGRNode>) MAGRNode.getOfAGR_StageList(getCtx(), stage.get_ID(), get_TrxName());
  		
  		if(nodes.size() == 2)
  		{
  			if(nodes.get(0).isBack() != nodes.get(1).isBack())
  			{
  				retValue = true;
  			}
  		}
  		else if(nodes.size() == 1 && stage.getStageType().equals(MAGRStage.STAGETYPE_Initial))
  			retValue = true;
  		else if(nodes.size() == 0)
  			retValue = true;
*/
  		return retValue;
  	}
  	
  	private boolean stageCanBeAchived(ArrayList<MAGRStage> stages)
  	{		
  		boolean retValue = true;
  		
  		ArrayList<Integer> next_stages = MAGRNode.getNodes(getCtx(), this.get_ID(), "N", get_TrxName());
  		  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  	  		if(!next_stages.contains(stages.get(i).get_ID()) && stages.get(i).getStageType() == MAGRStage.STAGETYPE_Ordinary)
  	  		{
  	  			errorsList.add("Stage \"" + stages.get(i).getName() + "\" can not be achived from another stages");
  	  			retValue = false;
  	  			break;
  	  		}
  	  		
  	  		if(next_stages.indexOf(stages.get(i).get_ID()) != next_stages.lastIndexOf(stages.get(i).get_ID()))
  	  		{
  	  			errorsList.add("Stage \"" + stages.get(i).getName() + "\" can cause cycle");
  	  			retValue = false;
  	  			break;
  	  		}
  		}

  		return retValue;
  	}
  	
  //Check for presence of persons
  	private boolean stageOptionCheck() throws SQLException
  	{	
  		ArrayList<MAGRStageList> stagesOptions = new ArrayList<MAGRStageList>();
  		
  		ArrayList<MAGRStage> stages = new ArrayList<MAGRStage>();
  		
  		stages.addAll(MAGRStage.getOfAGR_Agreement(Env.getCtx(), get_ID(), null));
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			List<MAGRStageList> options = MAGRStageList.getOfAGR_StageList(Env.getCtx(), stages.get(i).get_ID(), null);
  			
  			if(options.size() == 0) 
  			{
  				errorsList.add(stages.get(i).getName() + " does not have persons for agreement");
  				return false;
  			}
  			else stagesOptions.addAll(options);
  		}
  		
  		for(int i = 0; i < stagesOptions.size(); i++)
  		{
  			X_AGR_StageList stageOption = stagesOptions.get(i);
  			
  			if(!isHasAlternates(stageOption)) 
  			{
  				errorsList.add("Stage option does not contain persons for agreement or persons are not active");
  				return false;			
  			}
  		}
  		
  		for(int i = 0; i < stages.size(); i++)
  		{
  			if(!isHasStatuses(stages.get(i)))
  			{
  				errorsList.add(stages.get(i).getName() + " does not contain status");
  				return false;
  			}
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
  		
  		if(option.getC_BPartner_ID() > 0 && option.isHeaderActive())
  			flag = true;
  		
  		if(!flag && option.getAlternate_ID() > 0 && option.isAlternateActive())
  			flag = true;
  		
  		if(!flag && option.getAlternate2_ID() > 0 && option.isAlternate2Active())
  			flag = true;
  		
  		return flag;
  	}
 	
}
