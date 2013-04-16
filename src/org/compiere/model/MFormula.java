package org.compiere.model;
/**
 * @autor Ibrayev Y.A.
 */

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

public class MFormula extends X_BSC_Formula {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5300225632681322857L;

	private HashMap<String,Object> arguments = null;
	private Set<String> variables = null;

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MFormula(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MFormula(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public MFormula(Properties ctx, int BSC_Formula_ID, String trxName) {
		super(ctx, BSC_Formula_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public HashMap<String,Object> getArguments() {
		return arguments;
	}

	public void setArguments(HashMap<String,Object> arguments) {
		this.arguments = arguments;
	}

	protected Set<String> getVariables() {
		MVariables var = new MVariables(getFormula());
		variables = var.getVariables();
		return variables;
	}
	
	public BigDecimal calculate() {
		BigDecimal result=null;
		try {
			result = new BigDecimal(calc(getFormula(),getArguments()));
		} catch(Exception e) {
			System.err.println(e);
			result = null;
		}
		return result;
	}
	
	/**
	 * @param exp - Expression which will be designed
	 * @param args - Input arguments in a hash table 
	 * @return String - The result of calculation or null 
	 */
	public static String calc(String exp, HashMap<String,Object> args) {
		String result = null;
		try {
			JexlEngine jexl = new JexlEngine();
			Expression e = jexl.createExpression(exp);
			JexlContext jc = new MapContext();
			if (args != null) {
				for(Object key: args.keySet()) {
					jc.set(key.toString(), args.get(key));
				}
			}
			Object o = e.evaluate(jc);
			result = (o == null ? null : o.toString());
		} catch (Exception e) {
			System.err.println(e);
			result = null;
		}
		return result;
	}
	
	protected class MVariables {
	    
		private Set<String> MAIN_MATH_OPERATIONS;	    
		private String expression = null;
		private Set<String> variables;
		
		public Set<String> getVariables() {
			calcVariables();
			return variables;
		}

		public void setVariables(Set<String> variables) {
			this.variables = variables;
		}

		public String getExpression() {
			return expression;
		}

		public void setExpression(String expression) {
			this.expression = expression;
		}

		public MVariables(String expression) {
	        MAIN_MATH_OPERATIONS = new HashSet<String>();
	        MAIN_MATH_OPERATIONS.add("*");
	        MAIN_MATH_OPERATIONS.add("/");
	        MAIN_MATH_OPERATIONS.add("+");
	        MAIN_MATH_OPERATIONS.add("-");
	        MAIN_MATH_OPERATIONS.add("(");
	        MAIN_MATH_OPERATIONS.add(")");
	        MAIN_MATH_OPERATIONS.add("?");
	        MAIN_MATH_OPERATIONS.add(":");
	        MAIN_MATH_OPERATIONS.add("=");
	        MAIN_MATH_OPERATIONS.add("!");
	        MAIN_MATH_OPERATIONS.add("|");
	        MAIN_MATH_OPERATIONS.add("&");
	        MAIN_MATH_OPERATIONS.add("%");
	        MAIN_MATH_OPERATIONS.add("<");
	        MAIN_MATH_OPERATIONS.add(">");
	        MAIN_MATH_OPERATIONS.add("=");
			setExpression(expression);
			variables = new HashSet<String>();
		}
		
		protected void calcVariables() {
			String exp = expression;
			if (exp == null) {
				return;
			}
			exp=exp.replace(" ", "");
			int index = 0;
			variables.clear();
			while (index < exp.length()) {
				int nextOperationIndex = exp.length();
				for(String operation:MAIN_MATH_OPERATIONS) {
					int  i = exp.indexOf(operation, index);
					if (i >=0 && i < nextOperationIndex) {
						nextOperationIndex = i;
					}
				}
				if (index < nextOperationIndex) {
					String var = exp.substring(index, nextOperationIndex);
					if (!variableException(var)) {
						variables.add(var);
					}
				}
				index = nextOperationIndex+1;
			}
		}
		
		protected boolean variableException(String variable) {
			boolean result = isNumeric(variable) || isString(variable);
			return result;
		}
		
		protected boolean isNumeric(String variable) {
			boolean result = false;
			result = (variable == null ? false : variable.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") );
			return result;
		}

		protected boolean isString(String variable) {
			boolean result =false;
			result = (variable.indexOf("'") != -1) || (variable.indexOf("\"") != -1); 
			return result;
		}
	}

}
