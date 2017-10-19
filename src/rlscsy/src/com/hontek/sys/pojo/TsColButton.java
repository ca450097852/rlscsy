package com.hontek.sys.pojo;

/**
 * TsColButton entity. @author MyEclipse Persistence Tools
 */

public class TsColButton implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cbId;
	private int colId;
	private int buttonId;

	// Constructors

	/** default constructor */
	public TsColButton() {
	}

	/** full constructor */
	public TsColButton(int colId, int buttonId) {
		this.colId = colId;
		this.buttonId = buttonId;
	}

	// Property accessors

	public int getCbId() {
		return this.cbId;
	}

	public void setCbId(int cbId) {
		this.cbId = cbId;
	}

	public int getColId() {
		return this.colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public int getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

}