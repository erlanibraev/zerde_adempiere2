package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MVariable extends X_BSC_Variable {

	private MParameter parameter = null;
	private MParameterLine parameterLine = null;
	
	public MParameterLine getParameterLine() {
		if (parameterLine == null || parameterLine.getBSC_ParameterLine_ID() != getBSC_ParameterLine_ID()) {
			setParameterLine(new MParameterLine(Env.getCtx(),getBSC_ParameterLine_ID(),get_TrxName()));
		}
		return parameterLine;
	}

	public void setParameterLine(MParameterLine parameterLine) {
		this.parameterLine = parameterLine;
	}

	public MParameter getParameter() {
		if (parameter == null || parameter.getBSC_Parameter_ID() != getBSC_Parameter_ID()) {
			setParameter(new MParameter(Env.getCtx(),getBSC_Parameter_ID(),get_TrxName()));
		}
		return parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
	}

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
		if (getParameter() != null) {
			getParameter().setPeriod(period);
			result = getParameter().getValueNumber();
		}
		return result;
	}
}
