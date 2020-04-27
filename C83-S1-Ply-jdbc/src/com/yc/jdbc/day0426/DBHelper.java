package com.yc.jdbc.day0426;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {

	/**
	 * 	加载驱动
	 * 	静态块 特点 只会在该类被记载到虚拟机时, 执行一次
	 * 	静态不允许抛出  编译期异常
	 */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 注意: 异常转型 ==> 异常链 ==> 原因异常
			throw new RuntimeException("数据库驱动加载失败!", e);
		}
	}

	/**
	 * 	获取连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "a";
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * @param sql
	 * @param args  Object...args 可变参数数组
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object...args) throws SQLException {
		Connection conn = getConnection();
		try {
			// 3.创建语句
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.执行语句
			// for + alt + /
			// 设置参数
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(args));
			int rows = ps.executeUpdate();
			return rows;
		} finally {
			// 5.关闭连接
			conn.close();
			// 连接一定会被关闭
		}
	}
	
	public List<Map<String,Object>> query(String sql, Object...args) throws SQLException {
		Connection conn = getConnection();
		try {
			// 3.创建语句
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.执行语句
			// for + alt + /
			// 设置参数
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(args));
			ResultSet rs = ps.executeQuery();
			List<Map<String,Object>> ret = new ArrayList<>();
			ResultSetMetaData rsmd = rs.getMetaData(); // 结果集   元数据(  描述数据的信息  )  对象
			// 获取列数
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				//   Map 无序键不重复  ==> LinkedHashMap   有序键不重复
				Map<String,Object> row = new LinkedHashMap<>();
				for(int i=0; i<columnCount ; i++) {
					// 获取列名
					String columnName = rsmd.getColumnName( i + 1);
					// 获取列值
					Object value = rs.getObject(i + 1);
					row.put(columnName, value);
				}
				ret.add(row);
			}
			return ret;
		} finally {
			// 5.关闭连接
			conn.close();
			// 连接一定会被关闭
		}
	}
	
	/**
	 * 	作业
	 */
	/**
	 * 	用于查询一条记录
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> queryOne(String sql, Object...args) 
			throws SQLException {
		List<Map<String,Object>> list = query(sql,args);
		if(list.size()==0) {
			return null;
		} else if(list.size()==1) {
			return list.get(0);
		} else {
			throw new SQLException("查询结果大于1");
		}
	}
	
	/**
	 * 	用于查询某个sql结果集的数量
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int count(String sql, Object...args) 
			throws SQLException {
		sql = "select count(*) cnt from (" + sql + ")";
		List<Map<String,Object>> list = query(sql,args);
		Object number = list.get(0).get("CNT");  // BigDecimal 大实数
		return Integer.valueOf(number.toString());
	}
	
	public static void main(String[] args) throws SQLException {
		DBHelper dbh = new DBHelper();
		///String sql = "update dept set loc = ? where deptno = ?";
		///dbh.update(sql, "雁峰公园", 60);
		
		//sql = "insert into dept values (?,?,?)";
		//dbh.update(sql, 70, "XXX门店", "雁峰公园");
		
		///sql = "delete dept where deptno = 70";
		///dbh.update(sql);
		
		//new DBHelper().update("update dept set loc = '学公路' where deptno = 60");
		
		List<Map<String,Object>> list;
		
		list = dbh.query("select * from dept");
		for(Map<String,Object> row : list) {
			System.out.println(row);
		}
		
		list = dbh.query("select * from emp where ename like ? ", "%S%");
		for(Map<String,Object> row : list) {
			System.out.println(row);
		}
		
		list = dbh.query("select * from emp where sal > ? ", 2000);
		for(Map<String,Object> row : list) {
			System.out.println(row);
		}
		
		System.out.println("============================");
		System.out.println(dbh.queryOne("select * from dept where deptno=?", 60));
		System.out.println("============================");
		System.out.println(dbh.count("select * from emp where ename like ? ", "%S%"));
	}

}
