/**
 * 
 */
package org.compiere.apps.search;

import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MParameter;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * @author Y.Ibrayev
 *
 */
public class InfoBSCParameter extends Info {

	protected static CLogger sLog = CLogger.getCLogger ("MParameter"); 
	private CLabel lParameter_Modules = new CLabel(Msg.translate(Env.getCtx(), "Modules"));
	private CComboBox fParameter_Modules = new CComboBox(getModules())  ; 
	private CLabel lName = new CLabel(Msg.translate(Env.getCtx(), "Name"));
	private CTextField fName = new CTextField(10);
	private CLabel lBPartner = new CLabel(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	private CComboBox fBPartner = new CComboBox(getBPartners())  ;

	private CLabel lIsExports = new CLabel(Msg.translate(Env.getCtx(), "IsExports"));
	private CCheckBox fIsExports = new CCheckBox();
	
	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	private static final Info_Column[] BSC_ParameterLayout = {
		//hrm_Order_ID field
		 new Info_Column(Msg.translate(Env.getCtx(), "BSC_Parameter_ID"), "BSC_Parameter_ID", IDColumn.class)
		//1-field for order number		
		,new Info_Column(Msg.translate(Env.getCtx(), "Name"), "Name", String.class)
		//2-field for order name
		,new Info_Column(Msg.translate(Env.getCtx(), "Modules"), "Modules", String.class)
		//3-field for order date
		,new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT C_BPartner.Name FROM C_BPartner WHERE C_BPartner.C_BPartner_ID = BSC_Parameter.C_BPartner_ID) AS C_BPartner_ID", String.class)
		//4-field for order date
		,new Info_Column(Msg.translate(Env.getCtx(), "IsExports"), "IsExports", Boolean.class)
//		,new Info_Column(Msg.translate(Env.getCtx(), "Date"), "ch.datefrom", Timestamp.class)
	};
	
	
	/**
	 * @param frame
	 * @param modal
	 * @param WindowNo
	 * @param tableName
	 * @param keyColumn
	 * @param multiSelection
	 * @param whereClause
	 */
	protected InfoBSCParameter(Frame frame, boolean modal, int WindowNo, String keyColumn, boolean multiSelection,
			String whereClause) {
		super(frame, modal, WindowNo, MParameter.Table_Name , keyColumn, multiSelection, whereClause);
		log.info("InfoBSCParameter");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoBSCParameter"));
		try
		{
			statInit();
			p_loadedOK = initInfo ();
		}
		catch (Exception e)
		{
			return;
		}
		int no = p_table.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		if (keyColumn != null && keyColumn.length() > 0)
		{
			fName.setValue(keyColumn);
			executeQuery();
		}
		//
		pack();
		//	Focus
		fName.requestFocus();	
		// 
	}

