package com.hontek.webservice.pojo;

public class StoreTransport {
	private String stId;
	private String storageWay;		//仓储方式
	private String storageCondi;	//仓储条件
	private String transportWay;	//运输方式
	private String proCode;				//产品ID
	public String getStId() {
		return stId;
	}
	public void setStId(String stId) {
		this.stId = stId;
	}
	public String getStorageWay() {
		return storageWay;
	}
	public void setStorageWay(String storageWay) {
		this.storageWay = storageWay;
	}
	public String getStorageCondi() {
		return storageCondi;
	}
	public void setStorageCondi(String storageCondi) {
		this.storageCondi = storageCondi;
	}
	public String getTransportWay() {
		return transportWay;
	}
	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	
}
