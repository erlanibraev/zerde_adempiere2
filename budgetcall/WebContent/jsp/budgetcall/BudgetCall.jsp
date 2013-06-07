<%@page import="java.math.RoundingMode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="main.org.model.*, org.compiere.model.*, java.sql.*, org.compiere.util.*, java.util.*, java.math.BigDecimal" %>
<%@ page import="java.util.logging.*, org.adempiere.exceptions.DBException, main.org.action.BudgetCall" %>

<%
	BudgetCall bc = (BudgetCall) session.getAttribute("budgetcall"); 
	MPeriod[] period = Utils.getPeriod(bc.getCallID());
	BigDecimal[] sumMonth = new BigDecimal[period.length];
	//
	PreparedStatement pstmt = null;
	ResultSet rs = null;
%>

<%@ include file="/jsp/budgetcall/PrintExcel.jsp" %>
		
<%@ include file="/jsp/budgetcall/PropertyBudgetCall.jsp" %>

<table class="cursorHover more" width="100%" border="1" style="margin-top:20px;" bordercolor="#663300" cellspacing="2">
  <tr class="trLightBlue">
    <th scope="col">Код</th>
    <th scope="col">Наименование спецификифики (статьи)</th>
    <%
    	int j = 0;
	    for(MPeriod p: period){
	    	sumMonth[j] = new BigDecimal(0);
	    	%>
	    	 <th scope="col" width="90px"><%= p.getName().substring(0, p.getName().indexOf("-")) %></th>
	    	<%	  
	    	j++;
	    }
    %>
    <th scope="col">Всего по статье</th>
  </tr>
  
  <!--  -->	
	<%
		String sql_ = bc.getSql();
	    
		for(ChargeCode code: bc.getChargeCode()){
	%>
			<tr>
				<td>&nbsp;<b><%= code.getCode() %></b></td>
				<td style="text-align: left; padding-left: 10px;"><b><a href="<s:url action='chargeAmount' >
							<s:param name="callID"><%= bc.getCallID() %></s:param>
							<s:param name="chargeID"><%= code.getChargeID() %></s:param>
							<s:param name="processID"><s:property value="processID" /></s:param>
							</s:url>"><%= code.getName() %></a></b>
			</td>
	<%		
			BigDecimal total = new BigDecimal(0);
			int i = 0;
			for(MPeriod p: period){
					
				try
				{
					pstmt = DB.prepareStatement (sql_, null);
					pstmt.setInt(1, bc.getCallID());
					pstmt.setInt(2, code.getChargeID());
					pstmt.setInt(3, p.getC_Period_ID());
					rs = pstmt.executeQuery();
					
					while (rs.next ())
					{
						if(rs.getDouble(1) != 0){
							sumMonth[i] = sumMonth[i].add(new BigDecimal(rs.getDouble(1))); 
							total = total.add(new BigDecimal(rs.getDouble(1)));
					%>							
						<td><a href="<s:url action='chargeAmount' >
							<s:param name="callID"><%= bc.getCallID() %></s:param>
							<s:param name="chargeID"><%= code.getChargeID() %></s:param>
							<s:param name="periodID"><%= p.getC_Period_ID() %></s:param>
							<s:param name="processID"><s:property value="processID" /></s:param>
							</s:url>"><%= rs.getString(1) %></a></td>
					<%
						}
						else{
					%>
						<td class="tdWheat">&nbsp;</td>
					<%
						}
					}
				}
				catch (SQLException e)
				{
					CLogger.get().log(Level.INFO, sql_, e);
					throw new DBException(e, sql_);
				} finally {
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
				i++;
			}
	%>
				<td>&nbsp;<b><font color="073560"><%= String.valueOf(total.setScale(2, RoundingMode.HALF_UP)) %></font></b></td>
				</tr>
	<%
		}

	%>
 
	<tr class="trLightBlue">
    	<th scope="row">&nbsp;</th>
    	<th>Всего за период</th>
    <%
    	int t = 0;
	    for(MPeriod p: period){
	    	%>
	    	 <th>&nbsp;<%= String.valueOf(sumMonth[t].setScale(2, RoundingMode.HALF_UP)) %></th>
	    	<%	  
	    	t++;
	    }
    %>
    	<th>&nbsp;</th>
  	</tr>
</table>