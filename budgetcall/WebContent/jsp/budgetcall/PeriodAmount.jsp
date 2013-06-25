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

		<s:bean name="main.org.action.PeriodAmount" var="per" />
		
		<s:bean name="main.org.action.PeriodAmount" var="amo">
			<s:param name="callID"><%= periodTotal.getCallID() %></s:param>
			<s:param name="tableID"><%= periodTotal.getTableID() %></s:param>
			<s:param name="recordID"><%= periodTotal.getRecordID() %></s:param>
			<s:param name="periodBean" value="#per.periodBean" />
		</s:bean>

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
		<fieldset>
		<legend>Данные</legend>
			<s:form action="doUpdate">
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
						<td style="text-align: center; padding-left: 20px; padding-right: 20px;" rowspan="<%= rowSpan %>"><b><s:property value="#amo.periodBean[0].name" /></b></td>
				<%	} %>
				
				<s:iterator value="#amo.periodBean" status="stat" var="a">
					<s:if test="#stat.count > 1">
						<tr>
					</s:if>
						<td><s:property value="%{#a.uom}" /></td>
						<td><s:property value="%{#a.month}" /></td>
						<td>                             
	                		<s:textfield value="%{#a.quantity}" name="quantity_IDX_%{#stat.count}" size="11"  theme="simple" readonly="false" class="form-text" 
	                				onkeypress="return isQuantity(event)"
	                				onkeyup="do_math(this.form, 'amount_IDX_%{#stat.count}', 'quantity_IDX_%{#stat.count}', 'amountUnit_IDX_%{#stat.count}')" /> 
	                		<s:hidden name="period_IDX_%{#stat.count}" value="%{#a.periodID}" />
	            		</td>
	            		<td>                             
	                		<s:textfield value="%{#a.amountUnit}" name="amountUnit_IDX_%{#stat.count}" size="11"  theme="simple" readonly="false" class="form-text"
	                				onkeypress="return isAmountUnit(event)"
	                				onkeyup="do_math(this.form, 'amount_IDX_%{#stat.count}', 'quantity_IDX_%{#stat.count}', 'amountUnit_IDX_%{#stat.count}')" />  
	            		</td>  
	            		<td>                             
	                		<s:textfield value="%{#a.amount}" name="amount_IDX_%{#stat.count}" size="11" tabindex="-1" theme="simple" readonly="true" />  
	            		</td>  
	            		<td><s:property value="%{#a.payment}" /></td>
					</tr>
				</s:iterator>
				<tr class="trLightBlue">
			    	<th scope="col">Всего</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">
			    		<input type="text" value="<%= periodTotal.getsQuantity() %>" style="border-color:black;" name="sQuantity" size="10" tabindex="-1" readonly="true" />
			    	</th>
			    	<th scope="col">&nbsp;</th>
			    	<th scope="col">
			    		<input type="text" value="<%= periodTotal.getsAmount() %>" style="border-color:black;" name="sAmount" size="10" tabindex="-1" readonly="true" />
			    	</th>
			    	<th scope="col">&nbsp;</th>
				</tr>
				<tr>
					<th colspan="7">
						<input type="submit" class="button btn-large-green" value="Внести изменения" />
						
						<input type="hidden" name="callID" value=<s:property value="callID" /> />
						<input type="hidden" name="chargeID" value=<s:property value="chargeID" /> />
						<input type="hidden" name="periodID" value=<s:property value="periodID" /> />
						<input type="hidden" name="processID" value=<s:property value="processID" /> />
						<input type="hidden" name="tableID" value=<%= periodTotal.getTableID() %> />
						<input type="hidden" name="recordID" value=<%= periodTotal.getRecordID() %> />
						<input type="hidden" name="rowspan" value=<%= rowSpan %> />			
					</th>
				</tr>
			</table>
			</s:form>
		</fieldset>
	</div>

	<script>
	
		function getChar(event) {
			
			if (event.which == null) {
				if (event.keyCode < 32) return null;
			    return String.fromCharCode(event.keyCode); // IE
			}
	
			if (event.which!=0 && event.charCode!=0) {
				if (event.which < 32) return null;
				return String.fromCharCode(event.which);   // остальные
			}
	
			return null; // специальная клавиша
		}
	
		function isQuantity(event) {
			  
			e = event;
			if (e.ctrlKey || e.altKey || e.metaKey) return; 
			var chr = getChar(e);
			if (chr == null) return;
			if (chr < '0' || chr > '9') {
				return false;
			}else return true;
			  
		}
		
		function isAmountUnit(event) {
			  
			  e = event;
			  if (e.ctrlKey || e.altKey || e.metaKey) return; 
			  var chr = getChar(e);
			  if (chr == null) return;
			  if(chr != '.'){
				  if (chr < '0' || chr > '9') {
					return false;
				  }else return true;
			  }else return true;
			  
		}

	
		function do_math(f,edit,edit1,edit2)
		{
	
		    var t1 = f.elements[edit1].value;
		    var t2 = f.elements[edit2].value;
		    
		    var res = parseInt(t1)*parseFloat(t2);
		    f.elements[edit].value = isNaN(res) ? '' : Math.round(res * 100) / 100;
		}
	
	</script>
		
	<%@ include file="/jsp/budgetcall/Footer.jsp" %>
</body>
</html>