package com.hontek.company.pojo;
/**
 * 团体消费进货验收信息表
 * @author chenan
 *
 */
public class TeamBuyAcceptanceInfo {
	private int tbaiId;
	private int comId;
	private String teamConsumeId;
	private String teamConsumeName;
	private String inDate;
	private String supplierId;
	private String supplierName;
	private String tranId;
	private String goodsCode;
	private String goodsName;
	private String weight;
	private String price;
	private String wsSupplierId;
	private String wsSupplierName;
	
	

	public TeamBuyAcceptanceInfo(int tbaiId, int comId, String teamConsumeId,
			String teamConsumeName, String inDate, String supplierId,
			String supplierName, String tranId, String goodsCode,
			String goodsName, String weight, String price, String wsSupplierId,
			String wsSupplierName) {
		super();
		this.tbaiId = tbaiId;
		this.comId = comId;
		this.teamConsumeId = teamConsumeId;
		this.teamConsumeName = teamConsumeName;
		this.inDate = inDate;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.tranId = tranId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.weight = weight;
		this.price = price;
		this.wsSupplierId = wsSupplierId;
		this.wsSupplierName = wsSupplierName;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public TeamBuyAcceptanceInfo() {
		super();
	}
	
	public int getTbaiId() {
		return tbaiId;
	}
	public void setTbaiId(int tbaiId) {
		this.tbaiId = tbaiId;
	}
	public String getTeamConsumeId() {
		return teamConsumeId;
	}
	public void setTeamConsumeId(String teamConsumeId) {
		this.teamConsumeId = teamConsumeId;
	}
	public String getTeamConsumeName() {
		return teamConsumeName;
	}
	public void setTeamConsumeName(String teamConsumeName) {
		this.teamConsumeName = teamConsumeName;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	
	
}
