package com.hontek.company.action;

import java.io.File;
import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.pojo.ProductionAppendix;
import com.hontek.company.service.inter.ProductionAppendixServiceInter;

/**
 * <p>Title: 生产信息附件表</p>
 * <p>Description: 生产信息附件Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionAppendixAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductionAppendixServiceInter productionAppendixServiceInter;	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	private int proId;
	private String appType;
	
	
	public void setProductionAppendixServiceInter(ProductionAppendixServiceInter productionAppendixServiceInter) {
		this.productionAppendixServiceInter = productionAppendixServiceInter;
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

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	/**
	 * 上传附件
	 */
	public void uploadFile() {			
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "production");		
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		uploadify.renameTo(new File(fileDir,newFileName));
		
		if(proId!=0){
			ProductionAppendix productionAppendix = new ProductionAppendix(0, proId, appType, uploadifyFileName, newFileName, DateUtil.formatDateTime(), 5, "");	
			jsonMsg = productionAppendixServiceInter.addProductionAppendix(productionAppendix);
		}
				
		this.printJsonString(newFileName);
	}
	
	/**
	 * 删除附件
	 */
	public void deleteFile(){
		//获取资质附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "production");		
		File file = new File(fileDir,fileName);		
		if(file.exists()){
			if(productionAppendixServiceInter.deleteProductionAppendixByPath(fileName)>0){
				this.printJsonString(String.valueOf(file.delete()));
			}
		}
	}
	
	
	/**
	 * 查询生产信息附件
	 */
	public void findProductionAppendixPagerList(){
		jsonMsg  = productionAppendixServiceInter.findProductionAppendixPagerList(proId, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	

}
