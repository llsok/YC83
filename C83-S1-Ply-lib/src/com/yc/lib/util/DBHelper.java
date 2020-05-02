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
	 * �������, true ��ʾ�����������,   false ������
	 */
	private boolean isTransaction;
	
	/**
	 * �������, ʹ�õ�Ψһ������
	 */
	private Connection conn;
	
	public DBHelper() {
		this(false);
	}
	
	public DBHelper(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	/**
	 * 	��������
	 * 	��̬�� �ص� ֻ���ڸ��౻���ص������ʱ, ִ��һ��
	 * 	��̬�������׳�  �������쳣
	 */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// ע��: �쳣ת�� ==> �쳣�� ==> ԭ���쳣
			throw new RuntimeException("���ݿ���������ʧ��!", e);
		}
	}

	/**
	 * �ر�����
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
	}
	/**
	 * 	��ȡ����
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "lib";
		String password = "a";
		
		// �ж��Ƿ�����������
		if(isTransaction) {
			// �ж�Ψһ�����Ӷ����Ƿ�Ϊnull(�Ѿ�������)
			if(conn == null) {
				conn = DriverManager.getConnection(url, user, password);
				// �Զ��ύ����( �Զ��ύ����ÿ��ִ����sql���֮��, �Զ����ύ����), Ĭ��Ϊ true
				conn.setAutoCommit(false);
			}
			return conn;
		} else {
			return DriverManager.getConnection(url, user, password);
		}
	}

	/**
	 * @param sql
	 * @param args  Object...args �ɱ��������
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object...args) throws SQLException {
		System.out.println("SQL: " + sql);
		System.out.println("����: " + Arrays.toString(args));
		Connection conn = getConnection();
		try {
			// 3.�������
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.ִ�����
			// for + alt + /
			// ���ò���
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			int rows = ps.executeUpdate();
			return rows;
		} finally {
			// ���û�н����������, ��ر�����
			if(isTransaction == false ) {
				// 5.�ر�����
				conn.close();
				// ����һ���ᱻ�ر�
			}
		}
	}
	
	public List<Map<String,Object>> query(String sql, Object...args) throws SQLException {
		Connection conn = getConnection();
		try {
			// 3.�������
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.ִ�����
			// for + alt + /
			// ���ò���
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(args));
			ResultSet rs = ps.executeQuery();
			List<Map<String,Object>> ret = new ArrayList<>();
			ResultSetMetaData rsmd = rs.getMetaData(); // �����   Ԫ����(  �������ݵ���Ϣ  )  ����
			// ��ȡ����
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				//   Map ��������ظ�  ==> LinkedHashMap   ��������ظ�
				Map<String,Object> row = new LinkedHashMap<>();
				for(int i=0; i<columnCount ; i++) {
					// ��ȡ����
					String columnName = rsmd.getColumnName( i + 1);
					// ��ȡ��ֵ
					Object value = rs.getObject(i + 1);
					row.put(columnName, value);
				}
				ret.add(row);
			}
			return ret;
		} finally {
			// ���û�н����������, ��ر�����
			if(isTransaction == false ) {
				// 5.�ر�����
				conn.close();
				// ����һ���ᱻ�ر�
			}
		}
	}
	
	/**
	 * 	��ҵ
	 */
	/**
	 * 	���ڲ�ѯһ����¼
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
			throw new SQLException("��ѯ�������1");
		}
	}
	
	/**
	 * 	���ڲ�ѯĳ��sql�����������
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int count(String sql, Object...args) 
			throws SQLException {
		sql = "select count(*) cnt from (" + sql + ")";
		List<Map<String,Object>> list = query(sql,args);
		Object number = list.get(0).get("CNT");  // BigDecimal ��ʵ��
		return Integer.valueOf(number.toString());
	}
	
	/**
	 * 	���ͷ���
	 * @param sql
	 * @param cls 	ʵ����������
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public <E> List<E> query(String sql, Class<E> cls, Object...args) throws SQLException {
		Connection conn = getConnection();
		try {
			// 3.�������
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.ִ�����
			// for + alt + /
			// ���ò���
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(args));
			ResultSet rs = ps.executeQuery();
			
			List<E> ret = new ArrayList<>();
			ResultSetMetaData rsmd = rs.getMetaData(); // �����   Ԫ����(  �������ݵ���Ϣ  )  ����
			// ��ȡ����
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				//   Map ��������ظ�  ==> LinkedHashMap   ��������ظ�
				E row = cls.newInstance();  // ͨ�����䴴��ʵ�����
				for(int i=0; i<columnCount ; i++) {
					// ��ȡ����
					String columnName = rsmd.getColumnName( i + 1);
					// ��ȡ��ֵ
					/**
					 * 	�ص�:
					 * 	1. ���������ҵ���Ӧ������ ==> Field   (����)
					 * 	2.�������Ե�����, �ӽ�����л�ȡ��Ӧ��������ֵ
					 * 	3.������ֵ���õ���������ֵ
					 */
					Field field = null;
					try {
						// Լ��: ����������Сд
						field = cls.getDeclaredField(columnName.toLowerCase());
					} catch (NoSuchFieldException e) {  // ����û���ҵ����쳣
						System.out.println("��������û���ҵ��Ĵ���: " + e.getMessage());
						continue;
					} 
					Object columnValue;
					// ʹ�� rs �� �������� ���Ӧ�ķ�����ȡ����ֵ
					if( field.getType().equals(Integer.class)){
						columnValue = rs.getInt(columnName);
					} else if( field.getType().equals(Long.class)){
						columnValue = rs.getLong(columnName);
					} else if( field.getType().equals(Double.class)){
						columnValue = rs.getDouble(columnName);
					} else if( field.getType().equals(Byte.class)){
						columnValue = rs.getByte(columnName);
					/**
					 * �����Ļ�����������, ��ͬѧ���������
					 */
						
					// �������õ���������
					} else if( field.getType().equals(Date.class)){
						columnValue = rs.getDate(columnName);
					} else if( field.getType().equals(Timestamp.class)){
						columnValue = rs.getTimestamp(columnName);
						
					// �������Ͳ�������, ֱ�����õ�������
					} else {
						columnValue = rs.getObject(columnName);
					}
					field.setAccessible(true);  //  ����ǿ�Ʒ���
					field.set(row, columnValue); // ������ֵ���õ� row ����ĵ�ǰ field ������
				}
				ret.add(row);
			}
			return ret;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException(cls.getName() + "�����װ����", e );
		}  finally {
			// ���û�н����������, ��ر�����
			if(isTransaction == false ) {
				// 5.�ر�����
				conn.close();
				// ����һ���ᱻ�ر�
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
			throw new SQLException("��ѯ�������1");
		}
	}
	
	public static void main(String[] args) throws SQLException {
		DBHelper dbh = new DBHelper();
		///String sql = "update dept set loc = ? where deptno = ?";
		///dbh.update(sql, "��幫԰", 60);
		
		//sql = "insert into dept values (?,?,?)";
		//dbh.update(sql, 70, "XXX�ŵ�", "��幫԰");
		
		///sql = "delete dept where deptno = 70";
		///dbh.update(sql);
		
		//new DBHelper().update("update dept set loc = 'ѧ��·' where deptno = 60");
		
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
