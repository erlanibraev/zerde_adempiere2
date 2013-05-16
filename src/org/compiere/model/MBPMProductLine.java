/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author V.Sokolov
 *
 */
public class MBPMProductLine extends X_BPM_ProductLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4064605510576419962L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMProductLine.class);

	/**
	 * @param ctx
	 */
	public MBPMProductLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_ProductLine_ID
	 * @param trxName
	 */
	public MBPMProductLine(Properties ctx, int BPM_ProductLine_ID,
			String trxName) {
		super(ctx, BPM_ProductLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMProductLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/* 
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		super.beforeSave(newRecord);
		
		return delNullRecord(newRecord);
	}
	
	/* avoid duplication of records */
	private boolean delNullRecord(boolean newRecord){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean parent = false;
		int c = 0;
		
		StringBuffer sqlSel = new StringBuffer("SELECT * FROM "+Table_Name);
		sqlSel.append("\n WHERE M_ProductCat_ID="+getM_ProductCat_ID());
		sqlSel.append("\n AND BPM_ABP_ID="+getBPM_ABP_ID());
		
		StringBuilder sqlDel = new StringBuilder("DELETE FROM "+Table_Name);
		sqlDel.append("\n WHERE M_ProductCat_ID="+getM_ProductCat_ID());
		sqlDel.append("\n AND BPM_ABP_ID="+getBPM_ABP_ID());
		
		String where  = "\n AND M_Product_Category_ID is NULL ";
		String where1  = "\n AND M_Product_Category_ID is NOT NULL ";
		
		// query child		
		try
		{
			pstmt = DB.prepareStatement(sqlSel.toString()+where1,null);
			rs = pstmt.executeQuery();
			while(rs.next()){
				c++;
			}
				
		}
		catch (SQLException e)
		{
			s_log.log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}			
		
		// query parent	 	
		try
		{
			pstmt = DB.prepareStatement(sqlSel.toString()+where,null);
			rs = pstmt.executeQuery();
			if(rs.next())
				parent = true;

		}
		catch (SQLException e)
		{
			s_log.log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}			
		
		if(getM_Product_Category_ID() == 0 && c > 1)
			throw new AdempiereException("Warning. С данной группы имеются записи. Выберите Материал.");
		
		if(newRecord && parent && getM_Product_Category_ID() != 0)
			if(ADialog.ask(999, null, "Добавить Материал? \n WARNING: Запись родительской Группы будет удалена. \n", "Продолжить? \n"))
				DB.executeUpdate(sqlDel.toString()+where, null);
			else
				return false;
		
		return true;
	}

}
