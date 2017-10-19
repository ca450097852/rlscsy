package com.hontek.review.pojo;

import java.util.List;

/**
 * <p>Title: 仓储运输表</p>
 * <p>Description:仓储运输PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbStoreTransport {
	private int stId;
	private String storageWay;		//仓储方式
	private String storageCondi;	//仓储条件
	private String transportWay;	//运输方式
	private int proId;				//产品ID
	private List<TbTraceAppdix> traceAppdixs;

	public TbStoreTransport() {
		super();
	}
	public TbStoreTransport(int stId, String storageWay, String storageCondi,
			String transportWay, int proId) {
		super();
		this.stId = stId;
		this.storageWay = storageWay;
		this.storageCondi = storageCondi;
		this.transportWay = transportWay;
		this.proId = proId;
	}
	public int getStId() {
		return stId;
	}
	public void setStId(int stId) {
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
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}
	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}
	
}
