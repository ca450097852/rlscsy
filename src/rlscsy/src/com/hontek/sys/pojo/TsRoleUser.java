package com.hontek.sys.pojo;

/**
 * TsRoleUser entity. @author MyEclipse Persistence Tools
 */

public class TsRoleUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ruId;
	private String userId;
	private int roleId;
	// Constructors

	/** default constructor */
	public TsRoleUser() {
	}


	// Property accessors



	public TsRoleUser(String userId, int roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


	public TsRoleUser(int ruId, String userId, int roleId) {
		super();
		this.ruId = ruId;
		this.userId = userId;
		this.roleId = roleId;
	}


	public int getRuId() {
		return ruId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public void setRuId(int ruId) {
		this.ruId = ruId;
	}

}