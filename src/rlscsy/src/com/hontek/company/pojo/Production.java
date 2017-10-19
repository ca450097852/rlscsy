package com.hontek.company.pojo;

import java.util.List;

/**
 * 生产信息表 entity. @author MyEclipse Persistence Tools
 */

public class Production implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int proId;
	private int entId;
	private String productinfo;
	private String license;
	private String crttime;
	private String userId;
	private String entName;
	private List<ProductionAppendix> appList;

	// Constructors

	/** default constructor */
	public Production() {
	}

	public Production(int proId, int entId, String productinfo, String license,
			String crttime, String userId) {
		super();
		this.proId = proId;
		this.entId = entId;
		this.productinfo = productinfo;
		this.license = license;
		this.crttime = crttime;
		this.userId = userId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public List<ProductionAppendix> getAppList() {
		return appList;
	}

	public void setAppList(List<ProductionAppendix> appList) {
		this.appList = appList;
	}
	
}