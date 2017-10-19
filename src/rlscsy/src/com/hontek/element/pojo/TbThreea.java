package com.hontek.element.pojo;


/**
 * 三品一标信息实体类
 */

public class TbThreea implements java.io.Serializable {

	// Fields
	private int thId;
	private int recId;
	private String cuoshi;
	private String guicheng;
	private String jianyan;
	private String biaozhun;
	private String shengming;
	
	public TbThreea() {
		super();
	}

	public int getThId() {
		return thId;
	}

	public void setThId(int thId) {
		this.thId = thId;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCuoshi() {
		return cuoshi;
	}

	public void setCuoshi(String cuoshi) {
		this.cuoshi = cuoshi;
	}

	public String getGuicheng() {
		return guicheng;
	}

	public void setGuicheng(String guicheng) {
		this.guicheng = guicheng;
	}

	public String getJianyan() {
		return jianyan;
	}

	public void setJianyan(String jianyan) {
		this.jianyan = jianyan;
	}

	public String getBiaozhun() {
		return biaozhun;
	}

	public void setBiaozhun(String biaozhun) {
		this.biaozhun = biaozhun;
	}

	public String getShengming() {
		return shengming;
	}

	public void setShengming(String shengming) {
		this.shengming = shengming;
	}
	
}