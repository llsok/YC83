package com.yc.jdbc.day0425;

import java.sql.*;

public class Demo1 {
	
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		/**
		 * 注入攻击
		 */
		System.out.println("============");
		selectDept("' or '1'='1");
		System.out.println("============");
		selectDept("技术部");
		System.out.println("============");
		selectDept("RESEARCH");
	}
	
	/**
	 * 根据部门名称查询部门
	 * @param dname
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void selectDept(String dname) 
			throws ClassNotFoundException, SQLException {
		// 1.注册驱动   alt + /
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2.获取连接  DriverManager 驱动管理器
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(
				url, user, password);
		/**
		 * 预编译  语句对象 
		 * 好处:  安全,  效率高
		 * sql ==> java文件
		 * 	编译		编译class
		 */
		String sql = "select * from dept where dname = ?";
		// 3.创建预编译的语句
		PreparedStatement ps = conn.prepareStatement(sql);
		
		// 4.执行语句,  executeUpdate 可以用于执行除了 select 以外所有的sql语句
		System.out.println(sql);
		
		/**
		 * 设置 查询参数,  替换 sql 中的 ? (占位符)
		 */
		ps.setString(1, dname);
		ResultSet rs = ps.executeQuery();
		
		/**
		 * 1. rs.next(); 返回boolean, 作用: 用于控制游标(指针)访问结果集中的记录
		 * 2. rs.getXXX();  xxx 表示数据类型, 作用,获取当前游标指向的行指定的字段值
		 * 		rs.getXXX(列索引  列的编号, 从1开始计数)
		 * 		rs.getXXX(列名)
		 */
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print("\t");
			System.out.print(rs.getString(2));
			System.out.print("\t");
			System.out.print(rs.getString("LOC"));
			System.out.println();
		}
		
		// 5.关闭连接
		rs.close();
		ps.close();
		conn.close();
	}
	
	public static void selectDept() 
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
		String sql = "select * from dept";
		ResultSet rs = stat.executeQuery(sql);
		
		/**
		 * 1. rs.next(); 返回boolean, 作用: 用于控制游标(指针)访问结果集中的记录
		 * 2. rs.getXXX();  xxx 表示数据类型, 作用,获取当前游标指向的行指定的字段值
		 * 		rs.getXXX(列索引  列的编号, 从1开始计数)
		 * 		rs.getXXX(列名)
		 */
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print("\t");
			System.out.print(rs.getString(2));
			System.out.print("\t");
			System.out.print(rs.getString("LOC"));
			System.out.println();
		}
		
		// 5.关闭连接
		rs.close();
		stat.close();
		conn.close();
	}

}
