package com.hontek.element.pojo;

import java.util.List;

/**
 * <p>Title: 农业投入品表兽药</p>
 * <p>Description: 农业投入品PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class DrugUse {
	private int drugId;
	private String drugName;	//药物名称
	private String useDate;	//使用日期
	private String useObject;	//防治对象
	private String useDosage;	//使用剂量
	private String useTotal;	//施用量
	private String safeDay;	//安全隔离天数
	private String safeDate;	//安全隔离期
	private String useMan;	//施用人
	private String remark;	//	备注
	private String crttime;	//录入时间
	private int recId;			//档案ID	
	private String useWay;	//施用方法

	
	public DrugUse() {
		super();
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}



	public String getDrugName() {
		return drugName;
	}



	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}



	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getUseObject() {
		return useObject;
	}

	public void setUseObject(String useObject) {
		this.useObject = useObject;
	}

	public String getUseDosage() {
		return useDosage;
	}

	public void setUseDosage(String useDosage) {
		this.useDosage = useDosage;
	}



	public String getUseTotal() {
		return useTotal;
	}

	public void setUseTotal(String useTotal) {
		this.useTotal = useTotal;
	}

	public String getSafeDate() {
		return safeDate;
	}

	public void setSafeDate(String safeDate) {
		this.safeDate = safeDate;
	}

	public String getUseMan() {
		return useMan;
	}

	public void setUseMan(String useMan) {
		this.useMan = useMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSafeDay() {
		return safeDay;
	}

	public void setSafeDay(String safeDay) {
		this.safeDay = safeDay;
	}

	public String getUseWay() {
		return useWay;
	}

	public void setUseWay(String useWay) {
		this.useWay = useWay;
	}
	
	
	
}
