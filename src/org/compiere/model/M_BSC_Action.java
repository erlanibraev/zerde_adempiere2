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
public class M_BSC_Action extends X_BSC_Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506294109885922796L;

	/**
	 * @param ctx
	 */
	public M_BSC_Action(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Action_ID
	 * @param trxName
	 */
	public M_BSC_Action(Properties ctx, int BSC_Action_ID, String trxName) {
		super(ctx, BSC_Action_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_Action(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	private M_BSC_Responsible_Executor[] m_responsible_executer = null;
	
	public M_BSC_Responsible_Executor[] getResponsible_Executors (boolean requery){
		if(m_responsible_executer!=null && !requery){
			set_TrxName(m_responsible_executer, get_TrxName());
			return m_responsible_executer;
		}
		
		List<M_BSC_Responsible_Executor> list = new Query(getCtx(), I_BSC_Responsible_Executor.Table_Name, "BSC_Action_ID = ?", get_TrxName())
											.setParameters(get_ID())
											.setOrderBy(I_BSC_Responsible_Executor.COLUMNNAME_BSC_Responsible_Executor_ID)
											.list();
		m_responsible_executer = list.toArray(new M_BSC_Responsible_Executor[list.size()]);
		return m_responsible_executer;
	}

}
