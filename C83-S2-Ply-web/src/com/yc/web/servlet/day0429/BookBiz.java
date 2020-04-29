package com.yc.web.servlet.day0429;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.web.util.DbHelper;

public class BookBiz {
	
	public List<Map<String,Object>> query() throws Exception{
		return new DbHelper().findMutipl("select * from books", null);
	}
	
	public void create(String bookname, String bookpress, String pressdate,
			String bookauthor, String bookcount) throws SQLException {
		String sql = "insert into books values(null,?,?,?,?,?,null)";
		DbHelper dbh = new DbHelper();
		List<Object> params = new ArrayList<>();
		params.add(bookname);
		params.add(bookpress);
		params.add(pressdate);  // 日期格式可能会报错
		params.add(bookauthor);
		params.add(bookcount);
		dbh.update(sql, params);
	}

}
