package com.hontek.element.pojo;

/**
 * <p>Title: 投入品分类</p>
 * <p>Description: 投入品分类PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */

public class TbAgriType implements java.io.Serializable {

	// Fields

	private int typeId;
	private String typeNo;
	private String typeName;
	private int upcateId;
	private int seq;
	private int state;
	private String typeClass;//类别性质；1种植类； 2养殖类
	private String remark;
	
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

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


}