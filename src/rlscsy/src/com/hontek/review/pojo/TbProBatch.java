package com.hontek.review.pojo;



/**
 * 产品批次实体类
 */

public class TbProBatch implements java.io.Serializable {

	// Fields
	private int batchId;
	private int proId;
	private String batchNo;
	private String batchSts;
	private String dimenno;
	private String proTime;
	private String url;
	private String codeImg;
	private String crrtime;
	private String auditUser;
	private String auditTime;
	
	private String proName;//产品名称
	private String companyName;//企业名称
	
	
	public TbProBatch() {
		super();
	}


	public TbProBatch(int batchId, int proId, String batchNo, String batchSts,
			String dimenno, String proTime, String url, String codeImg,
			String crrtime) {
		super();
		this.batchId = batchId;
		this.proId = proId;
		this.batchNo = batchNo;
		this.batchSts = batchSts;
		this.dimenno = dimenno;
		this.proTime = proTime;
		this.url = url;
		this.codeImg = codeImg;
		this.crrtime = crrtime;
	}


	public int getBatchId() {
		return batchId;
	}


	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}


	public int getProId() {
		return proId;
	}


	public void setProId(int proId) {
		this.proId = proId;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getBatchSts() {
		return batchSts;
	}


	public void setBatchSts(String batchSts) {
		this.batchSts = batchSts;
	}


	public String getDimenno() {
		return dimenno;
	}


	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}


	public String getProTime() {
		return proTime;
	}


	public void setProTime(String proTime) {
		this.proTime = proTime;
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


	public String getCrrtime() {
		return crrtime;
	}


	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}


	public String getAuditUser() {
		return auditUser;
	}


	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}


	public String getAuditTime() {
		return auditTime;
	}


	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}