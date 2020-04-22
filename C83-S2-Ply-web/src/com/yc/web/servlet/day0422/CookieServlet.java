package com.yc.web.servlet.day0422;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/0422/cookie.s")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/**
 		 * 1. 添加 cookie 数据
 		 * 2.跳转到 cookie.html 
 		 * 		1. 请求转发
 		 * 		2. 响应重定向 
 		 */
 		
 		// 创建cookie 对象
 		Cookie cookie = new Cookie("like","book"); // name, value
 		
 		cookie.getName();// ==> name 属性的get 方法  ==>like 没有 set 方法
 		cookie.setValue("movie");
 		// 有效时间, 单位:秒, 时间如果不设置表示: 到期时间: 浏览会话结束时
 		cookie.setMaxAge(60 * 60); 
 		// cookie.setPath("");   // 设置cookie 的路径
 		
 		// 将cookie 数据添加到响应对象中, 发送浏览器
 		response.addCookie(cookie);
 		
 		// 获取转发器 ==> 调用转发方法
 		request.getRequestDispatcher("cookie.html")
 				.forward(request, response);

 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
