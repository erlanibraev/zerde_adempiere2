/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

import javax.swing.JPanel;

import org.compiere.model.MBSCPerspective;
import org.compiere.util.CLogger;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCPerspectivePanel extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897230592612134300L;
	private static CLogger log = CLogger.getCLogger (BSCPerspectivePanel.class);
	private MBSCPerspective perspective = null;
	
	public BSCPerspectivePanel(MBSCPerspective aPerspective) {
		super();
		try {
			setPerspective(aPerspective);
			setName(getPerspective().getName());
		} catch (Exception e) {
			log.log(Level.SEVERE,"BSCPerspectivePanel",e);
		}
	}
	
	public MBSCPerspective getPerspective() {
		return perspective;
	}

	public void setPerspective(MBSCPerspective perspective) {
		this.perspective = perspective;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
