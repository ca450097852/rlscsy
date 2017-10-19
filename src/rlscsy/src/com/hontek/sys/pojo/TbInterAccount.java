package com.hontek.sys.pojo;

/**
 * 接入系统账号表 entity. @author MyEclipse Persistence Tools
 */

public class TbInterAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysCode;
	private int entId;//上层机构ID
	private int areaId;//区域ID
	private String sysDesc;
	private String sysName;
	private String account;
	private String pass;
	private String ip;
	private int sts;//0使用，1停用

	private String crttime;
	private String userId;
	private long time;
	// Constructors

	/** default constructor */
	public TbInterAccount() {
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}


	public TbInterAccount(String sysCode, int entId, int areaId,
			String sysDesc, String sysName, String account, String pass,
			String ip, int sts, String crttime, String userId, long time) {
		super();
		this.sysCode = sysCode;
		this.entId = entId;
		this.areaId = areaId;
		this.sysDesc = sysDesc;
		this.sysName = sysName;
		this.account = account;
		this.pass = pass;
		this.ip = ip;
		this.sts = sts;
		this.crttime = crttime;
		this.userId = userId;
		this.time = time;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getSts() {
		return sts;
	}

	public void setSts(int sts) {
		this.sts = sts;
	}

	public String getSysDesc() {
		return sysDesc;
	}

	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


	
}