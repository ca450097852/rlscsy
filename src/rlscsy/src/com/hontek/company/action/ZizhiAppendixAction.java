package com.hontek.company.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Decoder;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.service.inter.ZizhiAppendixServiceInter;

/**
 * <p>Title: 资质证书附件表</p>
 * <p>Description: 资质证书附件Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAppendixAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ZizhiAppendixServiceInter zizhiAppendixServiceInter;	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	private int zid;
	
	public void setZizhiAppendixServiceInter(ZizhiAppendixServiceInter zizhiAppendixServiceInter) {
		this.zizhiAppendixServiceInter = zizhiAppendixServiceInter;
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
	

	public int getZid() {
		return zid;
	}

	public void setZid(int zid) {
		this.zid = zid;
	}

	
	
	public void uploadFileForBase64(){
        String success = "fail";
        
        // 只允许jpg
        String header ="data:image/jpeg;base64,";               		
		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "zizhi");		
		   		
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
	
	/**
	 * 上传附件
	 */
	public void uploadFile() {			
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "zizhi");		
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		uploadify.renameTo(new File(fileDir,newFileName));
		
		if(zid!=0){
			ZizhiAppendix zizhiAppendix = new ZizhiAppendix(0, zid, uploadifyFileName, newFileName, DateUtil.formatDateTime(), 5, "");
			jsonMsg = zizhiAppendixServiceInter.addZizhiAppendix(zizhiAppendix);
			System.out.println(jsonMsg);
		}
		
		this.printJsonString(newFileName);
	}
	
	/**
	 * 删除附件
	 */
	public void deleteFile(){
		//获取资质附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "zizhi");		
		File file = new File(fileDir,fileName);		
		if(file.exists()){
			if(zizhiAppendixServiceInter.deleteZizhiAppendixByPath(fileName)>0){
				this.printJsonString(String.valueOf(file.delete()));
			}
		}
	}
	
	
	/**
	 * 查询资质证书附件
	 */
	public void findZizhiAppendixPagerList(){
		rows = 1000;
		jsonMsg  = zizhiAppendixServiceInter.findZizhiAppendixPagerList(zid, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
}
