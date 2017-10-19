package com.hontek.sys.action;

import java.io.File;

import javax.ws.rs.core.Request;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.service.inter.EntStyleServiceInter;

/**
 * 风格关系
 * @author qql
 *
 */
public class EntStyleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntStyleServiceInter entStyleService;
	private EntStyle entStyle;
	private String ids;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	
	private String entId;	
	private String scType;	
	
	/**
	 * 查找 entStyle
	 */
	public void getEntStyleInfo(){
		EntStyle msg = entStyleService.getEntStyleInfo(entId,scType);
		printJsonString(getObjectJSON(msg));
	}
	
	
	/**
	 * 列表
	 */
	public void findPage(){
		Pager<EntStyle> pager = entStyleService.findPage(entStyle,page,rows,sort,order);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	/**
	 * 添加
	 */
	public void addEntStyle(){
		if(entStyle!=null)
			entStyle.setUserId(this.getLoginTsUser().getUserId());
		String msg = entStyleService.addEntStyle(entStyle);
		printJsonString(msg);
	}
	
	/**
	 * 修改
	 */
	public void updateEntStyle(){
		String msg = entStyleService.updateEntStyle(entStyle);
		printJsonString(msg);
	}
	
	/**
	 * 删除
	 */
	public void deleteEntStyle(){
		String msg = entStyleService.deleteEntStyle(ids);
		printJsonString(msg);
	}
	
	public void uploadFile(){
		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "entstyle");		
		
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
	
	/*****getter**setter*****/

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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public EntStyle getEntStyle() {
		return entStyle;
	}
	public void setEntStyle(EntStyle entStyle) {
		this.entStyle = entStyle;
	}
	public void setEntStyleService(EntStyleServiceInter entStyleService) {
		this.entStyleService = entStyleService;
	}


	public String getEntId() {
		return entId;
	}


	public void setEntId(String entId) {
		this.entId = entId;
	}


	public String getScType() {
		return scType;
	}


	public void setScType(String scType) {
		this.scType = scType;
	}
	
}
