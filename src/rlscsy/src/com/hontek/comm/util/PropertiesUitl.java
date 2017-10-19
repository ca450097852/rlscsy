package com.hontek.comm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 读取属性文件
 * @author dream
 *
 */
public class PropertiesUitl {

	private PropertiesUitl(){}
	private static Properties prop = null;
	
	public static Properties getProperties(){
		if(prop==null){
			prop = new Properties();		
			try {
				//InputStream in = new FileInputStream(PropertiesUitl.class.getClass().getResource("/logMapping.properties").getPath());
	            InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("logMapping.properties");
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return prop;
	}
}
