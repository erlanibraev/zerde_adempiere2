/**
 * 
 */
package org.adempiere.webui.apps.graph;

import org.adempiere.webui.panel.ADForm;

/**
 * @author Y.Ibrayev
 *
 */
public class WBSCView extends ADForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7472180995309359277L;

	/* (non-Javadoc)
	 * @see org.adempiere.webui.panel.ADForm#initForm()
	 */
	@Override
	protected void initForm() {
		WBSCPanel paPanel = new WBSCPanel();
		appendChild(paPanel);
	}

}
