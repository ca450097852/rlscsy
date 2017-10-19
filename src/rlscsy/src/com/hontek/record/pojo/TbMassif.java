package com.hontek.record.pojo;
/**
 * 地块信息
 * @author
 *
 */
public class TbMassif {
	
	private int maId;
	private int ptaId;
	private int ptqId;
	
	private String maName;
	private String maAcreage;
	private String startTime;
	private String getTime;
	private String state;
	private String maImg;
	
	private String typeName;
	
	private String areaName;//基地名称
	
	public TbMassif() {
		super();
	}

	public int getMaId() {
		return maId;
	}

	public void setMaId(int maId) {
		this.maId = maId;
	}

	public int getPtaId() {
		return ptaId;
	}

	public void setPtaId(int ptaId) {
		this.ptaId = ptaId;
	}

	public int getPtqId() {
		return ptqId;
	}

	public void setPtqId(int ptqId) {
		this.ptqId = ptqId;
	}

	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public String getMaAcreage() {
		return maAcreage;
	}

	public void setMaAcreage(String maAcreage) {
		this.maAcreage = maAcreage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMaImg() {
		return maImg;
	}

	public void setMaImg(String maImg) {
		this.maImg = maImg;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	
}
