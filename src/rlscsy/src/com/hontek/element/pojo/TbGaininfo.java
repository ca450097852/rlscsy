package com.hontek.element.pojo;


/**
 * 采摘收获信息实体类
 */

public class TbGaininfo implements java.io.Serializable {

	// Fields
	private int giId;
	private int recId;
	private String harvestDate;//收获日期
	private String basearea;//生产基地
	private String certificate;//是否认证
	private String ischeck;//是否检测
	private String checkway;//检测方式
	private String checkresult;//检测结果
	private String scale;//规模
	private String crttime;//录入时间
	private String proDate;//生产日期
	
	public TbGaininfo() {
		super();
	}

	public int getGiId() {
		return giId;
	}

	public void setGiId(int giId) {
		this.giId = giId;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getHarvestDate() {
		return harvestDate;
	}

	public void setHarvestDate(String harvestDate) {
		this.harvestDate = harvestDate;
	}

	public String getBasearea() {
		return basearea;
	}

	public void setBasearea(String basearea) {
		this.basearea = basearea;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getCheckway() {
		return checkway;
	}

	public void setCheckway(String checkway) {
		this.checkway = checkway;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public String getProDate() {
		return proDate;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	
}