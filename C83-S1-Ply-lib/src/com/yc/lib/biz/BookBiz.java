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

			if(book.getId()==null || book.getId()==0) {
				sql = "insert into book values(seq_lib.nextval,?,?,?,?,?,sysdate,0)";
				dbh.update(sql, 
						book.getName(),
						book.getAuthor(),
						book.getPress(),
						book.getIsbn(),
						book.getPressdate());
			} else {
				sql = "update book set name=?,author=?,press=?,isbn=?,pressdate=? where id=?";
				dbh.update(sql, 
						book.getName(),
						book.getAuthor(),
						book.getPress(),
						book.getIsbn(),
						book.getPressdate(),
						book.getId());
			}
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
	
	/**
	 * 组合条件查询
	 * @param book
	 * @return
	 */
	public List<Book> query(Book book){
		String sql = "select * from book where 1=1 ";
		List<Object> params = new ArrayList<>();
		if(DataHelper.isEmpty(book.getName()) == false ) {
			sql += "and name like ?";
			params.add("%" + book.getName() + "%");
		}
		if(DataHelper.isEmpty(book.getAuthor()) == false ) {
			sql += "and author like ?";
			params.add("%" + book.getAuthor() + "%");
		}
		if(DataHelper.isEmpty(book.getIsbn()) == false ) {
			sql += "and isbn like ?";
			params.add("%" + book.getIsbn() + "%");
		}
		if(DataHelper.isEmpty(book.getPress()) == false ) {
			sql += "and press like ?";
			params.add("%" + book.getPress() + "%");
		}
		
		try {
			return new DBHelper().query(sql, Book.class, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Book>();
		}
	}

}
