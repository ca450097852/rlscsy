package com.hontek.sys.pojo;

/**
 * TsRoleUserId entity. @author MyEclipse Persistence Tools
 */

public class TsRoleUserId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleId;
	private String userId;

	// Constructors

	/** default constructor */
	public TsRoleUserId() {
	}

	/** full constructor */
	public TsRoleUserId(int roleId, String userId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	// Property accessors

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}