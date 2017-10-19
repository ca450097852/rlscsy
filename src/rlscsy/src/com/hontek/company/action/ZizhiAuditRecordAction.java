package com.hontek.company.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.company.pojo.ZizhiAuditRecord;
import com.hontek.company.service.inter.ZizhiAuditRecordServiceInter;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EnterpriseServiceInter;

/**
 * <p>Title: 资质审核表</p>
 * <p>Description: 资质审核Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAuditRecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ZizhiAuditRecordServiceInter zizhiAuditRecordServiceInter;	
	private ZizhiAuditRecord zizhiAuditRecord;
	private EnterpriseServiceInter enterprseService;
	
	private String ids;	
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	public ZizhiAuditRecord getZizhiAuditRecord() {
		return zizhiAuditRecord;
	}

	public void setZizhiAuditRecord(ZizhiAuditRecord zizhiAuditRecord) {
		this.zizhiAuditRecord = zizhiAuditRecord;
	}

	public void setEnterprseService(EnterpriseServiceInter enterprseService) {
		this.enterprseService = enterprseService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setZizhiAuditRecordServiceInter(ZizhiAuditRecordServiceInter zizhiAuditRecordServiceInter) {
		this.zizhiAuditRecordServiceInter = zizhiAuditRecordServiceInter;
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
	 * 添加资质审核
	 */
	public void addZizhiAuditRecord(){
		TsUser tsUser =  getLoginTsUser();	
		TsEnterprise enterprise = (TsEnterprise)getSession().getAttribute("enterprse");		
		if(zizhiAuditRecord!=null){
			zizhiAuditRecord.setEntId(tsUser.getEntId());
			zizhiAuditRecord.setEntName(enterprise.getName());
			
			zizhiAuditRecord.setAuditEntid(enterprise.getParentId());
			
		}
		jsonMsg  = zizhiAuditRecordServiceInter.addZizhiAuditRecord(zizhiAuditRecord);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加资质审核
	 */
	public void addZizhiAuditRecordFromApp(){

		if(zizhiAuditRecord!=null){
			
			TsEnterprise enterprise = enterprseService.getEnterPirseByEntId(zizhiAuditRecord.getEntId());
			
			zizhiAuditRecord.setEntName(enterprise.getName());
			zizhiAuditRecord.setAuditEntid(enterprise.getParentId());

		}
		jsonMsg  = zizhiAuditRecordServiceInter.addZizhiAuditRecord(zizhiAuditRecord);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改资质审核
	 */
	public void updateZizhiAuditRecord(){
		
		TsUser tsUser =  getLoginTsUser();	
		TsEnterprise enterprise = (TsEnterprise)getSession().getAttribute("enterprse");		
		if(zizhiAuditRecord!=null){
			
			zizhiAuditRecord.setAuditEntid(tsUser.getEntId());
			zizhiAuditRecord.setAuditEntname(enterprise.getName());
			zizhiAuditRecord.setAuditUser(tsUser.getUserId());
			zizhiAuditRecord.setAuditUsername(tsUser.getUserName());
			zizhiAuditRecord.setAuditTime(DateUtil.formatDateTime());
		}
		
		jsonMsg  = zizhiAuditRecordServiceInter.updateZizhiAuditRecord(zizhiAuditRecord);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除资质审核
	 */
	public void deleteZizhiAuditRecord(){
		jsonMsg  = zizhiAuditRecordServiceInter.deleteZizhiAuditRecord(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询资质审核
	 */
	public void findZizhiAuditRecordPagerList(){
		
		TsEnterprise enterprise = (TsEnterprise)getSession().getAttribute("enterprse");		

		if(zizhiAuditRecord==null){
			zizhiAuditRecord = new ZizhiAuditRecord();
		}
		zizhiAuditRecord.setAuditEntid(enterprise.getEntId());
		
		jsonMsg  = zizhiAuditRecordServiceInter.findZizhiAuditRecordPagerList(zizhiAuditRecord, page, rows, sort , order);
		//获取文件夹
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 资质审核
	 */
	public void updateBatZizhiAuditRecord(){
		TsUser tsUser =  getLoginTsUser();	
		TsEnterprise enterprise = (TsEnterprise)getSession().getAttribute("enterprse");		
		tsUser.setEntName(enterprise.getName());		
		jsonMsg  = zizhiAuditRecordServiceInter.updateAuditZizhiAuditRecord(ids,tsUser);
		printJsonString(jsonMsg);
	}

}
