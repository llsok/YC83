package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 	URL地址格式
 * 	协议://域名(IP):端口/路径/资源名?参数列表
 * 	例子:
 * 	http://localhost/C83-S2-Ply-web/SayHello.s?name=jack&age=18
 * 
 * 	参数列表格式: 参数名1=参数值1&参数名2=参数值2&参数名3=参数值3
 * 	= & 不能出现在参数名和值中 ,  否则必须转义
 * 
 *	 名为 [com.yc.web.servlet.HelloServlet]和
 *  [com.yc.web.servlet.SayHelloServlet] 的
 *  servlet不能映射为一个url模式(url-pattern) [/hello.s]
 *  	地址冲突
 *  
 *  Invalid <url-pattern> [hello.s] in servlet mapping
 *  	非法的地址映射
 */
@WebServlet("/0419/SayHello.s")
public class SayHelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>"
				+ "<h1>Hello " + name + "</h1>"
				+ "<h2>年龄: " + age + "</h2>"
				+ "</body></html>");

	}*/

	/**
	 * 405 错误, 表示servlet 对应的方法未实现
	 * 	浏览器地址栏发起的请求是 get 请求, 它必须执行 doGet 方法
	 * 	如果对应的方法未实现 , 那么就会报出 405
	 * 
	 * 	现在唯一能够发送post 请求只有  form 表单
	 * 
	 * 	地址栏, 超链接,  img 全都是 get
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置页面内容字符集
		response.setContentType("text/html; charset=utf-8");
		// 设置响应的字符集
		response.setCharacterEncoding("utf-8");
		// 设置请求的字符集
		request.setCharacterEncoding("utf-8");
		
		// 获取请求参数
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>"
				+ "<h1>Hello " + name + "</h1>"
				+ "<h2>年龄: " + age + "</h2>"
				+ "</body></html>");
	}

}
