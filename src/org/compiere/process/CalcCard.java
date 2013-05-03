/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCCardLine;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class CalcCard extends SvrProcess {

	private int BSC_Card_ID = 0;

	public int getBSC_Card_ID() {
		return BSC_Card_ID;
	}

	public void setBSC_Card_ID(int bSC_Card_ID) {
		BSC_Card_ID = bSC_Card_ID;
	}

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
			} else if(name.equals("BSC_Card_ID")) {
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				setBSC_Card_ID(tmp.intValue()) ;
			} else {
				log.log(Level.SEVERE,"CalcCard: Unknown parameter - "+name);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO,"CalcCard: BSC_Card_ID - "+getBSC_Card_ID());
		if (getBSC_Card_ID() > 0) {
			MBSCCard card = new MBSCCard(Env.getCtx(),getBSC_Card_ID(),null);
			BigDecimal result = card.calculate();
			log.log(Level.INFO,"CalcCard: result - "+result.toString());
		}
		return null;
	}

}
