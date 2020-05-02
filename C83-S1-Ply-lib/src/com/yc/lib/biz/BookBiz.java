package com.yc.lib.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.lib.bean.Book;
import com.yc.lib.util.DBHelper;
import com.yc.lib.util.DataHelper;

public class BookBiz {
	
	public List<Book> query(){
		try {
			return new DBHelper().query("select * from book", Book.class);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Book>();
		}
	}
	
	public void save(Book book) throws BizException {
		DBHelper dbh = new DBHelper();
		/**
		 * 	基本属性判断:  判空,  格式判断(密码,用户名,邮箱, 电话)
		 */
		if(DataHelper.isEmpty(book.getName())) {
			throw new BizException("请输入书名");
		}
		if(DataHelper.isEmpty(book.getAuthor())) {
			throw new BizException("请输入作者");
		}
		if(DataHelper.isEmpty(book.getPress())) {
			throw new BizException("请输入出版社");
		}
		
		try {
			/**
			 * 	数据模型判断:  判断该数据是否已经存在(  同名判断   )
			 */
			String sql = "select * from book where name=? and press=?";
			int cnt = dbh.count(sql, book.getName(), book.getPress());
			if(cnt > 0) {
				throw new BizException("该图书已经录入系统了");
			}
			
			sql = "insert into book values(seq_lib.nextval,?,?,?,?,?,sysdate,0)";
			dbh.update(sql, 
					book.getName(),
					book.getAuthor(),
					book.getPress(),
					book.getIsbn(),
					book.getPressdate());
		} catch (SQLException e) {
			// 异常转型
			throw new BizException("系统错误,请联系管理员!", e);
		}
	}
	
	public void delete(Integer id) throws BizException {
		DBHelper dbh = new DBHelper();
		String sql = "delete book where id=?";
		try {
			dbh.update(sql, id);
		} catch (SQLException e) {
			// 异常转型
			throw new BizException("系统错误,请联系管理员!", e);
		}
	}

}
