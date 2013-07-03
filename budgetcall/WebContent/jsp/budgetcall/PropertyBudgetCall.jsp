<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.compiere.model.*, org.compiere.util.*" %>

<% 
	int call_ID = (Integer) session.getAttribute("callID");
	MBPMBudgetCall bCall = new MBPMBudgetCall(Env.getCtx(), call_ID, null); 
	
	MBPMABP abp = new MBPMABP(Env.getCtx(), bCall.getBPM_ABP_ID(), null);
	MYear ye = new MYear(Env.getCtx(), bCall.getC_Year_ID(), null);
	MProject project = new MProject(Env.getCtx(), bCall.getC_Project_ID(), null); 
%>

<table width="100%" cellspacing="2">
	<tr>
		<td scope="col" colspan="3">
		   	<font color="#321818" style="font-family: sans-serif; font-style:italic" size="+2">Бюджетная заявка № <b><%= bCall.getValue() %></b></font>
		   	<br/>&nbsp;
		</td>
	 </tr>
	 <tr>
	 	<td width="90px" style="border:double; border-color:#8DB3DC; text-align: left; padding-left: 10px;">
	 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Год</font>
	 	</td>
	 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
	 		<b><%= ye.getFiscalYear() %></b>
	 	</td>
	 	<td>&nbsp;</td>
	</tr>
	<tr>
	 	<td width="90px" style="border:double; border-color:#8DB3DC; text-align: left; padding-left: 10px;">
	 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Проект</font>
	 		</td>
	 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
	 		<b><%= project.getName() %></b>
	 	</td>
	 	<td>&nbsp;</td>
	</tr>
	<tr>
	 	<td width="90px" style="border:double; border-color:#8DB3DC; text-align: left; padding-left: 10px;">
	 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">АБП</font>
	 	</td>
	 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
	 		<b><%= abp.getHR_Department().getName() %></b>
	 	</td>
	 	<td>&nbsp;</td>
	</tr>
	<tr>
	 	<td width="90px" style="border:double; border-color:#8DB3DC; text-align: left; padding-left: 10px;">
	 		<font color="#321818" style="font-family: sans-serif; font-style:normal;" size="+1">Категория</font>
	 	</td>
	 	<td style="border:dotted; border-color:#999999; width:40%; text-align: left; padding-left: 10px;">
	 		<b><%= Msg.translate(Env.getCtx(), MRefList.getListName(Env.getCtx(), X_BPM_BudgetCall.CATEGORYNAME_AD_Reference_ID, bCall.getCategoryName())) %></b>
	 	</td>
	 	<td>&nbsp;</td>
	</tr>
</table>
