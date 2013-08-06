/**
 * 
 */
package org.adempiere.apps.graph;

import groovy.swing.impl.TableLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MBSCPerspective;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
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
	private BSCPerspectivePanel mainPanel = null;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private FormFrame m_frame;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
	@Override
	public void init(int WindowNo, FormFrame frame){
		log.fine("BSCViewPerspective");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try {
			confirmPanel.addActionListener(this);
			mainPanel = new BSCPerspectivePanel();
			if (mainPanel != null) {
				CScrollPane scroll =  new CScrollPane(mainPanel);
				//mainPanel.addComponentsToPane();
				m_frame.getContentPane().add(scroll, BorderLayout.CENTER);
				m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#dispose()
	 */
	@Override
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
		removeAll();
	}
	
}
