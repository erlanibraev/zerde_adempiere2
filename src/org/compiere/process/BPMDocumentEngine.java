package org.compiere.process;

import org.compiere.model.MBPMProject;
import org.compiere.model.MBSCNetWorkDiag;
import org.compiere.model.MOrder;

public class BPMDocumentEngine extends DocumentEngine 
{
	/** Persistent Document 	*/
	private DocAction	m_document;
	
	public BPMDocumentEngine(DocAction po, String docStatus) 
	{
		super(po, docStatus);
		
		m_document = po;
	}
	
	@Override
	public boolean processIt (String processAction, String docAction) 
	{
		boolean result = true;
		
		if (processAction != null) {} 
		
		if (ACTION_Approve.equals(docAction)) {
			result = approveIt();
		} else if (ACTION_Close.equals(docAction)) {
			result = closeIt();
		} else if (ACTION_Prepare.equals(docAction)) {
			result = prepareIt().equals(MBPMProject.ACTION_Prepare);
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 	Close Document.
	 * 	Status: Closed
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#closeIt()
	 */
	public boolean closeIt()
	{
		if(m_document.closeIt())
			m_document.setDocStatus(MBPMProject.STATUS_Closed);
		return true;
	}	//	closeIt
	public String prepareIt()
	{
		m_document.prepareIt();
		m_document.setDocStatus(MBPMProject.ACTION_Prepare);
		return MBPMProject.ACTION_Prepare;
	}	//	processIt
	/**************************************************************************
	 * 	Get Action Options based on current Status
	 *	@return array of actions
	 */
	@Override
	public String[] getActionOptions() {
		return new String[] {ACTION_None, ACTION_Approve, ACTION_Close, ACTION_Complete, ACTION_Reject, ACTION_WaitComplete, ACTION_Invalidate, ACTION_ReActivate, ACTION_Prepare};
	}
}
