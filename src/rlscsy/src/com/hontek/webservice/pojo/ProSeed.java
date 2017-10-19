package com.hontek.webservice.pojo;
/**
 * 同步种苗实体类
 * @author lzk
 *
 */
public class ProSeed {
	private String seedId;		//原系统的ID
	private String proCode;		//企业编码
	private String seedName;	//种苗名称
	private String seedAddr;	//种苗地址
	private String seedCompany;	//种苗厂家
	private String feature;		//特征特性
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
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
	
	
}
