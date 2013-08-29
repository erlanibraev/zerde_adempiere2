/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JButton;

import layout.TableLayout;

import org.adempiere.apps.graph.BSCViewDashboard.ValueComparator;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VComboBox;
import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCDashboard;
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
public class BSCViewDashboard1 extends CPanel  implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3965861742626702550L;
	private static CLogger log = CLogger.getCLogger (BSCViewDashboard1.class);
	private int m_WindowNo = 0;
	private FormFrame m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private CPanel dashboardView = new CPanel();
	private BSCCardPanel panel = new BSCCardPanel(null);  
	private VComboBox cbPeriod = new VComboBox();
	private ArrayList<JButton> buttons = new ArrayList<JButton>(); 
	private ArrayList<MBSCDashboard> cards = new ArrayList<MBSCDashboard>(); 
	private MPeriod period = null;
	private int currentCard = -1;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource() == cbPeriod && cbPeriod.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbPeriod.getSelectedItem();
			setPeriod(new MPeriod(Env.getCtx(),knp.getKey(),null));
			reDrawPanel();
		} else if (e.getSource() instanceof JButton){
			for(int i = 0 ; i < getButtons().size(); i++) {
				if (e.getSource() == getButtons().get(i)) {
					currentCard = i;
					reDrawPanel();
				}
			}
		}
	}

	/**
	 * 
	 */
	private void reDrawPanel() {
		MBSCCard card = (currentCard > -1 ? cards.get(currentCard).getCard() : null); 
		panel.setCard(card);
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
		
		m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		confirmPanel.addActionListener(this);
		m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
	
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

	private void initLoadPanel() {
		loadPanel.add(initCbPeriod());
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
	
	private void initMainPanel() {
		initDashboardView();
		mainPanel.add(dashboardView,BorderLayout.LINE_START);
		
		CScrollPane scroll =  new CScrollPane(panel);
		Dimension d = new Dimension(850,400);
		scroll.setSize(d);
		scroll.setPreferredSize(d);
		scroll.setMinimumSize(d);
		
		mainPanel.add(scroll,BorderLayout.LINE_END);
	}

	/**
	 * 
	 */
	private void initDashboardView() {
		GridBagLayout gbLayout = new GridBagLayout(); 
		dashboardView.setLayout(gbLayout);
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		for(JButton button : getButtons()) {
			gbLayout.setConstraints(button, gbc);
			dashboardView.add(button);
			button.addActionListener(this);
		}
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}

	private void initCards() {

		MBSCDashboard[] listDB = MBSCDashboard.getDashboard((period == null ? 0 : period.getC_Period_ID()));
		
		ValueComparator bvc =  new ValueComparator(listDB);

		TreeMap<MBSCDashboard,MBSCCard> dbmap = new TreeMap<MBSCDashboard,MBSCCard>(bvc);  
		for(MBSCDashboard item : listDB) {
			dbmap.put(item, item.getCard());
		}
		cards.clear();
		buttons.clear();
		
		for(MBSCDashboard item: dbmap.keySet()) {
			cards.add(item);
			JButton button = new JButton("<html>" + item.getHR_Job().getName() + "<br>("+item.getC_BPartner().getName()+")</html>");
			buttons.add(button);
		}
	}
	
	public MPeriod getPeriod() {
		return period;
	}

	public void setPeriod(MPeriod period) {
		if (period != null) {
			for(MBSCDashboard item: cards) {
				item.setPeriod(period);
			}
		}
		this.period = period;
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
}
