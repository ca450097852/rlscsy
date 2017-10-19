package com.hontek.sys.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TsRole entity. @author MyEclipse Persistence Tools
 */

public class TsRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleId;
	private int entId;
	private String roleName;
	private String desc;
	private int level;
	private String sts;
	private String account;
	// Constructors

	/** default constructor */
	public TsRole() {
	}


	/** full constructor */
	public TsRole(int entId, String roleName, String desc, int level, String sts) {
		this.entId = entId;
		this.roleName = roleName;
		this.desc = desc;
		this.level = level;
		this.sts = sts;
	}

	// Property accessors

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSts() {
		return this.sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}


	public int getEntId() {
		return entId;
	}


	public void setEntId(int entId) {
		this.entId = entId;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}

	
}