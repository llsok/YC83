package com.yc.jdbc;

import java.sql.*;

/**
 * @author 廖彦
 * JDBC 操作数据的 五个步骤
 * 1.注册驱动
 * 2.获取连接
 * 3.创建语句
 * 4.执行语句
 * 5.关闭连接
 */
public class Demo {

	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		// 1.注册驱动   alt + /
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2.获取连接  DriverManager 驱动管理器
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(
				url, user, password);
		// 3.创建语句
		Statement stat = conn.createStatement();
		
		// 4.执行语句,  executeUpdate 可以用于执行除了 select 以外所有的sql语句
		String sql = "update dept set loc = '学公路' where deptno = 60";
		int rows = stat.executeUpdate(sql);
		
		System.out.println("更新了"+rows+"记录");
		
		// 5.关闭连接
		conn.close();
	}

}
