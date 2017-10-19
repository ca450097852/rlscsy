package com.hontek.review.pojo;

import java.util.List;

import com.hontek.company.pojo.ProductInfo;

/**
 * TbProduct entity. @author MyEclipse Persistence Tools
 */

public class TbProduct implements java.io.Serializable {

	// Fields

	private int proId;
	private int typeId;
	private String typeName;
	private String proCode;
	private String proName;
	private String barcode;
	private String sizeAttr;
	private String dimenno;
	private String proDesc;
	private String unit;
	private String state;
	private int entId;
	private String entName;
	private String manufacturer;//生产商
	private String sourceTel;//生产商电话
	private String sourceAddr;//生产商地址
	private String distributor;//经销商
	private String distributorTel;//经销商电话
	private String distributorAddr;//经销商地址
	private String updateTime;
	private String userId;
	private String remark;
	private String sysCode;
	
	private String typeNo;//产品类型编号
	private String entCode;//机构代码
	private String dimennoUrl;//二维码图片内容
	private String logoUrl;//二维码logo地址
	private String codeImg;//二维码图片名子
	
	private String retain;//保鲜防腐
	private String storageConditions;//储藏条件
	private String shelfLife;//保质期
	private String authentication;//三品一标一名牌;1有机产品，2绿色 ，3无公害，4广东名牌
	private String typeClass;
	
	private List<ProductInfo> productInfo;
	
	//统计查询使用
	private String startDate;
	private String endDate;
	private String chartType;//0柱形,1线形,2饼图
	private String traceState;
	
	private List<TbProductAppendix> appendixList;
	
	public String getDimennoUrl() {
		return dimennoUrl;
	}
	public void setDimennoUrl(String dimennoUrl) {
		this.dimennoUrl = dimennoUrl;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getEntCode() {
		return entCode;
	}
	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSizeAttr() {
		return sizeAttr;
	}
	public void setSizeAttr(String sizeAttr) {
		this.sizeAttr = sizeAttr;
	}
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSourceAddr() {
		return sourceAddr;
	}
	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getDistributorAddr() {
		return distributorAddr;
	}
	public void setDistributorAddr(String distributorAddr) {
		this.distributorAddr = distributorAddr;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getSourceTel() {
		return sourceTel;
	}
	public void setSourceTel(String sourceTel) {
		this.sourceTel = sourceTel;
	}
	public String getDistributorTel() {
		return distributorTel;
	}
	public void setDistributorTel(String distributorTel) {
		this.distributorTel = distributorTel;
	}
	public String getCodeImg() {
		return codeImg;
	}
	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}
	public List<TbProductAppendix> getAppendixList() {
		return appendixList;
	}
	public void setAppendixList(List<TbProductAppendix> appendixList) {
		this.appendixList = appendixList;
	}
	
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getRetain() {
		return retain;
	}
	public void setRetain(String retain) {
		this.retain = retain;
	}
	public String getStorageConditions() {
		return storageConditions;
	}
	public void setStorageConditions(String storageConditions) {
		this.storageConditions = storageConditions;
	}
	public String getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	public String getTraceState() {
		return traceState;
	}
	public void setTraceState(String traceState) {
		this.traceState = traceState;
	}
	public List<ProductInfo> getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(List<ProductInfo> productInfo) {
		this.productInfo = productInfo;
	}

	
}