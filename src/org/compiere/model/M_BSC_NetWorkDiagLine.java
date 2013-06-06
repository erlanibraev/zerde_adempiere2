/**
 * 
 */
package org.compiere.model;

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
	private static final long serialVersionUID = 965135736234250761L;

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagLine_ID
	 * @param trxName
	 */
	public M_BSC_NetWorkDiagLine(Properties ctx, int BSC_NetWorkDiagLine_ID,
			String trxName) {
		super(ctx, BSC_NetWorkDiagLine_ID, trxName);
		if(BSC_NetWorkDiagLine_ID == 0){
			setBSC_NetWorkDiagLine_ID(0);
		}
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
