package com.yc.lib.biz;

import java.sql.SQLException;

import com.yc.lib.bean.Emp;
import com.yc.lib.util.DBHelper;

public class UserBiz {
	
	// 成功登陆的用户( 经办人 )
	public static Emp loginedEmp;
	
	public void login(String user, String pwd) throws BizException{
		
		/**
		 * 判断用户是否有输入的用户名 和 密码
		 * 通过查询,判断用户名和密码是否正确
		 */
		if(user==null || user.trim().isEmpty()) {
			throw new BizException("请填写用户名!");
		}
		if(pwd==null || pwd.trim().isEmpty()) {
			throw new BizException("请填写密码!");
		}
		DBHelper dbh = new DBHelper();
		try {
			loginedEmp = dbh.queryOne("select * from emp where name=? and pwd=?",Emp.class, user, pwd);
			if(loginedEmp != null) {
				return;
			} else {
				throw new BizException("用户名或密码错误!");
			}
		} catch (SQLException e) {
			throw new BizException("系统错误,请联系管理员!", e);
		}
	}
	
	

}
