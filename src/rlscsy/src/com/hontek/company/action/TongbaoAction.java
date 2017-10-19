package com.hontek.company.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.pojo.Tongbao;
import com.hontek.company.service.inter.TongbaoServiceInter;

/**
 * <p>Title: 通报企业表</p>
 * <p>Description: 通报企业Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class TongbaoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TongbaoServiceInter tongbaoServiceInter;	
	private Tongbao tongbao;
	private String ids;	
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	public Tongbao getTongbao() {
		return tongbao;
	}

	public void setTongbao(Tongbao tongbao) {
		this.tongbao = tongbao;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setTongbaoServiceInter(TongbaoServiceInter tongbaoServiceInter) {
		this.tongbaoServiceInter = tongbaoServiceInter;
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

	/**
	 * 添加通报企业
	 */
	public void addTongbao(){
		if(tongbao!=null){
			tongbao.setCrttime(DateUtil.formatDateTime());
		}
		jsonMsg  = tongbaoServiceInter.addTongbao(tongbao);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 上传附件
	 */
	public void uploadFile() {			
		
		//获取项目存放文件目录
		File parent = DirectoryUtil.getProjectFileDataPath(getRequest());	
		//获取具体附件目录
		File fileDir = new File(parent, "tongbao");	
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		uploadify.renameTo(new File(fileDir,newFileName));
				
		this.printJsonString(newFileName);
	}
	
	/**
	 * 删除附件
	 */
	public void deleteFile(){
		//获取通报附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "tongbao");		
		File file = new File(fileDir,fileName);		
		if(file.exists()){
			this.printJsonString(String.valueOf(file.delete()));
		}
	}
	
	/**
	 * 修改通报企业
	 */
	public void updateTongbao(){
		jsonMsg  = tongbaoServiceInter.updateTongbao(tongbao);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除通报企业
	 */
	public void deleteTongbao(){
		jsonMsg  = tongbaoServiceInter.deleteTongbao(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询通报企业
	 */
	public void findTongbaoPagerList(){
		
		jsonMsg  = tongbaoServiceInter.findTongbaoPagerList(tongbao, page, rows, sort , order);
		//获取文件夹
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 发布通报企业
	 */
	public void publishTongbao(){
		jsonMsg  = tongbaoServiceInter.updatePublishTongbao(ids);
		printJsonString(jsonMsg);
	}

}
