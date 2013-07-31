package org.compiere.pfr;

public interface I_PrepareQuery 
{
	public String getWhereClause();
	
	public String getOrderBy();
	
	public String getGroupBy();
	
	public String getSelectFrom();
	
	public String getAggregateValuesToSelect();
}
