package com.yc.lib.biz;

import java.sql.SQLException;

import com.yc.lib.bean.Lend;
import com.yc.lib.util.DBHelper;

public class LendBiz {
	
	/**
	 * 借书方法
	 * @param lend
	 * @throws BizException 
	 */
	public void lend(Lend lend) throws BizException {
		
		DBHelper dbh = new DBHelper();
		
		/**
		 *  1.向借阅表添加一个借阅记录, 表示该图书已经借出  ==> insert into lend ....
		 *  2.修改图书的状态, status 0 => 1  ===>  update book set status ....
		 */
		
		try {
			String sql = "insert into lend values (seq_lib.nextval,?,?,sysdate,null,?)";
			dbh.update(sql, lend.getBookid(),lend.getClient(),lend.getEmpid());
			// 模拟异常
			int i = 1/0;
			sql = "update book set status=1 where id=?";
			dbh.update(sql, lend.getBookid());
		} catch (SQLException e) {
			throw new BizException("借书失败!",e);
		}
		
	}

}
