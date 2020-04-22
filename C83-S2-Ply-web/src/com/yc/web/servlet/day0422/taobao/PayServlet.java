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
		 * 	��ȡ�Ự����:
		 * 		�Ự������β���?
		 * 		Web���������ڽ��յ���һ�ζ�̬�����ʱ��,Ϊÿһ�����������һ���Ự����
		 * 		�����ڸ�����������ķ��ʹ�����(  ��������  ), һֱ���øûỰ����
		 */
		
		// ��ȡweb�������Զ������ĻỰ����
		HttpSession session = request.getSession();
		// �ӻỰ�л�ȡ��¼�ı�ʶ
		// loginedUser �Ǳ����ڻỰ�еĵ�¼��־
		Object loginedUser = session.getAttribute("loginedUser");
		
		if(loginedUser == null) {
			// ��ʾû�е�¼
			request.setAttribute("msg", "���ȵ�¼ϵͳ!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// ��ʾ�Ѿ���¼
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
