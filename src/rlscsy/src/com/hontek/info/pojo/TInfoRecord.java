package com.hontek.info.pojo;

import java.io.Serializable;
/**
 * 临时资讯上报表
 * @author Administrator
 *
 */
public class TInfoRecord implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Long tid;		    //主键ID 资讯ID
	private String title;		//标题
	private Long typeId;		//资讯分类id
	private String content;		//内容
	private String url;			//链接地址
	private String remark;		//说明
	private String crttime;		//上报时间
	private String sysCode;		//接入系统编号
	
	private String typename;	//咨询类别名称
	
	
	public TInfoRecord() {
		
	}

	public TInfoRecord(Long tid, String title, Long typeId, String content,
			String url, String remark, String crttime, String sysCode,
			String typename) {
		super();
		this.tid = tid;
		this.title = title;
		this.typeId = typeId;
		this.content = content;
		this.url = url;
		this.remark = remark;
		this.crttime = crttime;
		this.sysCode = sysCode;
		this.typename = typename;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
}
