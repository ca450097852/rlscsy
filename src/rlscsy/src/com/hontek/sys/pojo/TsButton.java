package com.hontek.sys.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TsButton entity. @author MyEclipse Persistence Tools
 */

public class TsButton implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int buttonId;
	private String buttonName;
	private String remarks;
	private Set tsPurvButtons = new HashSet(0);

	// Constructors

	/** default constructor */
	public TsButton() {
	}

	/** full constructor */
	public TsButton(String buttonName, String remarks, Set tsPurvButtons) {
		this.buttonName = buttonName;
		this.remarks = remarks;
		this.tsPurvButtons = tsPurvButtons;
	}

	// Property accessors

	public int getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

	public String getButtonName() {
		return this.buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set getTsPurvButtons() {
		return this.tsPurvButtons;
	}

	public void setTsPurvButtons(Set tsPurvButtons) {
		this.tsPurvButtons = tsPurvButtons;
	}

}