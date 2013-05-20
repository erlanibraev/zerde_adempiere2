/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MParameterLine;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalcParameter extends SvrProcess {

	private int BSC_ParameterLine_ID = 0;
	
	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for(int i=0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(para[i].getParameter() == null ) {
				;
			} else if(name.equals("BSC_ParameterLine_ID")) {
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				BSC_ParameterLine_ID = tmp.intValue() ;
			} else {
				log.log(Level.SEVERE,"CalcParameter: Unknown parameter - "+name);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO,"CalcParameter: BSC_ParameterLine_ID - "+BSC_ParameterLine_ID);
		if (BSC_ParameterLine_ID > 0) {
			MParameterLine parameterLine = new MParameterLine(Env.getCtx(),BSC_ParameterLine_ID,null);
			String result = parameterLine.calculate();
			log.log(Level.INFO,"CalcParameter: result - "+result.toString());
			parameterLine.setValue(result);
			parameterLine.save();
		}
		return null;
	}

}
