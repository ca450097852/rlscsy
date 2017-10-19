package com.hontek.element.pojo;

/**
 * 要素附件表实体类
 * @author dream
 *
 */

public class TbElementApp {
	private int appId;
	private int fkId;
	private String appName;
	private String appType;/*1产品检测报告附件;*/
	private String appUrl;
	private String uploadTime;
	private int seq;
	private String remark;
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getFkId() {
		return fkId;
	}
	public void setFkId(int fkId) {
		this.fkId = fkId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	

}
