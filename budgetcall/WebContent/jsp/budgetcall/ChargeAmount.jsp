<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="main.org.action.ChargeAmount, main.org.model.Amount, java.text.*" %>
 
<%
 	ChargeAmount chargeTotal = (ChargeAmount) session.getAttribute("chargeamount");
 	Amount[] amt = chargeTotal.getAmt();
 	MCharge cha = new MCharge(Env.getCtx(), chargeTotal.getChargeID(), null);
 	MPeriod year = new MPeriod(Env.getCtx(), chargeTotal.getPeriodID(), null);
 	
 	DecimalFormatSymbols dfs2 = new DecimalFormatSymbols();
 	dfs2.setDecimalSeparator('.');
 	dfs2.setGroupingSeparator(',');
	DecimalFormat myFormatter2 = new DecimalFormat("###,###.00", dfs2);
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
	<div id="back" class="letter">
		<a class="aBack" title="Вернуться назад" href="<s:url action='index' >
					<s:param name="callID"><%= chargeTotal.getCallID() %></s:param>
					<s:param name="processID"><s:property value="processID" /></s:param>
				</s:url>" ><img src="images/back.png" /></a>
	</div>
	<%@ include file="/jsp/budgetcall/PrintExcel.jsp" %>
	
	<%@ include file="/jsp/budgetcall/PropertyBudgetCall.jsp" %>
	
	<table width="100%" cellspacing="2">
		<tr>
		 	<td width="90px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
		 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Статья</font>
		 	</td>
		 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
		 		<b><%= cha.getFI_Code() %>&nbsp;&nbsp;<%= cha.getName() %></b>
		 	</td>
		 	<td>&nbsp;</td>
		</tr>
		<%if(chargeTotal.getPeriodID() != 0){
		%>
		
			<tr>
			 	<td width="90px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
			 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Месяц</font>
			 	</td>
			 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
			 		<b><%= year.getName().substring(0, year.getName().indexOf("-")) %></b>
			 	</td>
			 	<td>&nbsp;</td>
			</tr>
		
		<%
		}
		%>
	</table>
	
	
	<table class="cursorHover more" width="60%" border="1" style="margin-top:20px;" align="center" bordercolor="#663300" cellspacing="2">
  		<tr class="trLightBlue">
  			<th scope="col">№ п.п.</th>
		    <th scope="col" width="60%">Наименование</th>
		    <th scope="col" width="10%">Сумма, тенге</th>
		</tr>
		<%	int k = 1;
		for(Amount a: amt) {	%>
			<tr>
				<td><b><%= k %></b></td>
				<td style="text-align: left; padding-left: 20px;"><b><a href="<s:url action='periodAmount' >
					<s:param name="callID"><%= chargeTotal.getCallID() %></s:param>
					<s:param name="chargeID"><%= chargeTotal.getChargeID() %></s:param>
					<s:param name="tableID"><%= a.getTableID() %></s:param>
					<s:param name="recordID"><%= a.getRecordID() %></s:param>
					<s:param name="periodID"><%= chargeTotal.getPeriodID() %></s:param>
					<s:param name="processID"><s:property value="processID" /></s:param>
					</s:url>"><%= a.getName() %></a></b>
				</td>
				<td><%= myFormatter2.format(Double.valueOf(a.getAmount())) %></td>
			</tr>
		<%
		k++;
		}
		%>
		<tr class="trLightBlue">
			<th scope="col">&nbsp;</th>
		    <th scope="col">Всего</th>
		    <th scope="col"><%= myFormatter2.format(Double.valueOf(chargeTotal.getsAmount())) %></th>
		</tr>
	</table>
	<%@ include file="/jsp/budgetcall/Footer.jsp" %>
	</div>
</body>
</html>