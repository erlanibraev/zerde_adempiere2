package org.compiere.pfr;

import org.compiere.model.MPFRCalculation;

public class PrepareQuery implements I_PrepareQuery 
{
	StringBuilder query = new StringBuilder();
	MPFRCalculation calculation = null;
	
	public PrepareQuery(MPFRCalculation calculation)
	{
		this.calculation = calculation;
	}
	
	@Override
	public String getWhereClause() 
	{
		return null;
	}

	@Override
	public String getOrderBy() 
	{
		return null;
	}

	@Override
	public String getGroupBy() 
	{
		return null;
	}

	@Override
	public String getSelectFrom() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("");
		return null;
	}

	@Override
	public String getAggregateValuesToSelect() 
	{
		return null;
	}

}
