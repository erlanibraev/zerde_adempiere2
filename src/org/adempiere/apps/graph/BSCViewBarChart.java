/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.logging.Level;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoBSCParameter;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.MBSCCard;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MParameter;
import org.compiere.model.MRole;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CField;
import org.compiere.swing.CFieldEditor;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCViewBarChart extends CPanel  implements FormPanel, ActionListener, VetoableChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3212565035042794285L;
	private static CLogger log = CLogger.getCLogger (BSCView.class);
	public static Dimension dimension = new Dimension(900,700);

	private int m_WindowNo = 0;
	private FormFrame m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private BSCBarChart barChart = new BSCBarChart(null);
	private VLookup lParameter = new VLookup("BSC_Parameter_ID", true, true, true, null);
	private VLookup lCard = new VLookup("BSC_Card_ID",false,true,true,null);
	private VComboBox cbParameter = null;
	
	private int BSC_Card_ID = 0;
	private MBSCCard card = null;

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
			log.log(Level.SEVERE,"BSCViewBarChart: ",e);
		}
	}

	/**
	 * 
	 */
	private void initMainPanel() {
		Dimension mySize = new Dimension(BSCBarChart.indicatordimension.width+5, BSCBarChart.indicatordimension.height+10);
		mainPanel.setPreferredSize(mySize);
		mainPanel.add(initBarChart());
	}
	
	private BSCBarChart initBarChart() {
		if(card != null) {
			if(getBSC_Parameter_ID() > 0) {
				barChart.setBSC_Parameter_ID(getBSC_Parameter_ID());
			} else if (getCard().getBSC_Parameter_ID() > 0) {
				setBSC_Parameter_ID(getCard().getBSC_Parameter_ID());
			}
		}
		return barChart;
	}

	/**
	 * 
	 */
	private void initLoadPanel() {
		loadPanel.add(initCard());
//		loadPanel.add(initlParameter());
		loadPanel.add(initCbParameter());
	}

	private Component initCard() {
		int AD_Column_ID = MColumn.getColumn_ID("BSC_Card", "BSC_Card_ID") ;
		MLookup lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		lCard = new VLookup("BSC_Card_ID", true, false, true, lookup);
		lCard.addVetoableChangeListener(this);
		lCard.addActionListener(this);
		return lCard;
	}

	private VLookup initlParameter() {
		int AD_Column_ID = MColumn.getColumn_ID("BSC_Parameter", "BSC_Parameter_ID") ;
		MLookup lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		lParameter = new VLookup("BSC_Parameter_ID", true, false, true, lookup);
		lParameter.addVetoableChangeListener(this);
		lParameter.addActionListener(this);
		return lParameter;
	}
	
	private VComboBox initCbParameter(){
		cbParameter = new VComboBox(getKeyNamePair());
		cbParameter.addActionListener(this);
		AutoCompletion.enable(cbParameter);
		return cbParameter;
	}
	
	private KeyNamePair[] getKeyNamePair() {
		String WhereClause = "";
		if(getCard() != null) {
			WhereClause = "WHERE BSC_Parameter_ID in (SELECT BSC_Parameter_Out_ID FROM BSC_CardLine WHERE BSC_Card_ID = "+getCard().getBSC_Card_ID()+")";
		}
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT BSC_Parameter_ID, Name FROM BSC_Parameter "+WhereClause+" ORDER BY 2",
				"BSC_Parameter", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] result = DB.getKeyNamePairs(sql, true);
		return result;
	}

	private void prepare() {
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource() == cbParameter) {
			int BSC_Parameter_ID = (cbParameter.getValue()==null ? 0 : (Integer)cbParameter.getValue());
			initBarChart();
		} else if (e.getSource() == lCard) {
			setBSC_Card_ID((Integer)lCard.getValue());
			createFilter();
			initBarChart();
		}
	}

	private void createFilter() {
		// TODO Auto-generated method stub
		loadPanel.remove(cbParameter);
		loadPanel.add(initCbParameter());
		loadPanel.validate();
		loadPanel.repaint();
		loadPanel.updateUI();	
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
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

	/* (non-Javadoc)
	 * @see java.beans.VetoableChangeListener#vetoableChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void vetoableChange(PropertyChangeEvent evt)	throws PropertyVetoException {
		// TODO Auto-generated method stub
		
		if(evt.getSource() == lParameter) {
			Integer BSC_Parameter_ID = (Integer) evt.getNewValue();
			if (BSC_Parameter_ID.intValue() > 0) {
				setBSC_Parameter_ID(BSC_Parameter_ID.intValue());
			}
		} else if (evt.getSource() == lCard) {
			Integer BSC_Card_ID = (Integer) evt.getNewValue();
			if (BSC_Card_ID.intValue() > 0) {
				setBSC_Card_ID(BSC_Card_ID.intValue());
				initBarChart();
			}
		}
	}

	public void setBSC_Parameter_ID(int BSC_Parameter_ID) {
		cbParameter.setValue(new Integer(BSC_Parameter_ID));
		barChart.setBSC_Parameter_ID(BSC_Parameter_ID);
	}
	
	public int getBSC_Parameter_ID() {
		int BSC_Parameter_ID = 0;
		Integer bpid = (Integer) cbParameter.getValue();
		if (bpid != null) {
			BSC_Parameter_ID = bpid.intValue();
		}
		return BSC_Parameter_ID;
	}

	public int getBSC_Card_ID() {
		return BSC_Card_ID;
	}

	public void setBSC_Card_ID(int bSC_Card_ID) {
		BSC_Card_ID = bSC_Card_ID;
		if (BSC_Card_ID > 0 && (card == null || card.getBSC_Card_ID() != BSC_Card_ID)) {
			setCard(new MBSCCard(Env.getCtx(),BSC_Card_ID,null));
			createFilter();
			lCard.setValue(new Integer(BSC_Card_ID));
			initBarChart();
		}
	}


	public MBSCCard getCard() {
		return card;
	}

	public void setCard(MBSCCard card) {
		this.card = card;
		if(card != null) {
			if (card.getBSC_Card_ID() != BSC_Card_ID) {
				BSC_Card_ID = card.getBSC_Card_ID();
			}
		} else {
			BSC_Card_ID = 0;
		}
	}
}
