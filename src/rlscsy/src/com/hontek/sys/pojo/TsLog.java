package com.hontek.sys.pojo;

import java.math.BigDecimal;

/**
 * TsLog entity. @author MyEclipse Persistence Tools
 */

public class TsLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int logId;
	private int colId;
	private String funcName;
	private String actType;
	private String userId;
	private int entId;
	private String logTime;
	private String computerIp;

	// Constructors

	/** default constructor */
	public TsLog() {
	}
	
	
	public TsLog( String funcName, String actType, String userId, int entId, String logTime, String computerIp) {
		this.funcName = funcName;
		this.actType = actType;
		this.userId = userId;
		this.entId = entId;
		this.logTime = logTime;
		this.computerIp = computerIp;
	}


	/** full constructor */
	public TsLog(int colId, String funcName, String actType, String userId, int entId, String logTime, String computerIp) {
		this.colId = colId;
		this.funcName = funcName;
		this.actType = actType;
		this.userId = userId;
		this.entId = entId;
		this.logTime = logTime;
		this.computerIp = computerIp;
	}

	// Property accessors

	public int getLogId() {
		return this.logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getColId() {
		return this.colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getActType() {
		return this.actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEntId() {
		return this.entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getComputerIp() {
		return this.computerIp;
	}

	public void setComputerIp(String computerIp) {
		this.computerIp = computerIp;
	}

	@Override
	public String toString() {
		return "TsLog [actType=" + actType + ", colId=" + colId
				+ ", computerIp=" + computerIp + ", entId=" + entId
				+ ", funcName=" + funcName + ", logId=" + logId + ", logTime="
				+ logTime + ", userId=" + userId + "]";
	}

	
}