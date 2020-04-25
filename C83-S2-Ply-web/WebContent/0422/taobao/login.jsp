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

	/* 取出cookie 
		1. JSP 脚本 + JSP 表达式 ==> request.getCookies()
		2. JS 代码 获取cookie 需要自己及解析  ==>document.cookie  ==> split
			a=1,b=2,c=3
		3. EL 表达式
	*/
%>
<form action="login.s" method="post">
	<font color="red"><%= msg == null ? "" : msg %></font><br>
	用户: <input name="name" value="${cookie.username.value}"><br>
	密码: <input name="pwd" type="password"><br>
	<input type="submit" value="登录">
</form>
</body>
</html>