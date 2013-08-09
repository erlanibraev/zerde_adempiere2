/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import layout.TableLayout;

import org.adempiere.apps.graph.BSCPerspectivePanel.ValueComparator;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VComboBox;
import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCDashboard;
import org.compiere.model.MBSCKeySuccessFactor;
import org.compiere.model.MBSCPerspective;
import org.compiere.model.MPeriod;
import org.compiere.model.MRole;
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
public class BSCViewDashboard extends CPanel implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -616065129301278550L;
	private static CLogger log = CLogger.getCLogger (BSCView.class);
	private int m_WindowNo = 0;
	private FormFrame m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private VComboBox cbPeriod = new VComboBox();
	
	private ArrayList<BSCCardPanel> panels = new ArrayList<BSCCardPanel>();  
	private Dimension mainPanelDimension = null;
	
	private ArrayList<MBSCCard> cards = new ArrayList<MBSCCard>();
	private MPeriod period = null;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource() == cbPeriod && cbPeriod.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbPeriod.getSelectedItem();
			period = new MPeriod(Env.getCtx(),knp.getKey(),null);
			initCards();
			initMainPanel();
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
	@Override
	public void init(int WindowNo, FormFrame frame) {
		log.fine(this.getName());
		m_WindowNo = WindowNo;
		m_frame = frame;
		m_frame.setSize(1200, 600);
		m_frame.setPreferredSize(new Dimension(1200,600));
		m_frame.setMinimumSize(new Dimension(1200,600));
		initCards();
		
		initLoadPanel();
		initMainPanel();
		
		m_frame.getContentPane().add(loadPanel, BorderLayout.NORTH);
		
		CScrollPane scroll =  new CScrollPane(mainPanel);
		m_frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		confirmPanel.addActionListener(this);
		m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		
	}

	/**
	 * 
	 */
	private void initCards() {

		if (period == null) {
			return;
		}
		
		MBSCDashboard[] listDB = MBSCDashboard.getDashboard(period.getC_Period_ID());
		
		ValueComparator bvc =  new ValueComparator(listDB);

		TreeMap<MBSCDashboard,MBSCCard> dbmap = new TreeMap<MBSCDashboard,MBSCCard>(bvc);  
		for(MBSCDashboard item : listDB) {
			dbmap.put(item, item.getCard());
		}
		
		
		cards.clear();
		
		for(MBSCDashboard item: dbmap.keySet()) {
			if(item.getCard() != null) {
				cards.add(item.getCard());
			}
		}
//		cards.add(new MBSCCard(Env.getCtx(),1000000,null));
//		cards.add(new MBSCCard(Env.getCtx(),1000001,null));
//		cards.add(new MBSCCard(Env.getCtx(),1000002,null));
	}

	class ValueComparator implements Comparator<MBSCDashboard> {

		MBSCDashboard[] base;
	    public ValueComparator(MBSCDashboard[] base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(MBSCDashboard a, MBSCDashboard b) {
	    	if (a != null && b != null) {
	    		if (a.getNumberOfRuns() < b.getNumberOfRuns())
	    			return -1;
	    		else if (a.getNumberOfRuns() > b.getNumberOfRuns())
	    			return 1;
	    		else
	    			return 0;
	    	} else {
	    		return 0;
	    	}
	    }
	}

	/**
	 * 
	 */
	private void initLoadPanel() {
		loadPanel.add(initCbPeriod());
	}

	/**
	 * 
	 */
	private void initMainPanel() {
		mainPanel.removeAll();
		initPanels();
		double[][] tableSize = getMainPanetTableSize();
		TableLayout tableLayout = new TableLayout(tableSize);
		mainPanel.setLayout(tableLayout);
		mainPanel.setSize(mainPanelDimension);
		mainPanel.setPreferredSize(mainPanelDimension);
		mainPanel.setMaximumSize(mainPanelDimension);
		mainPanel.setMinimumSize(mainPanelDimension);
		for(int i = 0; i < panels.size(); i ++){
			BSCCardPanel panel = panels.get(i);
			int j = i % 2;
			String pos = Integer.toString(j) + "," +Integer.toString(i / 2);
			mainPanel.add(panel, pos);
		}
		
		repaint();
	}

	/**
	 * @return
	 */
	private double[][] getMainPanetTableSize() {
		int size = panels.size() / 2 + 1;
		double[][] result = new double[2][size];
		
		int width = 0;
		int height = 0;
		for(int i = 0; i < panels.size(); i++) {
			if(width < panels.get(i).getWidth() ) {
				width = panels.get(i).getWidth();
			}
			if(height < panels.get(i).getHeight() ) {
				height = panels.get(i).getHeight();
			}
		}
		for(int i = 0 ; i < size; i++) {
			result[0][i] = width;
			result[1][i] = height;
		}
		
		mainPanelDimension = new Dimension(width * 2, height * size);
		
		return result.clone();
	}

	/**
	 * 
	 */
	private void initPanels() {
		for(MBSCCard card:cards) {
			panels.add(new BSCCardPanel(card));
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

	private CComboBox initCbPeriod() {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT C_Period_ID, Name FROM C_Period ORDER BY 2",
				"C_Period", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] pp1 = DB.getKeyNamePairs(sql, true);
		cbPeriod = new VComboBox(pp1);
		AutoCompletion.enable(cbPeriod);
		cbPeriod.addActionListener(this);
		return cbPeriod;
	}
	
}
