package com.hontek.review.action;

import java.io.File;
import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.TraceAppdixServiceInter;
/**
 * @ClassName: TraceAppdixAction
 * @Description: TODO(附件)
 * @date 2014-11-19 下午05:00:15
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TraceAppdixAction extends BaseAction {
	private TraceAppdixServiceInter traceAppdixService;
	private TbTraceAppdix traceAppdix;
	
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
	public TraceAppdixServiceInter getTraceAppdixService() {
		return traceAppdixService;
	}
	public void setTraceAppdixService(TraceAppdixServiceInter traceAppdixService) {
		this.traceAppdixService = traceAppdixService;
	}
	public TbTraceAppdix getTraceAppdix() {
		return traceAppdix;
	}
	public void setTraceAppdix(TbTraceAppdix traceAppdix) {
		this.traceAppdix = traceAppdix;
	}
	
	
	
	public void findAppList(){
		jsonMsg = traceAppdixService.findTraceAppdixList(traceAppdix,page,rows,order,sort);
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
	
	public void addTraceAppdix(){
		jsonMsg = traceAppdixService.addTraceAppdix(traceAppdix);
		printJsonString(jsonMsg);
	}
	
	public void deleteApps(){
		jsonMsg = traceAppdixService.deleteApps(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 根据产品ID查询附件信息
	 */
	public void findTraceAppdixsListByProId(){
		printJsonString(traceAppdixService.findTraceAppdixsListByProId(traceAppdix));
	}
	
	public void findTraceAppdixsListByPid(){
		printJsonString(traceAppdixService.findTraceAppdixsListByPid(traceAppdix));
	}
}
