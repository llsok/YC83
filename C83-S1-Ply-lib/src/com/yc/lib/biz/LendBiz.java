package com.yc.lib.biz;

import java.sql.Connection;
import java.sql.SQLException;

import com.yc.lib.bean.Lend;
import com.yc.lib.util.DBHelper;
/**
 * 要进行数据库事务控制的前提:  所有的sql语句必须在一个连接中执行
 * 
 * try{
 * 		业务逻辑 (insert update delete...)
 * 		没有异常的情况下, 必须提交事务
 * } catch (异常) {
 * 		有异常的情况下, 必须回滚事务
 * } finally {
 * 		最后都要关闭连接
 * }
 *
 */

public class LendBiz {
	
	/**
	 * 借书方法
	 * @param lend
	 * @throws BizException 
	 */
	public void lend(Lend lend) throws BizException {
		
		// 开始事务管理
		DBHelper dbh = new DBHelper(true);
		Connection conn = null;
		
		/**
		 *  1.向借阅表添加一个借阅记录, 表示该图书已经借出  ==> insert into lend ....
		 *  2.修改图书的状态, status 0 => 1  ===>  update book set status ....
		 */
		
		try {
			// 获取为一个这个连接对象
			conn = dbh.getConnection();
			String sql = "insert into lend values (seq_lib.nextval,?,?,sysdate,null,?)";
			dbh.update(sql, lend.getBookid(),lend.getClient(),lend.getEmpid());
			sql = "update book set status=1 where id=?";
			dbh.update(sql, lend.getBookid());
			// 提交事务
			conn.commit();
		} catch ( Exception e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new BizException("借书失败!",e1);
			}
			throw new BizException("借书失败!",e);
		} finally {
			try {
				dbh.closeConnection();
			} catch (SQLException e) {
				throw new BizException("借书失败!",e);
			}
		}
		
	}

	/**
	 * 根据图书id查借阅记录
	 * @param asInteger
	 * @return
	 * @throws BizException 
	 */
	public Lend queryByBookId(Integer bookid) throws BizException {
		DBHelper dbh = new DBHelper();
		String sql = "select * from lend where bookid=? and rettime is null";
		try {
			return dbh.queryOne(sql, Lend.class, bookid);
		} catch (SQLException e) {
			throw new BizException("检索借书记录失败!",e);
		}
	}

}
