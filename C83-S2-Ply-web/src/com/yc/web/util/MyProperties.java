package com.yc.web.util;

import java.io.IOException;
import java.util.Properties;

public class MyProperties extends Properties{
	
	private static MyProperties instance = new MyProperties();
	
	//鏋勯�犲嚱鏁扮鏈夊寲
	private MyProperties() {
		try {
			this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MyProperties getInstance() {
		return instance; 
	}
}
