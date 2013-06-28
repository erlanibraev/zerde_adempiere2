/**
 * 
 */
package main.org.model;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.compiere.util.DB;

/**
 * @author V.Sokolov
 *
 */
public class CitiesDAO {

	/**
	* Get a connection from a specified datasource
	* @param dataSourceName name
	* @return Connection
	*/
	public Connection getConnection(String dataSourceName) {
		
		InitialContext context = null;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(dataSourceName);
			return dataSource.getConnection();
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getCitiesByName (String searchText) {
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuffer cities = new StringBuffer();
		String sql = "select name from c_city where name like ?";
		try {
			psmt = DB.prepareStatement(sql,null);
			psmt.setString(1,searchText+"%");
			rs = psmt.executeQuery();
			//construct the xml string.
			cities.append("<cities>");
			while(rs.next()) {
				cities.append("<city>"+rs.getString("name")+"</city>");
			}
			cities.append("</cities>");
			rs.close();psmt.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			DB.close(rs, psmt);
			rs = null; psmt = null;
		}
		return cities.toString();
	}
	
}
