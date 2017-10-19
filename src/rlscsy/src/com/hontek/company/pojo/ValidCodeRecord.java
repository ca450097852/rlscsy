package com.hontek.company.pojo;
/**
 * 二维码验证记录表
 */
public class ValidCodeRecord {
	private int vrId;
	private String dimenno;
	private String createCount;
	private String creatTime;

	private String remarks;

	public int getVrId() {
		return vrId;
	}

	public void setVrId(int vrId) {
		this.vrId = vrId;
	}

	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public String getCreateCount() {
		return createCount;
	}

	public void setCreateCount(String createCount) {
		this.createCount = createCount;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
