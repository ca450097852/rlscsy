package com.hontek.sys.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.StyleConfig;
import com.hontek.sys.service.inter.StyleConfigServiceInter;

/**
 * 门户风格设置表
 * @author IMZK
 *
 */
public class StyleConfigAction extends BaseAction{
	private StyleConfigServiceInter styleConfigService;
	private StyleConfig styleConfig;
	private String ids;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	private String scType;
	
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
	public StyleConfig getStyleConfig() {
		return styleConfig;
	}
	public void setStyleConfig(StyleConfig styleConfig) {
		this.styleConfig = styleConfig;
	}
	public void setStyleConfigService(StyleConfigServiceInter styleConfigService) {
		this.styleConfigService = styleConfigService;
	}
	
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	public void findPage(){
		Pager<StyleConfig> pager = styleConfigService.findPage(styleConfig,page,rows,sort,order);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	public void addStyleConfig(){
		if(styleConfig!=null)
			styleConfig.setUserId(this.getLoginTsUser().getUserId());
		String msg = styleConfigService.addStyleConfig(styleConfig);
		printJsonString(msg);
	}
	
	public void updateStyleConfig(){
		String msg = styleConfigService.updateStyleConfig(styleConfig);
		printJsonString(msg);
	}
	
	public void deleteSc(){
		String msg = styleConfigService.deleteSc(ids);
		printJsonString(msg);
	}
	
	public void uploadFile(){
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "styleconfig");		
		
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
	
	
	/**
	 * 风格下拉
	 */
	public void getStyleCombobox(){
		printJsonString(styleConfigService.getStyleCombobox(scType));
	}
	
	
	
}
