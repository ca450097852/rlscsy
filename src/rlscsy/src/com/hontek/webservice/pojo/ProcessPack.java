package com.hontek.webservice.pojo;
/**
 * 加工包装接口实体类
 * @author lzk
 *
 */
public class ProcessPack {
	private String processId;
	private String processUser;		//加工负责人
	private String processAddr;		//加工地址
	private String processCompany;	//加工厂家
	private String processTime;		//加工时间
	private String proCode;				//商品ID
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
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
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	
}
