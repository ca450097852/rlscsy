package com.hontek.element.pojo;

import java.util.List;


/**
 * 检验信息实体类
 */

public class TbCheckinfo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private int checkId;
	private int recId;
	private String checkName;
	private String checkNum;
	private String checkUnit;
	private String checkTime;
	private String checkResult;
	private String checkType;//1生产档案检测报告；2企业自检报告；
	
	private String entId;
	private String entName;
	
	private List<TbElementApp> elementApp;
	
	public TbCheckinfo() {
		super();
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}

	public String getCheckUnit() {
		return checkUnit;
	}

	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public List<TbElementApp> getElementApp() {
		return elementApp;
	}

	public void setElementApp(List<TbElementApp> elementApp) {
		this.elementApp = elementApp;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}