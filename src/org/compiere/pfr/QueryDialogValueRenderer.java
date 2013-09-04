package org.compiere.pfr;

import java.awt.Component;
import java.awt.Insets;
import java.sql.Date;
import java.util.LinkedHashMap;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.MColumn;
import org.compiere.model.MQuery;
import org.compiere.util.DisplayType;
import org.compiere.util.ValueNamePair;

public class QueryDialogValueRenderer extends DefaultTableCellRenderer 
{
	private static final long serialVersionUID = -4331238353480434965L;

	public QueryDialogValueRenderer (QueriInterface queryDialog, boolean valueTo, LinkedHashMap<String, Integer> map)
	{
		super();
		this.queryDialog = queryDialog;
		this.map = map;
		m_valueToColumn = valueTo;
	}	//	FindValueRenderer

	/* */
	LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
	/** Find Window             */
	private QueriInterface			queryDialog;
	/** Value 2(to)             */
	private boolean         m_valueToColumn;
	/**	Between selected		*/
	private boolean			m_between = false;

	/** Current Row             */
	private volatile String	m_columnName = null;
	/** CheckBox                */
	private JCheckBox       m_check = null;

	/**
	 * 	Get Check Box
	 *	@return check box
	 */
	private JCheckBox getCheck()
	{
		if (m_check == null)
		{
			m_check = new JCheckBox();
			m_check.setMargin(new Insets(0,0,0,0));
			m_check.setHorizontalAlignment(JLabel.CENTER);
		}
		return m_check;
	}	//	getCheck


	/*************************************************************************
	 *	Get TableCell RendererComponent
	 *  @param table table
	 *  @param value value
	 *  @param isSelected selected
	 *  @param hasFocus focus
	 *  @param row row
	 *  @param col col
	 *  @return renderer component (Label or CheckBox)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int col)
	{
		//	Column
		m_columnName = null;
		Object column = table.getModel().getValueAt(row, map.get(QueryDialog.KEY_COLUMNNAME));
		if (column != null) {
			if (column instanceof ValueNamePair)
				m_columnName = ((ValueNamePair)column).getValue();
			else
				m_columnName = column.toString();
		}

		//	Between - enables valueToColumn
		m_between = false;
		Object betweenValue = table.getModel().getValueAt(row, map.get(QueryDialog.KEY_OPERATOR));
		if (m_valueToColumn && betweenValue != null 
			&& betweenValue.equals(QueryDialog.OPERATORS[MQuery.BETWEEN_INDEX]))
			m_between = true;
		boolean enabled = !m_valueToColumn || (m_valueToColumn && m_between); 

		//	set Background
		if (enabled)
			setBackground(AdempierePLAF.getFieldBackground_Normal());
		else
			setBackground(AdempierePLAF.getFieldBackground_Inactive());

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		if (value == null || (m_valueToColumn && !m_between))
			return c;
		//
		MColumn field = getMField();
		if (field != null && field.getAD_Reference_ID() == DisplayType.YesNo)
		{
			JCheckBox cb = getCheck();
			if (value instanceof Boolean)
				cb.setSelected(((Boolean)value).booleanValue());
			else
				cb.setSelected(value.toString().indexOf('Y') != -1);
			return cb;
		}
		return c;
	}	//	getTableCellRendererComponent

	
	/**************************************************************************
	 *	Format Display Value
	 *  @param value value
	 */
	protected void setValue(Object value)
	{
		boolean enabled = !m_valueToColumn || (m_valueToColumn && m_between); 
	//	Log.trace (Log.l4_Data, "FindValueRenderer.setValue (" + value + ") - Enabled=" + enabled);
		if (value == null || !enabled)
		{
			super.setValue(null);
			return;
		}

		String retValue = null;

		//	Strip ' '
		if (value != null)
		{
			String str = value.toString();
			if (str.startsWith("'") && str.endsWith("'"))
			{
				str = str.substring(1, str.length()-1);
				value = str;
			}
		}

		int displayType = 0;
		MColumn field = getMField();
		if (field != null)
			displayType = field.getAD_Reference_ID();
	
		setHorizontalAlignment(JLabel.LEFT);
		//	Number
		if (DisplayType.isNumeric(displayType))
		{
			setHorizontalAlignment(JLabel.RIGHT);
			retValue = DisplayType.getNumberFormat(displayType).format(value);
		}
		//	Date
		else if (DisplayType.isDate(displayType))
		{
			if (value instanceof Date)
			{
				retValue = DisplayType.getDateFormat(displayType).format(value);
				setHorizontalAlignment(JLabel.RIGHT);
			}
			else if (value instanceof String)	//	JDBC format
			{
				try
				{
					java.util.Date date = DisplayType.getDateFormat_JDBC().parse((String)value);
					retValue = DisplayType.getDateFormat(displayType).format(date);
					setHorizontalAlignment(JLabel.RIGHT);
				}
				catch (Exception e)
				{
				//	log.log(Level.SEVERE, "FindValueRenderer.setValue", e);
					retValue = value.toString();
				}
			}
			else
				retValue = value.toString();
		}
		//	Row ID
		else if (displayType == DisplayType.RowID)
			retValue = "";
		//	other
		else
		{
			super.setValue(value);
			return;
		}

		super.setValue(retValue);
	}	//	setValue

	/**
	 * 	Get MField
	 * 	@return field
	 */
	private MColumn getMField ()
	{
		return queryDialog.getTargetMField(m_columnName);
	}	//	getMField
}
