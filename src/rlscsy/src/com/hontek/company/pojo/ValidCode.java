package com.hontek.company.pojo;
/**
 * 二维码验证表
 * @author IMZK
 * vcId remarks 并没有什么卵用
 */
public class ValidCode {
	private int vcId;
	private String dimenno;
	private String validCode;
	private String validDate;
	private String validState;
	private String validUserr;
	private String remarks;
	public int getVcId() {
		return vcId;
	}
	public void setVcId(int vcId) {
		this.vcId = vcId;
	}
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getValidState() {
		return validState;
	}
	public void setValidState(String validState) {
		this.validState = validState;
	}
	public String getValidUserr() {
		return validUserr;
	}
	public void setValidUserr(String validUserr) {
		this.validUserr = validUserr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
