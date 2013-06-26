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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JPanel;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VComboBox;
import org.compiere.model.MParameter;
import org.compiere.model.MParameterLine;
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
import org.compiere.util.Trx;
import org.compiere.process.ProcessCall;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCView extends CPanel implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676814519138213648L;
	private static CLogger log = CLogger.getCLogger (ViewPI.class);
	private int m_WindowNo = 0;
	private FormFrame m_frame;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private CPanel mainPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private VComboBox cbParameter = new VComboBox();
	private VComboBox cbPeriod = new VComboBox();
	private CButton bBack = new CButton();
	private MPeriod period = null;
	private MParameter m_Parameter = null;

	public MParameter getM_Parameter() {
		return m_Parameter;
	}

	public void setM_Parameter(MParameter m_Parameter) {
		this.m_Parameter = m_Parameter;
	}

	public MPeriod getPeriod() {
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

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
//			m_Parameter = new MParameter(Env.getCtx(),knp.getKey(),null);
			load(knp.getKey());
		} else if (e.getSource() == cbPeriod && cbPeriod.getSelectedItem() != null) {
			KeyNamePair knp = (KeyNamePair) cbPeriod.getSelectedItem();
			period = new MPeriod(Env.getCtx(),knp.getKey(),null);
			load(m_Parameter.getBSC_Parameter_ID());
		} else if (e.getSource() == bBack) {
			goBack();
			load(m_Parameter.getBSC_Parameter_ID());
		}
	}

	/**
	 * 
	 */
	private void goBack() {
		String sql = "select * from BSC_Parameter " +
				     "where BSC_Parameter_ID in (select BSC_Parameter_ID from BSC_ParameterLine " +
				     "where BSC_ParameterLine_ID in ( select BSC_ParameterLine_ID from BSC_Variable where bsc_parameter_id = ? ))";
		int BSC_Parameter_ID = m_Parameter.getBSC_Parameter_ID();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, BSC_Parameter_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m_Parameter = new MParameter(Env.getCtx(), rs, null);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "getFormulaByUnit", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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

	private void prepare() {
		try {
			int BSC_Parameter_ID = Env.getContextAsInt(Env.getCtx(), "BSC_Parameter_ID");
			if (BSC_Parameter_ID > 0) {
				m_Parameter = new MParameter(Env.getCtx(),BSC_Parameter_ID,null);
				period = m_Parameter.getPeriod();
			}
		} catch(Exception e) {
			log.log(Level.SEVERE,"BSCView: ",e);
		}
	}
	
	private CComboBox initCbParameter() {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT BSC_Parameter_ID, Name FROM BSC_Parameter ORDER BY 2",
				"BSC_Parameter", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		cbParameter = new VComboBox(pp);
		AutoCompletion.enable(cbParameter);
		cbParameter.addActionListener(this); 
		return cbParameter;
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
	
	private CButton initBBack() {
		bBack = new CButton("<<");
		bBack.addActionListener(this);
		return bBack;
	}
	
	private void initLoadPanel() {
		loadPanel.add(initBBack());
		loadPanel.add(initCbParameter());
		loadPanel.add(initCbPeriod());
	}
	
	/**
	 * 
	 */
	private void initMainPanel() {
//		m_Parameter = new MParameter(Env.getCtx(),1000000,null);
//		period = m_Parameter.getPeriod();
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
		m_Parameter = new MParameter (Env.getCtx(), BSC_Parameter_ID, null);
		cbParameter.setValue(BSC_Parameter_ID);
		
		if (period == null || period.getC_Period_ID() == 0) {
			period = m_Parameter.getPeriod();
		}	
		
		m_Parameter.setPeriod(period);

		mainPanel.removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		addBSCIndicator(m_Parameter, gbc); // new GridBagConstraints(1, 1, 10, 10, 0.0, 0.0,GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0)
		
		JPanel temp = new JPanel();
		temp.setPreferredSize(BSCIndicator.paneldimension);
			
		gbc = new GridBagConstraints();
			
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
			
		mainPanel.add(temp,gbc);
		
		MParameterLine parameterLine = m_Parameter.getParameterLine(period); 
		Map<String,MParameter> pl = parameterLine.getParameters();
		
		int i = 1;
		BSCLine tempLine = null;
		for(String key: pl.keySet()) {

			tempLine = new BSCLine();
			
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = i;
			
			mainPanel.add(tempLine,gbc);
			
			MParameter p = pl.get(key);
			p.setPeriod(period);
			 
			gbc = new GridBagConstraints();
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = i++;
			
			addBSCIndicator(p,gbc); // new GridBagConstraints(1, i++, 10, 10, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0)
			
		}
		
		if (tempLine != null) {
			tempLine.setEnd(true);
			tempLine.repaint();
		}
		
		
		mainPanel.validate();
		mainPanel.repaint();
		mainPanel.updateUI();

//		validate();
//		repaint();
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
