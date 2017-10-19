package com.hontek.comm.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 项目路径工具类</p>
 * <p>Description: 获取项目各种目录</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZHANG
 * @version 1.0 ZHANG
 */
public class DirectoryUtil {

	/**
	 * 获取项目的父级目录
	 * @param request
	 * @return
	 */
	public static String getProjectParentPath(HttpServletRequest request){
		return new File(getProjectPath(request)).getParent();
	}
	
	/**
	 * 获取项目的文件存放目录
	 * 返回的是文件存放目录的File对象
	 * @param request
	 * @return
	 */
	public static File getProjectFileDataPath(HttpServletRequest request){
		String pathname = getProjectParentPath(request).concat(File.separator).concat(getProjectName(request)).concat("Files");
		File file = new File(pathname);
		if(!file.exists()){
			file.mkdir();
		}
		return file;
	}
	
	/**
	 * 获取某个目录
	 * @param request
	 * @param dirName
	 * @return
	 */
	public static File getDirectoryByName(HttpServletRequest request,String dirName){
		//获取项目存放文件目录
		File parent = getProjectFileDataPath(request);	
		//获取具体附件目录
		File fileDir = new File(parent, dirName);	
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}
	
	
	/**
	 * 获取项目上下文
	 * @param request
	 * @return
	 */
	public static String getProjectContextPath(HttpServletRequest request){
		return request.getContextPath();
	}
	
	/**
	 * 获取项目名称
	 * @param request
	 * @return
	 */
	public static String getProjectName(HttpServletRequest request){
		//return getProjectContextPath(request).substring(1);
		return "nytsy";
	}
	
	/**
	 * 获取项目根目录
	 * @param request
	 * @return
	 */
	public static String getProjectPath(HttpServletRequest request){
		return request.getRealPath("");
	}
}
