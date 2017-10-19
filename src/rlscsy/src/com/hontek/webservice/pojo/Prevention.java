package com.hontek.webservice.pojo;
/**
 * 防疫接口实体类
 * @author lzk
 *
 */
public class Prevention {
	private String ptId;		//防疫ID
	private String proCode;		//产品编码
	private String drugName;	//药品名称
	private String drugCompany;	//药品厂家
	private String drugWay;		//用药方法
	private String drugTime;	//用药时间
	private String drugObject;	//防治对象
	private String drugCycle;	//用药周期
	private String dosage;		//用药剂量
	public String getPtId() {
		return ptId;
	}
	public void setPtId(String ptId) {
		this.ptId = ptId;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugCompany() {
		return drugCompany;
	}
	public void setDrugCompany(String drugCompany) {
		this.drugCompany = drugCompany;
	}
	public String getDrugWay() {
		return drugWay;
	}
	public void setDrugWay(String drugWay) {
		this.drugWay = drugWay;
	}
	public String getDrugTime() {
		return drugTime;
	}
	public void setDrugTime(String drugTime) {
		this.drugTime = drugTime;
	}
	public String getDrugObject() {
		return drugObject;
	}
	public void setDrugObject(String drugObject) {
		this.drugObject = drugObject;
	}
	public String getDrugCycle() {
		return drugCycle;
	}
	public void setDrugCycle(String drugCycle) {
		this.drugCycle = drugCycle;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	
}
