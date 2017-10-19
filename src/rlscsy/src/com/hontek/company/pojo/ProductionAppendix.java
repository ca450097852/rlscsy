package com.hontek.company.pojo;

/**
 * ZizhiAppendix entity. @author MyEclipse Persistence Tools
 */

public class ProductionAppendix implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int appId;
	private int proId;
	private String appType;
	private String appName;
	private String path;
	private String uploadtime;
	private int orderby;
	private String remark;

	// Constructors

	/** default constructor */
	public ProductionAppendix() {
	}

	public ProductionAppendix(int appId, int proId, String appType,
			String appName, String path, String uploadtime, int orderby,
			String remark) {
		super();
		this.appId = appId;
		this.proId = proId;
		this.appType = appType;
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

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppName() {
		return appName;
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