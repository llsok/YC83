package com.yc.lib.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.lib.util.DBHelper;

public class BookBizTest {
	
	public static void main(String[] a) throws SQLException {
		
		String sql = "insert into book values(?,null,null,null,null,null,null,null)";
		List<Object> args = new ArrayList<>();
		args.add(4);
		new DBHelper().update(sql, args);
	}

}
