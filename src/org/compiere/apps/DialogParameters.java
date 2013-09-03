package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.compiere.apps.search.Find;
import org.compiere.apps.search.FindCellEditor;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.MColumn;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.X_PFR_Calculation;
import org.compiere.pfr.QueriInterface;
import org.compiere.pfr.QueryDialogValueRenderer;
import org.compiere.pfr.QueryValueEditor;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

public class DialogParameters extends CDialog implements ActionListener,
		ChangeListener, DataStatusListener, QueriInterface {	
	
	private static final long serialVersionUID = 7743491445006059549L;

	private int AD_Table_ID;
	private int Record_ID;
	
	private MColumn[] tableColumns;
	
	/** Index ColumnName = 3		*/
	public static final int		INDEX_COLUMNNAME = 0;
	/** Index Operator = 4			*/
	public static final int		INDEX_OPERATOR = 1;
	/** Index Value = 5				*/
	public static final int		INDEX_VALUE = 2;
	/** Index Value2 = 6			*/
	public static final int		INDEX_VALUE2 = 3;
	
	private static CLogger log = CLogger.getCLogger(Find.class);
	private ValueNamePair[] columnValueNamePairs;
	
	private CPanel advancedPanel = new CPanel();
	public CComboBox 	columns = null;
	public CComboBox 	operators = null;
	
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private StatusBar statusBar = new StatusBar();
	
	private ConfirmPanel confirmPanelA = new ConfirmPanel(true, true, false, false, false, false, true);
	private BorderLayout advancedLayout = new BorderLayout();
	private JToolBar toolBar = new JToolBar();
	private JScrollPane advancedScrollPane = new JScrollPane();
	
	private CButton bIgnore = new CButton();	
	private CButton bSave = new CButton();
	private CButton bNew = new CButton();
	private CButton bDelete = new CButton();
	private CButton bRefresh = new CButton();
	
	/**	Operators for Strings				*/
	public static final ValueNamePair[]	OPERATORS = new ValueNamePair[] {
		new ValueNamePair (MQuery.EQUAL,			" = "),		//	0
		new ValueNamePair (MQuery.NOT_EQUAL,		" != "),
		new ValueNamePair (MQuery.LIKE,				" ~ "),
		new ValueNamePair (MQuery.NOT_LIKE,			" !~ "),
		new ValueNamePair (MQuery.GREATER,			" > "),
		new ValueNamePair (MQuery.GREATER_EQUAL,	" >= "),	//	5
		new ValueNamePair (MQuery.LESS,				" < "),
		new ValueNamePair (MQuery.LESS_EQUAL,		" <= "),
		new ValueNamePair (MQuery.BETWEEN,			" >-< "),	//	8
	};
	
	private class ProxyRenderer implements TableCellRenderer
	{
		/**
		 * Creates a Find.ProxyRenderer.
		 */
		public ProxyRenderer(TableCellRenderer renderer)
		{
			this.m_renderer = renderer;
		}
	        
		/** The renderer. */
		private TableCellRenderer m_renderer;
	       
		/**
		 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent(final JTable table,
				Object value, boolean isSelected, boolean hasFocus, final int row, final int col)
		{
			Component comp = m_renderer.getTableCellRendererComponent(table,
					value, isSelected, hasFocus, row, col);
			if (hasFocus && table.isCellEditable(row, col))
				table.editCellAt(row, col);
			return comp;
		}
	}	// ProxyRenderer
	
	private CTable advancedTable = new CTable() {

		private static final long serialVersionUID = -6201749159307529032L;

		public boolean isCellEditable(int row, int column)
		{
			boolean editable = ( column == INDEX_COLUMNNAME
					|| column == INDEX_OPERATOR);
			if (!editable && row >= 0)
			{
				String columnName = null;
				Object value =
					getModel().getValueAt(row, INDEX_COLUMNNAME);
				if (value != null) 
				{
					if (value instanceof ValueNamePair)
						columnName = ((ValueNamePair)value).getValue();
					else
						columnName = value.toString();
				}
			    
				//  Create Editor
				editable = getTargetMField(columnName) != null;
			}
			
			if (row == 0 )
				editable = false;
			
			return editable;
		}
		
	    public void columnMoved(TableColumnModelEvent e) {
	        if (isEditing()) {
	            cellEditor.stopCellEditing();
	        }
	        super.columnMoved(e);
	    }

	    public void columnMarginChanged(ChangeEvent e) {
	        if (isEditing()) {
	            cellEditor.stopCellEditing();
	        }
	        super.columnMarginChanged(e);
	    }

	};
	
	public DialogParameters(Frame frame, int AD_Table_ID, int Record_ID)
	{
		super(frame, Msg.getMsg(Env.getCtx(), "QueryDialog"), true);
		
		this.AD_Table_ID = AD_Table_ID;
		this.Record_ID = Record_ID;
		
		init();
		
		AEnv.showCenterWindow(frame, this);		
	}
	
	private void init()
	{
		bIgnore.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Ignore24.gif")));
		bIgnore.setMargin(new Insets(2, 2, 2, 2));
		bIgnore.setToolTipText(Msg.getMsg(Env.getCtx(),"Ignore"));
		bIgnore.addActionListener(this);
		bSave.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Save24.gif")));
		bSave.setMargin(new Insets(2, 2, 2, 2));
		bSave.setToolTipText(Msg.getMsg(Env.getCtx(),"Save"));
		bSave.addActionListener(this);
		bNew.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/New24.gif")));
		bNew.setMargin(new Insets(2, 2, 2, 2));
		bNew.setToolTipText(Msg.getMsg(Env.getCtx(),"New"));
		bNew.addActionListener(this);
		bDelete.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Delete24.gif")));
		bDelete.setMargin(new Insets(2, 2, 2, 2));
		bDelete.setToolTipText(Msg.getMsg(Env.getCtx(),"Delete"));
		bDelete.addActionListener(this);
		bRefresh.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Refresh24.gif")));
		bRefresh.setMargin(new Insets(2, 2, 2, 2));
		bRefresh.setToolTipText(Msg.getMsg(Env.getCtx(),"Refresh"));
		bRefresh.addActionListener(this);
		toolBar.add(bIgnore, null);
		toolBar.addSeparator();
		toolBar.add(bNew, null);
		toolBar.addSeparator();
		toolBar.add(bDelete, null);
		toolBar.addSeparator();
		toolBar.add(bSave, null);
		toolBar.addSeparator();
		toolBar.add(bRefresh, null);
		
		southPanel.setLayout(southLayout);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		advancedScrollPane.getViewport().add(advancedTable, null);
		advancedPanel.setLayout(advancedLayout);
		advancedPanel.add(toolBar, BorderLayout.NORTH);
		advancedPanel.add(confirmPanelA, BorderLayout.SOUTH);
		advancedPanel.add(advancedScrollPane, BorderLayout.CENTER);
		
		confirmPanelA.getCancelButton().addActionListener(this);
		confirmPanelA.getOKButton().addActionListener(this);
		
		this.getContentPane().add(advancedPanel, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cmd_cancel();
			}
		});
		
		initFindAdvanced();
	}
	
	/**
	 *  Init Find GridController
	 */
	private void initFindAdvanced()
	{
		log.config("");
		advancedTable.setModel(new DefaultTableModel(0, 4));
		advancedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		advancedTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		advancedTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		TableCellRenderer renderer = new ProxyRenderer(advancedTable.getDefaultRenderer(Object.class));
		advancedTable.setDefaultRenderer(Object.class, renderer);
		
		InputMap im = advancedTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
		final Action tabAction = advancedTable.getActionMap().get(im.get(tab));
		Action tabActionWrapper = new AbstractAction()
        {
			private static final long serialVersionUID = -6868476640719619801L;

            public void actionPerformed(ActionEvent e)
            {
            	tabAction.actionPerformed(e);
            	
                JTable table = (JTable)e.getSource();
                table.requestFocusInWindow();
            }
        };
        advancedTable.getActionMap().put(im.get(tab), tabActionWrapper);
        KeyStroke shiftTab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK);
        final Action shiftTabAction = advancedTable.getActionMap().get(im.get(shiftTab));
		Action shiftTabActionWrapper = new AbstractAction()
        {
			private static final long serialVersionUID = 5493691483070046620L;

            public void actionPerformed(ActionEvent e)
            {
            	shiftTabAction.actionPerformed(e);
            	
                JTable table = (JTable)e.getSource();
                table.requestFocusInWindow();
            }
        };
        advancedTable.getActionMap().put(im.get(shiftTab), shiftTabActionWrapper);

		MTable table = new MTable(Env.getCtx(), AD_Table_ID, null);
		tableColumns =  table.getColumns(true);
		
//    	0 = Columns
		ArrayList<ValueNamePair> items = new ArrayList<ValueNamePair>();
		for (int c = 0; c < tableColumns.length; c++)
		{
			MColumn column = tableColumns[c];
			String columnName = column.getColumnName();
			String header = columnName;
			
			header = Msg.translate(Env.getCtx(), columnName);
			if (header == null || header.length() == 0)
					continue;
			if (column.isKey())
				header += (" (ID)");
			ValueNamePair pp = new ValueNamePair(columnName, header);
			items.add(pp);
		}
		columnValueNamePairs = new ValueNamePair[items.size()];
		items.toArray(columnValueNamePairs);
		Arrays.sort(columnValueNamePairs);		//	sort alpha
		columns = new CComboBox(columnValueNamePairs);
		columns.addActionListener(this);
		
		TableColumn tc = advancedTable.getColumnModel().getColumn(INDEX_COLUMNNAME);
		tc.setPreferredWidth(120);
		FindCellEditor dce = new FindCellEditor(columns); 

		dce.addCellEditorListener(new CellEditorListener()
		{
			public void editingCanceled(ChangeEvent ce)
			{
			}
		 
			public void editingStopped(ChangeEvent ce)
			{
				int col = advancedTable.getSelectedColumn();
				int row = advancedTable.getSelectedRow();
				if (col == INDEX_COLUMNNAME && row >= 0)
				{
					advancedTable.setValueAt(null, row, INDEX_VALUE);
					advancedTable.setValueAt(null, row, INDEX_VALUE2);
				}
			}
		});
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), X_PFR_Calculation.COLUMNNAME_AD_Column_ID));
		
		//	1 = Operators
		operators = new CComboBox(OPERATORS);
		tc = advancedTable.getColumnModel().getColumn(INDEX_OPERATOR);
		tc.setPreferredWidth(55);
		dce = new FindCellEditor(operators);
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "Operator"));

		// 	2 = QueryValue
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE);
		QueryValueEditor fve = new QueryValueEditor(this, false);		
		tc.setCellEditor(fve);
		tc.setPreferredWidth(120);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue"));

		// 	3 = QueryValue2
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE2);
		tc.setPreferredWidth(120);
		fve = new QueryValueEditor(this, true);
		tc.setCellEditor(fve);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue2"));
				
		//user query
