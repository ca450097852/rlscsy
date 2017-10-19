package com.hontek.sys.pojo;

/**
 * TsEntType entity. @author MyEclipse Persistence Tools
 */

public class TsEntType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeId;
	private String typeName;
	private String remarks;

	// Constructors

	/** default constructor */
	public TsEntType() {
	}

	/** full constructor */
	public TsEntType(String typeName, String remarks) {
		this.typeName = typeName;
		this.remarks = remarks;
	}

	public TsEntType(int typeId,String typeName, String remarks) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.remarks = remarks;
	}
	
	// Property accessors

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}