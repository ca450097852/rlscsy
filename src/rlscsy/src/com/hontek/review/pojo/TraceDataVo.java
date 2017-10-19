package com.hontek.review.pojo;

public class TraceDataVo {

	private int proId;//产品id
	private String proName;//产品名称
	private String companyName;//企业名称
	private String batchNo;//耳标号、批次号
	private String dimenno;//批次二维码
	private String auditTime;//批次审核时间
	
	public TraceDataVo() {
		super();
	}
	public TraceDataVo(int proId, String proName, String companyName,
			String batchNo,String dimenno) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.companyName = companyName;
		this.batchNo = batchNo;
		this.dimenno = dimenno;
	}
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
}
