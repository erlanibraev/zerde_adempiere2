/**
 * 
 */
package org.compiere.process;

/**
 * @author A.Kulantayev
 *
 */
public class DocumentEngineNWD extends DocumentEngine {

	/**
	 * @param po
	 */
	public DocumentEngineNWD(DocAction po) {
		super(po);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param po
	 * @param docStatus
	 */
	public DocumentEngineNWD(DocAction po, String docStatus) {
		super(po, docStatus);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean processIt (String processAction, String docAction) {
		boolean result = true;
		
		if (processAction != null) {
			//TODO Что делать с процессами
		} 
		if(ACTION_Approve.equals(docAction)){
			result = approveIt();
		} else if (ACTION_Close.equals(docAction)){
			result = closeIt();
		} else if (ACTION_Complete.equals(docAction)) { 
			result = STATUS_Completed.equals(completeIt()); 
		} else if (ACTION_Invalidate.equals(docAction)){
			result = invalidateIt();
		} else if(ACTION_Prepare.equals(docAction)){
			result = STATUS_InProgress.equals(prepareIt());
		} else if (ACTION_ReActivate.equals(docAction)){
			result = reActivateIt();
		} else if (ACTION_Reject.equals(docAction)) { 
			result = rejectIt(); 
		} else if (ACTION_Reverse_Accrual.equals(docAction)){
			result = reverseAccrualIt();
		} else if (ACTION_Reverse_Correct.equals(docAction)){
			result = reverseCorrectIt();
		} else { 
			result = false;	
		}
		return result;
	}
	
	/**************************************************************************
	 * 	Get Action Options based on current Status
	 *	@return array of actions
	 */
	@Override
	public String[] getActionOptions() {
		return new String[] {
				ACTION_Approve, //1
				ACTION_Close,   //2
				ACTION_Complete, //3
				ACTION_Invalidate, //4
				ACTION_None, //5
				//ACTION_Post, //6
				ACTION_Prepare, //7
				ACTION_ReActivate, //8
				ACTION_Reject, //9
				ACTION_Reverse_Accrual, //10
				ACTION_Reverse_Correct, //11
				//ACTION_Unlock, //12
				ACTION_Void, //13 
				ACTION_WaitComplete //14
				};
	}

}
