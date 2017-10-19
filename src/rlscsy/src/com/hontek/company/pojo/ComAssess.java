package com.hontek.company.pojo;
/**
 * 企业考核表
 * @author chenan
 *
 */
public class ComAssess {
	private int caId;
	private int comId;
	private String name;
	private String addr;
	private String linkUser;
	private String phone;
	private String tanwei;
	private String tanwei2;
	private String suyuanchen;
	private String suyuanchen2;
	public String getTanwei2() {
		return tanwei2;
	}

	public void setTanwei2(String tanwei2) {
		this.tanwei2 = tanwei2;
	}

	public String getSuyuanchen2() {
		return suyuanchen2;
	}

	public void setSuyuanchen2(String suyuanchen2) {
		this.suyuanchen2 = suyuanchen2;
	}

	public String getLingsou2() {
		return lingsou2;
	}

	public void setLingsou2(String lingsou2) {
		this.lingsou2 = lingsou2;
	}

	private String lingsou;
	private String lingsou2;
	private String inUser;
	private String inDate;
	private String crrtime;
	private String state;
	
	public int getCaId() {
		return caId;
	}

	public ComAssess(int caId, int comId, String name, String addr,
			String linkUser, String phone, String tanwei, String suyuanchen,
			String lingsou, String inUser, String inDate, String crrtime,
			String state) {
		super();
		this.caId = caId;
		this.comId = comId;
		this.name = name;
		this.addr = addr;
		this.linkUser = linkUser;
		this.phone = phone;
		this.tanwei = tanwei;
		this.suyuanchen = suyuanchen;
		this.lingsou = lingsou;
		this.inUser = inUser;
		this.inDate = inDate;
		this.crrtime = crrtime;
		this.state = state;
	}

	public void setCaId(int caId) {
		this.caId = caId;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(String linkUser) {
		this.linkUser = linkUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTanwei() {
		return tanwei;
	}

	public void setTanwei(String tanwei) {
		this.tanwei = tanwei;
	}

	public String getSuyuanchen() {
		return suyuanchen;
	}

	public void setSuyuanchen(String suyuanchen) {
		this.suyuanchen = suyuanchen;
	}

	public String getLingsou() {
		return lingsou;
	}

	public void setLingsou(String lingsou) {
		this.lingsou = lingsou;
	}

	public String getInUser() {
		return inUser;
	}

	public void setInUser(String inUser) {
		this.inUser = inUser;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getCrrtime() {
		return crrtime;
	}

	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ComAssess() {
		super();
	}



	
}
