package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class Mbcsinputfinreports extends X_bcs_inputfinreports{

	private Mbcsinputfinreportsline[] lines;
	private static CLogger s_log = CLogger.getCLogger(Mbcsinputfinreports.class);
	
	
	public Mbcsinputfinreports(Properties ctx, int bcs_inputfinreports_ID,
			String trxName) {
		super(ctx, bcs_inputfinreports_ID, trxName);
		
		
		
//		lines = getLines(null);
		// TODO Auto-generated constructor stub
	}
	
	
	
	private Mbcsinputfinreportsline[] getLines (String whereClause)
	{
		String whereClauseFinal = "bcs_inputfinreports_ID=? ";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<Mbcsinputfinreportsline> list = new Query(getCtx(), I_bcs_inputfinreportsline.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(I_bcs_inputfinreportsline.COLUMNNAME_bcs_inputfinreportsline_ID)
										.list();
		return list.toArray(new Mbcsinputfinreportsline[list.size()]);
	}	//	getLines
	
	
	private int getLines(){
		
		int ID = get_ID();
		
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		MInvoice result = null;
			
		// 
		String sql_ = "SELECT count(*) FROM adempiere.bcs_inputfinreportsline \n " +
						"WHERE ISACTIVE='Y' \n"  +
						   " AND bcs_inputfinreports_id="  +  ID;
			try
			{
				pstmt = DB.prepareStatement(sql_,null);				
				rs = pstmt.executeQuery();	
				if(rs.next())
					return rs.getInt(1);
			}
			catch (SQLException e)
			{
				s_log.log(Level.SEVERE, "product", e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		
		return 0;
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		int newValue = (int)get_Value("bcs_finreportspos_id");
		int oldValue = (int)get_ValueOld("bcs_finreportspos_id");
		
		if(newValue != oldValue){
			int ID = get_ID();
			
		    PreparedStatement pstmt = null;
			ResultSet rs = null;
			MInvoice result = null;
				
			// delete all positions
			String sqld = " DELETE FROM adempiere.bcs_inputfinreportsline \n " +
					   " WHERE bcs_inputfinreports_id="  +  ID;

			int no = DB.executeUpdate(sqld, get_TrxName());
			
			//create positions
			String sql = 
					  " SELECT \n"  
					+ "		dictline.bcs_finreportsdictline_id \n"
					+ "FROM adempiere.bcs_finreportspos head \n"
					+ "inner join adempiere.bcs_finreportsposline line on line.bcs_finreportspos_id = head.bcs_finreportspos_id \n"
					+ "inner join adempiere.bcs_finreportsdictline dictline on dictline.bcs_finreportsdictline_id = line.bcs_finreportsdictline_id \n"
					+ "WHERE \n"
					+ "	line.isactive='Y' \n" 
					+ "	AND head.isactive='Y' \n"
					+ "	AND dictline.isactive='Y' \n"
					+ "	AND head.bcs_finreportspos_id = " + newValue; 
					;

				try{
					pstmt = DB.prepareStatement(sql,null);				
					rs = pstmt.executeQuery();	
					while(rs.next()){
						Mbcsinputfinreportsline m = new Mbcsinputfinreportsline(Env.getCtx(), 0, get_TrxName());
						m.setbcs_inputfinreports_ID(ID);
						m.setbcs_finreportsdictline_ID(rs.getInt(1));
						m.save();
					}
				}catch (SQLException e){
					s_log.log(Level.SEVERE, "product", e);
				}finally{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}			
		}
		
//		System.out.println(getLines());
		return true;
	}
	
//	public boolean save(){
//		
//		return true;
//	};

}
