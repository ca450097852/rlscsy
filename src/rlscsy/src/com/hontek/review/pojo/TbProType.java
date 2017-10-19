package com.hontek.review.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: 产品分类</p>
 * <p>Description: 产品分类PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */

public class TbProType implements java.io.Serializable {

	// Fields

	private int typeId;
	private String typeNo;
	private String typeName;
	private int upcateId;
	private int entId;
	private int state;
	private String remark;
	private String typeClass;//类别性质；1种植类； 2养殖类
	private int chct;
	
	private Set tbProducts = new HashSet(0);
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getUpcateId() {
		return upcateId;
	}
	public void setUpcateId(int upcateId) {
		this.upcateId = upcateId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Set getTbProducts() {
		return tbProducts;
	}
	public void setTbProducts(Set tbProducts) {
		this.tbProducts = tbProducts;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	public int getChct() {
		return chct;
	}
	public void setChct(int chct) {
		this.chct = chct;
	}


}