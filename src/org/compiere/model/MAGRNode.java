package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

public class MAGRNode extends X_AGR_Node 
{
	private static final long serialVersionUID = -231556931277061675L;

	public MAGRNode(Properties ctx, int AGR_Node_ID, String trxName) 
	{
		super(ctx, AGR_Node_ID, trxName);
	}

	public MAGRNode (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public static List<MAGRNode> getOfAGR_StageList(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRNode> list = new Query(ctx, I_AGR_Node.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		return list;
	}
	
	public static MAGRNode[] getOfTRM_Stage(Properties ctx, int AGR_Stage_ID, String trxName)
	{
		List<MAGRNode> list = new Query(ctx, I_AGR_Node.Table_Name, "AGR_Stage_ID=?", trxName)
		.setParameters(AGR_Stage_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MAGRNode[] retValue = new MAGRNode[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
	
	public static ArrayList<Integer> getNodes(Properties ctx, int AGR_Agreement_ID, String isBack, String trxName)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT n.AGR_NextStage_ID FROM AGR_Node n INNER JOIN AGR_Stage s ON n.AGR_Stage_ID = s.AGR_Stage_ID");
		builder.append("\n WHERE s.AGR_Agreement_ID = ");
		builder.append(AGR_Agreement_ID);
		builder.append("\n AND n.isBack = '");
		builder.append(isBack);
		builder.append("'");
		
		ArrayList<Integer> stages_Id = new ArrayList<Integer>();
		
		try
		{
			pstmt = DB.prepareStatement(builder.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next())
				stages_Id.add(rs.getInt(1));
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			try{
				rs.close(); pstmt.close(); rs = null; pstmt = null;
			}
			catch(Exception ex){}
		}
		
		return stages_Id;
	}
}
