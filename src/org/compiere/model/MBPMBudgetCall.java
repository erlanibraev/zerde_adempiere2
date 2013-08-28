/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author V.Sokolov
 *
 */
public class MBPMBudgetCall extends X_BPM_BudgetCall {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5851240296742909043L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPMBudgetCall.class);

	/**
	 * @param ctx
	 */
	public MBPMBudgetCall(Properties ctx) {
		super(ctx);
	}

	/**
	 * @param ctx
	 * @param BPM_BudgetCall_ID
	 * @param trxName
	 */
	public MBPMBudgetCall(Properties ctx, int BPM_BudgetCall_ID, String trxName) {
		super(ctx, BPM_BudgetCall_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBPMBudgetCall(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/*
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		super.afterSave(newRecord, success);
		
		if(newRecord && getBPM_Parent_ID() == 0){
			
			MPeriod[] period = getPeriodBudget(getC_Year_ID());
		
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql_ = "SELECT * FROM bpm_budgetline_v WHERE bpm_budgetline_v.BPM_ABP_ID="+getBPM_ABP_ID();
			try{
				pstmt = DB.prepareStatement(sql_,null);
				rs = pstmt.executeQuery();	
				while(rs.next()){
					for(MPeriod p: period){
						MBPMBudgetCallLine line = new MBPMBudgetCallLine(getCtx(), 0, get_TrxName());
						line.setBPM_BudgetCall_ID(getBPM_BudgetCall_ID());
						line.setAD_Table_ID(rs.getInt(I_BPM_BudgetCallLine.COLUMNNAME_AD_Table_ID.toLowerCase()));
						line.setRecord_ID(rs.getInt(I_BPM_BudgetCallLine.COLUMNNAME_Record_ID.toLowerCase()));
						line.setAmount(new BigDecimal(0));
						line.setAmountUnit(new BigDecimal(0));
						line.setC_Charge_ID(rs.getInt(I_BPM_BudgetCallLine.COLUMNNAME_C_Charge_ID.toLowerCase()));
						line.setC_Period_ID(p.getC_Period_ID());
						line.setC_UOM_ID(100); // TODO 100 = шт.
						line.setPaymentMonth(X_BPM_BudgetCallLine.PAYMENTMONTH_Current);
						line.setQuantity(0);
						line.saveEx();
					}
				}				
			}catch(SQLException e){
				s_log.log(Level.INFO, "product", e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}	
			
		}
		
		return true;
	}	
	
	public static MPeriod[] getPeriodBudget(int Year){
		
		//
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		MPeriod result = null;
		
		ArrayList<MPeriod> list = new ArrayList<MPeriod>();
		
		// 
		String sql_ = "SELECT * FROM C_Period WHERE C_Year_ID="+Year+" ORDER BY periodno";
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				result = new MPeriod(Env.getCtx(), rs, null);
				list.add(result);
			}				
		}
		catch (SQLException e)
		{
			s_log.log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		
		return list.toArray(new MPeriod[list.size()]);
		
	}  
	
	public static MBPMBudgetCallLine[] getLines(Properties ctx, int BPM_BudgetCall_ID, String trxName){
		
		List<MBPMBudgetCallLine> list = new Query(ctx, I_BPM_BudgetCallLine.Table_Name, "BPM_BudgetCall_ID=?", trxName)
		.setParameters(BPM_BudgetCall_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MBPMBudgetCallLine[] retValue = new MBPMBudgetCallLine[list.size ()];
		
		list.toArray (retValue);
		
		return retValue;
		
	}
	
	public static MBPMBudgetCall[] getBudgetCallProject(int BPM_Project_ID){
		
		//
	    PreparedStatement pstmt = null;
		ResultSet rs = null;
		MBPMBudgetCall result = null;
		
		ArrayList<MBPMBudgetCall> list = new ArrayList<MBPMBudgetCall>();
		
		// 
		String sql_ = "SELECT * FROM "+X_BPM_BudgetCall.Table_Name+" \n " +
				" WHERE "+X_BPM_BudgetCall.COLUMNNAME_BPM_Project_ID+"="+BPM_Project_ID;
		try
		{
			pstmt = DB.prepareStatement(sql_,null);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				result = new MBPMBudgetCall(Env.getCtx(), rs, null);
				list.add(result);
			}				
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.INFO, "product", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
		
		return list.toArray(new MBPMBudgetCall[list.size()]);
		
	}

}
