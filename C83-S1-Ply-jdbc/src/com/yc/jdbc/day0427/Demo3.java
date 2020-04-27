package com.yc.jdbc.day0427;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.yc.jdbc.day0426.DBHelper;

/**
 *	1.���������ѯ
 *	2.�����ֶδ���
 *		java.sql.Date		=> ������
 *		java.sql.Timestamp	=> ������ʱ���� ����
 *		jdbc����������   !=   ���ݵ���������
 *
 *	3.�����װ List<Map> ==> List<ʵ�����>
 *	4.������� 
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
