package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.model.I_BPM_Form;
import org.compiere.model.I_BPM_Project;
import org.compiere.model.I_BSC_ParameterLine;
import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormCell;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MBPMFormParameters;
import org.compiere.model.MBPMFormValues;
import org.compiere.model.MParameterLine;
import org.compiere.model.Query;
import org.compiere.model.X_BSC_ParameterLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class BPM_CalcFormValues extends SvrProcess {

	/** Current context		*/
	private Properties m_ctx;
	/** */
	private int BPM_Form_ID = 0;
	/** */
	public static final int Project_ID = 1000;  // TODO The test project to preliminary calculations
	public int BPM_Project_ID = Project_ID;  
	/** */
	private MBPMForm form = null;
	private MBPMFormLine formLine[] = null;
	private MBPMFormCell formCell[] = null;
	/**/
	private int AD_Process_ID;
	
	@Override
	protected void prepare() {

		m_ctx = Env.getCtx();
		BPM_Form_ID = getRecord_ID();
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)	{
			String name = para[i].getParameterName();
			if (name == null); 
				//
			else if (name.compareTo(I_BPM_Form.COLUMNNAME_BPM_Form_ID) == 0 && para[i].getParameter() != null)
				BPM_Form_ID = new BigDecimal(Integer.parseInt(para[i].getParameter().toString())).intValue();
			else if (name.compareTo(I_BPM_Project.COLUMNNAME_BPM_Project_ID) == 0 && para[i].getParameter() != null)
				BPM_Project_ID = new BigDecimal(Integer.parseInt(para[i].getParameter().toString())).intValue();
			else
			{
				log.log(Level.INFO, "Unknown Parameter: " + name);
			}
		}
		
		form = new MBPMForm(m_ctx, BPM_Form_ID, get_TrxName());
		formLine = MBPMForm.getLines(m_ctx, form.getBPM_Form_ID(), get_TrxName());
		

	}

	@Override
	protected String doIt() throws Exception {
		
		for(MBPMFormLine l: formLine){
			
			formCell = MBPMFormLine.getLines(m_ctx, l.getBPM_FormLine_ID(), get_TrxName());
			for(MBPMFormCell cell: formCell){
				
				MBPMFormValues value = MBPMFormValues.getFormValueLine(l.getBPM_FormLine_ID(), cell.getBPM_FormColumn_ID(), form.getBPM_VersionBudget_ID(), BPM_Project_ID);
				if(value == null)
					value = new MBPMFormValues(m_ctx, 0, get_TrxName());
				value.setBPM_Form_ID(l.getBPM_Form_ID());
				value.setBPM_FormLine_ID(l.getBPM_FormLine_ID());
				value.setBPM_FormColumn_ID(cell.getBPM_FormColumn_ID());
				value.setBPM_VersionBudget_ID(form.getBPM_VersionBudget_ID());
				value.setBPM_Project_ID(BPM_Project_ID);
				
				if(cell.getBSC_Parameter_ID() != 0){
					
					MBPMFormParameters[] parameters = MBPMFormParameters.getLines(getCtx(), cell.getBPM_FormCell_ID(), get_TrxName());
					LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
					String in = "";
					int n = 0;
					for(MBPMFormParameters par: parameters){
						if(parameters.length > 1){
							if(n != 0) in += ",";
							in += par.getC_Charge_ID();
							obj.put(MBPMFormParameters.COLUMNNAME_C_Charge_ID, in);
						}
						else
							obj.put(MBPMFormParameters.COLUMNNAME_C_Charge_ID, String.valueOf(par.getC_Charge_ID()));
						n++;
					}
					
					value.setCellValue(new BigDecimal(0));
					value.setAlternateValue("");
					
					for(X_BSC_ParameterLine parLine: getLineParameter(cell.getBSC_Parameter_ID())){
						MParameterLine par = new MParameterLine(m_ctx, parLine.getBSC_ParameterLine_ID(), get_TrxName());
						String result = par.calculate(obj);
						try{
							value.setCellValue(new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP));
						}catch(Exception ex){
							value.setAlternateValue(result);
						}
						finally{
							value.saveEx();
						}
					}
				}else{
					value.setCellValue(cell.getCellValue().setScale(2, BigDecimal.ROUND_HALF_UP));
					value.saveEx();
				}
			}
			
		}
		
		// Run Excel
		if(BPM_Project_ID == 1000)
			return runExcel(BPM_Form_ID, BPM_Project_ID);
		else
			return Msg.translate(m_ctx, "Success");
	}
	
	private X_BSC_ParameterLine[] getLineParameter(int BSC_Parameter_ID){
		
		List<X_BSC_ParameterLine> list = new Query(m_ctx, I_BSC_ParameterLine.Table_Name, X_BSC_ParameterLine.COLUMNNAME_BSC_Parameter_ID+" = ? ", null)
		.setParameters(BSC_Parameter_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		X_BSC_ParameterLine[] retValue = new X_BSC_ParameterLine[list.size()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}
	
	private String runExcel(int BPM_Form_ID, int BPM_Project_ID){
		
		String nameProcess = "DSR_Generate";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// The name must start with a report hrm_order
		String sql_ = "select distinct t.ad_process_id from ad_process t "+ 
					  "where (lower(t.value) like lower('%"+nameProcess+"%') or lower(t.name) like lower('%"+nameProcess+"%')) "+ 
				      "and (lower(t.value) like lower('%"+nameProcess+"%') or lower(t.name) like lower('%"+nameProcess+"%'))";
		try
		{
			pstmt = DB.prepareStatement(sql_, null);
			rs = pstmt.executeQuery();
			if (rs.next())
				AD_Process_ID = rs.getInt(1);
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
		
		if(AD_Process_ID == 0)
			return  "The process can not be found";
		ProcessInfo pi = new ProcessInfo ("The run Excel form values", AD_Process_ID);
		//  Prepare Process
		pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
		pi.setAD_Client_ID(Env.getAD_Client_ID(m_ctx));

		List<ProcessInfoParameter> po = new ArrayList<ProcessInfoParameter>();
		po.clear();
		po.add(new ProcessInfoParameter(I_BPM_Form.COLUMNNAME_BPM_Form_ID, BPM_Form_ID,null,"",""));
		po.add(new ProcessInfoParameter(I_BPM_Project.COLUMNNAME_BPM_Project_ID, BPM_Project_ID,null,"",""));
		ProcessInfoParameter[] pp = new ProcessInfoParameter[po.size()];
		po.toArray(pp);
		pi.setParameter(pp);
		//	Execute Process
		ProcessParameterPanel pu = new ProcessParameterPanel(0, pi);
		ProcessCtl.process(null, 0, (IProcessParameter) pu, pi, null);
		
		return Msg.translate(m_ctx, "Success");
	}

}
