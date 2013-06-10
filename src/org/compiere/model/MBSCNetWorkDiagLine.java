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
public class MBSCNetWorkDiagLine extends X_BSC_NetWorkDiagLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 965135736234250761L;

	/**
	 * @param ctx
	 * @param BSC_NetWorkDiagLine_ID
	 * @param trxName
	 */
	public MBSCNetWorkDiagLine(Properties ctx, int BSC_NetWorkDiagLine_ID,
			String trxName) {
		super(ctx, BSC_NetWorkDiagLine_ID, trxName);
	}
	public MBSCNetWorkDiagLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBSCNetWorkDiagLine(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	/** */
	private MBSCNetWorkDiagSubLine[] m_sublines = null;
	
	public MBSCNetWorkDiagSubLine[] getSubLines (boolean requery){
		if(m_sublines!=null && !requery){
			set_TrxName(m_sublines, get_TrxName());
			return m_sublines;
		}
		List<MBSCNetWorkDiagSubLine> list = new Query(getCtx(), I_BSC_NetWorkDiagSubLine.Table_Name, "BSC_NetWorkDiagLine_ID = ?", get_TrxName())
											.setParameters(get_ID())
											.setOrderBy(I_BSC_NetWorkDiagSubLine.COLUMNNAME_BSC_NetWorkDiagSubLine_ID)
											.list();
		m_sublines = list.toArray(new MBSCNetWorkDiagSubLine[list.size()]);
		return m_sublines;
	}

}
