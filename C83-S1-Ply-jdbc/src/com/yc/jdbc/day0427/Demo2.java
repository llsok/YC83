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
public class Demo2 {
	
	public static List<Map<String,Object>> queryByDate(Timestamp date) throws SQLException{
		DBHelper dbh = new DBHelper();
		return dbh.query("select * from emp where hiredate=?", date);
	}
	
	public static List<Map<String,Object>> queryByYear(int year) throws SQLException{
		DBHelper dbh = new DBHelper();
		/* ��ʽһ */
		// return dbh.query("select * from emp where to_char(hiredate,'yyyy')=?", year);
		/* ��ʽ�� �����ƻ�����*/
		String sql = "select * from emp where hiredate between ? and ?";
		Date begin = Date.valueOf(year+"-1-1");
		Date end = Date.valueOf(year+"-12-31");
		return dbh.query(sql, begin, end);
	}

	public static void main(String[] args) throws SQLException {
		
		/*DBHelper dbh = new DBHelper();
		Map<String,Object> emp = dbh.queryOne("select * from emp where empno=?", 7369);
		System.out.println(emp.get("HIREDATE"));
		System.out.println(emp.get("HIREDATE").getClass());*/
		
		//Date date = Date.valueOf("1980-12-17");
		Timestamp timestamp = Timestamp.valueOf("1980-12-17 00:00:00");
		System.out.println(queryByDate(timestamp));
		
		System.out.println(queryByYear(1981));
	}

}
