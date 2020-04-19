package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ����
 * 
 * 	����Servlet������:
 * 	1. 	�̳� ==> HttpServlet
 * 	2.	ʵ�� ==> ʵ�� doXXX ����
 * 	3.	���� ==> ����Servlet ����һ�����ʵĵ�ַ
 * 			1. ��ַ: /��ͷ���ַ�����ַ, / ��ʾ WebContent Ŀ¼, ���̸�Ŀ¼
 * 			2. ��ַȫ��Ψһ, �����ظ�
 * 		���÷�ʽ:
 * 			1.ע������		WebServlet
 * 			2.web.xml����
 *
 */
@WebServlet("/hello.s")
public class HelloServlet extends HttpServlet{

	/**
	 * HttpServletRequest req	�������,  Web�������Ὣ ������Ϣ��װ�� req ������
	 * HttpServletResponse resp	��Ӧ����, ������ҳ���������
	 */
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// �÷����ڲ������������ҳ��Ԫ��
		PrintWriter out = resp.getWriter();
		out.print("Hello World!");
	}
	
	

}
