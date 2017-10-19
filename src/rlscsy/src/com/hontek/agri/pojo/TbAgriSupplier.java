package com.hontek.agri.pojo;
/**
 * @Description:投入品供应商目录表
 * @author qql
 */
public class TbAgriSupplier {
	
	private int asId;
	private String asName;//供应商名称
	private String asAddr;//供应商地址
	private String asPhone;//供应商电话
	private String asGoods;//主营商品
	private String asMan;//联系人
	private String crttime;//
	
	public TbAgriSupplier(){
		super();
	}
	
	public int getAsId() {
		return asId;
	}
	public void setAsId(int asId) {
		this.asId = asId;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public String getAsAddr() {
		return asAddr;
	}
	public void setAsAddr(String asAddr) {
		this.asAddr = asAddr;
	}
	public String getAsPhone() {
		return asPhone;
	}
	public void setAsPhone(String asPhone) {
		this.asPhone = asPhone;
	}
	public String getAsGoods() {
		return asGoods;
	}
	public void setAsGoods(String asGoods) {
		this.asGoods = asGoods;
	}
	public String getAsMan() {
		return asMan;
	}
	public void setAsMan(String asMan) {
		this.asMan = asMan;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}
	
	
	

}
