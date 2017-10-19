package com.hontek.jcmanager.pojo;
/**
 * 批发、零售市场、超市肉类蔬菜进场基本信息表
 * @author IMZK
 *
 */
public class MarketInInfoBase {
	private int miibId;
	private int comId;
	private int ptbId;
	private String flag;	//1代表屠宰企业、2代表批发企业、3代表零售市场、4代表超市、5代表团体消费单位、6代表其他
	private String marketId;
	private String marketName;
	private String inDate;
	private String wholesalerId;
	private String wholesalerName;
	private String tranId;
	private String quarantineAnimalProductsId;
	private String inspectionMeatId;
	private String provId;
	private String quarantineVegeId;
	private String batchId;
	private String transporterId;
	private String createTime;
	private String remark;
	public int getMiibId() {
		return miibId;
	}
	public void setMiibId(int miibId) {
		this.miibId = miibId;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public int getPtbId() {
		return ptbId;
	}
	public void setPtbId(int ptbId) {
		this.ptbId = ptbId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getWholesalerId() {
		return wholesalerId;
	}
	public void setWholesalerId(String wholesalerId) {
		this.wholesalerId = wholesalerId;
	}
	public String getWholesalerName() {
		return wholesalerName;
	}
	public void setWholesalerName(String wholesalerName) {
		this.wholesalerName = wholesalerName;
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
	public String getTransporterId() {
		return transporterId;
	}
	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
