<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ajax testing</title>
	<link rel="shortcut icon" href="images/logo.png" type="image/jpg" /> 	
	<link type="text/css" href="css/Main.css" rel="stylesheet">
	<script src="js/Ajax-source.js" type="text/javascript"></script>
</head>
<body>
<form action="searchCity" id="searchByCityForm">
	<table border="0" cellpadding="3" cellspacing="0" width="100%">
		<tr>
			<td ><B>By Country</b></td>
		</tr>
		<tr>
			<td class="promo">
				<table border="0" cellpadding="3" cellspacing="0">
					<tr>
					<td valign="top">
					<input type="textbox" id="getCities" size="20" onkeypress="search(this);" style="width:300px;" autocomplete="off" />
					<div align="left" id="searchResult" style="width:300px;	border:#000000;"></div>
					<a href="javascript:searchByCity();">
					<img src="images/adempiere.png" alt="go"
					width="23" height="15" border="0"></a>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>