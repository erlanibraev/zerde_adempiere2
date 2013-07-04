<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<s:url id="fileDownload" namespace="/" action="DownloadIt" >
	<s:param name="page"><s:property value="page" /></s:param>
	<s:param name="processID"><s:property value="processID" /></s:param>
	<s:param name="callID"><s:property value="callID" /></s:param>
	<s:param name="chargeID"><s:property value="chargeID" /></s:param>
	<s:param name="periodID"><s:property value="periodID" /></s:param>
	<s:param name="tableID"><s:property value="tableID" /></s:param>
	<s:param name="recordID"><s:property value="recordID" /></s:param>
</s:url>

<s:url id="fileDownloadAll" namespace="/" action="DownloadIt" >
	<s:param name="page">templateAll</s:param>
	<s:param name="processID"><s:property value="processID" /></s:param>
	<s:param name="callID"><s:property value="callID" /></s:param>
	<s:param name="chargeID"><s:property value="chargeID" /></s:param>
	<s:param name="periodID"><s:property value="periodID" /></s:param>
	<s:param name="tableID"><s:property value="tableID" /></s:param>
	<s:param name="recordID"><s:property value="recordID" /></s:param>
</s:url>

<s:url id="ajax" namespace="/" action="ajaxTest" />


<div id="printExcel" class="letterRight letter">
		<s:a href="%{fileDownload}"><img src="images/excel.png" style="margin-left: 10px;" /></s:a>
		<img src="images/separator.png" style="margin-left: 5px;" />
	<!-- <s:a href="%{fileDownloadAll}"><img src="images/excelAll.png" style="margin-left: 10px;" /></s:a> -->
		<a href='javascript:window.print(); void 0;' title="Печать"><img src="images/print.png" style="margin-left: 5px;" /></a>
	<!--	<s:a href="%{ajax}"><font size="4">AJAX</font></s:a> -->
</div>