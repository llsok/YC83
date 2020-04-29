package com.yc.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {

	
	private Connection conn; 
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//加载驱动
	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
			
	//获取连接对象
	public Connection getConn() throws SQLException {
		conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),MyProperties.getInstance());
		//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
		return conn;
	}
	
	//关闭资源
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(null != rs) {
				rs.close();
			}
			if(null != pstmt) {
				pstmt.close();
			}
			if(null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回多条记录的
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findMutipl(String sql,List<Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			conn = getConn();//获取连接
			pstmt = conn.prepareStatement(sql);//获取预编译对象
			//设置参数
			setParams(pstmt,params);
			rs = pstmt.executeQuery(); //执行查询操作返回结果集
			List<String> columnNames = getColumnNames(rs);
			while(rs.next()){
				map = new HashMap<String, Object>();
				//循环所有的列
				for(String name : columnNames) {
					//获取对应字段值
					Object obj = rs.getObject(name);
					if(obj == null) {
						continue ;//结束本次循环
					}
					String typeName = obj.getClass().getName();
					//System.out.println(typeName); //number
					if("java.sql.Blob".equals(typeName)) {
						Blob blob = (Blob)obj;
						//图片放在字节数组中
						InputStream in = blob.getBinaryStream();
						byte [] bt = new byte[(int)blob.length()];
						in.read(bt);
						map.put(name, bt);//图片以字节数组形式存储
					}else if("java.sql.Clob".equals(typeName)){//文本进行处理，返回字符串
						Clob clob =(Clob)obj;
						StringBuffer sb =new StringBuffer();
						Reader in = clob.getCharacterStream();
						BufferedReader br = new BufferedReader(in);
						String str = null;
						while( (str=br.readLine()) != null) {
							sb.append(str);
						}
						map.put(name, sb.toString());//文本以字符串进行存储
					}else{
						map.put(name, rs.getObject(name));
					}
				}
				//map添加到List集合
				list.add(map);
			}
		} finally {
			closeAll(conn,pstmt,rs);
		}
		return list;
	}
	
	/**
	 * 返回单条记录查询操作 select * from table_name where id =?
	 * @param sql
	 * @param params  参数的顺序必须和？顺序一致
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findSingle(String sql,List<Object> params) throws Exception{
		Map<String, Object> map = null;
		try {
			conn = getConn();//获取连接
			pstmt = conn.prepareStatement(sql);//获取预编译对象
			//设置参数
			setParams(pstmt,params);
			rs = pstmt.executeQuery(); //执行查询操作返回结果集
			List<String> columnNames = getColumnNames(rs);
			if(rs.next()){
				map = new HashMap<String, Object>();
				//循环所有的列
				for(String name : columnNames) {
					//获取对应字段值
					Object obj = rs.getObject(name);
					if(obj == null) {
						continue ;//结束本次循环
					}
					String typeName = obj.getClass().getName();
					//System.out.println(typeName); //number
					if("java.sql.Blob".equals(typeName)) {
						Blob blob = (Blob)obj;
						//图片放在字节数组中
						InputStream in = blob.getBinaryStream();
						byte [] bt = new byte[(int)blob.length()];
						in.read(bt);
						map.put(name, bt);//图片以字节数组形式存储
					}else if("java.sql.Clob".equals(typeName)){//文本进行处理，返回字符串
						Clob clob =(Clob)obj;
						StringBuffer sb =new StringBuffer();
						Reader in = clob.getCharacterStream();
						BufferedReader br = new BufferedReader(in);
						String str = null;
						while( (str=br.readLine()) != null) {
							sb.append(str);
						}
						map.put(name, sb.toString());//文本以字符串进行存储
					}else{
						map.put(name, rs.getObject(name));
					}
				}
			}
		} finally {
			closeAll(conn,pstmt,rs);
		}
		return map;
	}
	
	/**
	 * 根据结果集获取所有的列名
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<String> getColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		//如何获取sql语句中字段名称
		ResultSetMetaData data = rs.getMetaData();
		int count = data.getColumnCount();
		//System.out.println("总列数:"+count);
		for(int i = 1; i <= count ; i++) {// i  起始值1开始，最大值=count
			//System.out.println(data.getColumnName(i));
			//添加到List集合中
			columnNames.add(data.getColumnName(i));
		}
		return columnNames;
	}
	
	/**
	 * 多条sql语句更新 事务
	 * @param sqls  多条sql语句  sql语句必须和List<Object>存储的顺序一致
	 * @param params  一条sql语句的参数放在List<Object>  多条一起存在params
	 * @return
	 * @throws SQLException
	 */
	public int update(List<String> sqls,List<List<Object>> params)  throws SQLException {
		int result = 0;
		try {
			conn = getConn();
			//默认事务自动提交，但是在此处必须手动提交，所有的sql语句执行成功后才可以提交
			//事务改为手动提交
			conn.setAutoCommit(false);
			//循环sql集合
			for(int i=0;i<sqls.size();i++) {
				String sql = sqls.get(i);
				List<Object> param = params.get(i);//一一对应
				//获取预编译对象
				pstmt = conn.prepareStatement(sql);
				//设置参数
				setParams(pstmt,param);//小list
				result = pstmt.executeUpdate();
				if(result <= 0) {//如果其中某个sql语句执行时为成功
					conn.rollback();
					return result;
				}
			}
			//事务提交
			conn.commit();
		} catch (Exception e) {
			result = 0;
			// 发生异常后，事务必须回滚
			conn.rollback();
			e.printStackTrace();
		}finally {
			//还原事务的转态
			conn.setAutoCommit(true);
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	/**
	 * 更新操作  单条sql语句
	 * @param sql
	 * @param params   参数的顺序必须和？顺序一致
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql,List<Object> params) throws SQLException {
		int result = 0;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);//获取预编译对象
			//设置参数
			setParams(pstmt,params);
			//执行更新操作
			result = pstmt.executeUpdate();
		}finally {
			//关闭资源
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	/**
	 * 预编译对象设置参数
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	private void setParams(PreparedStatement pstmt, List<Object> params) throws SQLException {
		if(params==null || params.size()<=0) {
			return ;
		}
		for(int i=0;i<params.size();i++) {
			pstmt.setObject(i+1, params.get(i));
		}
	}
	
	//count  sum  min max  avg    select count(*) from table_name
	/**
	 * 聚合函数  只是用一个聚合函数
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public double getPolymer(String sql,List<Object> params) throws SQLException {
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getDouble(1);
			}
		}finally {
			closeAll(conn, pstmt, null);
		}
		return 0;
	}
	
}
