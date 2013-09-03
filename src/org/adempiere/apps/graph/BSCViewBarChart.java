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
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MParameter;
import org.compiere.model.MRole;
import org.compiere.swing.CField;
import org.compiere.swing.CFieldEditor;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

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
		mainPanel.setPreferredSize(BSCBarChart.indicatordimension);
		mainPanel.add(barChart);
	}

	/**
	 * 
	 */
	private void initLoadPanel() {
		loadPanel.add(initlParameter());
	}

	private VLookup initlParameter() {
		int AD_Column_ID = MColumn.getColumn_ID("BSC_Parameter", "BSC_Parameter_ID") ;
		MLookup lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		lParameter = new VLookup("BSC_Parameter_ID", true, false, true, lookup);
		lParameter.addVetoableChangeListener(this);
		lParameter.addActionListener(this);
		return lParameter;
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
		else if (e.getSource() == lParameter) {
			int BSC_Parameter_ID = (Integer)lParameter.getValue();
			barChart.setBSC_Parameter_ID(BSC_Parameter_ID);
		}
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
		Integer BSC_Parameter_ID = (Integer) evt.getNewValue();
		if (BSC_Parameter_ID.intValue() > 0) {
			setBSC_Parameter_ID(BSC_Parameter_ID.intValue());
		}
	}

	public void setBSC_Parameter_ID(int BSC_Parameter_ID) {
		barChart.setBSC_Parameter_ID(BSC_Parameter_ID);
		lParameter.setValue(new Integer(BSC_Parameter_ID));
	}
	
	public int getBSC_Parameter_ID() {
		int BSC_Parameter_ID = 0;
		Integer bpid = (Integer) lParameter.getValue();
		if (bpid != null) {
			BSC_Parameter_ID = bpid.intValue();
		}
		return BSC_Parameter_ID;
	}
}
