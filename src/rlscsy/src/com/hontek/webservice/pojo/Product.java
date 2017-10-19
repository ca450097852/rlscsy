package com.hontek.webservice.pojo;
/**
 * 接口    产品实体类
 * @author lzk
 *
 */
public class Product {
	private String proId;//产品ID,原系统ID
	private String typeNo;//产品类型编码
	private String proName;//产品名称
	private String barcode;//条码
	private String sizeAttr;//规格
	private String proDesc;//产品说明
	private String unit;//单位
	private String manufacturer;//生产商
	private String sourceTel;//生产商电话
	private String sourceAddr;//生产商地址
	private String distributor;//经销商
	private String distributorTel;//经销商电话
	private String distributorAddr;//经销商地址
	private String retain;//保鲜防腐
	private String storageConditions;//储藏条件
	private String shelfLife;//保质期
	private String authentication;//三品一标一名牌;1有机产品，2绿色 ，3无公害，4广东名牌
	private String entCode;//企业编号
	
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSourceTel() {
		return sourceTel;
	}
	public void setSourceTel(String sourceTel) {
		this.sourceTel = sourceTel;
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
	public String getDistributorTel() {
		return distributorTel;
	}
	public void setDistributorTel(String distributorTel) {
		this.distributorTel = distributorTel;
	}
	public String getDistributorAddr() {
		return distributorAddr;
	}
	public void setDistributorAddr(String distributorAddr) {
		this.distributorAddr = distributorAddr;
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
	public String getEntCode() {
		return entCode;
	}
	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
	
}
