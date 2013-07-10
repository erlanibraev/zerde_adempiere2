/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MBSCPerspective;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCViewPerspective extends CPanel implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250907119653145646L;
	
	private static CLogger log = CLogger.getCLogger (BSCViewPerspective.class);

	private int m_WindowNo = 0;
	private CPanel mainPanel = new CPanel();
	private FormFrame m_frame;
	private List<MBSCPerspective> perspectives;
	private List<CPanel> perspectivesPanel;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
	@Override
	public void init(int WindowNo, FormFrame frame){
		log.fine("BSCViewPerspective");
		m_WindowNo = WindowNo;
		m_frame = frame;
		prepare();
		try {
			if (getPerspectives() == null) {
				throw new Exception("BSCViewPerspective: Pserspective not found");
			}
			for(MBSCPerspective perspective:getPerspectives()) {
				// TODO Auto-generated method stub
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}

	/**
	 * 
	 */
	private void prepare() {
		loadPerspectives();
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public List<MBSCPerspective> getPerspectives() {
		return perspectives;
	}

	public void setPerspectives(List<MBSCPerspective> perspective) {
		this.perspectives = perspective;
	}
	
	private void loadPerspectives() {
		setPerspectives(MBSCPerspective.getPerspectives());
	}
}
