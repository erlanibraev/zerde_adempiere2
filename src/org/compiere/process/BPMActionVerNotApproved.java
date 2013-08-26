/**
 * 
 */
package org.compiere.process;

import org.compiere.util.Msg;

/**
 * @author V.Sokolov
 *
 */
public class BPMActionVerNotApproved extends BPMActionsVersions {

	/* 
	 */
	@Override
	protected void prepare() {
		super.prepare();
	}

	/* 
	 */
	@Override
	protected String doIt() throws Exception {

		if(!IsApproved)
			actionProject(project, IsApproved);
		
		return Msg.translate(m_ctx, "Success");
	}
	
}
