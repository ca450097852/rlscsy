package com.hontek.sys.pojo;

import java.math.BigDecimal;

/**
 * TsLog entity. @author MyEclipse Persistence Tools
 */

public class TsLogVo implements java.io.Serializable {

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
	private String colName;
	private String userName;
	private String account;
	
	
	// Constructors

	/** default constructor */
	public TsLogVo() {
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


	public String getColName() {
		return colName;
	}


	public void setColName(String colName) {
		this.colName = colName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}
	
}