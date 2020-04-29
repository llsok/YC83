package com.yc.web.servlet.day0429;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create.s")
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookBiz bb = new BookBiz();
		
		String bookname = request.getParameter("bookname");
		String bookpress = request.getParameter("bookpress");
		String pressdate = request.getParameter("pressdate");
		String bookauthor = request.getParameter("bookauthor");
		String bookcount = request.getParameter("bookcount");
		try {
			bb.create(bookname, bookpress, pressdate, bookauthor, bookcount);
			response.getWriter().append("1");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("0");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
