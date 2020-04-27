package com.yc.jdbc.day0427;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.yc.jdbc.day0426.DBHelper;

/**
 *	1.组合条件查询
 *	2.日期字段处理
 *		java.sql.Date		=> 年月日
 *		java.sql.Timestamp	=> 年月日时分秒 毫秒
 *		jdbc的日期类型   !=   数据的日期类型
 *
 *	3.结果封装 List<Map> ==> List<实体对象>
 *	4.事务管理 
 *
 */
public class Demo3 {
	
	public static void main(String[] args) throws SQLException {
		
		String sql1 = "select * from emp where empno = ?";
		String sql2 = "update emp set sal = ? where empno = ?";
		
		DBHelper dbh = new DBHelper();
		Emp emp = dbh.queryOne(sql1, Emp.class, 7369);
		
		System.out.println(emp);
		
		double sal = emp.getSal() + 200;
		
		dbh.update(sql2, sal, emp.getEmpno());
		
		
	}

}
