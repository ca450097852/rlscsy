package com.hontek.record.pojo;

import java.util.List;

/**
 * 分类产地信息
 * @author
 *
 */
public class TbProTypeArea {
	private int ptaId;
	private int ptqId;
	private int entId;
	//区域
	private String chandi;
	private String province;
	private String city;
	private String town;
	
	private String areaName;
	private String areaAddr;
	private String scale;
	private String areaAcreage;
	private String areaValue;
	private String startTime;
	private String getTime;
	private String lat;
	private String lng;
	
	private String areaImg;
	private String areaType;//1种植基地；2养殖基地
	
	private List<TbMassif> chlist;//基地地块
	
	public TbProTypeArea() {
		super();
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

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAddr() {
		return areaAddr;
	}

	public void setAreaAddr(String areaAddr) {
		this.areaAddr = areaAddr;
	}

	public String getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
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

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getAreaAcreage() {
		return areaAcreage;
	}

	public void setAreaAcreage(String areaAcreage) {
		this.areaAcreage = areaAcreage;
	}

	public List<TbMassif> getChlist() {
		return chlist;
	}

	public void setChlist(List<TbMassif> chlist) {
		this.chlist = chlist;
	}

	public String getAreaImg() {
		return areaImg;
	}

	public void setAreaImg(String areaImg) {
		this.areaImg = areaImg;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
}
