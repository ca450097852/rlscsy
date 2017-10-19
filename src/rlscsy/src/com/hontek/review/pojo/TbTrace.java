package com.hontek.review.pojo;
/**
 * 
 * <p>Title:溯源信息表   </p>
 * <p>Description: 溯源信息表 po</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbTrace {
	private int tid;
	private int proId;		//产品ID
	private String title;	//标题
	private String content;	//内容
	private String purl;	//手机扫描链接
	private String url;		//链接URL
	private String remark;	//说明
	private String crttime;	//录入时间
	private String sysCode;	//接入系统编号
	private String sysName;
	
	private String proCode;
	private int retroId;//原样返回给客户
	
	
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public int getRetroId() {
		return retroId;
	}
	public void setRetroId(int retroId) {
		this.retroId = retroId;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	
	
}
