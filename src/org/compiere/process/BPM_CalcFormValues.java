package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import org.compiere.model.I_BSC_ParameterLine;
import org.compiere.model.MBPMForm;
import org.compiere.model.MBPMFormCell;
import org.compiere.model.MBPMFormLine;
import org.compiere.model.MParameterLine;
import org.compiere.model.Query;
import org.compiere.model.X_BPM_FormValues;
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
				
				X_BPM_FormValues value = new X_BPM_FormValues(m_ctx, 0, get_TrxName());
				value.setBPM_FormLine_ID(l.getBPM_FormLine_ID());
				value.setBPM_FormColumn_ID(cell.getBPM_FormColumn_ID());
				
				if(cell.getBSC_Parameter_ID() != 0){
					for(X_BSC_ParameterLine parLine: getLineParameter(cell.getBSC_Parameter_ID())){
						MParameterLine par = new MParameterLine(m_ctx, parLine.getBSC_ParameterLine_ID(), get_TrxName());
						String result = par.calculate();
						value.setAlternateValue(result);
						try{
							value.setCellValue(new BigDecimal(Double.parseDouble(result)));
						}catch(Exception ex){ /* */ }
						finally{
							value.saveEx();
						}
					}
				}else{
					value.setCellValue(cell.getCellValue());
					value.saveEx();
				}
			}
			
		}
		
		return Msg.translate(m_ctx, "Success");
	}
	
	private X_BSC_ParameterLine[] getLineParameter(int BSC_Parameter_ID){
		
		List<X_BSC_ParameterLine> list = new Query(m_ctx, I_BSC_ParameterLine.Table_Name, "BSC_Parameter_ID=?", null)
		.setParameters(BSC_Parameter_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		X_BSC_ParameterLine[] retValue = new X_BSC_ParameterLine[list.size()];
		
		list.toArray(retValue);
		
		return retValue;
		
	}

}