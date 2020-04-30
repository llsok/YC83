package com.yc.lib.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.lib.bean.Book;
import com.yc.lib.util.DBHelper;

public class BookBiz {
	
	public List<Book> query(){
		try {
			return new DBHelper().query("select * from book", Book.class);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Book>();
		}
	}
	
	

}
