<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ajax testing</title>
	<link rel="shortcut icon" href="images/logo.png" type="image/jpg" /> 	
	<link type="text/css" href="css/Main.css" rel="stylesheet">
	<%@ include file="/js/JScript.jsp" %>
</head>
<body>
<form action="searchCity" id="myForm">
	<input type="textbox" name="getCities" id="getCities" size="20" onkeypress="search(this);" style="width:300px;" autocomplete="off" />
	<div align="left" id="searchResult" style="width:300px;	border:#000000;"></div>
</form>
</body>
</html>