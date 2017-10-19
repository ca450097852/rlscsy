/**
 * 
 */
package com.hontek.review.pojo;

import java.util.List;

/**
 * @ClassName: TbPrevention
 * @Description: TODO(防疫表、植保表)
 * @date 2014-11-19 下午03:47:50
 * @author qql
 * @version 1.0
 */
public class TbPrevention {
	private int ptId;			//防疫ID
	private int proId;			//产品ID
	private String drugName;	//药品名称
	private String drugCompany;	//药品厂家
	private String drugWay;		//用药方法
	private String drugTime;	//用药时间
	private String drugObject;	//防治对象
	private String drugCycle;	//用药周期
	private String dosage;		//用药剂量
	private List<TbTraceAppdix> traceAppdixs;
	
	public int getPtId() {
		return ptId;
	}
	public void setPtId(int ptId) {
		this.ptId = ptId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
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
	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}
	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}

	
}
