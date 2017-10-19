package com.hontek.comm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.hontek.comm.util.DirectoryUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DownLocationAction extends ActionSupport{
	
	 private String fileName;
	 
	 private String outFileName;
     
     public void setFileName(String fileName) {
             this.fileName = fileName;
     }
     
     private InputStream inputStream;
     

	public InputStream getInputStream() throws FileNotFoundException {
    	 String path = System.getProperty("file.separator") ;
    	 String mpath = DirectoryUtil.getProjectParentPath(ServletActionContext.getRequest())+path+"nytsyFiles"+path+"qrcode"+path+fileName;
    	// mpath = mpath+"qrcode"+System.getProperties().getProperty("file.separator")+fileName;
		try {
			inputStream = new FileInputStream(mpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    	 outFileName = fileName;
    	 
         return inputStream;
     }
    
     public String execute(){
    	 String path = System.getProperty("file.separator") ;
    	 String mpath = DirectoryUtil.getProjectParentPath(ServletActionContext.getRequest())+path+"nytsyFiles"+path+"qrcode"+path+fileName;
    	 File file = new File(mpath);
    	 if(!file.exists()){
    		 return "inputStreamError";
    	 }else{
             return "success";
    	 }
     }
	public String getFileName() {
		return fileName;
	}
	
	
	public String getOutFileName() {
		return outFileName;
	}
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	

    
}