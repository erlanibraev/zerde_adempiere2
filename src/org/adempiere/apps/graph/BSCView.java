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
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JPanel;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.model.MParameter;
import org.compiere.model.MParameterLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MRole;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCView extends CPanel implements FormPanel, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676814519138213648L;
	private static CLogger log = CLogger.getCLogger (ViewPI.class);
	private int         m_WindowNo = 0;
	private FormFrame 	m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private CComboBox cbParameter = new CComboBox();
	private MPeriod period = null;
	private MParameter m_Parameter = null;
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource() instanceof BSCIndicator)	{
			BSCIndicator ind = (BSCIndicator) e.getSource(); 
			load(ind.getParameter().getBSC_Parameter_ID());
		} else if (e.getSource() == cbParameter && cbParameter.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbParameter.getSelectedItem();
			load(knp.getKey());
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
		try {
			initLoadPanel();
			initMainPanel();
			confirmPanel.addActionListener(this);
			
			m_frame.getContentPane().add(loadPanel, BorderLayout.NORTH);
			m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			
		} catch(Exception e) {
			log.log(Level.SEVERE,"BSCView: ",e);
		}
		
	}

	
	
	private void initLoadPanel() {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT BSC_Parameter_ID, Name FROM BSC_Parameter ORDER BY 2",
				"BSC_Parameter", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		cbParameter = new CComboBox(pp);
		AutoCompletion.enable(cbParameter);
		cbParameter.addActionListener(this); 
		loadPanel.add(cbParameter);
	}
	
	/**
	 * 
	 */
	private void initMainPanel() {
		m_Parameter = new MParameter(Env.getCtx(),1000000,null);
		period = m_Parameter.getPeriod();
		mainPanel.setLayout(new GridBagLayout());
		load((m_Parameter == null? 0: m_Parameter.getBSC_Parameter_ID()));
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
	
	private void sizeIt()
	{
		//	Frame
		m_frame.pack();
		//	Dimension size = m_frame.getPreferredSize();
		//	size.width = WINDOW_WIDTH;
		//	m_frame.setSize(size);
	}	//	size
	/**
	 * 	Load Workflow & Nodes
	 * 	@param AD_Workflow_ID ID
	 * 	@param readWrite if true nodes can be moved
	 */
	
	public void load (int BSC_Parameter_ID)
	{
		log.fine("BSC_Parameter_ID=" + BSC_Parameter_ID);
		if (BSC_Parameter_ID == 0)
			return;
		if (period == null && period.getC_Period_ID() == 0)
			return;
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		m_Parameter = new MParameter (Env.getCtx(), BSC_Parameter_ID, null);
		period = m_Parameter.getPeriod(); 
		m_Parameter.setPeriod(period);

		mainPanel.removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		addBSCIndicator(m_Parameter, gbc); // new GridBagConstraints(1, 1, 10, 10, 0.0, 0.0,GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0)
		
		for(int i = 1; i < 2; i++) {
			JPanel temp = new JPanel();
			temp.setPreferredSize(BSCIndicator.paneldimension);
			
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = i;
			gbc.gridy = 0;
			
			mainPanel.add(temp,gbc);
		}
		
		MParameterLine parameterLine = m_Parameter.getParameterLine(period); 
		Map<String,MParameter> pl = parameterLine.getParameters();
		
		int i = 1;
		
		for(String key: pl.keySet()) {

			BSCLine temp = new BSCLine();
			
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = i;
			
			mainPanel.add(temp,gbc);
			
			MParameter p = pl.get(key);
			p.setPeriod(period);
			 
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = i++;
			
			addBSCIndicator(p,gbc); // new GridBagConstraints(1, i++, 10, 10, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0)
			
//			for (int j = 0; j < nexts.length; j++)
//				centerPanel.add (new WFLine (nexts[j]), false);
		}
		
		mainPanel.validate();
		mainPanel.repaint();
		validate();
	}	//	load

	/**
	 * @param p
	 */
	private void addBSCIndicator(MParameter p, GridBagConstraints gr) {
		if (p==null) {
			return;
		}
		BSCIndicator indicator = new BSCIndicator(p);
		indicator.addActionListener(this);
		mainPanel.add(indicator,gr);
	}

}
