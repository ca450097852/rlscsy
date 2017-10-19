package com.hontek.webservice.pojo;

import java.util.List;

public class Zizhi {
	private int appType;		//类型     1、龙头企业类，2、认证类，3、示范合作社，4、示范区、场，5、家庭农场，6、其它
	private String zname;		//名称
//	private String grantUnit;	//授予单位
	private String awardUnit;	//颁发单位
	private String awardTime;	//颁发时间
	private String remark;		//备注
	private String entCode;		//企业编码
	private List<Annex> annexList;//附件集合
	private String levelIds;	//级别
	
	public int getAppType() {
		return appType;
	}
	public void setAppType(int appType) {
		this.appType = appType;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
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
	public String getEntCode() {
		return entCode;
	}
	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
	public List<Annex> getAnnexList() {
		return annexList;
	}
	public void setAnnexList(List<Annex> annexList) {
		this.annexList = annexList;
	}
	public String getLevelIds() {
		return levelIds;
	}
	public void setLevelIds(String levelIds) {
		this.levelIds = levelIds;
	}
	
	
}
