package com.hontek.sys.pojo;

/**
 * TsPurvButtonId entity. @author MyEclipse Persistence Tools
 */

public class TsPurvButtonId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int purvId;
	private int buttonId;

	// Constructors

	/** default constructor */
	public TsPurvButtonId() {
	}

	/** full constructor */
	public TsPurvButtonId(int purvId, int buttonId) {
		this.purvId = purvId;
		this.buttonId = buttonId;
	}

	// Property accessors

	public int getPurvId() {
		return this.purvId;
	}

	public void setPurvId(int purvId) {
		this.purvId = purvId;
	}

	public int getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

}