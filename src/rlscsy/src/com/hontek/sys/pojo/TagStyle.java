package com.hontek.sys.pojo;

import java.io.Serializable;

public class TagStyle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tsId;
	private int tsType; // 风格1单二维码图；2二维码在左边；3二维码在右边
	private int entId; // 主体ID

	private String toplogo; // 头部LOGO图片
	private String codelogo; // 二维码LOGO信息
	private String userId; // 操作用户
	private String createTime; // 创建时间
	private String remark;
	private String tagSize;//1小；2中；3大
	
	//
	private String mbDomain;//主体域名

	public int getTsId() {
		return tsId;
	}

	public void setTsId(int tsId) {
		this.tsId = tsId;
	}

	public int getTsType() {
		return tsType;
	}

	public void setTsType(int tsType) {
		this.tsType = tsType;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getToplogo() {
		return toplogo;
	}

	public void setToplogo(String toplogo) {
		this.toplogo = toplogo;
	}

	public String getCodelogo() {
		return codelogo;
	}

	public void setCodelogo(String codelogo) {
		this.codelogo = codelogo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMbDomain() {
		return mbDomain;
	}

	public void setMbDomain(String mbDomain) {
		this.mbDomain = mbDomain;
	}

	public String getTagSize() {
		return tagSize;
	}

	public void setTagSize(String tagSize) {
		this.tagSize = tagSize;
	}

	
}
