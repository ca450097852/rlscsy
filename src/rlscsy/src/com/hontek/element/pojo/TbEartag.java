package com.hontek.element.pojo;


/**
 * 耳标信息实体类
 */

public class TbEartag implements java.io.Serializable {

	// Fields
	private int earId;
	private int recId;
	private String earNo;
	private String wearTime;

	
	public TbEartag() {
		super();
	}


	public int getEarId() {
		return earId;
	}


	public void setEarId(int earId) {
		this.earId = earId;
	}


	public int getRecId() {
		return recId;
	}


	public void setRecId(int recId) {
		this.recId = recId;
	}


	public String getEarNo() {
		return earNo;
	}


	public void setEarNo(String earNo) {
		this.earNo = earNo;
	}


	public String getWearTime() {
		return wearTime;
	}


	public void setWearTime(String wearTime) {
		this.wearTime = wearTime;
	}
	
	
}