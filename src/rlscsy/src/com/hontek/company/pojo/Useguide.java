package com.hontek.company.pojo;

import java.io.Serializable;
/**
 * 指引信息表
 * @author dream
 *
 */
public class Useguide implements Serializable {

	private int ugId;
	private String contents;
	private String title;
	private String ugNo;
	private String remark;
	private String crttime;
	public int getUgId() {
		return ugId;
	}
	public void setUgId(int ugId) {
		this.ugId = ugId;
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
	public String getUgNo() {
		return ugNo;
	}
	public void setUgNo(String ugNo) {
		this.ugNo = ugNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCrttime() {
		return crttime;
	}
	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	
}
