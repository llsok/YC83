package com.yc.web.servlet.day0422;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost/C83-S2-Ply-web/0422/add.s 404
 * 
 * http://localhost/C83-S2-Ply-web/add.s
 * 
 * C83-S2-Ply-web/  ==> C83-S2-Ply-web/WebContent
 * 
 */
@WebServlet("/0422/add.s")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置页面内容字符集
		response.setContentType("text/html; charset=utf-8");
		// 设置响应的字符集
		response.setCharacterEncoding("utf-8");
		// 设置请求的字符集
		request.setCharacterEncoding("utf-8");

		String numStr1 = request.getParameter("num1");
		String numStr2 = request.getParameter("num2");

		double num1 = 0;
		try {
			num1 = Double.valueOf(numStr1);
		} catch (NumberFormatException e) {
			System.out.println("num1参数错误!");
		}
		double num2 = 0;
		try {
			num2 = Double.valueOf(numStr2);
		} catch (NumberFormatException e) {
			System.out.println("num2参数错误!");
		}

		double res = num1 + num2;
		
		response.getWriter().append(num1 + " + " + num2 + " = " + res);

	}

}
