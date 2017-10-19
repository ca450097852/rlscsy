package com.hontek.review.pojo;

import java.util.List;



/**
 * 种源、种子、种苗实体类
 */

public class TbProSeed implements java.io.Serializable {

	// Fields
	private int seedId;
	private int proId;
	private String seedName;
	private String seedAddr;
	private String seedCompany;
	private String feature;
	private List<TbTraceAppdix> traceAppdixs;

	public TbProSeed() {
		super();
	}

	
	public TbProSeed(int seedId, int proId, String seedName, String seedAddr,
			String seedCompany, String feature) {
		super();
		this.seedId = seedId;
		this.proId = proId;
		this.seedName = seedName;
		this.seedAddr = seedAddr;
		this.seedCompany = seedCompany;
		this.feature = feature;
	}


	public int getSeedId() {
		return seedId;
	}



	public void setSeedId(int seedId) {
		this.seedId = seedId;
	}



	public int getProId() {
		return proId;
	}



	public void setProId(int proId) {
		this.proId = proId;
	}



	public String getSeedName() {
		return seedName;
	}



	public void setSeedName(String seedName) {
		this.seedName = seedName;
	}



	public String getSeedAddr() {
		return seedAddr;
	}



	public void setSeedAddr(String seedAddr) {
		this.seedAddr = seedAddr;
	}



	public String getSeedCompany() {
		return seedCompany;
	}



	public void setSeedCompany(String seedCompany) {
		this.seedCompany = seedCompany;
	}



	public String getFeature() {
		return feature;
	}



	public void setFeature(String feature) {
		this.feature = feature;
	}


	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}


	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}
	
}