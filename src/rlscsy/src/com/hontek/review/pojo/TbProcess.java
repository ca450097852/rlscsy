package com.hontek.review.pojo;

import java.util.List;

/**
 * <p>Title: 加工包装表</p>
 * <p>Description: 加工包装PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbProcess {
	private int processId;
	private String processUser;		//加工负责人
	private String processAddr;		//加工地址
	private String processCompany;	//加工厂家
	private String processTime;		//加工时间
	private int proId;				//商品ID
	private List<TbTraceAppdix> traceAppdixs;
	

	
	public TbProcess() {
		super();
	}
	
	public TbProcess(int processId, String processUser, String processAddr,
			String processCompany, String processTime, int proId) {
		super();
		this.processId = processId;
		this.processUser = processUser;
		this.processAddr = processAddr;
		this.processCompany = processCompany;
		this.processTime = processTime;
		this.proId = proId;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public String getProcessUser() {
		return processUser;
	}
	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}
	public String getProcessAddr() {
		return processAddr;
	}
	public void setProcessAddr(String processAddr) {
		this.processAddr = processAddr;
	}
	public String getProcessCompany() {
		return processCompany;
	}
	public void setProcessCompany(String processCompany) {
		this.processCompany = processCompany;
	}
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
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