//		refreshUserQueries();		
	}
	
	/**
	 *	New record
	 */
	private void cmd_new()
	{
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		model.addRow(new Object[] {"", null, "", ""});		
		advancedTable.requestFocusInWindow();
	}	//	cmd_new
	
	/**************************************************************************
	 *	Action Listener
	 *  @param e ActionEvent
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand());
		//
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			cmd_cancel();
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			cmd_ok();
		else if (e.getSource() == bNew)
			cmd_new();
/*		else if (e.getSource() == bSave)
			cmd_save();
		else if (e.getSource() == bDelete)
			cmd_delete(true);
		else if(e.getSource() == bIgnore)
			cmd_ignore();
		else if (e.getSource() == bRefresh)
			cmd_refresh(false);*/
	}	//	actionPerformed
	
	public MColumn getTargetMField (String columnName)
	{
		if (columnName == null)
			return null;
		for (int c = 0; c < tableColumns.length; c++)
		{
			MColumn tableColumn = tableColumns[c];
			if (columnName.equals(tableColumn.getColumnName()))
				return tableColumn;
		}
		return null;
	}
	
	/**
	 *	Cancel Button pressed
	 */
	private void cmd_cancel()
	{
		advancedTable.stopEditor(false);
		log.info("");
		dispose();
	}	//	cmd_ok
	
	/**
	 *	Cancel Button pressed
	 */
	private void cmd_ok()
	{
//		if(cmd_save())
		{
			advancedTable.stopEditor(false);
			log.info("");
			dispose();
		}
	}	//	cmd_ok
	
	@Override
	public void dataStatusChanged(DataStatusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
