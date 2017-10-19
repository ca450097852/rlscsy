package com.hontek.webservice.pojo;
/**
 * 同步施肥喂养实体类
 * @author lzk
 *
 */
public class PlantRaise {
	private String prId;			//种植喂养ID
	private String proCode;			//产品编码
	private String feedName;	//饲料/肥料名称
	private String feedCompany;//饲料/肥料厂家
	private String feedWay;	//施肥/喂养方法
	private String feedCycle;	//施肥/喂养周期
	private String feedTime;	//施肥/喂养时间
	private String dosage;		//施肥/喂养量
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	public String getFeedCompany() {
		return feedCompany;
	}
	public void setFeedCompany(String feedCompany) {
		this.feedCompany = feedCompany;
	}
	public String getFeedWay() {
		return feedWay;
	}
	public void setFeedWay(String feedWay) {
		this.feedWay = feedWay;
	}
	public String getFeedCycle() {
		return feedCycle;
	}
	public void setFeedCycle(String feedCycle) {
		this.feedCycle = feedCycle;
	}
	public String getFeedTime() {
		return feedTime;
	}
	public void setFeedTime(String feedTime) {
		this.feedTime = feedTime;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	
	
	
}
