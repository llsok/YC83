package com.yc.jdbc.day0427;

import java.sql.SQLException;
import java.util.*;

import com.yc.jdbc.day0426.DBHelper;

/**
 *	1.���������ѯ
 *	2.�����ֶδ���
 *	3.�����װ List<Map> ==> List<ʵ�����>
 *	4.������� 
 *
 */
public class Demo1 {

	/**
	 * 	���������ѯ
	 * 	emp == null 				  ==> select * from emp
	 * 	emp != null �������е��ֶζ��� null ==> select * from emp
	 * 	ename='����'					  ==> select * from emp where ename = '����'
	 *  ename='����'  , sal = 2000		  ==> select * from emp where ename = '����' and sal=2000
	 * @throws SQLException 
	 * 
	 * 
	 * 
	 */
	public List<Map<String, Object>> queryEmp(Emp emp) throws SQLException {

		DBHelper dbh = new DBHelper();
		// SQL ƴ�Ӳ�Ӱ�����Ĳ�ѯ���� where 1=1
		String sql = "select * from emp where 1=1";
		// ִ���������Ҫ�Ĳ�������
		List<Object> args = new ArrayList<>();

		if(emp!=null) {
			// emp.getEname().trim().isEmpty() �ж��Ƿ���ȫ�ո� ��Ч����
			if (emp.getEname() != null && emp.getEname().trim().isEmpty() == false) {
				sql += " and ename like ?";
				args.add("%" + emp.getEname() + "%");
			}
	
			if (emp.getJob() != null && emp.getJob().trim().isEmpty() == false) {
				sql += " and job like ?";
				args.add("%" + emp.getJob() + "%");
			}
			
			if (emp.getSal() != null) {
				sql += " and sal = ?";
				args.add(emp.getSal());
			}
		}

		return dbh.query(sql, args.toArray());

	}
	
	public static void main(String[] args) throws SQLException {
		
		Demo1 d = new Demo1();
		
		System.out.println(d.queryEmp(null));
		
		Emp emp = new Emp();
		
		System.out.println(d.queryEmp(emp));
		
		emp.setJob("S");
		System.out.println(d.queryEmp(emp));

		emp.setEname("S");
		System.out.println(d.queryEmp(emp));
		
		emp.setSal(2000d);
		System.out.println(d.queryEmp(emp));
	}

}
