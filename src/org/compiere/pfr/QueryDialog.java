package org.compiere.pfr;

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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.DialogAgreement;
import org.compiere.apps.StatusBar;
import org.compiere.apps.search.Find;
import org.compiere.apps.search.FindCellEditor;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VString;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.MColumn;
import org.compiere.model.MPFRCalculation;
import org.compiere.model.MPFRWhereClause;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.X_PFR_Calculation;
import org.compiere.model.X_PFR_WhereClause;
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

public class QueryDialog extends CDialog implements ActionListener, ChangeListener, DataStatusListener, QueriInterface
{
	public QueryDialog(Frame frame, int AD_Table_ID, int PFR_Calculation_ID)
	{
		super(frame, Msg.getMsg(Env.getCtx(), "QueryDialog"), true);
		
		this.AD_Table_ID = AD_Table_ID;
		this.PFR_Calculation_ID = PFR_Calculation_ID;
		init();
		
		loadLines();
		MPFRWhereClause pfr = new MPFRWhereClause(Env.getCtx());
		maxLineNo = pfr.getMaxLine(PFR_Calculation_ID);
		lineNo = maxLineNo;
		
		AEnv.showCenterWindow(frame, this);		
	}
	
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
	
	private int PFR_Calculation_ID;
	private int AD_Table_ID;
	private int maxLineNo = 0;
	private int lineNo = 0;
	private MColumn[] tableColumns;
	
	/** Index LineNo = 0			*/
	public static final int		INDEX_LINENO = 0;
	/** Index AndOr = 1				*/
	public static final int		INDEX_ANDOR = 1;
	/** Index LeftBracket = 2		*/
	public static final int		INDEX_LEFTBRACKET = 2;
	/** Index ColumnName = 3		*/
	public static final int		INDEX_COLUMNNAME = 3;
	/** Index Operator = 4			*/
	public static final int		INDEX_OPERATOR = 4;
	/** Index Value = 5				*/
	public static final int		INDEX_VALUE = 5;
	/** Index Value2 = 6			*/
	public static final int		INDEX_VALUE2 = 6;
	/** Index RightBracket = 7		*/
	public static final int		INDEX_RIGHTBRACKET = 7;
	/** Index Line ID = 8	 		*/
	public static final int		INDEX_LINEID = 8;
	/** Index Check static = 9		*/
	public static final int		INDEX_STATIC = 9;
	
	/** IN	*/
	public static final String	IN = " IN ";
	/** IN - 8		*/
	public static final int		IN_INDEX = 9;

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
	
	private CComboBox leftBrackets;
	private CComboBox rightBrackets;
	private CComboBox andOr;
	
	private CButton bIgnore = new CButton();	
	private CButton bSave = new CButton();
	private CButton bNew = new CButton();
	private CButton bDelete = new CButton();
	private CButton bRefresh = new CButton();
	
