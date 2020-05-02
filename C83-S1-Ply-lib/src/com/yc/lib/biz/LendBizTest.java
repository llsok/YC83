package com.yc.lib.biz;

import org.junit.Test;

import com.yc.lib.bean.Lend;

public class LendBizTest {
	
	@Test // 注解给JDK看的    注释给人看的信息
	public void testLend() throws BizException {
		LendBiz lb = new LendBiz();
		Lend lend = new Lend();
		lend.setBookid(5);
		lend.setClient("李逵");
		lend.setEmpid(1);
		lb.lend(lend);
		
		// 测试结果应该使用断言进行判断
	}

}
