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
		this(po,STATUS_Drafted);
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
		
		if(ACTION_Prepare.equals(docAction)){
			result = STATUS_WaitingConfirmation.equals(prepareIt());}
		
		else if(ACTION_Approve.equals(docAction)){
			result = approveIt();} 
		
		else if (ACTION_Reject.equals(docAction)) { 
			result = rejectIt();}
		
		else if (ACTION_Complete.equals(docAction)) { 
			result = STATUS_Completed.equals(completeIt());} 
		
		else if (ACTION_Close.equals(docAction)){
			result = closeIt();} 
		
		else if (ACTION_ReActivate.equals(docAction)){
			result = reActivateIt();} 
		else { result = false;}
		
		return result;
	}
	
	/**************************************************************************
	 * 	Get Action Options based on current Status
	 *	@return array of actions
	 */
	@Override
	public String[] getActionOptions() {
		/*return new String[] {ACTION_Approve, ACTION_Close, ACTION_Complete, ACTION_Invalidate, ACTION_None, ACTION_Post, ACTION_Prepare,
				ACTION_ReActivate, ACTION_Reject, ACTION_Reverse_Accrual, ACTION_Reverse_Correct, ACTION_Unlock, ACTION_Void, ACTION_WaitComplete};*/

		if (isDrafted() || isNotApproved() || isInvalid())
			return new String[] {ACTION_Prepare, ACTION_Void};
		
		if (isApproved())
			return new String[] {ACTION_Complete, ACTION_Void};
		
		if (isWaiting())
			return new String[] {ACTION_Approve, ACTION_Reject, ACTION_Void};
		
		if (isCompleted())
			return new String[] {ACTION_Close, ACTION_ReActivate, ACTION_Void};
		
		if (isClosed())
			return new String[] {ACTION_ReActivate, ACTION_ReOpen};

		return new String[] {};
	}

}
