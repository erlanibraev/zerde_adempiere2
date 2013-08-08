/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 ADempiere, Inc. All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Adempiere, Inc.                                                            *
 *****************************************************************************/
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;

/**
 *	Create tables and copy columns from one table to other
 *	
 *  @author Duman Zhunissov
 */
public class FillFRTables extends SvrProcess
{
	/** FR Table		*/
	private int		p_AD_Table_ID = 0;
	/** Main Table		*/
	private int		p_main_AD_Table_ID = 0;
	/** Record ID		*/
	private int		p_FR_Table_ID = 0;
	/** Column Count	*/
	private int 	m_count = 0;
	/* trx 				*/
	private String trxName;
	/** Current context		*/
	private Properties m_ctx;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare ()
	{
		p_AD_Table_ID = getTable_ID();
		p_FR_Table_ID = getRecord_ID();
		m_ctx = getCtx();
		trxName = get_TrxName();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		if (p_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ " + p_AD_Table_ID);
		log.info("Main AD_Table_ID=" + p_AD_Table_ID);

		MTable frTable = new MTable(m_ctx, p_AD_Table_ID, trxName);
		PO p_po = frTable.getPO(p_FR_Table_ID, trxName);
		int index = p_po.get_ColumnIndex("AD_Table_ID");
		if (index != -1) p_main_AD_Table_ID = (Integer)p_po.get_Value(index);
		index = p_po.get_ColumnIndex("Name");
		String extTableName=null;
		if (index != -1) extTableName = (String)p_po.get_Value(index);
		
		MTable mainTable = new MTable(m_ctx, p_main_AD_Table_ID, trxName);

		StringBuffer sql = new StringBuffer(
				  "SELECT Counter FROM FR_Column "
				  + " WHERE FR_Table_ID = "+ p_FR_Table_ID + " AND IsActive = 'Y' "
				  + " AND AD_Client_ID ="+getAD_Client_ID()
				  + " GROUP BY Counter");
		StringBuffer sql2=new StringBuffer();
		log.fine("SQL=" + sql.toString());
		try	{  // find counter
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())	{
				String sCounter=rs.getString(1);
				String sTableName="FR_"+extTableName+sCounter;
				sTableName=sTableName.replaceAll(" ", "_");
				sql2.setLength(0);
				sql2.append("SELECT a.FR_Column_ID, a.AccumulationType, b.ColumnName"
					+" FROM FR_Column AS a"
					+" LEFT JOIN AD_Element AS b"
					+" ON  b.AD_Element_ID = a.AD_Element_ID"
					+" WHERE a.counter = " + sCounter
					+" AND a.FR_Table_ID =" + p_FR_Table_ID 
					+" AND a.IsActive = 'Y' "
					+" AND a.AD_Client_ID ="+getAD_Client_ID()
					);
				StringBuffer sqlUpdate = new StringBuffer("INSERT INTO "+sTableName
						+"( AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,");
				StringBuffer sqlSelect = new StringBuffer("SELECT "+getAD_Client_ID()+", AD_Org_ID, 'Y', SysDate," 
						+getAD_User_ID()+", SysDate, "+getAD_User_ID()+", ");
				StringBuffer sqlWhere = new StringBuffer(" WHERE IsActive = 'Y' ");
				StringBuffer sqlGroupBy = new StringBuffer(" GROUP BY AD_Org_ID,");
				
				PreparedStatement pstmt2;
				ResultSet rs2;
				try	{ // fill select
					pstmt2 = DB.prepareStatement(sql2.toString(), trxName);
					rs2 = pstmt2.executeQuery();
					int i=0;
					while (rs2.next())	{
						String sAT = rs2.getString(2);
						String sColName = rs2.getString(3);
						if (rs2.getRow()>1) {
							sqlSelect.append(", ");
							sqlUpdate.append(",");
						}
						sqlUpdate.append(sColName);
						if (sAT==null) {
							if (i>0) {
								sqlGroupBy.append(", ");
							}
							i++;
							sqlGroupBy.append(sColName);
						} else if ("1".equals(sAT)) {
							sColName="AVG("+sColName+")";
						} else if ("2".equals(sAT)) {
							sColName="COUNT("+sColName+")";
						} else if ("3".equals(sAT)) {
							sColName="FIRST("+sColName+")";
						} else if ("4".equals(sAT)) {
							sColName="LAST("+sColName+")";
						} else if ("5".equals(sAT)) {
							sColName="MAX("+sColName+")";
						} else if ("6".equals(sAT)) {
							sColName="MIN("+sColName+")";
						} else if ("7".equals(sAT)) {
							sColName="SUM("+sColName+")";
						}
						sqlSelect.append("COALESCE("+sColName+",0)");
					}
				}
				catch (SQLException e)	{
						log.log(Level.SEVERE, sql2.toString(), e);
				}
				sqlSelect.append(" FROM "+mainTable.getTableName()+sqlWhere+sqlGroupBy);
				sqlUpdate.append(") "+sqlSelect);
				DB.executeUpdate("DELETE FROM "+sTableName+
						" WHERE AD_Client_ID="+getAD_Client_ID(),trxName);
				DB.executeUpdate(sqlUpdate.toString(),trxName);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)	{
			log.log(Level.SEVERE, sql.toString(), e);
		}		
		return "#" + m_count;
	}	//	doIt

}	//	FillFRTables