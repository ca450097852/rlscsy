package com.hontek.agri.pojo;
/**
 * @Description:预警信息记录表
 * @author qql
 */
public class TbWarning {
	private int wid;
	private String wtype;//预警分类:1禁用农业投入品预警；2
	private String contents;
	private String warningTime;
	private String state;//1未读，2已阅
	private String crttime;
	
	private int entId;
	private String entName;
	
	public TbWarning(){
		super();
	}


	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}


	public String getWtype() {
		return wtype;
	}


	public void setWtype(String wtype) {
		this.wtype = wtype;
	}


	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}


	public int getEntId() {
		return entId;
	}


	public void setEntId(int entId) {
		this.entId = entId;
	}


	public String getEntName() {
		return entName;
	}


	public void setEntName(String entName) {
		this.entName = entName;
	}

	
}
