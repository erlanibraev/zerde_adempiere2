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
import java.util.LinkedHashMap;
import java.util.List;
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
import org.compiere.grid.ed.VString;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_BPM_Parameters;
import org.compiere.model.MBPMParameters;
import org.compiere.model.MColumn;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_BPM_Parameters;
import org.compiere.model.X_PFR_Calculation;
import org.compiere.pfr.QueriInterface;
import org.compiere.pfr.QueryDialog;
import org.compiere.pfr.QueryDialogValueRenderer;
import org.compiere.pfr.QueryValueEditor;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

public class DialogParameters extends CDialog implements ActionListener,
		ChangeListener, DataStatusListener, QueriInterface {	
	
	private static final long serialVersionUID = 7743491445006059549L;

	private int AD_Table_ID;
	private int Record_ID;
	
	private MColumn[] tableColumns;
	
	/** Index Table Name = 0		*/
	public static final int		INDEX_TABLE = 0;
	public static final String	KEY_TABLE = "INDEX_TABLE";
	/** Index ColumnName = 1		*/
	public static final int		INDEX_COLUMNNAME = 1;
	public static final String	KEY_COLUMNNAME = QueryDialog.KEY_COLUMNNAME;
	/** Index Operator = 2			*/
	public static final int		INDEX_OPERATOR = 2;
	public static final String	KEY_OPERATOR = QueryDialog.KEY_OPERATOR;
	/** Index Value = 3				*/
	public static final int		INDEX_VALUE = 3;
	public static final String	KEY_VALUE = QueryDialog.KEY_VALUE;
	/** Index Value2 = 4			*/
	public static final int		INDEX_VALUE2 = 4;
	public static final String	KEY_VALUE2 = QueryDialog.KEY_VALUE2;
	/** Index Line ID = 5	 		*/
	public static final int		INDEX_LINEID = 5;
	public static final String	KEY_LINEID = "INDEX_LINEID";
	
	/** IN	*/
	public static final String	IN = " IN ";
	/** IN - 8		*/
	public static final int		IN_INDEX = 9;
	
	LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
	
	private static CLogger log = CLogger.getCLogger(Find.class);
	private ValueNamePair[] columnValueNamePairs;
	private ValueNamePair[] tableNamePairs;
	
	private CPanel advancedPanel = new CPanel();
	public CComboBox 	columns = null;
	public CComboBox 	tables = null;
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
		new ValueNamePair (IN,						" IN ")		// 	9
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
					|| column == INDEX_OPERATOR
					|| column == INDEX_TABLE);
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
		loadLines();
		
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
		
		map.put(KEY_COLUMNNAME, INDEX_COLUMNNAME);
		map.put(KEY_OPERATOR, INDEX_OPERATOR);
		map.put(KEY_VALUE, INDEX_VALUE);
		map.put(KEY_VALUE2, INDEX_VALUE2);
		map.put(KEY_LINEID, INDEX_LINEID);
		
		initFindAdvanced();
	}
	
	/**
	 *  Init Find GridController
	 */
	private void initFindAdvanced()
	{
		log.config("");
		advancedTable.setModel(new DefaultTableModel(0, 6));
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

        // 0 = Tables
        MTable[] tablesList = getTables();
        ArrayList<ValueNamePair> it = new ArrayList<ValueNamePair>();
		for (int c = 0; c < tablesList.length; c++)
		{
			MTable table = tablesList[c];
			String tableName = table.getTableName();
			String header = table.getName();
			
			if (header == null || header.length() == 0)
					continue;
			ValueNamePair pp = new ValueNamePair(tableName, header);
			it.add(pp);
		}
		tableNamePairs = new ValueNamePair[it.size()];
		it.toArray(tableNamePairs);
		Arrays.sort(tableNamePairs);		//	sort alpha
		tables = new CComboBox(tableNamePairs);
		tables.addActionListener(this);
		
		TableColumn tc = advancedTable.getColumnModel().getColumn(INDEX_TABLE);
		tc.setPreferredWidth(120);
		FindCellEditor dce = new FindCellEditor(tables); 

		dce.addCellEditorListener(new CellEditorListener()
		{
			public void editingCanceled(ChangeEvent ce)
			{
			}
		 
			public void editingStopped(ChangeEvent ce)
			{
				int col = advancedTable.getSelectedColumn();
				int row = advancedTable.getSelectedRow();
				if (col == INDEX_TABLE && row >= 0)
				{
					columnValueNamePairs = loadColumns(MTable.getTable_ID(String.valueOf(advancedTable.getValueAt(row, col))));
					columns = new CComboBox(columnValueNamePairs);
					columns.addActionListener(DialogParameters.this);
					
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
				}
			}
		});
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), X_BPM_Parameters.COLUMNNAME_AD_Table2_ID));
		
		columnValueNamePairs = loadColumns(AD_Table_ID);
		columns = new CComboBox(columnValueNamePairs);
		columns.addActionListener(DialogParameters.this);
		
		tc = advancedTable.getColumnModel().getColumn(INDEX_COLUMNNAME);
		tc.setPreferredWidth(120);
		dce = new FindCellEditor(columns); 

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
		QueryValueEditor fve = new QueryValueEditor(this, false, map);		
		tc.setCellEditor(fve);
		tc.setPreferredWidth(120);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false, map)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue"));

		// 	3 = QueryValue2
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE2);
		tc.setPreferredWidth(120);
		fve = new QueryValueEditor(this, true, map);
		tc.setCellEditor(fve);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false, map)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue2"));
		
		// 4 = Line ID
		VString vs = new VString(Msg.translate(Env.getCtx(), "LineID"), true, true, true, 0, 0, "", "");  
		vs.setName(Msg.translate(Env.getCtx(), "LineID"));
		tc = advancedTable.getColumnModel().getColumn(INDEX_LINEID);	
		dce = new FindCellEditor(vs);
		tc.setCellEditor(dce);
		tc.setPreferredWidth(120);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), "LineID"));
				
		//user query
