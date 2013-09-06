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
	BigDecimal[] sumMonth = new BigDecimal[period.length+4]; // 4 = count Quarter
	//
	PreparedStatement pstmt = null;
	ResultSet rs = null;
%>

<%@ include file="/jsp/budgetcall/PrintExcel.jsp" %>
		
<%@ include file="/jsp/budgetcall/PropertyBudgetCall.jsp" %>

<table class="cursorHover more" width="100%" border="1" style="margin-top:20px;" bordercolor="#663300" cellspacing="2">
  <tr class="trLightBlue">
    <th scope="col">Код</th>
    <th scope="col" width="250px">Наименование специфики (статьи)</th>
    <%
    	int j = 0;
    	int mo = 1;
	    for(MPeriod p: period){
	    	sumMonth[j] = new BigDecimal(0);
	    	if(!bc.isQuarter(mo)){
		    	%>
		    	 <th scope="col" width="90px"><%= p.getName().substring(0, p.getName().indexOf("-")) %></th>
		    	<%
	    	}else{
	    		j++;
	    		sumMonth[j] = new BigDecimal(0);
	    		String qS = String.valueOf(mo/3);
	    		%>
	    		<th scope="col" width="90px"><%= p.getName().substring(0, p.getName().indexOf("-")) %></th>
	    		<th class="tdQuarter" scope="col" width="90px"><%= qS %> квартал</th>
	    		<%
	    	}
	    	j++;
	    	mo++;
	    }
    %>
    <th scope="col">Всего по Статье / Заявке</th>
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
			int quarter = 1;
			int quarter_ = 0;
			String q = "";
			int n = 1;
			int jj = 0;
			LinkedHashMap<Integer, String> quar = new LinkedHashMap<Integer, String>();
			for(MPeriod p: period){
				
				if(n == 1){
					q = "";
					q += p.getC_Period_ID();
				}
				else
					q += "," + p.getC_Period_ID();
				
				double periodAmt = bc.getPeriodAmount(bc.getCallID(), code.getChargeID(), p.getC_Period_ID(), quar, 0);
				if(periodAmt != 0){
					sumMonth[jj] = sumMonth[jj].add(new BigDecimal(periodAmt));
					total = total.add(new BigDecimal(periodAmt));
				%>							
					<td><a href="<s:url action='chargeAmount' >
						<s:param name="callID"><%= bc.getCallID() %></s:param>
						<s:param name="chargeID"><%= code.getChargeID() %></s:param>
						<s:param name="periodID"><%= p.getC_Period_ID() %></s:param>
						<s:param name="processID"><s:property value="processID" /></s:param>
						</s:url>"><%= String.valueOf(periodAmt) %></a></td>
				<%
					}
					else{
				%>
					<td class="tdWheat">&nbsp;</td>
				<%
					}
				
				if(bc.isQuarter(quarter)){
					jj++;
					quar.put(quarter, q);
					quarter_ = quarter;
					n = 0;
				}else
					quarter_ = 0;
				
				if(bc.isQuarter(quarter)){
					periodAmt = bc.getPeriodAmount(bc.getCallID(), code.getChargeID(), p.getC_Period_ID(), quar, quarter_);
						if(periodAmt != 0){
							sumMonth[jj] = sumMonth[jj].add(new BigDecimal(periodAmt));
						%>							
							<td class="tdQuarter"><font size="2" style="font-weight: bold;"><%= String.valueOf(periodAmt) %></font></td>
						<%
							}
							else{
						%>
							<td class="tdQuarter">&nbsp;</td>
						<%
						}
				}
				
				i++;
				quarter++;
				n++;
				jj++;
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
    	BigDecimal totalCall = new BigDecimal(0);
    	String classQuarter = "";
	    for(int t = 0; t < sumMonth.length; t++){
	    	if(((t+1) % 4) == 0 && t != 0){
	    		totalCall = totalCall.add(sumMonth[t].setScale(2, RoundingMode.HALF_UP));
	    		classQuarter = "class=\"tdQuarter\"";
	    	}else
	    		classQuarter = "";
	    	%>
	    	 <th <%= classQuarter %>>&nbsp;<%= String.valueOf(sumMonth[t].setScale(2, RoundingMode.HALF_UP)) %></th>
	    	<%	  
	    }
    %>
    	<th>&nbsp;<font size="3"><%= totalCall.setScale(2, RoundingMode.HALF_UP) %></font></th>
  	</tr>
</table>