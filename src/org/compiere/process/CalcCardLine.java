/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MBSCCardLine;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalcCardLine extends SvrProcess {

	private int BSC_CardLine_ID = 0;
	
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
			} else if(name.equals("BSC_CardLine_ID")) {
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				BSC_CardLine_ID = tmp.intValue() ;
			} else {
				log.log(Level.SEVERE,"CalcCardLine: Unknown parameter - "+name);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO,"CalcCardLine: BSC_CardLine_ID - "+BSC_CardLine_ID);
		if (BSC_CardLine_ID > 0) {
			MBSCCardLine cardLine = new MBSCCardLine(Env.getCtx(),BSC_CardLine_ID,null);
			BigDecimal result = cardLine.calculate();
			log.log(Level.INFO,"CalcCardLine: result - "+result.toString());
			cardLine.setValueNumber(result);
			cardLine.save();
		}
		return null;
	}

}
