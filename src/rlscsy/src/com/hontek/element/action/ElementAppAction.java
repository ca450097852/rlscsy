package com.hontek.element.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Decoder;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.element.pojo.TbElementApp;
import com.hontek.element.service.inter.ElementAppServiceInter;

/**
 * 要素附件Action类
 */
@SuppressWarnings("serial")
public class ElementAppAction extends BaseAction {
	private ElementAppServiceInter elementAppServiceInter ;
	private TbElementApp elementApp;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	private String ids;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getUploadify() {
		return uploadify;
	}
	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}
	public String getUploadifyFileName() {
		return uploadifyFileName;
	}
	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}
	
	public void setElementAppServiceInter(
			ElementAppServiceInter elementAppServiceInter) {
		this.elementAppServiceInter = elementAppServiceInter;
	}
	public void setElementApp(TbElementApp elementApp) {
		this.elementApp = elementApp;
	}
	
	public TbElementApp getElementApp() {
		return elementApp;
	}
	public void findAppList(){
		jsonMsg = elementAppServiceInter.findElementAppList(elementApp,page,rows,order,sort);
		printJsonString(jsonMsg);
	}
	
	public void uploadFile(){
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "element");		
		
		// 获取扩展名
		String extName = "";// 扩展名
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		File newFile = new File(fileDir,newFileName);
		uploadify.renameTo(newFile);
				
		this.printJsonString(newFileName);
	}
	
	
	public void uploadFileForBase64(){
        String success = "fail";

    	if(uploadifyFileName==null){
			this.printJsonString(success);
			return;
		}
    	   // 只允许jpg/png
        String header ="data:image/jpeg;base64";    
        String header2 ="data:image/png;base64";     
        String postfix = ".jpg";       
		if(uploadifyFileName.startsWith(header)){
			postfix = ".jpg";
		}else if(uploadifyFileName.startsWith(header2)){
			postfix = ".png";
		}else{
			System.out.println("文件上传失败！不是图片jpg、png类型");
			this.printJsonString(success);
			return;
		}		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "element");				   		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sdf.format(new Date())+postfix;   		

        try{    		
	            // 去掉头部
        		uploadifyFileName=uploadifyFileName.split(",")[1];
	            //image = image.substring(header.length());
	            // 写入磁盘
	            BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(uploadifyFileName);        //将字符串格式的image转为二进制流（biye[])的decodedBytes
                String imgFilePath =fileDir.getAbsolutePath()+File.separator+fileName;                        //指定图片要存放的位置
                FileOutputStream out = new FileOutputStream(imgFilePath);        //新建一个文件输出器，并为它指定输出位置imgFilePath
                out.write(decodedBytes); //利用文件输出器将二进制格式decodedBytes输出
                out.close();                        //关闭文件输出器
                System.out.println("文件上传成功！文件名称为："+imgFilePath);
                
                if(elementApp!=null){
                	if(elementApp.getFkId()!=0&&null!=elementApp.getAppType()){
                		elementApp.setAppUrl(fileName);
                		elementAppServiceInter.addElementApp(elementApp);
                	}
                }
                
                
        		this.printJsonString(fileName);
        }catch(Exception e){
                success = "上传文件失败！|"+e.getMessage();
                e.printStackTrace();
        }
	}
	
	
	public void uploadFileDorForBase64(){
		
        String success = "fail";
        
    	// 只允许jpg
        String header ="data:image/jpeg;base64,";               		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "protype");		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sdf.format(new Date())+".jpg";   		
       
		if(uploadifyFileName==null){
			this.printJsonString(success);
			return;
		}
		
		if(!uploadifyFileName.startsWith(header)){
			System.out.println("文件上传失败！不是图片类型");
			this.printJsonString(success);
			return;
		}
		
        try{    		
	            // 去掉头部
        		uploadifyFileName=uploadifyFileName.split(",")[1];
	            //image = image.substring(header.length());
	            // 写入磁盘
	            BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(uploadifyFileName);        //将字符串格式的image转为二进制流（biye[])的decodedBytes
                String imgFilePath =fileDir.getAbsolutePath()+File.separator+fileName;                        //指定图片要存放的位置
                FileOutputStream out = new FileOutputStream(imgFilePath);        //新建一个文件输出器，并为它指定输出位置imgFilePath
                out.write(decodedBytes); //利用文件输出器将二进制格式decodedBytes输出
                out.close();                        //关闭文件输出器
                System.out.println("文件上传成功！文件名称为："+imgFilePath);
        		this.printJsonString(fileName);
        }catch(Exception e){
                success = "上传文件失败！|"+e.getMessage();
                e.printStackTrace();
        }
        
        
	}
	
	
	
	
	
	
	
	public void addElementApp(){
		jsonMsg = elementAppServiceInter.addElementApp(elementApp);
		printJsonString(jsonMsg);
	}
	
	public void deleteApps(){
		jsonMsg = elementAppServiceInter.deleteElementApp(ids);
		printJsonString(jsonMsg);
	}
	

}
