package com.hontek.sys.pojo;

/**
 * TsPurvButton entity. @author MyEclipse Persistence Tools
 */

public class TsPurvButton implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TsPurvButtonId id;
	private TsRolePurv tsRolePurv;
	private TsButton tsButton;

	// Constructors

	/** default constructor */
	public TsPurvButton() {
	}

	/** full constructor */
	public TsPurvButton(TsPurvButtonId id, TsRolePurv tsRolePurv, TsButton tsButton) {
		this.id = id;
		this.tsRolePurv = tsRolePurv;
		this.tsButton = tsButton;
	}

	// Property accessors

	public TsPurvButtonId getId() {
		return this.id;
	}

	public void setId(TsPurvButtonId id) {
		this.id = id;
	}

	public TsRolePurv getTsRolePurv() {
		return this.tsRolePurv;
	}

	public void setTsRolePurv(TsRolePurv tsRolePurv) {
		this.tsRolePurv = tsRolePurv;
	}

	public TsButton getTsButton() {
		return this.tsButton;
	}

	public void setTsButton(TsButton tsButton) {
		this.tsButton = tsButton;
	}

}