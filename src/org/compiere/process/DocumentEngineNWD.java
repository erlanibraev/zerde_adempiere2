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
		
		if (ACTION_WaitComplete.equals(docAction)) {
			result = STATUS_Completed.equals(prepareIt());
			} else if (ACTION_Complete.equals(docAction)) { 
				result = STATUS_Completed.equals(completeIt()); 
				} else if (ACTION_Approve.equals(docAction)) { 
					result = approveIt(); 
					} else if (ACTION_Reject.equals(docAction)) { 
						result = rejectIt(); 
						} else if (ACTION_Close.equals(docAction)) { 
							result = closeIt(); 
							} else if (ACTION_ReActivate.equals(docAction)) { 
								result = reActivateIt(); 
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
		return new String[] {ACTION_None, ACTION_Approve, ACTION_Close, ACTION_Complete, ACTION_Reject, ACTION_WaitComplete, ACTION_Invalidate, ACTION_ReActivate, ACTION_Prepare};
	}

}
