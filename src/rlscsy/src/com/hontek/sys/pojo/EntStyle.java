package com.hontek.sys.pojo;

import java.io.Serializable;
import java.util.List;

public class EntStyle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int esId;
	private int scId;			//风格ID
	private int entId;			//企业ID
	
	private String logoImage;	//头部LOGO图片
	private String bottomInfo;	//底部信息
	private String scType;		//1门户；2企业会员；3监管
	private String userId;		//操作用户
	private String createTime;	//创建时间
	private String remark;
	private String loginView;	//企业登录
	
	//关联风格
	private String scName;
	private String scCss;
	
	private List<Banner> banner;//banner幻灯片
	
	private List<Banner> advert;//中部广告位

	public EntStyle(){
		super();
	}

	public int getEsId() {
		return esId;
	}

	public void setEsId(int esId) {
		this.esId = esId;
	}

	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getBottomInfo() {
		return bottomInfo;
	}

	public void setBottomInfo(String bottomInfo) {
		this.bottomInfo = bottomInfo;
	}

	public String getScType() {
		return scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
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

	public String getLoginView() {
		return loginView;
	}

	public void setLoginView(String loginView) {
		this.loginView = loginView;
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

	public List<Banner> getBanner() {
		return banner;
	}

	public void setBanner(List<Banner> banner) {
		this.banner = banner;
	}

	public List<Banner> getAdvert() {
		return advert;
	}

	public void setAdvert(List<Banner> advert) {
		this.advert = advert;
	}
	
	

}
