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
public class MBPMServicesLine extends X_BPM_ServicesLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5178189752818367502L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMServicesLine.class);

	/**
	 * @param ctx
	 */
	public MBPMServicesLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_ServicesLine_ID
	 * @param trxName
	 */
	public MBPMServicesLine(Properties ctx, int BPM_ServicesLine_ID,
			String trxName) {
		super(ctx, BPM_ServicesLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMServicesLine(Properties ctx, ResultSet rs, String trxName) {
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
		sqlSel.append("\n WHERE BPM_Services_ID="+getBPM_Services_ID());
		sqlSel.append("\n AND BPM_ABP_ID="+getBPM_ABP_ID());
		
		StringBuilder sqlDel = new StringBuilder("DELETE FROM "+Table_Name);
		sqlDel.append("\n WHERE BPM_Services_ID="+getBPM_Services_ID());
		sqlDel.append("\n AND BPM_ABP_ID="+getBPM_ABP_ID());
		
		String where  = "\n AND BPM_ServicesWork_ID is NULL ";
		String where1  = "\n AND BPM_ServicesWork_ID is NOT NULL ";
		
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
		
		if(getBPM_ServicesWork_ID() == 0 && c > 1)
			throw new AdempiereException("Warning. С данной группы имеются записи. Выберите ");
		
		if(newRecord && parent && getBPM_ServicesWork_ID() != 0)
			if(ADialog.ask(999, null, "Добавить Услугe/Работe? \n WARNING: Запись родительской Группы будет удалена. \n", "Продолжить? \n"))
				DB.executeUpdate(sqlDel.toString()+where, get_TrxName());
			else 
				return false;
		
		return true;
	}


}
