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
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Create tables and copy columns from one table to other
 *	
 *  @author Duman Zhunissov
 */
public class GenerateFRTables extends SvrProcess
{
	/** Main Table		*/
	private int		p_main_AD_Table_ID = 0;
	/** Record ID		*/
	private int		p_main_FR_Table_ID = 0;
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
		p_main_AD_Table_ID = getTable_ID();
		p_main_FR_Table_ID = getRecord_ID();
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
		if (p_main_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ " + p_main_AD_Table_ID);
		log.info("Main AD_Table_ID=" + p_main_AD_Table_ID);

		MTable mainTable = new MTable(m_ctx, p_main_AD_Table_ID, trxName);
		PO p_po = mainTable.getPO(p_main_FR_Table_ID, trxName);
		int index = p_po.get_ColumnIndex("AD_Table_ID");
		//int i_AD_Table_ID=0;
		//if (index != -1) i_AD_Table_ID = (Integer)p_po.get_Value(index);
		index = p_po.get_ColumnIndex("Name");
		String extTableName=null;
		if (index != -1) extTableName = (String)p_po.get_Value(index);

		StringBuffer sql = new StringBuffer(
				  "SELECT Counter FROM FR_Column "
				  + " WHERE FR_Table_ID = "+ p_main_FR_Table_ID + " AND IsActive = 'Y' "
				  + " GROUP BY Counter");
		StringBuffer sql2=new StringBuffer();
		log.fine("SQL=" + sql.toString());
		try	{  // find counter
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())	{
				String sCounter = rs.getString(1);
				String sETN = "FR_"+extTableName+sCounter;
				sETN=sETN.replaceAll(" ", "_");
				sql2.setLength(0);
				sql2.append("SELECT AD_Table_ID FROM AD_Table "
							  + " WHERE Name = '"+ sETN + "' AND IsActive = 'Y' ");
				PreparedStatement pstmt2;
				ResultSet rs2;
				int i_AD_Table_ID=0;
				try	{ // find table ID
					pstmt2 = DB.prepareStatement(sql2.toString(), trxName);
					rs2 = pstmt2.executeQuery();
					while (rs2.next())	{
						i_AD_Table_ID = rs2.getInt(1);
						break;
					}
				}
				catch (SQLException e)	{
						log.log(Level.SEVERE, sql2.toString(), e);
				}
				
				DB.executeUpdate("DROP TABLE IF EXISTS "+sETN, null);
				if (createTable(sETN, sCounter)!=0) continue;
			
				MTable extTable = new MTable(m_ctx, i_AD_Table_ID, trxName);
				extTable.setName(sETN);
				extTable.setTableName(sETN);
				extTable.set_ValueOfColumn("AD_Client_ID", 0);
				extTable.setAD_Org_ID(0);
				extTable.setEntityType("U");
				extTable.setAccessLevel("3"); // Client+Organization
				if (extTable.save(trxName)) {
					i_AD_Table_ID=extTable.get_ID();

					ProcessInfo pi = new ProcessInfo("Generate Table", 1000000);
					pi.setAD_Client_ID(getAD_Client_ID());
					pi.setAD_User_ID(getAD_User_ID());
				
					ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
					list.add(new ProcessInfoParameter("EntityType", "U", null, null, null));
					ProcessInfoParameter[] parameters = new ProcessInfoParameter[list.size()];
					list.toArray(parameters);
					pi.setParameter(parameters);
					pi.setRecord_ID(i_AD_Table_ID);
					TableCreateColumns tcc = new TableCreateColumns();
					Trx m_trx = Trx.get(trxName, true);
					tcc.startProcess(Env.getCtx(), pi, m_trx);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)	{
			log.log(Level.SEVERE, sql.toString(), e);
		}		
		return "#" + m_count;
	}

	private int createTable(String sETN, String sCounter) {
		StringBuffer sqlCreate = new StringBuffer(
				  "CREATE TABLE "+sETN+" \n"
				 +"(\n");
		if ("0".equals(sCounter)) {
			sqlCreate.append(sETN+"_id numeric(10,0) NOT NULL,\n");
		}
		sqlCreate.append("ad_client_id numeric(10,0) NOT NULL,\n"
				 +"ad_org_id numeric(10,0) NOT NULL,\n"
				 +"created timestamp without time zone NOT NULL,\n"
				 +"createdby numeric(10,0) NOT NULL,\n"
				 +"isactive character(1) NOT NULL DEFAULT 'Y'::bpchar,\n"
				 +"updated timestamp without time zone NOT NULL,\n"
				 +"updatedby numeric(10,0) NOT NULL,\n");
		StringBuffer sql=new StringBuffer("SELECT f.FR_Column_ID, f.AccumulationType,"
				+" e.ColumnName, e.AD_Reference_ID, e.FieldLength" 
				+" FROM FR_Column AS f"
				+" LEFT JOIN AD_Element AS e ON e.AD_Element_ID = f.AD_Element_ID"
				+" WHERE FR_Table_ID = "+ p_main_FR_Table_ID + " AND f.IsActive = 'Y' "
				+" AND f.Counter='"+sCounter+"'");
		log.fine("SQL=" + sql.toString());
		StringBuffer sKey=new StringBuffer();
				
		try	{  // find elements
			int i=0;
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())	{
				String sAT = rs.getString("AccumulationType");
				String sCN = rs.getString("ColumnName");
				int iRID = rs.getInt("AD_Reference_ID");
				int iFL = rs.getInt("FieldLength");
				if (sAT==null) {
					if (i>0) sKey.append(", ");
					sKey.append(sCN);
					i++;
				}
				sqlCreate.append(sCN);
				if (sCN.endsWith("_ID")) {
					sqlCreate.append(" numeric(10,0) NOT NULL,\n"); 
				} else {
					switch (iRID) {
					case 11: // Integer
					case 13: // ID
					case 18: // Table
					case 19: // Table Direct
					case 21: // Location (Address)
					case 25: // Account
					case 27: // Color	
					case 30: // Search 
					case 31: // Locator (WH)
					case 32: // Image 
					case 33: // Assignment 
					case 35: // Product Attribute 
						sqlCreate.append(" numeric(10,0) NOT NULL,\n"); break;
					case 12: // Amount 
					case 22: // Number 
					case 29: // Quantity 
					case 37: // Costs+Prices 
						sqlCreate.append(" numeric NOT NULL,\n"); break;
					case 14: // Text 
					case 34: // Memo 
					case 36: // Text Long 
						sqlCreate.append(" character varying(2000),\n"); break;
					case 15: // Date 
					case 24: // Time 
						sqlCreate.append(" timestamp without time zone,\n"); break;
					case 20: // Yes-No 
					case 28: // Button 
						sqlCreate.append(" character(1),\n"); break;
					case 10: // String 
					case 38: // FilePath 
						sqlCreate.append(" character varying(255),\n"); break;
					case 16: // Date+Time 
						sqlCreate.append(" timestamp without time zone NOT NULL DEFAULT now(),\n"); break;
					case 17: // List 
						sqlCreate.append(" character ("+iFL+"),\n"); break;
					case 23: // Binary 
						sqlCreate.append(" bytea,\n"); break;
					case 39: // FileName 
						sqlCreate.append(" character varying(500) NOT NULL,\n"); break;
					case 40: // URL 
						sqlCreate.append(" character varying(120),\n"); break;
					}
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)	{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		if ("0".equals(sCounter)) {
			sKey.setLength(0);
			sKey.append(sETN+"_id");
		}
		sqlCreate.append("CONSTRAINT "+sETN+"_pkey PRIMARY KEY ("+sKey+"),\n"
			+"CONSTRAINT "+sETN+"_isactive_check CHECK (isactive = ANY "
			+"(ARRAY['Y'::bpchar, 'N'::bpchar]))\n)"
			+"WITH (OIDS=FALSE);");
		return DB.executeUpdate(sqlCreate.toString(),trxName);
	}

}	//	GenerateFRTables