//		refreshUserQueries();		
	}
	
	private ValueNamePair[] loadColumns(int AD_Table_ID){
		
		ValueNamePair[] columnValueNamePairs;
		MTable table = new MTable(Env.getCtx(), AD_Table_ID, null);
		tableColumns =  table.getColumns(true);
		
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

		return columnValueNamePairs;
	}
	
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
		else if (e.getSource() == bSave)
			cmd_save();
		else if (e.getSource() == bDelete)
			cmd_delete(true);
		else if(e.getSource() == bIgnore)
			cmd_ignore();
		else if (e.getSource() == bRefresh)
			cmd_refresh(false);
	}	//	actionPerformed
	
	/**
	 *	New record
	 */
	private void cmd_new()
	{
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		model.addRow(new Object[] {null, null, OPERATORS[MQuery.EQUAL_INDEX], "", "", ""});		
		advancedTable.requestFocusInWindow();
	}	//	cmd_new
	
	/**
	 *	Save (Advanced)
	 */
	private boolean cmd_save()
	{
		advancedTable.stopEditor(true);
		MBPMParameters param = null;
		Object column = null;
		String value = "";
		String valueTable = "";
		StringBuilder errorLog = new StringBuilder();
		StringBuilder totalLog = new StringBuilder();
		int AD_Reference_ID = 0;
		boolean hasError = false;
		
		for (int row = 0; row < advancedTable.getRowCount(); row++)
		{
			column = advancedTable.getValueAt(row, INDEX_LINEID);
		
			value = column instanceof ValueNamePair ? 
				((ValueNamePair)column).getValue() : column.toString();
		
			if(value != null && !value.isEmpty())
				param = new MBPMParameters(Env.getCtx(), Integer.parseInt(value), null);
			else
				param = new MBPMParameters(Env.getCtx(), 0, null);
			
			// Table Name
			column = advancedTable.getValueAt(row, INDEX_TABLE);	
			
			if(column != null && !column.toString().isEmpty())
			{
				valueTable = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(valueTable != null)
				{			
					param.setAD_Table2_ID(MTable.getTable_ID(valueTable));
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(INDEX_TABLE, "required"));
			}
			
			// Column ColumnName
			column = advancedTable.getValueAt(row, INDEX_COLUMNNAME);	
			
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(value != null)
				{				
					param.setAD_Column_ID(MColumn.getColumn_ID(MTable.getTableName(Env.getCtx(), MTable.getTable_ID(valueTable)), value));
					param.setColumnName(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(INDEX_COLUMNNAME, "required"));
			}
			
			// Column Operator
			column = advancedTable.getValueAt(row, INDEX_OPERATOR);	
			
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(value != null)
				{
					param.setOperation(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(INDEX_OPERATOR, "required"));
			}
			
			//Value2 
			//if Operator = 'BETWEEN'
			if(value.equals(MQuery.BETWEEN))
			{
				// Column
				column = advancedTable.getValueAt(row, INDEX_VALUE2);

				if(column != null && !column.toString().isEmpty())
				{
					value = column instanceof ValueNamePair ? 
							((ValueNamePair)column).getValue() : column.toString();
					
					if(value != null)
					{	
						param.setValue2(value);
					}
				}
				else 
				{
					hasError = true;
					errorLog.append(valueCheck(INDEX_VALUE2, "required"));
				}
			}
			// Column Value
			column = advancedTable.getValueAt(row, INDEX_VALUE);
			
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
						
				if(value != null)
				{
					AD_Reference_ID = MColumn.get(Env.getCtx(), param.getAD_Column_ID()).getAD_Reference_ID();
					
					if(AD_Reference_ID == DisplayType.YesNo)
					{
						value = (value.equals("true") || value.equals("Y")) ? "Y" : "N";
					}
					
					param.setValue1(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(INDEX_VALUE, "required"));
			}
			
			if(!hasError)
			{
				param.setAD_Table_ID(AD_Table_ID);
				param.setRecord_ID(Record_ID);
				param.saveEx();
				
				advancedTable.setValueAt(param.get_ID(), row, INDEX_LINEID);
			}
			else
			{
				totalLog.append(errorLog).append("\n");
			}
			
			errorLog.setLength(0);
			hasError = false;
		}
		
		if(totalLog.length() == 0)
		{
			cmd_cancel();
			return true;
		}
		else
		{
			DialogAgreement.dialogOK(Msg.translate(Env.getCtx(), "Error"), totalLog.toString(), 0);
			return false;
		}
	}
	
	/**
	 *	Delete
	 */
	private void cmd_delete(boolean db)
	{		
		advancedTable.stopEditor(false);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int row = advancedTable.getSelectedRow();
		int deleteRows = 1;
		
		if(db)
		{			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ");
			sql.append(X_BPM_Parameters.Table_Name);
			
			String indexValue = advancedTable.getValueAt(row, INDEX_LINEID).toString();
			
			if(AD_Table_ID > 0 && Record_ID > 0)
			{
				sql.append(" WHERE ").append(I_BPM_Parameters.COLUMNNAME_AD_Table_ID).append(" = ").append(AD_Table_ID);
				sql.append(" AND ").append(I_BPM_Parameters.COLUMNNAME_Record_ID).append(" = ").append(Record_ID);
				
				if(indexValue != null && !indexValue.isEmpty())
					sql.append(" AND ").append(I_BPM_Parameters.COLUMNNAME_BPM_Parameters_ID).append(" = ").append(indexValue);
			}
			
			if(indexValue != null && !indexValue.isEmpty())
			{
				deleteRows = DB.executeUpdate(sql.toString(), null);
			}
		}
		
		if (row >= 0 && deleteRows > 0)
			model.removeRow(row);
		cmd_refresh(true);
		advancedTable.requestFocusInWindow();
		
	}	//	cmd_delete
	
	/**
	 *	Refresh
	 */
	private void cmd_refresh(boolean del)
	{
		advancedTable.stopEditor(false);
		
		if(!del){
			DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
			int rowCount = advancedTable.getRowCount()-1;
			for(int i = rowCount; i >= 0; i--){
				model.removeRow(i);
			}
			
			loadLines();
		}
		
		setStatusDB (advancedTable.getRowCount());
		statusBar.setStatusLine(Msg.translate(Env.getCtx(), "Success"));
	}	//	cmd_refresh
	
	/**
	 *	Ignore
	 */
	private void cmd_ignore()
	{
		int row = advancedTable.getSelectedRow();
		if(row == -1){
			DialogAgreement.dialogOK(Msg.translate(Env.getCtx(), "Error"), "Row not selected", 0);
			return;
		}
		
		String indexValue = advancedTable.getValueAt(row, INDEX_LINEID).toString();
		
		if(indexValue != null && !indexValue.isEmpty())
		{
			MBPMParameters param = new MBPMParameters(Env.getCtx(), Integer.parseInt(indexValue), null);
			
			DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
			
			String columnName = param.getColumnName();
			String header = Msg.translate(Env.getCtx(), columnName);
			
			String op = OPERATORS[0].getValue();
			for(ValueNamePair oper : OPERATORS){
				if(oper.getValue().equals(param.getOperation()))
					op = oper.getName();
			}
			
			model.setValueAt(new ValueNamePair(columnName, header), row, INDEX_COLUMNNAME);
			model.setValueAt(new ValueNamePair(param.getOperation(), op), row, INDEX_OPERATOR);
			model.setValueAt(new ValueNamePair(param.getValue1(), param.getValue1()), row, INDEX_VALUE);
			model.setValueAt(new ValueNamePair(param.getValue2(), param.getValue2()), row, INDEX_VALUE2);
		}
		else
		{
			cmd_delete(false);
		}
	}	//	cmd_ignore
	
	private int loadLines()
	{
		MBPMParameters[] params = MBPMParameters.getLines(Env.getCtx(), AD_Table_ID, Record_ID, null);
		
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int n = 0;
		for(MBPMParameters p : params)
		{
			String op = OPERATORS[0].getValue();
			for(ValueNamePair oper : OPERATORS){
				if(oper.getValue().equals(p.getOperation()))
					op = oper.getName();
			}
			
			String columnName = p.getColumnName();
			String header = Msg.translate(Env.getCtx(), columnName);
			if (header == null || header.length() == 0)
					continue;
			
			String tableName = MTable.getTableName(Env.getCtx(), p.getAD_Table2_ID());
			String headerTable = Msg.translate(Env.getCtx(), tableName);
			if (headerTable == null || headerTable.length() == 0)
					continue;
			
			model.addRow(new Object[] { 
							new ValueNamePair(tableName, headerTable),
							new ValueNamePair(columnName, header), 
							new ValueNamePair(p.getOperation(), op), 
							new ValueNamePair(p.getValue1(), p.getValue1()), 
							new ValueNamePair(p.getValue2(), p.getValue2()),
							p.getBPM_Parameters_ID()
						});
			n++;
		}
		
		return n;
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
		if(cmd_save())
		{
			advancedTable.stopEditor(false);
			log.info("");
			dispose();
		}
	}	//	cmd_ok
	
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
	
	private String valueCheck(int column, String message)
	 {
	  StringBuilder errors = new StringBuilder();
	  return errors.append(Msg.translate(Env.getCtx(), "Field").toLowerCase()).append(" \"").append(advancedTable.getColumnModel().getColumn(column).getHeaderValue()).append("\" ")
				.append(Msg.translate(Env.getCtx(), message)).append("\n").toString();
	 }
	
	/**
	 *	Display current count
	 *  @param currentCount String representation of current/total
	 */
	private void setStatusDB (int count)
	{
		statusBar.setStatusDB("#"+count);
	}	//	setDtatusDB
	
	private MTable[] getTables()
	{
		List<MTable> list = new Query(Env.getCtx(), I_AD_Table.Table_Name, "", null)
		.setOrderBy(I_AD_Table.COLUMNNAME_TableName)
		.setOnlyActiveRecords(true)
		.list();
		
		MTable[] retValue = new MTable[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
	
	@Override
	public void dataStatusChanged(DataStatusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
