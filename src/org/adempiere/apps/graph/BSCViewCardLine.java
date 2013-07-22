/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VComboBox;
import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCCardLine;
import org.compiere.model.MParameter;
import org.compiere.model.MPeriod;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCViewCardLine extends CPanel implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566997842038308824L;
	private static CLogger log = CLogger.getCLogger (BSCView.class);
	private int m_WindowNo = 0;
	private FormFrame m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private VComboBox cbCard = new VComboBox();
	private VComboBox cbCardLine = new VComboBox();
	private CButton bBack = new CButton();
	private MPeriod period = null;
	private MParameter m_Parameter = null;
	private MBSCCardLine cardLine = null;
	private MBSCCard card = null;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource() == cbCard && cbCard.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbCard.getSelectedItem();
			int BSC_Card_ID = knp.getKey(); 
			setCard(BSC_Card_ID);
			KeyNamePair[] pp = getBSCCardLine(BSC_Card_ID);			
			cbCardLine.setModel(new DefaultComboBoxModel<KeyNamePair>(pp));
			load(BSC_Card_ID,0);
		} else if (e.getSource() == cbCardLine && cbCardLine.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbCard.getSelectedItem();
			int BSC_Card_ID = knp.getKey(); 
			knp = (KeyNamePair) cbCardLine.getSelectedItem();
			int BSC_CardLine_ID = knp.getKey();
			setCardLine(BSC_CardLine_ID);
			load(BSC_Card_ID,BSC_CardLine_ID);
		} else if (e.getSource() instanceof BSCCardLineIndicator) {
			BSCCardLineIndicator ind = (BSCCardLineIndicator) e.getSource();
			MBSCCardLine cardLine = ind.getCardLine();
			setCardLine(cardLine.getBSC_CardLine_ID());
			load(cardLine.getBSC_Card_ID(),cardLine.getBSC_CardLine_ID());
			KeyNamePair[] pp = getBSCCardLine(cardLine.getBSC_Card_ID());			
			cbCardLine.setModel(new DefaultComboBoxModel<KeyNamePair>(pp));
			cbCardLine.setValue(cardLine.getBSC_CardLine_ID());
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
	@Override
	public void init(int WindowNo, FormFrame frame) {
		log.fine("BSCView");
		m_WindowNo = WindowNo;
		m_frame = frame;
		
		prepare();
		
		try {
			initLoadPanel();
			initMainPanel();
			confirmPanel.addActionListener(this);
			
			m_frame.getContentPane().add(loadPanel, BorderLayout.NORTH);
			m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			CScrollPane scroll =  new CScrollPane(mainPanel);
			m_frame.getContentPane().add(scroll, BorderLayout.CENTER);
			
		} catch(Exception e) {
			log.log(Level.SEVERE,"BSCView: ",e);
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

	private void prepare() {
		try {
			int BSC_Card_ID = Env.getContextAsInt(Env.getCtx(), "BSC_Card_ID");
			int BSC_CardLine_ID = Env.getContextAsInt(Env.getCtx(), "BSC_CardLine_ID");
			setCard(BSC_Card_ID);
			setCardLine(BSC_Card_ID);
		} catch(Exception e) {
			log.log(Level.SEVERE,"BSCViewCardLine: ",e);
		}
	}
	
	private void initLoadPanel() {
		loadPanel.add(initBBack());
		loadPanel.add(initCBCard());
		loadPanel.add(initCBCardLine());
	}
	
	private void initMainPanel() {
		mainPanel.setLayout(new GridBagLayout());
		load((card == null ? 0: card.getBSC_Card_ID()) , (cardLine == null ? 0: cardLine.getBSC_CardLine_ID()));
	}

	private CButton initBBack() {
		bBack = new CButton("<<");
		bBack.addActionListener(this);
		return bBack;
	}
	
	private CComboBox initCBCard() {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT BSC_Card_ID, Name FROM BSC_Card ORDER BY 2",
				"BSC_Card", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		cbCard = new VComboBox(pp);
		AutoCompletion.enable(cbCard);
		cbCard.addActionListener(this); 
		return cbCard;
	}
	
	private CComboBox initCBCardLine() {
		int BSC_Card_ID = (card == null ? 0 : card.getBSC_Card_ID());
		KeyNamePair[] pp = getBSCCardLine(BSC_Card_ID);
		cbCardLine = new VComboBox(pp);
		AutoCompletion.enable(cbCardLine);
		cbCardLine.addActionListener(this); 
		return cbCardLine;
	}
	
	private void load(int BSC_Card_ID, int BSC_CardLine_ID) {
		
		log.fine("BSC_Card_ID=" + BSC_Card_ID+" \n BSC_CardLine_ID=" + BSC_CardLine_ID);

		mainPanel.removeAll();
		
		if (BSC_Card_ID == 0 ) {
			return;
		}
		
		setCard(BSC_Card_ID);
		setCardLine(BSC_CardLine_ID);
		
		if (card != null && cardLine == null) {
			viewCard();
		} else if (card != null && cardLine != null) {
			viewCardLine();
		}
		
		return ;
	}
	
	private void viewCard() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		addCardIndicator(gbc);
		
		gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		addTempPanel(gbc);
		
		ArrayList<MBSCCardLine> cardLines = card.getCardLine();
		addCardLines(cardLines.toArray(new MBSCCardLine[cardLines.size()]), gbc);
		
		mainPanel.validate();
		mainPanel.repaint();
		mainPanel.updateUI();
	}
	
	/**
	 * @param cardLines
	 * @param gbc
	 */
	private void addCardLines(MBSCCardLine[] cardLines, GridBagConstraints gbc) {
		BSCLine tempLine = null;
		int i = 0;
		for(MBSCCardLine line: cardLines) {
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = i;
			tempLine = addTempLine(gbc);
			
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = i++;
			addCardLineIndicator(line,gbc);
		}
		
		if (tempLine != null) {
			tempLine.setEnd(true);
			tempLine.repaint();
		}
		
	}

	/**
	 * @param line
	 * @param gbc
	 */
	private void addCardLineIndicator(MBSCCardLine line, GridBagConstraints gbc) {
		if (line==null) {
			return;
		}
		BSCCardLineIndicator indicator = new BSCCardLineIndicator(line);
		indicator.addActionListener(this);
		mainPanel.add(indicator,gbc);
		return;
	}

	/**
	 * @param gbc
	 * @return
	 */
	private BSCLine addTempLine(GridBagConstraints gbc) {
		BSCLine tempLine = new BSCLine();
		mainPanel.add(tempLine,gbc);
		return tempLine;
	}

	/**
	 * @param gbc
	 */
	private void addTempPanel(GridBagConstraints gbc) {
		JPanel temp = new JPanel();
		temp.setPreferredSize(BSCIndicator.paneldimension);
		mainPanel.add(temp,gbc);
	}

	private void viewCardLine() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		addCardLineIndicator(cardLine,gbc);
		
		gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		addTempPanel(gbc);
		
		MBSCCardLine[] cardLines = cardLine.getLinkLines();
		addCardLines(cardLines, gbc);
		
		mainPanel.validate();
		mainPanel.repaint();
		mainPanel.updateUI();
	}
	
	private void addCardIndicator(GridBagConstraints gr) {
		BSCCardIndicator indicator = new BSCCardIndicator(card);
		indicator.addActionListener(this);
		mainPanel.add(indicator,gr);
	}
	
	private void setCardLine(int BSC_CardLine_ID) {
		if (BSC_CardLine_ID > 0) {
			cardLine = new MBSCCardLine(Env.getCtx(),BSC_CardLine_ID,null);
			if (card == null || card.getBSC_Card_ID() != cardLine.getBSC_Card_ID()) {
				setCard(cardLine.getBSC_Card_ID());
			}
		} else {
			cardLine = null;
		}
	}
	
	private void setCard(int BSC_Card_ID) {
		if (BSC_Card_ID > 0) {
			card = new MBSCCard(Env.getCtx(),BSC_Card_ID,null);
			period = card.getPeriod();
		} else {
			card = null;
		}
	}
	
	private KeyNamePair[] getBSCCardLine(int BSC_Card_ID) {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT BSC_CardLine_ID, Name FROM BSC_CardLine WHERE BSC_Card_ID = "+BSC_Card_ID+" ",
				"BSC_CardLine", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO );	//	all
//		sql =  "SELECT BSC_CardLine_ID, Name FROM BSC_CardLine WHERE BSC_Card_ID = "+BSC_Card_ID+" ";
		KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		return pp;
	}
}
