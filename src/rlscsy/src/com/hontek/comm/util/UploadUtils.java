package com.hontek.comm.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class UploadUtils {
	
	public static String saveUploadFile(File upload,String uploadFileName){
		
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload");
		//把日期类型格式化为"/yyyy/MM/dd/"这种形式的字符串
		
		//String path = basePath+"/"+this.uploadFileName;
		File dest = new File(basePath+"/"+uploadFileName);
	
		try {
			FileUtils.copyFile(upload, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return basePath;
	}
}
