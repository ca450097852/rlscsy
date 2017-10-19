package com.hontek.review.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.service.impl.DimennoValueManager;
import com.hontek.review.service.inter.ProductAppendixServiceInter;
/**
 * <p>Title: 产品图片</p>
 * <p>Description: 产品分图片</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductAppendixAction extends BaseAction {
	private ProductAppendixServiceInter productAppendixService;
	private TbProductAppendix productAppend;

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

	public TbProductAppendix getProductAppend() {
		return productAppend;
	}
	public void setProductAppend(TbProductAppendix productAppend) {
		this.productAppend = productAppend;
	}
	public void setProductAppendixService(ProductAppendixServiceInter productAppendixService) {
		this.productAppendixService = productAppendixService;
	}
	
	public void findProAppList(){
		jsonMsg = "";
		if(productAppend==null){
			this.printJsonString("");
		}else{
			jsonMsg = productAppendixService.findProAppList(productAppend,page,rows,order,sort);
		}
		printJsonString(jsonMsg);
	}
	
	public void uploadFile(){
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "proimg");		
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		File newFile = new File(fileDir,newFileName);
		uploadify.renameTo(newFile);
		
		System.out.println(newFileName);
		
		this.printJsonString(newFileName);
	}
	
	public void addProAppendix(){
		jsonMsg = productAppendixService.addProAppendix(productAppend);
		printJsonString(jsonMsg);
	}
	
	public void deleteApps(){
		jsonMsg = productAppendixService.deleteApps(ids);
		printJsonString(jsonMsg);
	}
}
