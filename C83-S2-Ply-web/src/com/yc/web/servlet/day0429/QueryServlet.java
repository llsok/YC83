package com.yc.web.servlet.day0429;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/query.s")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 设置页面内容字符集
		response.setContentType("text/html; charset=utf-8");
		// 设置响应的字符集
		response.setCharacterEncoding("utf-8");
		// 设置请求的字符集
		request.setCharacterEncoding("utf-8");

		BookBiz bb = new BookBiz();
		
		try {
			List<Map<String,Object>> list = bb.query();
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
