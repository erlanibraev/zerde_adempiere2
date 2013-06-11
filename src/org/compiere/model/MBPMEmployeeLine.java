/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author V.Sokolov
 *
 */
public class MBPMEmployeeLine extends X_BPM_EmployeeLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987384373000435600L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMEmployeeLine.class);

	/**
	 * @param ctx
	 */
	public MBPMEmployeeLine(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_EmployeeLine_ID
	 * @param trxName
	 */
	public MBPMEmployeeLine(Properties ctx, int BPM_EmployeeLine_ID,
			String trxName) {
		super(ctx, BPM_EmployeeLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMEmployeeLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * list of employee
	 * @return
	 */
	public X_BPM_EmployeeLine[] getEmployee(){
		
		return getEmployeeLines("");
		
	}
	
	/**
	 * list of employee ABP
	 * @param abpID
	 * @return
	 */
	public X_BPM_EmployeeLine[] getEmployee(int abpID){
		
		return getEmployeeLines("\n AND BPM_ABP_ID="+abpID);
		
	}
	
	private X_BPM_EmployeeLine[] getEmployeeLines(String clause){
		
		//
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		X_BPM_EmployeeLine result = null;
		
		ArrayList<X_BPM_EmployeeLine> list = new ArrayList<X_BPM_EmployeeLine>();
		
		// 
		String sql_ = "SELECT * FROM "+Table_Name+" WHERE isActive='Y'" + clause;
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				result = new X_BPM_EmployeeLine(Env.getCtx(), rs, null);
				list.add(result);
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
		
		return list.toArray(new X_BPM_EmployeeLine[list.size()]);
		
	}  
	

}
