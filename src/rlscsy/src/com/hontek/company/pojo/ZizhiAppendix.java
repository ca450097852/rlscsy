package com.hontek.company.pojo;

/**
 * ZizhiAppendix entity. @author MyEclipse Persistence Tools
 */

public class ZizhiAppendix implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int appId;
	private int zid;
	private String appName;
	private String path;
	private String uploadtime;
	private int orderby;
	private String remark;
	private String zName;

	// Constructors

	/** default constructor */
	public ZizhiAppendix() {
	}

	public ZizhiAppendix(int appId, int zid, String appName, String path,
			String uploadtime, int orderby, String remark) {
		super();
		this.appId = appId;
		this.zid = zid;
		this.appName = appName;
		this.path = path;
		this.uploadtime = uploadtime;
		this.orderby = orderby;
		this.remark = remark;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getZid() {
		return zid;
	}

	public void setZid(int zid) {
		this.zid = zid;
	}

	public String getAppName() {
		return appName;
	}

	public String getzName() {
		return zName;
	}

	public void setzName(String zName) {
		this.zName = zName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public int getOrderby() {
		return orderby;
	}

	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}