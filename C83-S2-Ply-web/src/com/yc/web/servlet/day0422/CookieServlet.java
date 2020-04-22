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
 		 * 1. ��� cookie ����
 		 * 2.��ת�� cookie.html 
 		 * 		1. ����ת��
 		 * 		2. ��Ӧ�ض��� 
 		 */
 		
 		// ����cookie ����
 		Cookie cookie = new Cookie("like","book"); // name, value
 		
 		cookie.getName();// ==> name ���Ե�get ����  ==>like û�� set ����
 		cookie.setValue("movie");
 		// ��Чʱ��, ��λ:��, ʱ����������ñ�ʾ: ����ʱ��: ����Ự����ʱ
 		cookie.setMaxAge(60 * 60); 
 		// cookie.setPath("");   // ����cookie ��·��
 		
 		// ��cookie ������ӵ���Ӧ������, ���������
 		response.addCookie(cookie);
 		
 		// ��ȡת���� ==> ����ת������
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
