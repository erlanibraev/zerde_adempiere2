/**
 * 
 */
package main.org.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MPeriod;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author V.Sokolov
 *
 */
public class Utils {
	
	/* constants for the names of templates */
	public static final String TEMPLATE_FIRST = "templateFirst";
	public static final String TEMPLATE_SECOND = "templateSecond";
	public static final String TEMPLATE_THREE = "templateThree";
	public static final String TEMPLATE_ALL = "templateAll";
	
	public static MPeriod[] getPeriod(int budgetCallID){
		
		MPeriod period = null;
		List<MPeriod> list = new ArrayList<MPeriod>();	
		
		String sql = "select p.* from BPM_budgetCall b \n" +
				"join c_year y on y.c_year_id=b.c_year_id \n" +
				"join c_period p on p.c_year_id=y.c_year_id \n" +
				"where b.bpm_budgetCall_id="+budgetCallID;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery();
			while (rs.next ())
			{
				period = new MPeriod(Env.getCtx(), rs, null);
				list.add(period);
			}
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.INFO, sql, e);
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return list.toArray(new MPeriod[list.size()]);
	}

}
