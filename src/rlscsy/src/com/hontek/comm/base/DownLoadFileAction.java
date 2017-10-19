package com.hontek.comm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.hontek.comm.util.DirectoryUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件下载
 * @author dream
 *
 */
public class DownLoadFileAction extends ActionSupport{
	
	 private String fileName;
	 
	 private String outFileName;
     
     private InputStream inputStream;
     
	public InputStream getInputStream() throws FileNotFoundException {
		try {
	    	 File fileDir = DirectoryUtil.getProjectFileDataPath(ServletActionContext.getRequest ());
			 inputStream = new FileInputStream(new File(fileDir,fileName));
	    	 outFileName = fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}  	 
         return inputStream;
     }
    
     public String execute(){
    	 File fileDir = DirectoryUtil.getProjectFileDataPath(ServletActionContext.getRequest ());
    	 File file = new File(fileDir,fileName);
    	 if(!file.exists()){
    		 return "inputStreamError";
    	 }else{
             return "success";
    	 }
     }
     
     
     
	public String getFileName() {
		return fileName;
	}
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }	
	public String getOutFileName() {
		return outFileName;
	}
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	

    
}