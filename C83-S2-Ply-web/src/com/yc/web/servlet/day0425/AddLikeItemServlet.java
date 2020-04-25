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
		
		// 设置页面内容字符集
		response.setContentType("text/html; charset=utf-8");
		// 设置响应的字符集
		response.setCharacterEncoding("utf-8");
		// 设置请求的字符集
		request.setCharacterEncoding("utf-8");
		
		// 百度 json
		String json = "['电影','读书','唱歌','跳舞','手游']";
		response.getWriter().append(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
