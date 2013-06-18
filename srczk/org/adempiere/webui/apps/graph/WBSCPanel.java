/**
 * 
 */
package org.adempiere.webui.apps.graph;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Rows;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 * @author Y.Ibrayev
 *
 */
public class WBSCPanel extends Panel implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411431885701330107L;
	private static CLogger log = CLogger.getCLogger (WPAPanel.class);
	
	public WBSCPanel() {
		super();
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		Grid grid = new Grid();
		appendChild(grid);
		grid.setWidth("100%");
		grid.setStyle("margin:0; padding:0; position: absolute;");
		grid.makeNoStrip();
		grid.setOddRowSclass("even");

		Rows rows = new Rows();
		grid.appendChild(rows);
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	@Override
	public void onEvent(Event event) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
