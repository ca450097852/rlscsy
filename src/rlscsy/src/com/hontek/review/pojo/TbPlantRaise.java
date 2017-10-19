/**
 * 
 */
package com.hontek.review.pojo;

import java.util.List;

/**
 * @ClassName: TbPlantRaise
 * @Description: TODO(施肥喂养表)
 * @date 2014-11-19 下午03:59:02
 * @author qql
 * @version 1.0
 */
public class TbPlantRaise {
	private int prId;			//种植喂养ID
	private int proId;			//产品ID
	private String feedName;	//饲料/肥料名称
	private String feedCompany;//饲料/肥料厂家
	private String feedWay;	//施肥/喂养方法
	private String feedCycle;	//施肥/喂养周期
	private String feedTime;	//施肥/喂养时间
	private String dosage;		//施肥/喂养量
	private List<TbTraceAppdix> traceAppdixs;
	
	public int getPrId() {
		return prId;
	}
	public void setPrId(int prId) {
		this.prId = prId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
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
	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}
	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}

	
}
