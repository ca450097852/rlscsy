package com.hontek.sys.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.sys.pojo.TagStyle;
import com.hontek.sys.service.inter.TagStyleServiceInter;

/**
 * 二维码风格
 *
 */
public class TagStyleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TagStyleServiceInter tagStyleService;
	private TagStyle tagStyle;
	private String ids;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	
	private int entId;	
	
	/**
	 * 查找 tagStyle
	 */
	public void getTagStyleInfo(){
		TagStyle msg = tagStyleService.getTagStyleInfo(entId);
		printJsonString(getObjectJSON(msg));
	}
	
	

	/**
	 * 添加
	 */
	public void addTagStyle(){
		if(tagStyle!=null)
			tagStyle.setUserId(this.getLoginTsUser().getUserId());
		String msg = tagStyleService.addTagStyle(tagStyle);
		printJsonString(msg);
	}
	
	/**
	 * 修改
	 */
	public void updateTagStyle(){
		String msg = tagStyleService.updateTagStyle(tagStyle);
		printJsonString(msg);
	}
	
	/**
	 * 删除
	 */
	public void deleteTagStyle(){
		String msg = tagStyleService.deleteTagStyle(ids);
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
	public TagStyle getTagStyle() {
		return tagStyle;
	}
	public void setTagStyle(TagStyle tagStyle) {
		this.tagStyle = tagStyle;
	}
	public void setTagStyleService(TagStyleServiceInter tagStyleService) {
		this.tagStyleService = tagStyleService;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}
	
}
