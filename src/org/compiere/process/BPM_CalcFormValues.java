package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.apps.IProcessParameter;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.model.I_BPM_Form;
import org.compiere.model.I_BPM_Project;
import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormCell;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MBPMFormParameters;
import org.compiere.model.MBPMFormValues;
import org.compiere.model.MBPMProject;
import org.compiere.model.MParameterLine;
import org.compiere.model.MVariable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

import extend.org.compiere.utils.Util;

public class BPM_CalcFormValues extends SvrProcess {

	/** Current context			*/
	private Properties m_ctx;
	/**	Optional Transaction 	*/
	private String	m_trxName = null;
	/** */
	private int BPM_Form_ID = 0;
	/** */
	public int BPM_Project_ID = MBPMProject.TempProjectID;  
	/** */
	private MBPMForm form = null;
	private MBPMFormLine formLine[] = null;
	private MBPMFormCell formCell[] = null;
	/**/
	private int AD_Process_ID;
	
	@Override
	protected void prepare() {

		m_ctx = getCtx();
		m_trxName = get_TrxName();
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
		
		form = new MBPMForm(m_ctx, BPM_Form_ID, m_trxName);
		formLine = MBPMForm.getLines(m_ctx, form.getBPM_Form_ID(), m_trxName);
		

	}

	@Override
	protected String doIt() throws Exception {
		
		for(MBPMFormLine l: formLine){
			
			formCell = MBPMFormLine.getLines(m_ctx, l.getBPM_FormLine_ID(), m_trxName);
			for(MBPMFormCell cell: formCell){
				
				MBPMFormValues value = MBPMFormValues.getFormValueLine(l.getBPM_FormLine_ID(), cell.getBPM_FormColumn_ID(), form.getBPM_VersionBudget_ID(), BPM_Project_ID);
				if(value == null)
					value = new MBPMFormValues(m_ctx, 0, m_trxName);
				value.setBPM_Form_ID(l.getBPM_Form_ID());
				value.setBPM_FormLine_ID(l.getBPM_FormLine_ID());
				value.setBPM_FormColumn_ID(cell.getBPM_FormColumn_ID());
				value.setBPM_VersionBudget_ID(form.getBPM_VersionBudget_ID());
				value.setBPM_Project_ID(BPM_Project_ID);
				
				if(cell.getCellValue().doubleValue() == 0){
					
					LinkedHashMap<String, Object> obj = getParameters(cell.getBPM_FormCell_ID());
					
					value.setCellValue(new BigDecimal(0));
					value.setAlternateValue("");
					
					MParameterLine parLine = getLineParameter(cell.getBSC_Parameter_ID());
					
					//
					String result = "0";
					if(obj.size() == 0){
						Map<String, MVariable> map = parLine.getVariables();
						LinkedHashMap<String, LinkedHashMap<String, Object>> prs = new LinkedHashMap<String, LinkedHashMap<String, Object>>();
						for(String key: map.keySet()){
							MVariable var = map.get(key);  
							if(var.getBSC_Parameter_ID() > 0) {
								MBPMFormCell[] fCell = MBPMFormCell.getLinesParameter(m_ctx, var.getBSC_Parameter_ID(), m_trxName);
								for(MBPMFormCell ce: fCell){
									obj = getParameters(ce.getBPM_FormCell_ID());
									prs.put(var.getName(), obj);
								}
							}
						}
						if(prs.size() != 0)
							result = parLine.calculate2(prs);
						else
							result = parLine.calculate();
					}else{
						result = parLine.calculate(obj);
					}
					
					try{
						value.setCellValue(new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP));
					}catch(Exception ex){
						value.setAlternateValue(result);
					}
					finally{
						value.saveEx();
					}
				}else{
					value.setCellValue(cell.getCellValue().setScale(2, BigDecimal.ROUND_HALF_UP));
					value.saveEx();
				}
			}
			
		}
		
		// 
		Trx trx = Trx.get(m_trxName, false);
		trx.commit();
		trx.close();
		
		// Run Excel
		if(BPM_Project_ID == MBPMProject.TempProjectID)
			return runExcel(BPM_Form_ID, BPM_Project_ID);
		else
			return Msg.translate(m_ctx, "Success");
	}
	
	private MParameterLine getLineParameter(int BSC_Parameter_ID){
		
		CLogger log = CLogger.getCLogger (MParameterLine.class);
		MParameterLine result = new MParameterLine(m_ctx, 0, m_trxName);
		String sql = "SELECT * FROM BSC_ParameterLine WHERE isActive = 'Y' AND BSC_Parameter_ID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt (1, BSC_Parameter_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new MParameterLine(Env.getCtx(),rs,null);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MParameterLine: ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
	
		return result;
	}
	
	private String runExcel(int BPM_Form_ID, int BPM_Project_ID){
		
		String nameProcess = DSR_GenerateReport.class.getName();		
		
		// org.compiere.process.DSR_GenerateReport
		AD_Process_ID = Util.getAD_Process(nameProcess.substring(nameProcess.lastIndexOf(".")+1, nameProcess.length()));
		
		if(AD_Process_ID == 0)
			return  "The process can not be found";
		ProcessInfo pi = new ProcessInfo ("The run Excel form values", AD_Process_ID);
		//  Prepare Process
		pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
		pi.setAD_Client_ID(Env.getAD_Client_ID(m_ctx));

		List<ProcessInfoParameter> po = new ArrayList<ProcessInfoParameter>();
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
	
	private LinkedHashMap<String, Object> getParameters(int BPM_FormCell_ID){
		
		MBPMFormParameters[] parameters = MBPMFormParameters.getLines(m_ctx, BPM_FormCell_ID, m_trxName);
		LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();
		
		String in = "";
		int counter = 0;
		boolean isChanged = false;
		
		String type = parameters.length > 0 ? parameters[0].getParameterType() : "";
		
		for(MBPMFormParameters par: parameters){
			
			isChanged = !type.equals(par.getParameterType());
			
			if(!isChanged)
			{
				if(counter > 0)
					in += ", ";
				
				counter++;
			}
			else
			{
				counter = 1;
				in = "";
			}
			in += par.get_Value(par.getParameterType()).toString();
			
			param.put(par.getParameterType(), in);
			
			type = par.getParameterType();
		}
		
		if(BPM_Project_ID != MBPMProject.TempProjectID && BPM_Project_ID != 0)
			param.put(I_BPM_Project.COLUMNNAME_BPM_Project_ID, String.valueOf(BPM_Project_ID));
		
		return param;
		
	}

}
