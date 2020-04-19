<!-- JSP 指令: 用于设置动态页面的属性, 导入java类
	本质上其实就是 java 代码
  -->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- 创建完JSP页面, 必须将页面的字符集编码 从 iso-8859-1 改为 utf-8 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello</title>
</head>
<body>
Hello World!
<hr>
当前的系统时间是(JSP脚本输出):
<!-- JSP 脚本  ,用于嵌入java代码 , 可以写java 方法中的代码  -->
<%
	//获取服务器的当前时间
	Date date = new Date();   	// alt + /  代码提示
	// 当前在此处输出了日期值
	out.append(date.toString());// out 是jsp页面的输出流, 是jsp内置对象
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String datestr = sdf.format(date);
%>
<hr>
<!-- JSP表达式必须有返回结果 -->
当前的系统时间是(JSP表达式输出):<%=datestr%>

<hr>
<!-- 动态生成下拉列表项 -->
<%
	String[] items = {"高中","大专","本科","研究生"};
%>
学历
<select>
	<%for(int i=0 ; i< items.length ; i++){%>
		<option><%=items[i] %></option>
	<%} %>
</select>

</body>
</html>