package com.hontek.review.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


/**
 * <p>Title: 二维码全局变量管理</p>
 * <p>Description: 二维码全局变量管理</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class DimennoValueManager {
	public static String QrCodePath = "";//二维码存在路径
	public static String QrCodeLogoPath = "";//二维码存在路径
	public static String TomcatPath = "";
	public static String ZiZhi = "";//资质附件存在路径
	public static String ProImg = "";//产品图片存放路径
	public static String URL = "";
	public static String proBatchURL = "";

	public static String file_separator = System.getProperties().getProperty("file.separator");
	//初始化变量
	static{
		Properties prop = new Properties();
		try {
			prop.load(DimennoValueManager.class.getClassLoader().getResourceAsStream("dimenno.properties"));
			//tomcat6 跟 tomcat7 获取到的路径不一样
			String path = System.getProperty("user.dir");
			TomcatPath = path.lastIndexOf("bin")!=-1?path.substring(0,path.length()-3):path.endsWith(file_separator)?path:path+file_separator;
			
			path = TomcatPath+"webapps"+file_separator;
			
			QrCodePath = path+"nytsyFiles"+file_separator+"qrcode"+file_separator;
			
			QrCodeLogoPath = QrCodePath+"logo"+file_separator;
			
			URL = prop.getProperty("url");
			
			proBatchURL = prop.getProperty("proBatchUrl");
			
			ZiZhi = path+"nytsyFiles"+file_separator+"zizhi"+file_separator;
			
			ProImg = path+"nytsyFiles"+file_separator+"proimg"+file_separator;
			
			//判断目录是否存在
			File file = new File(QrCodePath);
			if(!file.exists()){
				file.mkdir();
			}
			
			file = new File(QrCodeLogoPath);
			if(!file.exists()){
				file.mkdir();
			}
			
			file = new File(ZiZhi);
			if(!file.exists()){
				file.mkdir();
			}
			file = new File(ProImg);
			if(!file.exists()){
				file.mkdir();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
