package com.yc.jdbc.day0425;

import java.sql.*;

public class Demo1 {
	
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		/**
		 * ע�빥��
		 */
		System.out.println("============");
		selectDept("' or '1'='1");
		System.out.println("============");
		selectDept("������");
		System.out.println("============");
		selectDept("RESEARCH");
	}
	
	/**
	 * ���ݲ������Ʋ�ѯ����
	 * @param dname
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void selectDept(String dname) 
			throws ClassNotFoundException, SQLException {
		// 1.ע������   alt + /
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2.��ȡ����  DriverManager ����������
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(
				url, user, password);
		/**
		 * Ԥ����  ������ 
		 * �ô�:  ��ȫ,  Ч�ʸ�
		 * sql ==> java�ļ�
		 * 	����		����class
		 */
		String sql = "select * from dept where dname = ?";
		// 3.����Ԥ��������
		PreparedStatement ps = conn.prepareStatement(sql);
		
		// 4.ִ�����,  executeUpdate ��������ִ�г��� select �������е�sql���
		System.out.println(sql);
		
		/**
		 * ���� ��ѯ����,  �滻 sql �е� ? (ռλ��)
		 */
		ps.setString(1, dname);
		ResultSet rs = ps.executeQuery();
		
		/**
		 * 1. rs.next(); ����boolean, ����: ���ڿ����α�(ָ��)���ʽ�����еļ�¼
		 * 2. rs.getXXX();  xxx ��ʾ��������, ����,��ȡ��ǰ�α�ָ�����ָ�����ֶ�ֵ
		 * 		rs.getXXX(������  �еı��, ��1��ʼ����)
		 * 		rs.getXXX(����)
		 */
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print("\t");
			System.out.print(rs.getString(2));
			System.out.print("\t");
			System.out.print(rs.getString("LOC"));
			System.out.println();
		}
		
		// 5.�ر�����
		rs.close();
		ps.close();
		conn.close();
	}
	
	public static void selectDept() 
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
		String sql = "select * from dept";
		ResultSet rs = stat.executeQuery(sql);
		
		/**
		 * 1. rs.next(); ����boolean, ����: ���ڿ����α�(ָ��)���ʽ�����еļ�¼
		 * 2. rs.getXXX();  xxx ��ʾ��������, ����,��ȡ��ǰ�α�ָ�����ָ�����ֶ�ֵ
		 * 		rs.getXXX(������  �еı��, ��1��ʼ����)
		 * 		rs.getXXX(����)
		 */
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print("\t");
			System.out.print(rs.getString(2));
			System.out.print("\t");
			System.out.print(rs.getString("LOC"));
			System.out.println();
		}
		
		// 5.�ر�����
		rs.close();
		stat.close();
		conn.close();
	}

}
