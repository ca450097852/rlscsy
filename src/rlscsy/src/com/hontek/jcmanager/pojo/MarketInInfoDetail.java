package com.hontek.jcmanager.pojo;

public class MarketInInfoDetail {
	private int miidId;
	private int miibId;
	
	//以下6个字段与基本表重复，搞不懂为什么要这样设计，所以基本表信息更新时，同时要更新明细表中的字段，麻烦
	private String tranId;
	private String quarantineAnimalProductsId;
	private String inspectionMeatId;
	private String provId;
	private String quarantineVegeId;
	private String batchId;
	
	
	
	private String goodsCode;
	private String goodsName;
	private String weight;
	private String areaOriginId;
	private String areaOriginName;
	private String baseName;
	private String wsSupplierId;
	private String wsSupplierName;
	private String saleTranId;
	public int getMiidId() {
		return miidId;
	}
	public void setMiidId(int miidId) {
		this.miidId = miidId;
	}
	public int getMiibId() {
		return miibId;
	}
	public void setMiibId(int miibId) {
		this.miibId = miibId;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getQuarantineAnimalProductsId() {
		return quarantineAnimalProductsId;
	}
	public void setQuarantineAnimalProductsId(String quarantineAnimalProductsId) {
		this.quarantineAnimalProductsId = quarantineAnimalProductsId;
	}
	public String getInspectionMeatId() {
		return inspectionMeatId;
	}
	public void setInspectionMeatId(String inspectionMeatId) {
		this.inspectionMeatId = inspectionMeatId;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getQuarantineVegeId() {
		return quarantineVegeId;
	}
	public void setQuarantineVegeId(String quarantineVegeId) {
		this.quarantineVegeId = quarantineVegeId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAreaOriginId() {
		return areaOriginId;
	}
	public void setAreaOriginId(String areaOriginId) {
		this.areaOriginId = areaOriginId;
	}
	public String getAreaOriginName() {
		return areaOriginName;
	}
	public void setAreaOriginName(String areaOriginName) {
		this.areaOriginName = areaOriginName;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getWsSupplierId() {
		return wsSupplierId;
	}
	public void setWsSupplierId(String wsSupplierId) {
		this.wsSupplierId = wsSupplierId;
	}
	public String getWsSupplierName() {
		return wsSupplierName;
	}
	public void setWsSupplierName(String wsSupplierName) {
		this.wsSupplierName = wsSupplierName;
	}
	public String getSaleTranId() {
		return saleTranId;
	}
	public void setSaleTranId(String saleTranId) {
		this.saleTranId = saleTranId;
	}
	
	
	
}
