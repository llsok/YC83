package com.yc.web.servlet.day0425;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkName.s")
public class CheckNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] dbusers = {"zhangsan","lisi","root","admin","wangwu"}; 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 设置页面内容字符集
		response.setContentType("text/html; charset=utf-8");
		// 设置响应的字符集
		response.setCharacterEncoding("utf-8");
		// 设置请求的字符集
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		
		for(String user : dbusers) {
			if(user.equals(name)) {
				response.getWriter().append("该用户名已经被注册了!");
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
