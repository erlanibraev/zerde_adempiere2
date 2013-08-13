package org.compiere.process;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.compiere.model.I_BSC_ParameterLine;
import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormCell;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MBPMFormParameters;
import org.compiere.model.MBPMFormValues;
import org.compiere.model.MParameterLine;
import org.compiere.model.Query;
import org.compiere.model.X_BSC_ParameterLine;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class BPM_CalcFormValues extends SvrProcess {

	/** Current context		*/
	private Properties m_ctx;
	/** */
	private MBPMForm form = null;
	private MBPMFormLine formLine[] = null;
	private MBPMFormCell formCell[] = null;
	
	@Override
	protected void prepare() {

		m_ctx = Env.getCtx();
		
		form = new MBPMForm(m_ctx, getRecord_ID(), get_TrxName());
		formLine = MBPMForm.getLines(m_ctx, form.getBPM_Form_ID(), get_TrxName());

	}

	@Override
	protected String doIt() throws Exception {
		
		for(MBPMFormLine l: formLine){
			
			formCell = MBPMFormLine.getLines(m_ctx, l.getBPM_FormLine_ID(), get_TrxName());
			for(MBPMFormCell cell: formCell){
				
				MBPMFormValues value = MBPMFormValues.getFormValueLine(l.getBPM_FormLine_ID(), cell.getBPM_FormColumn_ID());
				if(value == null)
					value = new MBPMFormValues(m_ctx, 0, get_TrxName());
				value.setBPM_Form_ID(l.getBPM_Form_ID());
				value.setBPM_FormLine_ID(l.getBPM_FormLine_ID());
				value.setBPM_FormColumn_ID(cell.getBPM_FormColumn_ID());
				
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

}
