/**
 * 
 */
package org.compiere.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.DB;

/**
 * Class: model for directory services / works
 * 
 * @author V.Sokolov
 *
 */
public class MBPMServicesWork extends X_BPM_ServicesWork {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837142065443628510L;

	/**
	 * @param ctx
	 */
	public MBPMServicesWork(Properties ctx) {
		super(ctx);
	}
	
	/**
	 * @param ctx
	 * @param bpm_ServicesWork_ID
	 * @param trxName
	 */
	public MBPMServicesWork(Properties ctx, int bpm_ServicesWork_ID,
			String trxName) {
		super(ctx, bpm_ServicesWork_ID, trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMServicesWork(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/* 
	 * adding tree node automatically check
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		int treeId = get_TreeId();
		
		if(treeId != 0){
			MTree tree = new MTree (getCtx(), treeId, get_TrxName());
			if (tree == null || tree.getAD_Tree_ID() == 0)
				throw new IllegalArgumentException("No Tree -" + tree);
			verifyTree(tree);
		}
		return true;
	}
	
	private int get_TreeId(){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The name must start with a report hrm_order
		String sql_ = "select ad_tree_id from ad_tree where treeType='"+X_AD_Tree.TREETYPE_ServicesWork+"'";
		try
		{
			pstmt = DB.prepareStatement(sql_, null);
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	

		return 0;
	}
	
	/**
	 *  Verify Tree
	 * 	@param tree tree
	 */
	private void verifyTree (MTree_Base tree)
	{
		String nodeTableName = tree.getNodeTableName();
		String sourceTableName = tree.getSourceTableName(true);
		String sourceTableKey = sourceTableName + "_ID";
		int AD_Client_ID = tree.getAD_Client_ID();
		int C_Element_ID = 0;
		if (MTree.TREETYPE_ElementValue.equals(tree.getTreeType()))
		{
			String sql = "SELECT C_Element_ID FROM C_Element "
				+ "WHERE AD_Tree_ID=" + tree.getAD_Tree_ID();
			C_Element_ID = DB.getSQLValue(null, sql);
			if (C_Element_ID <= 0)
				throw new IllegalStateException("No Account Element found");
		}
		
		//	Delete unused
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ").append(nodeTableName)
			.append(" WHERE AD_Tree_ID=").append(tree.getAD_Tree_ID())
			.append(" AND Node_ID NOT IN (SELECT ").append(sourceTableKey)
			.append(" FROM ").append(sourceTableName)
			.append(" WHERE AD_Client_ID=").append(AD_Client_ID);
		if (C_Element_ID > 0)
			sql.append(" AND C_Element_ID=").append(C_Element_ID);
		sql.append(")");
		log.finer(sql.toString());
		
		//
		if (!tree.isAllNodes())
			return;
		
		//	Insert new
		sql = new StringBuffer();
		sql.append("SELECT ").append(sourceTableKey)
			.append(" FROM ").append(sourceTableName)
			.append(" WHERE AD_Client_ID=").append(AD_Client_ID);
		if (C_Element_ID > 0)
			sql.append(" AND C_Element_ID=").append(C_Element_ID);
		sql.append(" AND ").append(sourceTableKey)
			.append("  NOT IN (SELECT Node_ID FROM ").append(nodeTableName)
			.append(" WHERE AD_Tree_ID=").append(tree.getAD_Tree_ID()).append(")");
		log.finer(sql.toString());
		
		//
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int Node_ID = rs.getInt(1);
				PO node = new MTree_Node(tree, Node_ID);
				if (!node.save())
					log.log(Level.SEVERE, "Could not add to " + tree + " Node_ID=" + Node_ID);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "verifyTree", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
	}	//	verifyTree

}
