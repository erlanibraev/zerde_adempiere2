/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * @author A.Kulantayev
 *
 */
public class M_BSC_NetWorkDiagSubLine extends X_BSC_NetWorkDiagSubLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9030008792522222350L;

	/**
	 * @param ctx
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagSubLine_ID
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx,
			int BSC_NetWorkDiagSubLine_ID, String trxName) {
		super(ctx, BSC_NetWorkDiagSubLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagSubLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	private M_BSC_Target_Indicator[] m_tar_indicators = null;
	
	private M_BSC_Action[] m_actions = null;
	
	public M_BSC_Target_Indicator[] getTarget_Indicator (boolean requery){
		if(m_tar_indicators!=null && !requery){
			set_TrxName(m_tar_indicators, get_TrxName());
			return m_tar_indicators;
		}
		
		List<M_BSC_Target_Indicator> list = new Query(getCtx(), I_BSC_Target_Indicator.Table_Name, "BSC_NetWorkDiagSubLine_ID = ?", get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(I_BSC_Target_Indicator.COLUMNNAME_BSC_Target_Indicator_ID)
										.list();
		m_tar_indicators = list.toArray(new M_BSC_Target_Indicator[list.size()]);
		
		return m_tar_indicators;
	}
	
	public M_BSC_Action[] getActions (boolean requery){
		if(m_actions!=null && !requery){
			set_TrxName(m_actions, get_TrxName());
			return m_actions;
		}
		
		List<M_BSC_Action> list = new Query(getCtx(), I_BSC_Action.Table_Name, "BSC_NetWorkDiagSubLine_ID = ?", get_TrxName())
								.setParameters(get_ID())
								.setOrderBy(I_BSC_Action.COLUMNNAME_BSC_Action_ID)
								.list();
		m_actions = list.toArray(new M_BSC_Action[list.size()]);
		return m_actions;
	}

}