/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MBSCKeySuccessFactor;
import org.compiere.model.MBSCPerspective;
import org.compiere.swing.CLabel;
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
	private static int SizeWidth = 1200;
	private static int SizeHeight = 200;
	public static Dimension paneldimension = new Dimension(SizeWidth, SizeHeight);
	private MBSCPerspective perspective = null;
	private List<MBSCKeySuccessFactor> keySuccessFactors = new ArrayList<MBSCKeySuccessFactor>();
	private CLabel label = null;
	private GridBagLayout gbLayout = null;
	private JPanel KSFpanel = null;
	
	public BSCPerspectivePanel(MBSCPerspective aPerspective) {
		super();
		try {
			setPerspective(aPerspective);
			setName(getPerspective().getName());
			setFocusable(true);
	    	Border border = BorderFactory.createLineBorder(Color.CYAN) ;
	    	setBorder(border);
	    	
	    	JPanel panel = new JPanel();
	    	panel.setBackground(Color.GRAY);
	    	
			
	    	label = new CLabel(getName());
	    	label.setUI(new VerticalLabelUI(false));
	    	panel.setBorder(getBorder());
	    	
	    	panel.add(label);
	    	add(panel,BorderLayout.WEST);
	    	
	    	KSFpanel = new JPanel();
	    	
			
			KSFpanel.setLayout(new GridLayout(3,1));
			for(MBSCKeySuccessFactor keySF : getKeySuccessFactors()) {
				CLabel label = new CLabel(keySF.getName());
				panel.add(label);
				KSFpanel.add(panel);
			}
			add(KSFpanel,BorderLayout.CENTER);
	    	invalidate();
		} catch (Exception e) {
			log.log(Level.SEVERE,"BSCPerspectivePanel",e);
		}
		
	}
	
	/**
	 * @param gbc
	 * @param keySF
	 */
	private void addKeySuccessFactor(GridBagConstraints gbc, MBSCKeySuccessFactor keySF) {
		if (keySF == null) return;
		
		
		JPanel panel = new JPanel();
		CLabel label = new CLabel(keySF.getName());
		panel.add(label);
		panel.setBorder(getBorder());
		gbLayout.setConstraints(panel, gbc);
		add(panel);
	}

	public List<MBSCKeySuccessFactor> getKeySuccessFactors() {
		return keySuccessFactors;
	}

	public void setKeySuccessFactors(List<MBSCKeySuccessFactor> keySuccessFactors) {
		this.keySuccessFactors = keySuccessFactors;
	}

	protected void loadKeySuccessFactors() {
		setKeySuccessFactors(MBSCKeySuccessFactor.load(getPerspective().getBSC_Perspective_ID()));
	}
	public MBSCPerspective getPerspective() {
		return perspective;
	}

	public void setPerspective(MBSCPerspective perspective) {
		this.perspective = perspective;
		if (perspective != null) {
			loadKeySuccessFactors();
		}
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

    public ActionListener[] getActionListeners() 
    {
        return (ActionListener[])(listenerList.getListeners(ActionListener.class));
    }	//	getActionListeners

	public void addActionListener(ActionListener l) 
    {
    	if (l != null)
    		listenerList.add(ActionListener.class, l);
    }	//	addActionListener

    public void removeActionListener(ActionListener l) 
    {
    	if (l != null)
    		listenerList.remove(ActionListener.class, l);
    }	//	removeActionListener

    @Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
//    	g.drawString(getName(), 5, 15);
//    	g.drawRect(1, 1, getSize().width - 10, getSize().height - 10);
    }
}
