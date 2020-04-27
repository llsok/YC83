package com.yc.jdbc.day0427;

import java.sql.SQLException;
import java.util.*;

import com.yc.jdbc.day0426.DBHelper;

/**
 *	1.组合条件查询
 *	2.日期字段处理
 *	3.结果封装 List<Map> ==> List<实体对象>
 *	4.事务管理 
 *
 */
public class Demo1 {

	/**
	 * 	组合条件查询
	 * 	emp == null 				  ==> select * from emp
	 * 	emp != null 但是所有的字段都是 null ==> select * from emp
	 * 	ename='张三'					  ==> select * from emp where ename = '张三'
	 *  ename='张三'  , sal = 2000		  ==> select * from emp where ename = '张三' and sal=2000
	 * @throws SQLException 
	 * 
	 * 
	 * 
	 */
	public List<Map<String, Object>> queryEmp(Emp emp) throws SQLException {

		DBHelper dbh = new DBHelper();
		// SQL 拼接不影响结果的查询参数 where 1=1
		String sql = "select * from emp where 1=1";
		// 执行语句所需要的参数集合
		List<Object> args = new ArrayList<>();

		if(emp!=null) {
			// emp.getEname().trim().isEmpty() 判断是否是全空格 有效输入
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
