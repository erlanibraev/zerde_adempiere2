package org.compiere.agreement;

import java.util.ArrayList;

import org.compiere.model.MAGRApprovalList;
import org.compiere.model.MAGRStage;
import org.compiere.model.MAGRStageList;
import org.compiere.model.MAGRStageListLine;
import org.compiere.util.Env;

public class Agreement_PrepareList 
{
	private int AGR_Agreement_ID = 0;
	private int C_BPartner_ID = 0;
	
	private int AD_Table_ID = 0;
	private int Record_ID = 0;
	
	public Agreement_PrepareList(int AGR_Agreement_ID, int C_BPartner_ID, int AD_Table_ID, int Record_ID)
	{
		this.AGR_Agreement_ID = AGR_Agreement_ID;
		this.C_BPartner_ID = C_BPartner_ID;
		this.AD_Table_ID = AD_Table_ID;
		this.Record_ID = Record_ID;
	}
	
	public void FillAgreementList()
	{
		MAGRStage rootStage = AddRootStage();
		
		if(rootStage == null) return;
		
		FillList(rootStage);
	}
	
	
	
	private boolean RootStageCheck(MAGRStage rootStage)
	{
		ArrayList<MAGRStageList> lines = (ArrayList<MAGRStageList>) MAGRStageList.getOfAGR_StageList(Env.getCtx(), rootStage.get_ID(), null);
		
		boolean retValue = false;
		
		for(int i = 0; i < lines.size(); i++)
		{
			if(lines.get(i).getActiveSigner() == C_BPartner_ID)
			{
				retValue = true;
				break;
			}
		}
		
		return retValue;
	}
	
	private MAGRStage AddRootStage()
	{
		MAGRStage rootStage = MAGRStage.getFirstStage(Env.getCtx(), AGR_Agreement_ID, null);
		
		if(!RootStageCheck(rootStage))
		{
			return null;
		}
			
		AddSigner(C_BPartner_ID, rootStage);
		
		return rootStage;
	}
	
	private void FillList(MAGRStage rootStage)
	{
		MAGRStage stage = rootStage;
		
		ArrayList<Integer> currentSigners = new ArrayList<Integer>();
		currentSigners.add(C_BPartner_ID);
		
		while(true)
		{
			MAGRStage nextStage = stage.getNextStage();
			
			if(nextStage == null)
				break;
			
			ArrayList<Integer> nextStageSigners = getStageSigners(nextStage, currentSigners);
			
			for(int i = 0; i < nextStageSigners.size(); i++)
			{
				AddSigner(nextStageSigners.get(i), nextStage);
			}
			
			currentSigners = nextStageSigners;
			
			stage = nextStage;
			
			if(stage.getStageType().equals(MAGRStage.STAGETYPE_Final))
				break;
		}
	}
	
	private ArrayList<Integer> getStageSigners(MAGRStage stage, ArrayList<Integer> signers)
	{
		ArrayList<MAGRStageList> stageList = (ArrayList<MAGRStageList>) MAGRStageList.getOfAGR_StageList(Env.getCtx(), stage.get_ID(), null);
		
		ArrayList<Integer> newSigners = new ArrayList<Integer>();
		
		for(int i = 0; i < stageList.size(); i++)
		{
			if(checkSigner(stageList.get(i), signers))
			{
				//AddSigner(stageList.get(i).getActiveSigner(), stage);
				newSigners.add(stageList.get(i).getActiveSigner());
			}
		}
		
		return newSigners;
	}
	
	private boolean isAlsoIclude(MAGRStageList line, ArrayList<Integer> signers)
	{
		boolean retValue = false;
		
		ArrayList<MAGRStageListLine> includeLines = (ArrayList<MAGRStageListLine>) MAGRStageListLine.getOfAGR_StageListAlsoInclude(Env.getCtx(), line.get_ID(), null);
			
		if(includeLines.size() == 0)
		{
			retValue = true;
		}
		else
		{			
			for(int i = 0; i < includeLines.size(); i++)
			{
				if(signers.contains(includeLines.get(i).getC_BPartner_ID()))
				{
					retValue = true;
				}
			}
		}
		
		return retValue;
	}
	
	private boolean isCanBeSigned(MAGRStageList line, ArrayList<Integer> signers)
	{
		boolean retValue = false;
		
		ArrayList<MAGRStageListLine> includeLines = (ArrayList<MAGRStageListLine>) MAGRStageListLine.getOfAGR_StageList(Env.getCtx(), line.get_ID(), null);
			
		if(includeLines.size() == 0)
		{
			retValue = true;
		}
		else
		{
			retValue = true;
			
			for(int i = 0; i < includeLines.size(); i++)
			{
				if(!signers.contains(includeLines.get(i).getC_BPartner_ID()))
				{
					retValue = false;
				}
			}
		}
		
		return retValue;
	}
	
	private boolean isAvoid(MAGRStageList line, ArrayList<Integer> signers)
	{
		boolean retValue = false;
		
		ArrayList<MAGRStageListLine> includeLines = (ArrayList<MAGRStageListLine>) MAGRStageListLine.getOfAGR_StageListAvoid(Env.getCtx(), line.get_ID(), null);
			
		if(includeLines.size() == 0)
		{
			retValue = true;
		}
		else
		{
			retValue = true;
			for(int i = 0; i < includeLines.size(); i++)
			{
				if(signers.contains(includeLines.get(i).getC_BPartner_ID()))
				{
					retValue = false;
				}
			}
		}
		
		return retValue;
	}
	
	private boolean checkSigner(MAGRStageList list, ArrayList<Integer> signers)
	{
		boolean retValue = false;
		
		if(isAlsoIclude(list, signers) && isCanBeSigned(list, signers) && isAvoid(list, signers))
			retValue = true;	
		
		return retValue;
	}
		
	private boolean AddSigner(int C_BPartner_ID, MAGRStage stage)
	{
		MAGRApprovalList list = new MAGRApprovalList(Env.getCtx(), null, null);
		list.setAD_Table_ID(AD_Table_ID);
		list.setRecord_ID(Record_ID);
		list.setC_BPartner_ID(C_BPartner_ID);
		list.setAGR_Agreement_ID(AGR_Agreement_ID);
		list.setAGR_Stage_ID(stage.get_ID());
		
		return list.save();
	}

}
