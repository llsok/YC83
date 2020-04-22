package com.yc.web.servlet.day0422.taobao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/0422/taobao/pay.s")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/**
		 * 	获取会话对象:
		 * 		会话对象如何产生?
		 * 		Web服务器会在接收到第一次动态请求的时候,为每一个浏览器创建一个会话对象
		 * 		并且在该浏览器后续的访问过程中(  交互过程  ), 一直沿用该会话对象
		 */
		
		// 获取web服务器自动创建的会话对象
		HttpSession session = request.getSession();
		// 从会话中获取登录的标识
		// loginedUser 是保存在会话中的登录标志
		Object loginedUser = session.getAttribute("loginedUser");
		
		if(loginedUser == null) {
			// 表示没有登录
			request.setAttribute("msg", "请先登录系统!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// 表示已经登录
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
