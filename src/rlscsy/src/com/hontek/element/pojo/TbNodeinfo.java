package com.hontek.element.pojo;

import java.io.Serializable;
/**
 * 生产节点信息表
 * @ClassName: TbNodeinfo 
 * @author qql
 */
public class TbNodeinfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int niId;
	private int recId;
	private String niName;
	private String nodeImg;
	private String userId;
	private String crttime;
	private String remark;
	
	
	public TbNodeinfo(){
		super();
	}


	public int getNiId() {
		return niId;
	}


	public void setNiId(int niId) {
		this.niId = niId;
	}


	public int getRecId() {
		return recId;
	}


	public void setRecId(int recId) {
		this.recId = recId;
	}


	public String getNiName() {
		return niName;
	}


	public void setNiName(String niName) {
		this.niName = niName;
	}


	public String getNodeImg() {
		return nodeImg;
	}


	public void setNodeImg(String nodeImg) {
		this.nodeImg = nodeImg;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCrttime() {
		return crttime;
	}


	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	
}
