package com.hontek.element.pojo;

import java.util.List;

/**
 * <p>Title: 农业投入品表</p>
 * <p>Description: 农业投入品PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbAgriInput {
	private int agriId;
	private String agriName;	//
	private String buyDate;	//
	private String buyNum;	//
	private String agriImg;	//
	private String crttime;	//
	private int recId;			//产品ID	
	
	private int typeId;	//
	private String typeName;	//
	private String buyAddr;	//
	
	private String agriCompany;	//
	private String buyUser;	//
	private String buyUnit;	//
	private String agspc;
	private String agnum;
	

	public TbAgriInput() {
		super();
	}

	public TbAgriInput(int agriId, String agriName, String buyDate,
			String buyNum, String agriImg, String crttime, int recId) {
		super();
		this.agriId = agriId;
		this.agriName = agriName;
		this.buyDate = buyDate;
		this.buyNum = buyNum;
		this.agriImg = agriImg;
		this.crttime = crttime;
		this.recId = recId;
	}

	public int getAgriId() {
		return agriId;
	}

	public void setAgriId(int agriId) {
		this.agriId = agriId;
	}

	public String getAgriName() {
		return agriName;
	}

	public void setAgriName(String agriName) {
		this.agriName = agriName;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public String getAgriImg() {
		return agriImg;
	}

	public void setAgriImg(String agriImg) {
		this.agriImg = agriImg;
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

	public String getBuyAddr() {
		return buyAddr;
	}

	public void setBuyAddr(String buyAddr) {
		this.buyAddr = buyAddr;
	}

	public String getAgriCompany() {
		return agriCompany;
	}

	public void setAgriCompany(String agriCompany) {
		this.agriCompany = agriCompany;
	}

	public String getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(String buyUser) {
		this.buyUser = buyUser;
	}

	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	public String getAgspc() {
		return agspc;
	}

	public void setAgspc(String agspc) {
		this.agspc = agspc;
	}

	public String getAgnum() {
		return agnum;
	}

	public void setAgnum(String agnum) {
		this.agnum = agnum;
	}
	
	
}
