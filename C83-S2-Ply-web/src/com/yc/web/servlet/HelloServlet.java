package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 廖彦
 * 
 * 	定义Servlet三步骤:
 * 	1. 	继承 ==> HttpServlet
 * 	2.	实现 ==> 实现 doXXX 方法
 * 	3.	配置 ==> 给该Servlet 配置一个访问的地址
 * 			1. 地址: /开头的字符串地址, / 表示 WebContent 目录, 工程根目录
 * 			2. 地址全局唯一, 不能重复
 * 		配置方式:
 * 			1.注解配置		WebServlet
 * 			2.web.xml配置
 *
 */
@WebServlet("/hello.s")
public class HelloServlet extends HttpServlet{

	/**
	 * HttpServletRequest req	请求对象,  Web服务器会将 请求信息封装到 req 对象中
	 * HttpServletResponse resp	响应对象, 用于向页面输出内容
	 */
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// 该方法内部可以用于输出页面元素
		PrintWriter out = resp.getWriter();
		out.print("Hello World!");
	}
	
	

}
