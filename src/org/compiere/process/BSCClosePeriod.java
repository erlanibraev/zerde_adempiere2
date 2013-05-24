/**
 * 
 */
package org.compiere.process;

import java.util.List;

import org.compiere.model.MBSCCard;
import org.compiere.model.MPeriod;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 * Close period do BSC Card
 */
public class BSCClosePeriod extends SvrProcess {

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	
	protected int C_Period_ID = 0;
	protected MPeriod period = null;
	protected List<MBSCCard> cards = null;
	protected int AD_Client_ID = Env.getAD_Client_ID(getCtx());
	protected int AD_Org_ID = Env.getAD_Org_ID(getCtx());
	protected int C_DocType_ID = 0;
	protected String resultIt = null;  
//---------------------------------------------------------------------------
	@Override
	protected void prepare() {
		initParameters();
		
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		if (BSCClose()) {
			resultIt = "Период отчета по КПИ и ССП закрыт";
		}
		return resultIt;
	}
//---------------------------------------------------------------------------
	public int getC_DocType_ID() {
		StringBuffer sql = new StringBuffer();
		String clientCheck = " AND AD_Client_ID=" + getAD_Client_ID();
		sql.setLength(0);
        sql.append("SELECT C_DocType_ID FROM C_DocType"
		+ " WHERE Name='BSC_Card' AND isActive ='Y'"+clientCheck);        
        C_DocType_ID = DB.getSQLValue(get_TrxName(), sql.toString());
		return C_DocType_ID;
	}

	public int getC_Period_ID() {
		return C_Period_ID;
	}

	public void setC_Period_ID(int c_Period_ID) {
		C_Period_ID = c_Period_ID;
		MPeriod p = new MPeriod(Env.getCtx(),C_Period_ID,null);
		if (p != null) {
			setPeriod(p);
		}
	}

	public MPeriod getPeriod() {
		if (period == null && getC_Period_ID() > 0) {
			period = new MPeriod(Env.getCtx(),getC_Period_ID(),null);
		}
		return period;
	}

	public void setPeriod(MPeriod period) {
		this.period = period;
	}

	public List<MBSCCard> getCards() {
		return cards;
	}

	public void setCards(List<MBSCCard> cards) {
		this.cards = cards;
	}
//---------------------------------------------------------------------------
	/**
	 * 
	 */
	private void initParameters() {
		for(ProcessInfoParameter param: getParameter()) {
			if (param.getParameterName().equals("C_Period_ID")) {
				setC_Period_ID(Integer.parseInt(param.getParameter().toString()));
			}
		}
	}
	
	/**
	 * @return
	 */
	private boolean BSCClose() {
		boolean result = false;
		//TODO 
		
		return result;
	}


}