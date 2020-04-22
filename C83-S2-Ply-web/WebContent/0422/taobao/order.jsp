<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单页面</title>
</head>
<%
	String user = (String)session.getAttribute("loginedUser");
%>
<body>
<h3>欢迎<%= user == null ? "" : user %></h3>
订单信息
</body>
</html>