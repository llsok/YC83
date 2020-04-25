package com.yc.jdbc;

import java.sql.*;

/**
 * @author ����
 * JDBC �������ݵ� �������
 * 1.ע������
 * 2.��ȡ����
 * 3.�������
 * 4.ִ�����
 * 5.�ر�����
 */
public class Demo {

	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		// 1.ע������   alt + /
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2.��ȡ����  DriverManager ����������
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(
				url, user, password);
		// 3.�������
		Statement stat = conn.createStatement();
		
		// 4.ִ�����,  executeUpdate ��������ִ�г��� select �������е�sql���
		String sql = "update dept set loc = 'ѧ��·' where deptno = 60";
		int rows = stat.executeUpdate(sql);
		
		System.out.println("������"+rows+"��¼");
		
		// 5.�ر�����
		conn.close();
	}

}
