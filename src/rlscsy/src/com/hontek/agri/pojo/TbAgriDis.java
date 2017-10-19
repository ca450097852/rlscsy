package com.hontek.agri.pojo;
/**
 * 
 * @Description:禁用投入品目录表
 * @author qql
 */
public class TbAgriDis {
	
	private int agId;
	private String agName;
	private String agTime;
	private String agReason;
	private String agNum;
	private String crttime;

	public TbAgriDis(){
		super();
	}

	public int getAgId() {
		return agId;
	}

	public void setAgId(int agId) {
		this.agId = agId;
	}

	public String getAgName() {
		return agName;
	}

	public void setAgName(String agName) {
		this.agName = agName;
	}

	public String getAgTime() {
		return agTime;
	}

	public void setAgTime(String agTime) {
		this.agTime = agTime;
	}

	public String getAgReason() {
		return agReason;
	}

	public void setAgReason(String agReason) {
		this.agReason = agReason;
	}

	public String getAgNum() {
		return agNum;
	}

	public void setAgNum(String agNum) {
		this.agNum = agNum;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	
	
}
