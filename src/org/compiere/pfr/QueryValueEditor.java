package org.compiere.pfr;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.compiere.apps.search.FindValueEditor;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.model.MColumn;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.ValueNamePair;

public class QueryValueEditor extends AbstractCellEditor implements TableCellEditor 
{
	private static final long serialVersionUID = -8162019498692286688L;
	/** Find Window             */
	private QueryDialog 			queryDialog;
	/** Value 2(to)             */
	private boolean         m_valueToColumn;
	/**	Between selected		*/
	private boolean			m_between = false;
	/**	Editor					*/
	private VEditor			m_editor = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(FindValueEditor.class);
	
	public QueryValueEditor (QueryDialog queryDialog, boolean valueTo)
	{
		super();
		this.queryDialog = queryDialog;
		m_valueToColumn = valueTo;
	}
	
	@Override
	public Object getCellEditorValue() 
	{
		if (m_editor == null)
			return null;
		Object obj = m_editor.getValue();		//	returns Integer, BidDecimal, String
		log.config("Obj=" + obj);
		return obj;
	}

	public boolean isCellEditable (EventObject e)
	{
		return true;
	}   //  isCellEditable

	/**
	 *  Cell Selectable.
	 * 	Called after getTableCellEditorComponent
	 *  @param e event
	 *  @return true if selectable
	 */
	public boolean shouldSelectCell (EventObject e) 
	{
		boolean retValue = !m_valueToColumn || (m_valueToColumn && m_between); 
		return retValue; 
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) 
	{
		m_between = false;
		Object betweenValue = table.getModel().getValueAt(row, QueryDialog.INDEX_OPERATOR);
		if (m_valueToColumn &&  betweenValue != null 
			&& betweenValue.equals(MQuery.OPERATORS[MQuery.BETWEEN_INDEX]))
			m_between = true;

		boolean enabled = !m_valueToColumn || (m_valueToColumn && m_between); 
		log.config("(" + value + ") - Enabled=" + enabled);

		if ( enabled )
		{
		String columnName = null;
		Object column = table.getModel().getValueAt(row, QueryDialog.INDEX_COLUMNNAME);
		if (column != null)
			columnName = ((ValueNamePair)column).getValue();

		MColumn field = queryDialog.getTargetMField(columnName);
		if (field.isKey())
			m_editor = new VNumber(columnName, false, false, true, DisplayType.Integer, columnName);
		else
			m_editor = VEditorFactory.getEditor(field, true);
		if (m_editor == null)
			m_editor = new VString();
		
		//ResultSet rs = null;
		
		//GridFieldVO vo = GridFieldVO.createParameter(ctx, WindowNo, rs);
		//GridField field = new GridField(vo)

		m_editor.setValue(value);
		m_editor.setReadWrite(enabled);
		m_editor.setBorder(null);
		}
		else
		{
			m_editor = null;
		}
		//
		return (Component)m_editor;
	}

}
