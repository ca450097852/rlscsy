package com.hontek.record.pojo;

/**
 * 批次信息
 * @author
 *
 */
public class TbProTypeBatch {
	private int ptbId;
	private Integer ptqId;
	private int entId;
	
	private int recId;
	//	
	private String batchName;
	private String batchTime;
	private String dimenno;
	private String url;
	private String codeImg;
	private String batchState;//0待提交；1待审核；2审核通过；3审核不通过
	
	private String submitTime;
	private String auditTime;
	private Integer auditEntId;
	
	public int getPtbId() {
		return ptbId;
	}
	public void setPtbId(int ptbId) {
		this.ptbId = ptbId;
	}
	public Integer getPtqId() {
		return ptqId;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditEntId() {
		return auditEntId;
	}
	public void setAuditEntId(Integer auditEntId) {
		this.auditEntId = auditEntId;
	}
	public void setPtqId(Integer ptqId) {
		this.ptqId = ptqId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getBatchTime() {
		return batchTime;
	}
	public void setBatchTime(String batchTime) {
		this.batchTime = batchTime;
	}
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCodeImg() {
		return codeImg;
	}
	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}
	public String getBatchState() {
		return batchState;
	}
	public void setBatchState(String batchState) {
		this.batchState = batchState;
	}
	
	
	
}
