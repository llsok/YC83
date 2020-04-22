package com.yc.web.servlet.day0422.taobao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/0422/taobao/login.s")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ����
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		// �û�����������ж�: yc  123
		if("yc".equals(name) && "123".equals(pwd)) {
			// �ɹ�
			HttpSession session = request.getSession();
			session.setAttribute("loginedUser", name);
			request.getRequestDispatcher("order.jsp")
				.forward(request, response);
		} else {
			// ������������һ��������Ϣ
			request.setAttribute("msg", "�û������������!");
			request.getRequestDispatcher("login.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
