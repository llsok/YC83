package com.yc.web.servlet.day0429;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.yc.web.util.DbHelper;

public class DBTest {
	
	@Test
	public void test() throws Exception {
		
		DbHelper dbh = new DbHelper();
		
		List<Map<String,Object>> list = dbh.findMutipl("select * from books", null);
		
		for(Map<String,Object> row : list) {
			System.out.println(row);
		}
		
		Assert.assertTrue(list.size() > 0);
		
	}

}
