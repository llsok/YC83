<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
</head>
<body>
<%
	// 获取请求对象的属性值
	String msg = (String)request.getAttribute("msg");
%>
<form action="login.s" method="post">
	<font color="red"><%= msg == null ? "" : msg %></font><br>
	用户: <input name="name" ><br>
	密码: <input name="pwd" type="password"><br>
	<input type="submit" value="登录">
</form>
</body>
</html>