package com.hontek.company.pojo;

import java.util.List;

/**
 * Zizhi entity. @author MyEclipse Persistence Tools
 */

public class Zizhi implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int entId;
	private int appType;
	private String zizhiName;
	private String grantUnit;
	private String awardUnit;
	private String awardTime;
	private String remark;
	private String entCode;
	private List<ZizhiAppendix> appendix;
	private String zid;//用户同步上来资质的标识，用以返回给用户，说明此信息已同步
	private List<ZizhiType> zizhiTypeList;

	private String state;
	// Constructors

	/** default constructor */
	public Zizhi() {
	}

	public Zizhi(int id, int entId, int appType, String zizhiName,
			String grantUnit, String awardUnit, String awardTime, String remark) {
		super();
		this.id = id;
		this.entId = entId;
		this.appType = appType;
		this.zizhiName = zizhiName;
		this.grantUnit = grantUnit;
		this.awardUnit = awardUnit;
		this.awardTime = awardTime;
		this.remark = remark;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public String getZizhiName() {
		return zizhiName;
	}

	public void setZizhiName(String zizhiName) {
		this.zizhiName = zizhiName;
	}

	public String getGrantUnit() {
		return grantUnit;
	}

	public void setGrantUnit(String grantUnit) {
		this.grantUnit = grantUnit;
	}

	public String getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ZizhiAppendix> getAppendix() {
		return appendix;
	}

	public void setAppendix(List<ZizhiAppendix> appendix) {
		this.appendix = appendix;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public List<ZizhiType> getZizhiTypeList() {
		return zizhiTypeList;
	}

	public void setZizhiTypeList(List<ZizhiType> zizhiTypeList) {
		this.zizhiTypeList = zizhiTypeList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	// Property accessors


	
}