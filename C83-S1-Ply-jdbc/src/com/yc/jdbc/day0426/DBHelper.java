package com.yc.jdbc.day0426;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {

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
	 * 	��ȡ����
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
	 * @param args  Object...args �ɱ��������
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object...args) throws SQLException {
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
			int rows = ps.executeUpdate();
			return rows;
		} finally {
			// 5.�ر�����
			conn.close();
			// ����һ���ᱻ�ر�
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
			// 5.�ر�����
			conn.close();
			// ����һ���ᱻ�ر�
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
	public Map<String,Object> queryOne(String sql, Object...args) throws SQLException {
		return null;
	}
	
	/**
	 * 	���ڲ�ѯĳ��sql�����������
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int count(String sql, Object...args) throws SQLException {
		return 0;
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
		
		
		String sql = "select * from emp wher empno = ?";
		
	}

}
