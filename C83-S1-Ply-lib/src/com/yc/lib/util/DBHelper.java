package com.yc.lib.util;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	
	/**
	 * 事务控制, true 表示进行事务管理,   false 不管理
	 */
	private boolean isTransaction;
	
	/**
	 * 事务控制, 使用的唯一的连接
	 */
	private Connection conn;
	
	public DBHelper() {
		this(false);
	}
	
	public DBHelper(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

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
	 * 关闭连接
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
	}
	/**
	 * 	获取连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "lib";
		String password = "a";
		
		// 判断是否进行事务管理
		if(isTransaction) {
			// 判断唯一的连接对象是否为null(已经被创建)
			if(conn == null) {
				conn = DriverManager.getConnection(url, user, password);
				// 自动提交参数( 自动提交会在每次执行完sql语句之后, 自动的提交数据), 默认为 true
				conn.setAutoCommit(false);
			}
			return conn;
		} else {
			return DriverManager.getConnection(url, user, password);
		}
	}

	/**
	 * @param sql
	 * @param args  Object...args 可变参数数组
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object...args) throws SQLException {
		System.out.println("SQL: " + sql);
		System.out.println("参数: " + Arrays.toString(args));
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
			int rows = ps.executeUpdate();
			return rows;
		} finally {
			// 如果没有进行事务控制, 则关闭连接
			if(isTransaction == false ) {
				// 5.关闭连接
				conn.close();
				// 连接一定会被关闭
			}
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
			// 如果没有进行事务控制, 则关闭连接
			if(isTransaction == false ) {
				// 5.关闭连接
				conn.close();
				// 连接一定会被关闭
			}
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
	
	/**
	 * 	泛型方法
	 * @param sql
	 * @param cls 	实体类的类对象
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public <E> List<E> query(String sql, Class<E> cls, Object...args) throws SQLException {
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
			
			List<E> ret = new ArrayList<>();
			ResultSetMetaData rsmd = rs.getMetaData(); // 结果集   元数据(  描述数据的信息  )  对象
			// 获取列数
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				//   Map 无序键不重复  ==> LinkedHashMap   有序键不重复
				E row = cls.newInstance();  // 通过反射创建实体对象
				for(int i=0; i<columnCount ; i++) {
					// 获取列名
					String columnName = rsmd.getColumnName( i + 1);
					// 获取列值
					/**
					 * 	重点:
					 * 	1. 根据列名找到对应的属性 ==> Field   (反射)
					 * 	2.根据属性的类型, 从结果集中获取对应类型属性值
					 * 	3.将属性值设置到对象属性值
					 */
					Field field = null;
					try {
						// 约定: 属性名都是小写
						field = cls.getDeclaredField(columnName.toLowerCase());
					} catch (NoSuchFieldException e) {  // 属性没有找到的异常
						System.out.println("忽略属性没有找到的错误: " + e.getMessage());
						continue;
					} 
					Object columnValue;
					// 使用 rs 与 属性类型 相对应的方法获取属性值
					if( field.getType().equals(Integer.class)){
						columnValue = rs.getInt(columnName);
					} else if( field.getType().equals(Long.class)){
						columnValue = rs.getLong(columnName);
					} else if( field.getType().equals(Double.class)){
						columnValue = rs.getDouble(columnName);
					} else if( field.getType().equals(Byte.class)){
						columnValue = rs.getByte(columnName);
					/**
					 * 其他的基本数据类型, 请同学们自行添加
					 */
						
					// 两个常用的日期类型
					} else if( field.getType().equals(Date.class)){
						columnValue = rs.getDate(columnName);
					} else if( field.getType().equals(Timestamp.class)){
						columnValue = rs.getTimestamp(columnName);
						
					// 其他类型不处理了, 直接设置到属性中
					} else {
						columnValue = rs.getObject(columnName);
					}
					field.setAccessible(true);  //  设置强制访问
					field.set(row, columnValue); // 将属性值设置到 row 对象的当前 field 属性中
				}
				ret.add(row);
			}
			return ret;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException(cls.getName() + "对象封装错误", e );
		}  finally {
			// 如果没有进行事务控制, 则关闭连接
			if(isTransaction == false ) {
				// 5.关闭连接
				conn.close();
				// 连接一定会被关闭
			}
		}
	}

	public <E> E queryOne(String sql, Class<E> cls, Object...args) 
			throws SQLException {
		List<E> list = query(sql,cls, args);
		if(list.size()==0) {
			return null;
		} else if(list.size()==1) {
			return list.get(0);
		} else {
			throw new SQLException("查询结果大于1");
		}
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
