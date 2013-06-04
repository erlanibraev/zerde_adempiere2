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
public class M_BSC_NetWorkDiagLine extends X_BSC_NetWorkDiagLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087548607739319930L;

	/**
	 * @param ctx
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagLine_ID
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx, int BSC_NetWorkDiagLine_ID,
			String trxName) {
		super(ctx, BSC_NetWorkDiagLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	/** */
	private M_BSC_NetWorkDiagSubLine[] m_sublines = null;
	
	public M_BSC_NetWorkDiagSubLine[] getSubLines (boolean requery){
		if(m_sublines!=null && !requery){
			set_TrxName(m_sublines, get_TrxName());
			return m_sublines;
		}
		List<M_BSC_NetWorkDiagSubLine> list = new Query(getCtx(), I_BSC_NetWorkDiagSubLine.Table_Name, "BSC_NetWorkDiagLine_ID = ?", get_TrxName())
											.setParameters(get_ID())
											.setOrderBy(I_BSC_NetWorkDiagSubLine.COLUMNNAME_BSC_NetWorkDiagSubLine_ID)
											.list();
		m_sublines = list.toArray(new M_BSC_NetWorkDiagSubLine[list.size()]);
		return m_sublines;
	}

}