	/**
	 * @return
	 */
	private KeyNamePair[] getBPartners() {
		String sql = "SELECT C_BPartner_ID \n"
				   + "     , Name \n"
				   + "FROM C_BPartner \n"
				   + "WHERE C_BPartner_ID IN (SELECT C_BPartner_ID FROM BSC_Parameter WHERE IsActive = 'Y' AND AD_Client_ID = ?)";
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		List<Object> params = new ArrayList<Object>();
		params.add(new Integer(AD_Client_ID));
		KeyNamePair[] result = DB.getKeyNamePairs(sql, true, params);
		return result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5554395454552579333L;

	/* (non-Javadoc)
	 * @see org.compiere.apps.search.Info#getSQLWhere()
	 */
	@Override
	protected String getSQLWhere() {
		StringBuffer sql = new StringBuffer();
		ValueNamePair vnp = (ValueNamePair) fParameter_Modules.getSelectedItem();
		KeyNamePair knp = (KeyNamePair) fBPartner.getValue(); 
		if (fName.getText().length() > 0)
			sql.append(" AND UPPER(Name) LIKE ?");
		if (vnp != null && vnp.getValue() != null && vnp.getValue().length() > 0)
			sql.append(" AND UPPER(Modules) LIKE ?");
		if (fIsExports.isSelected()) 
			sql.append(" AND IsExports = 'Y'");
		if (knp.getKey() > 0) 
			sql.append(" AND C_BPartner_ID = ?");
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.search.Info#setParameters(java.sql.PreparedStatement, boolean)
	 */
	@Override
	protected void setParameters(PreparedStatement pstmt, boolean forCount)
			throws SQLException {
		int index = 1;
		ValueNamePair vnp = (ValueNamePair) fParameter_Modules.getSelectedItem();
		KeyNamePair knp = (KeyNamePair) fBPartner.getValue(); 
		if (fName.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fName));
		if (vnp != null && vnp.getValue() != null && vnp.getValue().length() > 0) 
			pstmt.setString(index++, vnp.getValue());
		if (knp.getKey() > 0)
			pstmt.setInt(index++, knp.getKey());
	}

	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be created
	 */
	private void statInit() throws Exception{
		lParameter_Modules.setHorizontalAlignment(JLabel.RIGHT );
		lParameter_Modules.setLabelFor(fParameter_Modules);
		fParameter_Modules.setBackground(AdempierePLAF.getInfoBackground());
		fParameter_Modules.addActionListener(this);
		
		lName.setHorizontalAlignment(JLabel.RIGHT);
		lName.setLabelFor(fName);
		fName.setBackground(AdempierePLAF.getInfoBackground());
		fName.addActionListener(this);
		
		lIsExports.setHorizontalAlignment(JLabel.RIGHT);
		lIsExports.setLabelFor(fIsExports);
		fIsExports.setBackground(AdempierePLAF.getInfoBackground());
		fIsExports.setValue(true);
		fIsExports.addActionListener(this);
		
		lBPartner.setHorizontalAlignment(JLabel.RIGHT);
		lBPartner.setLabelFor(fBPartner);
		fBPartner.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner.addActionListener(this);
		
		parameterPanel.setLayout(new ALayout(2,2,true));
		//
		parameterPanel.add(lName, new ALayoutConstraint(0,0));
		parameterPanel.add(fName, null);
		parameterPanel.add(lParameter_Modules,null);
		parameterPanel.add(fParameter_Modules,null);
		parameterPanel.add(lIsExports,new ALayoutConstraint(1,0));
		parameterPanel.add(fIsExports,null);
		parameterPanel.add(lBPartner,null);
		parameterPanel.add(fBPartner,null);
	}
	
	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initInfo (){
		
		//  prepare table
			StringBuffer where = new StringBuffer(" IsActive='Y' "); 
			prepareTable (BSC_ParameterLayout,
				" BSC_Parameter ",
				where.toString(),
				" Modules, Name ");
		return true;
	}
	
	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return Upper case text with % at the end
	 */
	
	private String getSQLText(CTextField f) {
		String s = f.getText().toUpperCase();
		if (!s.endsWith("%"))
			s = "%"+s+"%";
		log.fine( "String=" + s);
		return s;
	}
	
	private ValueNamePair[] getModules() {
		String AD_Language = Env.getAD_Language(Env.getCtx());
		String sql = "SELECT AD_Ref_List.Value \n" 
				   + "     , AD_Ref_List_Trl.Name \n"
				   + "FROM AD_Ref_List \n"
				   + "LEFT JOIN AD_Ref_List_Trl \n"
				   + "       ON AD_Ref_List.AD_Ref_List_ID = AD_Ref_List_Trl.AD_Ref_List_ID \n"
				   + "      AND AD_Language =  ?\n"
				   + "WHERE AD_Reference_ID IN (SELECT AD_Reference_ID FROM AD_Reference WHERE Name = 'Modules')";
		List<Object> param = new ArrayList<Object>();
		param.add(AD_Language);
		ValueNamePair[] result = DB.getValueNamePairs(sql, true, param);
		return result;
	}
	
}
