package com.hontek.company.pojo;

import java.io.Serializable;
/**
 * 监管信息表
 * @author dream
 *
 */
public class Supervise implements Serializable {

	private int supId;
	private int entId;
	private String contents;
	private String title;
	private int supEnt;
	private String supTime;
	private String supUser;
	private String supCode;
	private String state;

	private String companyContents;
	private String companyTime;
	
	public Supervise() {
		super();
	}


	public int getSupId() {
		return supId;
	}


	public void setSupId(int supId) {
		this.supId = supId;
	}


	public int getEntId() {
		return entId;
	}


	public void setEntId(int entId) {
		this.entId = entId;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getSupEnt() {
		return supEnt;
	}


	public void setSupEnt(int supEnt) {
		this.supEnt = supEnt;
	}


	public String getSupTime() {
		return supTime;
	}


	public void setSupTime(String supTime) {
		this.supTime = supTime;
	}


	public String getSupUser() {
		return supUser;
	}


	public void setSupUser(String supUser) {
		this.supUser = supUser;
	}


	public String getSupCode() {
		return supCode;
	}


	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}


	public String getCompanyContents() {
		return companyContents;
	}


	public void setCompanyContents(String companyContents) {
		this.companyContents = companyContents;
	}


	public String getCompanyTime() {
		return companyTime;
	}


	public void setCompanyTime(String companyTime) {
		this.companyTime = companyTime;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
}
