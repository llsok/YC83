package com.yc.web.servlet.day0425;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addLikeItem.s")
public class AddLikeItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ����ҳ�������ַ���
		response.setContentType("text/html; charset=utf-8");
		// ������Ӧ���ַ���
		response.setCharacterEncoding("utf-8");
		// ����������ַ���
		request.setCharacterEncoding("utf-8");
		
		// �ٶ� json
		String json = "['��Ӱ','����','����','����','����']";
		response.getWriter().append(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