	private CTable advancedTable = new CTable() {

		private static final long serialVersionUID = -6201749159307529032L;

		public boolean isCellEditable(int row, int column)
		{
			boolean editable = ( column == INDEX_COLUMNNAME
					|| column == INDEX_OPERATOR 
					|| column == INDEX_ANDOR
					|| column == INDEX_LEFTBRACKET
					|| column == INDEX_RIGHTBRACKET );
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
			
			if ( column == INDEX_ANDOR && row == 0 )
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
		advancedTable.setModel(new DefaultTableModel(0, 10));
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
		
		// 0 = LineNo
		VString vs = new VString(Msg.translate(Env.getCtx(), "Line"), true, true, true, 120, 120, "", "");
		vs.setName (Msg.translate(Env.getCtx(), "Line"));
		tc = advancedTable.getColumnModel().getColumn(INDEX_LINENO);	
		dce = new FindCellEditor(vs);
		tc.setCellEditor(dce);
		tc.setPreferredWidth(120);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), "Line"));
		
		// 1 = And/Or
		andOr = new CComboBox(new String[] {"","AND","OR"});
		tc = advancedTable.getColumnModel().getColumn(INDEX_ANDOR);
		tc.setPreferredWidth(60);
		dce = new FindCellEditor(andOr);
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "And/Or"));
		
		// 2 = Left Bracket
		leftBrackets = new CComboBox(new String[] {"","(","((","((("});
		tc = advancedTable.getColumnModel().getColumn(INDEX_LEFTBRACKET);
		tc.setPreferredWidth(60);
		dce = new FindCellEditor(leftBrackets);
		tc.setCellEditor(dce);
		tc.setHeaderValue("(");

		//	3 = Operators
		operators = new CComboBox(OPERATORS);
		tc = advancedTable.getColumnModel().getColumn(INDEX_OPERATOR);
		tc.setPreferredWidth(55);
		dce = new FindCellEditor(operators);
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "Operator"));

		// 	4 = QueryValue
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE);
		QueryValueEditor fve = new QueryValueEditor(this, false);		
		tc.setCellEditor(fve);
		tc.setPreferredWidth(120);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue"));

		// 	5 = QueryValue2
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE2);
		tc.setPreferredWidth(120);
		fve = new QueryValueEditor(this, true);
		tc.setCellEditor(fve);
		tc.setCellRenderer(new ProxyRenderer(new QueryDialogValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue2"));
		
		// 6 = Right Bracket
		rightBrackets = new CComboBox(new String[] {"",")","))",")))"});
		tc = advancedTable.getColumnModel().getColumn(INDEX_RIGHTBRACKET);
		tc.setPreferredWidth(60);
		dce = new FindCellEditor(rightBrackets);
		tc.setCellEditor(dce);
		tc.setHeaderValue(")");
		
		// 8 = Line ID
		vs = new VString(Msg.translate(Env.getCtx(), "LineID"), true, true, true, 0, 0, "", "");  
		vs.setName(Msg.translate(Env.getCtx(), "LineID"));
		tc = advancedTable.getColumnModel().getColumn(INDEX_LINEID);	
		dce = new FindCellEditor(vs);
		tc.setCellEditor(dce);
		tc.setPreferredWidth(120);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), "LineID"));
		
		//
		VCheckBox check = new VCheckBox(Msg.translate(Env.getCtx(), "Static"), false, false, true, "", "", true);
		check.setName (Msg.translate(Env.getCtx(), "Static"));
		tc = advancedTable.getColumnModel().getColumn(INDEX_STATIC);	
		dce = new FindCellEditor(check);
		tc.setCellEditor(dce);
		tc.setPreferredWidth(120);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), "Static"));
				
		//user query
		refreshUserQueries();
		
	}
	
	private void refreshUserQueries() 
	{
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
	 *	Save (Advanced)
	 */
	private boolean cmd_save()
	{
		advancedTable.stopEditor(true);
		MPFRWhereClause clause = null;
		Object column = null;
		String value = "";
		StringBuilder errorLog = new StringBuilder();
		StringBuilder totalLog = new StringBuilder();
		int AD_Reference_ID = 0;
		boolean hasError = false;
		
		
		sortByIndex(INDEX_LINENO);
		
		for (int row = 0; row < advancedTable.getRowCount(); row++)
		{
			errorLog.append(Msg.translate(Env.getCtx(), "Line")).append("[")
			.append(advancedTable.getValueAt(row, INDEX_LINENO)).append("]: \n");
			//	Column LineNo
			column = advancedTable.getValueAt(row, INDEX_LINEID);
		
			value = column instanceof ValueNamePair ? 
				((ValueNamePair)column).getValue() : column.toString();
		
			if(value != null && !value.isEmpty())
				clause = new MPFRWhereClause(Env.getCtx(), Integer.parseInt(value), null);
			else
				clause = new MPFRWhereClause(Env.getCtx(), 0, null);
			//	Column LineNo
			column = advancedTable.getValueAt(row, INDEX_LINENO);
		
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
						
				if(value != null && !value.isEmpty())
					clause.setLine(Integer.parseInt(value));
			}
			else
			{		
				hasError = true;
				errorLog.append(valueCheck(row, INDEX_ANDOR, "required"));
			}
			//	Column AND_OR
			if(row > 0)
			{
				column = advancedTable.getValueAt(row, INDEX_ANDOR);	
				
				if(column != null && !column.toString().isEmpty())
				{
					value = column instanceof ValueNamePair ? 
							((ValueNamePair)column).getValue() : column.toString();
					
					if(value != null && !value.isEmpty())
						clause.setAndOr(value);
				}
				else
				{
					hasError = true;
					errorLog.append(valueCheck(clause.getLine(), INDEX_ANDOR, "required"));
				}
			}
			// Column Left Bracket
			column = advancedTable.getValueAt(row, INDEX_LEFTBRACKET);	
			value = (String)advancedTable.getValueAt(row, INDEX_RIGHTBRACKET);
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(value != null)
					clause.setOpenBracket(value);
			}
			else if(value != null && !value.isEmpty())
			{
				hasError = true;
				errorLog.append(valueCheck(clause.getLine(), INDEX_LEFTBRACKET, "required"));
			}
			// Column ColumnName
			column = advancedTable.getValueAt(row, INDEX_COLUMNNAME);	
			
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(value != null)
				{				
					clause.setAD_Column_ID(MColumn.getColumn_ID(MTable.getTableName(Env.getCtx(), AD_Table_ID), value));
					clause.setColumnName(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(clause.getLine(), INDEX_COLUMNNAME, "required"));
			}
			// Column Operator
			column = advancedTable.getValueAt(row, INDEX_OPERATOR);	
			
			if(column != null && !column.toString().isEmpty())
			{
				value = column instanceof ValueNamePair ? 
						((ValueNamePair)column).getValue() : column.toString();
				
				if(value != null)
				{
					clause.setOperation(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(clause.getLine(), INDEX_OPERATOR, "required"));
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
						clause.setValue2(value);
					}
				}
				else 
				{
					hasError = true;
					errorLog.append(valueCheck(clause.getLine(), INDEX_VALUE2, "required"));
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
					AD_Reference_ID = MColumn.get(Env.getCtx(), clause.getAD_Column_ID()).getAD_Reference_ID();
					
					if(AD_Reference_ID == DisplayType.YesNo)
					{
						value = (value.equals("true") || value.equals("Y")) ? "Y" : "N";
					}
					
					clause.setValue1(value);
				}
			}
			else
			{
				hasError = true;
				errorLog.append(valueCheck(clause.getLine(), INDEX_VALUE, "required"));
			}
			if(clause.getOpenBracket() != null && !clause.getOpenBracket().isEmpty())
			{
				// Column
				column = advancedTable.getValueAt(row, INDEX_RIGHTBRACKET);
				if(column != null && !column.toString().isEmpty())
				{
					value = column instanceof ValueNamePair ? 
							((ValueNamePair)column).getValue() : column.toString();
					
					if(value != null)
						clause.setCloseBracket(value);
				}
				else
				{
					hasError = true;
					errorLog.append(valueCheck(clause.getLine(), INDEX_RIGHTBRACKET, "required"));
				}
			}
			
			// Column static
			column = advancedTable.getValueAt(row, INDEX_STATIC);
			if(column != null)
				value = column instanceof ValueNamePair ? ((ValueNamePair)column).getValue() : column.toString();
			else
				value = "false";
			
			if(value != null)
				clause.setisStatic((value.equals("true") || value.equals("Y")) ? true : false);
		
			if(!hasError)
			{
				clause.setPFR_Calculation_ID(PFR_Calculation_ID);
				clause.saveEx();
				
				advancedTable.setValueAt(clause.get_ID(), row, INDEX_LINEID);
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
	 *	New record
	 */
	private void cmd_new()
	{
		lineNo += 10;
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int rows = model.getRowCount();
		
		if(rows == 0)
			model.addRow(new Object[] {String.valueOf(lineNo), "","", null, OPERATORS[MQuery.EQUAL_INDEX], null, null,"", "", true});
		else
			model.addRow(new Object[] {String.valueOf(lineNo), "AND","", null, OPERATORS[MQuery.EQUAL_INDEX], null, null,"", "", true});
		
		advancedTable.requestFocusInWindow();
	}	//	cmd_new
	
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
			sql.append(X_PFR_WhereClause.Table_Name);
			
			if(PFR_Calculation_ID > 0)
			{
				sql.append(" WHERE ").append(X_PFR_WhereClause.COLUMNNAME_PFR_Calculation_ID).append(" = ").append(PFR_Calculation_ID);
			}
			
			Object value = advancedTable.getValueAt(row, INDEX_LINENO);
			if(value != null)
			{
				sql.append(" AND ").append(X_PFR_WhereClause.COLUMNNAME_Line).append(" = ").append(value);
			}
			
			String indexValue = advancedTable.getValueAt(row, INDEX_LINEID).toString();
			
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
			MPFRWhereClause clause = new MPFRWhereClause(Env.getCtx(), Integer.parseInt(indexValue), null);
			
			DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
			
			String columnName = clause.getColumnName();
			String header = Msg.translate(Env.getCtx(), columnName);
			
			String op = OPERATORS[0].getValue();
			for(ValueNamePair oper : OPERATORS){
				if(oper.getValue().equals(clause.getOperation()))
					op = oper.getName();
			}
			
			model.setValueAt(clause.getLine(), row, INDEX_LINENO);
			model.setValueAt(clause.getAndOr(), row, INDEX_ANDOR);
			model.setValueAt(clause.getOpenBracket(), row, INDEX_LEFTBRACKET);
			model.setValueAt(new ValueNamePair(columnName, header), row, INDEX_COLUMNNAME);
			model.setValueAt(new ValueNamePair(clause.getOperation(), op), row, INDEX_OPERATOR);
			model.setValueAt(new ValueNamePair(clause.getValue1(), clause.getValue1()), row, INDEX_VALUE);
			model.setValueAt(new ValueNamePair(clause.getValue2(), clause.getValue2()), row, INDEX_VALUE2);
			model.setValueAt(clause.getCloseBracket(), row, INDEX_RIGHTBRACKET);
		}
		else
		{
			cmd_delete(false);
		}
	}	//	cmd_ignore
	
	private int loadLines()
	{
		MPFRWhereClause[] clauses = MPFRCalculation.getLinesWhere(Env.getCtx(), PFR_Calculation_ID, null);
		
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int n = 0;
		for(MPFRWhereClause wh : clauses)
		{
			String op = OPERATORS[0].getValue();
			for(ValueNamePair oper : OPERATORS){
				if(oper.getValue().equals(wh.getOperation()))
					op = oper.getName();
			}
			
			String columnName = wh.getColumnName();
			String header = Msg.translate(Env.getCtx(), columnName);
			if (header == null || header.length() == 0)
					continue;
			
			String statCheck = wh.isStatic() ? "Y" : "N";
			
			model.addRow(new Object[] {wh.getLine(), 
						wh.getAndOr(),
						wh.getOpenBracket(), 
						new ValueNamePair(columnName, header), 
						new ValueNamePair(wh.getOperation(), op), 
						new ValueNamePair(wh.getValue1(), wh.getValue1()), 
						new ValueNamePair(wh.getValue2(), wh.getValue2()),
						wh.getCloseBracket(),
						wh.getPFR_WhereClause_ID(),
						statCheck  });
			n++;
		}
		
		sortByIndex(INDEX_LINENO);
		
		return n;
	}
	
	private String valueCheck(int row, int column, String message)
	 {
	  StringBuilder errors = new StringBuilder();
	  return errors.append(Msg.translate(Env.getCtx(), "Field").toLowerCase()).append(" \"").append(advancedTable.getColumnModel().getColumn(column).getHeaderValue()).append("\" ")
				.append(Msg.translate(Env.getCtx(), message)).append("\n").toString();
	 }
	
	private void sortByIndex(int index)
	{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(advancedTable.getModel());
		sorter.toggleSortOrder(index);
		sorter.sort();
		advancedTable.setRowSorter(sorter);
	
		advancedTable.requestFocusInWindow();
	}
	
	/**
	 *	Display current count
	 *  @param currentCount String representation of current/total
	 */
	private void setStatusDB (int count)
	{
		statusBar.setStatusDB("#"+count);
	}	//	setDtatusDB
	
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
	
	private static final long serialVersionUID = -6982781676757963095L;

	@Override
	public void dataStatusChanged(DataStatusEvent e) 
	{

	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
	}
	
	public void dispose()
	{
		log.config("");
		//
		removeAll();
		super.dispose();
	}	//	dispose

}
