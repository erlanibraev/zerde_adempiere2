/**
 * 
 */
package org.adempiere.webui.apps.graph;

import java.util.Map;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.model.MParameter;
import org.compiere.model.MParameterLine;
import org.compiere.model.MPeriod;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

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
	private MParameter m_Parameter = null;
	private MPeriod period = null; 
	
	public WBSCPanel() {
		super();
		
		m_Parameter = new MParameter(Env.getCtx(),1000000,null);
		period = new MPeriod(Env.getCtx(),1000089,null);
		
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
		
		
		Row row = new Row();
		rows.appendChild(row);
		row.setWidth("100%");
		
		m_Parameter.setPeriod(period);
		
		WBSCIndicator pi = new WBSCIndicator(m_Parameter);
		row.appendChild(pi);
		pi.addEventListener(Events.ON_CLICK, this);			
		
		Panel temp = new Panel();
		row.appendChild(temp);
		
		MParameterLine parameterLine = m_Parameter.getParameterLine(period); 
		Map<String,MParameter> pl = parameterLine.getParameters();
		WBSCLine tempLine = null;
		for(String key: pl.keySet()) {
			row = new Row();
			rows.appendChild(row);
			row.setWidth("100%");
			
			tempLine = new WBSCLine();
			row.appendChild(tempLine);
			
			MParameter p = pl.get(key);
			p.setPeriod(period);
			pi = new WBSCIndicator(p); 
			row.appendChild(pi);
			pi.addEventListener(Events.ON_CLICK, this);			
		}
/*		
		if (tempLine != null) {
			tempLine.setEnd(true);
			try {
				tempLine.redraw(null);
			} catch (Exception e) {
				log.severe(e.getMessage());
			}
		}
*/		
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	@Override
	public void onEvent(Event event) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
