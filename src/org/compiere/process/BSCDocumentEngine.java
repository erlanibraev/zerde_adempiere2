/**
 * 
 */
package org.compiere.process;

import org.compiere.model.MTable;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCDocumentEngine extends DocumentEngine {

	/**
	 * @param po
	 * @param docStatus
	 */
	public BSCDocumentEngine(DocAction po, String docStatus) {
		super(po, docStatus);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean processIt (String processAction, String docAction) {
		boolean result = true;
		
		if (processAction != null) {
			// ToDo Что делать с процессами
		} 
		
		if (ACTION_WaitComplete.equals(docAction)) {
			return STATUS_Completed.equals(prepareIt());
		} else if (ACTION_Complete.equals(docAction)) {
			return STATUS_Completed.equals(completeIt());
		} else if (ACTION_Approve.equals(docAction)) {
			return approveIt();
		} else if (ACTION_Reject.equals(docAction)) {
			return rejectIt();
		} else if (ACTION_Close.equals(docAction)) {
			return closeIt();
		} else if (ACTION_ReActivate.equals(docAction)) {
			return reActivateIt();
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
		return new String[] {ACTION_None, ACTION_Approve, ACTION_Close, ACTION_Complete, ACTION_Reject, ACTION_WaitComplete, ACTION_Invalidate, ACTION_ReActivate};
	}
}
