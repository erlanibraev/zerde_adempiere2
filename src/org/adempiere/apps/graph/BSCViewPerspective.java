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
	private CPanel mainPanel = new CPanel();
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private FormFrame m_frame;
	private List<MBSCPerspective> perspectives;

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
		
		prepare();
		
		try {
			if (getPerspectives() == null) {
				throw new Exception("BSCViewPerspective: Perspective not found");
			}
			
			
			confirmPanel.addActionListener(this);
			initMainPanel();
			
			CScrollPane scroll =  new CScrollPane(mainPanel);
			m_frame.getContentPane().add(scroll, BorderLayout.CENTER);
			m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}

	/**
	 * 
	 */
	private void initMainPanel() {
        
        GridLayout l = new GridLayout(4,1);
		mainPanel.setLayout(l);//new GridBagLayout()
		load();
	}
	
	private void load() {
		mainPanel.removeAll();
		if (getPerspectives() != null) {
			GridBagConstraints gbc;
			int i = 0;
			for(MBSCPerspective perspective:getPerspectives()) {
				gbc = new GridBagConstraints();
				gbc.fill = GridBagConstraints.BOTH;
				gbc.gridx = 0;
				gbc.gridy = i++;
				addPerpsective(perspective,gbc);
			}
		}
		
		mainPanel.validate();
		mainPanel.repaint();
		mainPanel.updateUI();
	}
	/**
	 * @param perspective
	 */
	private void addPerpsective(MBSCPerspective perspective, GridBagConstraints gbc) {
		
		if (perspective == null) {
			return;
		}
		
		BSCPerspectivePanel panel = new BSCPerspectivePanel(perspective);
		panel.addActionListener(this);
		mainPanel.add(panel, gbc);
//		mainPanel.add(panel);
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
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
		removeAll();
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
