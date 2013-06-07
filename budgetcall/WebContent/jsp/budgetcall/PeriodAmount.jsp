<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="main.org.action.PeriodAmount, main.org.model.Period" %>

<%
	PeriodAmount periodTotal = (PeriodAmount) session.getAttribute("periodamount");
	Period[] period = periodTotal.getPeriod();
	int rowSpan = period.length;
	MCharge charge = new MCharge(Env.getCtx(), periodTotal.getChargeID(), null);
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Budget Call</title>
	<link rel="shortcut icon" href="images/logo.png" type="image/jpg" /> 	
	<link type="text/css" href="css/Main.css" rel="stylesheet">
</head>
<body>
	<div>
		<div  id="back" class="letter">
			<a class="aBack" title="Вернуться назад" href="<s:url action='chargeAmount' >
					<s:param name="callID"><s:property value="callID" /></s:param>
					<s:param name="chargeID"><s:property value="chargeID" /></s:param>
					<s:param name="periodID"><s:property value="periodID" /></s:param>
					<s:param name="processID"><s:property value="processID" /></s:param>
				</s:url>" ><img src="images/back.png" /></a>
		</div>
		<%@ include file="/jsp/budgetcall/PrintExcel.jsp" %>
				
		<%@ include file="/jsp/budgetcall/PropertyBudgetCall.jsp" %>
		
		<table width="100%" cellspacing="2">
			<tr>
			 	<td width="70px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
			 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Статья</font>
			 	</td>
			 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
			 		<b><%= charge.getFI_Code() %>&nbsp;&nbsp;<%= charge.getName() %></b>
			 	</td>
			 	<td>&nbsp;</td>
			</tr>
			<tr>
			 	<td width="70px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
			 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Вид</font>
			 	</td>
			 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
			 		<b><%= period[0].getDescription() %></b>
			 	</td>
			 	<td>&nbsp;</td>
			</tr>
		</table>
		
		<table class="more" width="100%" border="1" style="margin-top:20px;" align="center" bordercolor="#663300" cellspacing="2">
  			<tr class="trLightBlue">
		    	<th scope="col" width="40%">Наименование</th>
		    	<th scope="col" width="10%">Ед. изм.</th>
		    	<th scope="col" width="10%">Месяц</th>
		    	<th scope="col" width="10%">Кол-во</th>
		    	<th scope="col" width="10%">Стоимость за единицу, тенге</th>
		    	<th scope="col" width="10%">Сумма, тенге</th>
		    	<th scope="col" width="10%">Оплата</th>
			</tr>
			<%
				if(rowSpan > 0){
			%>
				<tr>
					<td style="text-align: center; padding-left: 20px;" rowspan="<%= rowSpan %>"><b><%= period[0].getName() %></b></td>
			<%
				}
				
				int n = 0; 
				for(Period p: periodTotal.getPeriod()){
					
					// rowspan
					if(n > 0){
			%>
					<tr>			
			<%
					}
			%>
						<td><%= p.getUom() %></td>
						<td><b><%= p.getMonth().substring(0, p.getMonth().indexOf("-")) %></b></td>
						<td><%= p.getQuantity() %></td>
						<td><%= p.getAmountUnit() %></td>
						<td><b><%= p.getAmount() %></b></td>
						<td><%= p.getPayment() %></td>
					</tr>				
			<%
					n++;
				} 
			%>
			<tr class="trLightBlue">
		    	<th scope="col">Всего</th>
		    	<th scope="col">&nbsp;</th>
		    	<th scope="col">&nbsp;</th>
		    	<th scope="col"><%= periodTotal.getsQuantity() %></th>
		    	<th scope="col">&nbsp;</th>
		    	<th scope="col"><%= periodTotal.getsAmount() %></th>
		    	<th scope="col">&nbsp;</th>
			</tr>
		</table>
		<%@ include file="/jsp/budgetcall/Footer.jsp" %>
	</div>
</body>
</html>