package com.hontek.sys.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.Banner;
import com.hontek.sys.service.inter.BannerServiceInter;

public class BannerAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BannerServiceInter bannerService;
	private Banner banner;
	private String ids;
	
	private int entId;//主体Id;
	private int position;//广告位置;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	
	
	
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
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
	public Banner getBanner() {
		return banner;
	}
	public void setBanner(Banner banner) {
		this.banner = banner;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setBannerService(BannerServiceInter bannerService) {
		this.bannerService = bannerService;
	}
	
	public void addBanner(){
		if(banner!=null)
			banner.setUserId(this.getLonginUserId());
		jsonMsg = bannerService.addBanner(banner);
		printJsonString(jsonMsg);
	}
	
	public void updateBanner(){
		if(banner!=null)
			banner.setUserId(this.getLonginUserId());
		jsonMsg = bannerService.updateBanner(banner);
		printJsonString(jsonMsg);
	}
	
	public void delete(){
		jsonMsg = bannerService.deleteBanners(ids);
		printJsonString(jsonMsg);
	}
	
	public void findList(){
		Pager<Banner> pager = bannerService.findList(banner,page,rows,sort,order);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	public void uploadFile(){
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "banner");		
		
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
	
	
	public void upFile(){
//		System.out.println("entId==@@@"+entId);
//		System.out.println("position==@@@"+position);
		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "banner");		
		
		// 获取扩展名
		String extName = "";// 扩展名
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		File newFile = new File(fileDir,newFileName);
		uploadify.renameTo(newFile);
		
		
		if(entId>0&&position>0){
			
			banner = new Banner();
			banner.setPosition(position);
			banner.setEntId(entId);
			banner.setImgUrl(newFileName);
			banner.setImgName(uploadifyFileName);
			banner.setSeq(5);
			banner.setSts(1);
			bannerService.addBanner(banner);
		}
				
		this.printJsonString(newFileName);
	}
	
}
