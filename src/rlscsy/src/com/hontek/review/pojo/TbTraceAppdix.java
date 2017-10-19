package com.hontek.review.pojo;

/**
 * @ClassName: TbTraceAppdix
 * @Description: TODO(附件表)
 * @date 2014-11-19 下午03:41:04
 * @author qql
 * @version 1.0
 */
public class TbTraceAppdix {
	private int appdixId;
	private int pid;
	private int proId;
	private String appdixName;
	private String appdixUrl;
	private String appdixType;/*1产地附件;2种子种苗附件;3种植喂养附件;4防疫附件;5加工包装附件;6仓储运输附件;7产品检测报告附件;*/
	private String uploadTime;
	public int getAppdixId() {
		return appdixId;
	}
	public void setAppdixId(int appdixId) {
		this.appdixId = appdixId;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getAppdixName() {
		return appdixName;
	}
	public void setAppdixName(String appdixName) {
		this.appdixName = appdixName;
	}
	public String getAppdixUrl() {
		return appdixUrl;
	}
	public void setAppdixUrl(String appdixUrl) {
		this.appdixUrl = appdixUrl;
	}
	public String getAppdixType() {
		return appdixType;
	}
	public void setAppdixType(String appdixType) {
		this.appdixType = appdixType;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	
	

}
