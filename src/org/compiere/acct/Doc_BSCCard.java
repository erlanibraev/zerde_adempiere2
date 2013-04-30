/**
 * 
 */
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAcctSchema;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class Doc_BSCCard extends Doc {

	/**
	 * @param ass
	 * @param clazz
	 * @param rs
	 * @param defaultDocumentType
	 * @param trxName
	 */
	Doc_BSCCard(MAcctSchema[] ass, Class<?> clazz, ResultSet rs,
			String defaultDocumentType, String trxName) {
		super(ass, clazz, rs, defaultDocumentType, trxName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.compiere.acct.Doc#loadDocumentDetails()
	 */
	@Override
	protected String loadDocumentDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.compiere.acct.Doc#getBalance()
	 */
	@Override
	public BigDecimal getBalance() {
		BigDecimal retValue = Env.ZERO;
		StringBuffer sb = new StringBuffer (" [");
		//  Total
//		retValue = retValue.add(getAmount(Doc.AMTTYPE_Gross));
//		sb.append(getAmount(Doc.AMTTYPE_Gross));
		//  - Header Charge
//		retValue = retValue.subtract(getAmount(Doc.AMTTYPE_Charge));
//		sb.append("-").append(getAmount(Doc.AMTTYPE_Charge));
		log.fine(toString() + " Balance=" + retValue + sb.toString());
		return retValue;
	}

	/* (non-Javadoc)
	 * @see org.compiere.acct.Doc#createFacts(org.compiere.model.MAcctSchema)
	 */
	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as) {
		ArrayList<Fact> result = new ArrayList<Fact>(); 
		return result;
	}

}
