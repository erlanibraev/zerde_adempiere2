package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

public class MVariable extends X_BSC_Variable {

	public MVariable(Properties ctx, int BSC_Variable_ID, String trxName) {
		super(ctx, BSC_Variable_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVariable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getVariable() {
		BigDecimal result = new BigDecimal(0);
		result = getVariable(null);
		return result;
	}
	
	public BigDecimal getVariable(MPeriod period) {
		BigDecimal result = new BigDecimal(0);
		MParameter parameter = (MParameter) getBSC_Parameter();
		if (parameter != null) {
			parameter.setPeriod(period);
			result = parameter.getValueNumber();
		}
		return result;
	}
}
