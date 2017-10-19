package com.hontek.sys.pojo;
/**
 * 门户风格设置表
 * @author ZK
 *
 */
public class StyleConfig {
	private int scId;
	private String scName;
	private String scCss;
	private String scImg;//风格图片
	private String scType;//1门户；2企业会员；3监管
	private String state;//1启用 2 禁用
	private String userId;
	private String createTime;
	private String remark;
	public int getScId() {
		return scId;
	}
	public void setScId(int scId) {
		this.scId = scId;
	}
	public String getScName() {
		return scName;
	}
	public void setScName(String scName) {
		this.scName = scName;
	}
	public String getScCss() {
		return scCss;
	}
	public void setScCss(String scCss) {
		this.scCss = scCss;
	}
	public String getScImg() {
		return scImg;
	}
	public void setScImg(String scImg) {
		this.scImg = scImg;
	}
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
}
