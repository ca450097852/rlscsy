package com.hontek.element.pojo;


/**
 * 销售信息实体类
 */

public class TbSaleinfo implements java.io.Serializable {

	// Fields
	private int saleId;
	private int recId;
	private String listed;
	private String salearea;
	private String packing;
	private String havetag;
	private String crttime;
	
	private String sgCompany;
	private String sgUser;
	private String saleUser;
	private String proDate;//生产日期
	private String tranId;

	public TbSaleinfo() {
		super();
	}



	public int getSaleId() {
		return saleId;
	}



	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}



	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getListed() {
		return listed;
	}

	public void setListed(String listed) {
		this.listed = listed;
	}

	public String getSalearea() {
		return salearea;
	}

	public void setSalearea(String salearea) {
		this.salearea = salearea;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getHavetag() {
		return havetag;
	}

	public void setHavetag(String havetag) {
		this.havetag = havetag;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}



	public String getSgCompany() {
		return sgCompany;
	}



	public void setSgCompany(String sgCompany) {
		this.sgCompany = sgCompany;
	}



	public String getSgUser() {
		return sgUser;
	}



	public void setSgUser(String sgUser) {
		this.sgUser = sgUser;
	}



	public String getSaleUser() {
		return saleUser;
	}



	public void setSaleUser(String saleUser) {
		this.saleUser = saleUser;
	}



	public String getProDate() {
		return proDate;
	}



	public void setProDate(String proDate) {
		this.proDate = proDate;
	}



	public String getTranId() {
		return tranId;
	}



	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	
	
}