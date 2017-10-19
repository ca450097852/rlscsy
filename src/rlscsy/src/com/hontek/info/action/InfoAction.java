package com.hontek.info.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.service.inter.InfoServiceInter;
import com.hontek.info.service.inter.InfoTypeServiceInter;
/**
 * 资讯 Action
 * @author qql
 *
 */
@SuppressWarnings("serial")
public class InfoAction extends BaseAction{
	private InfoServiceInter infoService;
	private InfoTypeServiceInter infoTypeService;
	private TInfo info;
	private String infoIds;
	private String rsts;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	/**
	 * 资讯列表
	 * @throws AppException
	 */
	public void findInfoList() throws AppException{
		if(info==null){
			info = new TInfo();
		}
		info.setEntId(getLoginUserEntId()*1l);
		printJsonString(this.infoService.findInfoList(info, page, rows, sort, order)); 
	}
	
	/**
	 * 添加资讯信息
	 */
	public void addInfo(){
		info.setEntId(getLoginUserEntId()*1l);
		info.setUserId(getLoginUserId());
		info.setCrttime(DateUtil.formatDate());
		printJsonString(this.infoService.addInfo(info));
	}
	
	/**
	 * 修改资讯信息
	 */
	public void updateInfo(){
		info.setAuditor(getLoginUserName());
		printJsonString(this.infoService.updateInfo(info));
	}
	
	/**
	 * 删除一个资讯信息
	 */
	public void deleteInfo(){
		printJsonString(this.infoService.deleteInfo(infoIds));
	}
	
	/**
	 * 批量审核资讯
	 */
	public void updateAuditInfo(){
		System.out.println(rsts);
	    printJsonString(this.infoService.updateAuditInfo(infoIds,rsts));
	}
	/**
	 * 分类下拉
	 * 
	 */
	public void getInfoTypeTree(){
		printJsonString(this.infoTypeService.getInfoTypeTree());
	}
	
	
	public void uploadFile(){
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "titleImg");		
		
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
	
	
	//getter and setter
	public void setInfoService(InfoServiceInter infoService) {
		this.infoService = infoService;
	}
	public void setInfoTypeService(InfoTypeServiceInter infoTypeService) {
		this.infoTypeService = infoTypeService;
	}

	public TInfo getInfo() {
		return info;
	}
	public void setInfo(TInfo info) {
		this.info = info;
	}
	public String getInfoIds() {
		return infoIds;
	}
	public void setInfoIds(String infoIds) {
		this.infoIds = infoIds;
	}
	public String getRsts() {
		return rsts;
	}
	public void setRsts(String rsts) {
		this.rsts = rsts;
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
	
	

}
