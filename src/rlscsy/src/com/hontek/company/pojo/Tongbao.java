package com.hontek.company.pojo;

/**
 * 通报处罚企业表 entity. @author MyEclipse Persistence Tools
 */

public class Tongbao implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tid;
	private String entName;
	private String title;
	private String content;
	private String appdUrl;
	private String appdName;
	private String crttime;
	private String userName;
	private String sts;
	private String remark;
	private String sysCode;
	// Constructors

	/** default constructor */
	public Tongbao() {
	}

 

	public Tongbao(int tid, String entName, String title, String content,
			String appdUrl, String appdName, String crttime, String userName,
			String sts, String remark, String sysCode) {
		super();
		this.tid = tid;
		this.entName = entName;
		this.title = title;
		this.content = content;
		this.appdUrl = appdUrl;
		this.appdName = appdName;
		this.crttime = crttime;
		this.userName = userName;
		this.sts = sts;
		this.remark = remark;
		this.sysCode = sysCode;
	}



	public String getAppdUrl() {
		return appdUrl;
	}



	public void setAppdUrl(String appdUrl) {
		this.appdUrl = appdUrl;
	}



	public String getAppdName() {
		return appdName;
	}



	public void setAppdName(String appdName) {
		this.appdName = appdName;
	}



	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

}