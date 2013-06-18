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
public class MBSCAction extends X_BSC_Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506294109885922796L;

	/**
	 * @param ctx
	 */
	public MBSCAction(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_Action_ID
	 * @param trxName
	 */
	public MBSCAction(Properties ctx, int BSC_Action_ID, String trxName) {
		super(ctx, BSC_Action_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCAction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	private MBSCResponsibleExecutor[] m_responsible_executer = null;
	
	public MBSCResponsibleExecutor[] getResponsible_Executors (boolean requery){
		if(m_responsible_executer!=null && !requery){
			set_TrxName(m_responsible_executer, get_TrxName());
			return m_responsible_executer;
		}
		
		List<MBSCResponsibleExecutor> list = new Query(getCtx(), I_BSC_Responsible_Executor.Table_Name, "BSC_Action_ID = ?", get_TrxName())
											.setParameters(get_ID())
											.setOrderBy(I_BSC_Responsible_Executor.COLUMNNAME_BSC_Responsible_Executor_ID)
											.list();
		m_responsible_executer = list.toArray(new MBSCResponsibleExecutor[list.size()]);
		return m_responsible_executer;
	}

}
