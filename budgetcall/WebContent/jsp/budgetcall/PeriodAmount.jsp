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
	String selectPeriod = "bgcolor=\"#DAE1EA\"";
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Budget Call</title>
	<link rel="shortcut icon" href="images/logo.png" type="image/jpg" /> 	
	<link type="text/css" href="css/Main.css" rel="stylesheet">
	<%@ include file="/js/JScript.jsp" %>
	<script type="text/javascript">
		countRow = <%= period.length %>
	</script>
</head>
<body>
	<div>
		<div  id="back" class="letterLeft letter">
			<a class="aBack" title="Вернуться назад" href="<s:url action='chargeAmount' >
					<s:param name="callID"><s:property value="callID" /></s:param>
					<s:param name="chargeID"><s:property value="chargeID" /></s:param>
					<s:param name="periodID"><s:property value="periodID" /></s:param>
					<s:param name="processID"><s:property value="processID" /></s:param>
				</s:url>" ><img src="images/back.png" /></a>
			<img src="images/separator.png" style="padding-right: 4px;" />
			<a class="aBack" title="Главная страница" href="<s:url action='index' >
					<s:param name="callID"><s:property value="callID" /></s:param>
					<s:param name="processID"><s:property value="processID" /></s:param>
				</s:url>" ><img src="images/home.png" /></a>
		</div>
		<%@ include file="/jsp/budgetcall/PrintExcel.jsp" %>
				
		<%@ include file="/jsp/budgetcall/PropertyBudgetCall.jsp" %>
		
		<table width="100%" cellspacing="2">
			<tr>
			 	<td width="90px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
			 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Статья</font>
			 	</td>
			 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
			 		<b><%= charge.getFI_Code() %>&nbsp;&nbsp;<%= charge.getName() %></b>
			 	</td>
			 	<td>&nbsp;</td>
			</tr>
			<tr>
			 	<td width="90px" style="border:double; border-color:#F18800; text-align: left; padding-left: 10px;">
			 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Вид</font>
			 	</td>
			 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
			 		<b><%= period[0].getDescription() %></b>
			 	</td>
			 	<td>&nbsp;</td>
			</tr>
		</table>
		<fieldset>
		<legend>Данные</legend>
			<s:form id="myForm">
			<table class="more" border="1" style="margin-top:20px;" align="center" bordercolor="#663300" cellspacing="2">
	  			<tr class="trLightBlue">
			    	<th scope="col">Наименование</th>
			    	<th scope="col" width="100px">Ед. изм.</th>
			    	<th scope="col" width="100px">Месяц</th>
			    	<th scope="col" width="100px">Кол-во</th>
			    	<th scope="col" width="100px">Стоимость за единицу, тенге</th>
			    	<th scope="col" width="100px">Сумма, тенге</th>
			    	<th scope="col" width="100px">Оплата</th>
				</tr>
				<%
					if(rowSpan > 0){
				%>
					<tr>
						<td style="text-align: center; padding-left: 20px; padding-right: 20px;" rowspan="<%= rowSpan %>"><b><%= period[0].getName() %></b></td>
				<%	} %>
				
				<%
				int n = 1;
					for(Period p: period){
						if(n > 1){
				%>
							<tr>
				<%
						}
						if(periodTotal.getPeriodID() == p.getPeriodID()){
				%>
							<% selectPeriod = "bgcolor=\"#9BC995\""; %>
				<%
						}
						else{
				%>
							<% selectPeriod = "bgcolor=\"#DAE1EA\""; %>
				<%
						}
				%>
						<s:set var="quantityV"><%= p.getQuantity() %></s:set>
						<s:set var="periodV"><%= p.getPeriodID() %></s:set>
						<s:set var="amountUnitV"><%= p.getAmountUnit() %></s:set>
						<s:set var="amountV"><%= p.getAmount() %></s:set>
						<s:set var="count"><%= n %></s:set>                
						
						<td <%= selectPeriod %>><%= p.getUom() %></td>
						<td <%= selectPeriod %>><b><%= p.getMonth() %></b></td>
						<td <%= selectPeriod %>>                             
	                		<s:textfield value="%{#quantityV}" name="quantity_IDX_%{#count}" id="quantity_IDX_%{#count}" size="11"  theme="simple" readonly="false" 
	                				onkeypress="return isQuantity(event)"
	                				onkeyup="do_math(this.form, 'amount_IDX_%{#count}', 'quantity_IDX_%{#count}', 'amountUnit_IDX_%{#count}'); summa(this.form, 'quantity_IDX_%{#count}','sQuantity', %{period.length}, 'quantity');" 
	                				onchange="changeValue('quantity_IDX_%{#count}', this, 'quantity_Copy_%{#count}');" /> 
	                		<s:hidden name="period_IDX_%{#count}" value="%{#periodV}" />
	                		<s:hidden name="quantity_Copy_%{#count}" id="quantity_Copy_%{#count}" value="%{#quantityV}" />
	            		</td>
	            		<td <%= selectPeriod %>>                             
	                		<s:textfield value="%{#amountUnitV}" name="amountUnit_IDX_%{#count}" id="amountUnit_IDX_%{#count}" size="11"  theme="simple" readonly="false" 
	                				onkeypress="return isAmountUnit(event)"
	                				onkeyup="do_math(this.form, 'amount_IDX_%{#count}', 'quantity_IDX_%{#count}', 'amountUnit_IDX_%{#count}'); summa(this.form, 'amount_IDX_%{#count}','sAmount', %{period.length}, 'amount');" 
	                				onchange="changeValue('amountUnit_IDX_%{#count}', this, 'amountUnit_Copy_%{#count}');" />
	                		<s:hidden name="amountUnit_Copy_%{#count}" id="amountUnit_Copy_%{#count}" value="%{#amountUnitV}" />  
	            		</td>  
	            		<td <%= selectPeriod %>>                             
	                		<s:textfield value="%{#amountV}" name="amount_IDX_%{#count}" size="11" tabindex="-1" theme="simple" readonly="true" />  
	            		</td>  
	            		<td <%= selectPeriod %>><%= p.getPayment() %></td>
					</tr>
				<% 
					n++; 
					} 
				%>
				<tr class="trLightBlue">
			    	<th scope="col">Всего</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">
			    		<s:property value="sQuantity" />
			    		<input type="text" value="<%= periodTotal.getsQuantity() %>" style="border-color:black; background-color: #DED592; font-weight: bold;" name="sQuantity" size="10" tabindex="-1" readonly />
			    	</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">
			    		<s:property value="sAmount" />
			    		<input type="text" value="<%= periodTotal.getsAmount() %>" style="border-color:black; background-color: #DED592; font-weight: bold;" name="sAmount" size="10" tabindex="-1" readonly />
			    	</th>
			    	<th scope="col">&nbsp;</th>
				</tr>
				<tr>
					<th colspan="7">
						<!--  <input type="submit" class="button btn-large-green" value="Внести изменения" />  -->
						<input type="button" class="button btn-large-green" value="Внести изменения" onclick="sendForm('doUpdate');" />
						<input type="hidden" name="callID" value=<%= periodTotal.getCallID() %> />
						<input type="hidden" name="chargeID" value=<%= periodTotal.getChargeID() %> />
						<input type="hidden" name="periodID" value=<%= periodTotal.getPeriodID() %> />
						<input type="hidden" name="processID" value=<%= periodTotal.getProcessID() %> />
						<input type="hidden" name="tableID" value=<%= periodTotal.getTableID() %> />
						<input type="hidden" name="recordID" value=<%= periodTotal.getRecordID() %> />
						<input type="hidden" name="rowspan" value=<%= rowSpan %> />			
					</th>
				</tr>
			</table>
			</s:form>
		</fieldset>
	</div>
	
	<%@ include file="/jsp/budgetcall/Footer.jsp" %>
	
</body>
</html>