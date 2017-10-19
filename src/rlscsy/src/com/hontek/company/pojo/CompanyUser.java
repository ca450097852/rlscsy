package com.hontek.company.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 企业用户表
 * @ClassName: CompanyUser 
 * @author qql
 */

public class CompanyUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int comUserId;
	private int comId;
	private String account;
	private String password;
	private String realName;
	private String phone;
	private String sex;//1男；2女
	private String state;//1使用；2禁用
	private String regTime;


	public CompanyUser() {
	}

	public CompanyUser(int comUserId, int comId, String account, String password,
			String realName, String phone, String sex,String state,String regTime) {
		super();
		this.comUserId = comUserId;
		this.comId = comId;
		this.account = account;
		this.password = password;
		this.realName = realName;
		this.phone = phone;
		this.sex = sex;
		this.state = state;
		this.regTime = regTime;
	}


	public int getComUserId() {
		return comUserId;
	}


	public void setComUserId(int comUserId) {
		this.comUserId = comUserId;
	}


	public int getComId() {
		return comId;
	}


	public void setComId(int comId) {
		this.comId = comId;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getRegTime() {
		return regTime;
	}


	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}


	
}