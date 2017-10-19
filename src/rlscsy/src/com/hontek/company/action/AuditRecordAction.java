package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.AuditRecord;
import com.hontek.company.service.inter.AuditRecordServiceInter;

/**
 * <p>Title: 审核记录表</p>
 * <p>Description: 审核记录Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class AuditRecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AuditRecordServiceInter auditRecordServiceInter;	
	private AuditRecord auditRecord;
	private String ids;	
	
	public AuditRecord getAuditRecord() {
		return auditRecord;
	}

	public void setAuditRecord(AuditRecord auditRecord) {
		this.auditRecord = auditRecord;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setAuditRecordServiceInter(AuditRecordServiceInter auditRecordServiceInter) {
		this.auditRecordServiceInter = auditRecordServiceInter;
	}

	/**
	 * 添加审核记录
	 */
	public void addAuditRecord(){
		jsonMsg  = auditRecordServiceInter.addAuditRecord(auditRecord,getEnterprse());
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改审核记录
	 */
	public void updateAuditRecord(){
		jsonMsg  = auditRecordServiceInter.updateAuditRecord(auditRecord);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除审核记录
	 */
	public void deleteAuditRecord(){
		jsonMsg  = auditRecordServiceInter.deleteAuditRecord(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询审核记录
	 */
	public void findAuditRecordPagerList(){
		jsonMsg  = auditRecordServiceInter.findAuditRecordPagerList(auditRecord, page, rows, sort , order);
		printJsonString(jsonMsg);
	}

}
