package com.hontek.company.pojo;

/**
 * 企业二维码信息 entity. @author MyEclipse Persistence Tools
 */

public class EntQrcode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eqId;
	private int entId;
	private String dimenno;
	private String url;
	private String codeImg;
	private String crrtime;
	// Constructors

	/** default constructor */
	public EntQrcode() {
	}
	public EntQrcode(int eqId, int entId, String dimenno, String url,
			String codeImg, String crrtime) {
		super();
		this.eqId = eqId;
		this.entId = entId;
		this.dimenno = dimenno;
		this.url = url;
		codeImg = codeImg;
		this.crrtime = crrtime;
	}
	public int getEqId() {
		return eqId;
	}
	public void setEqId(int eqId) {
		this.eqId = eqId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCodeImg() {
		return codeImg;
	}
	public void setCodeImg(String codeImg) {
		codeImg = codeImg;
	}
	public String getCrrtime() {
		return crrtime;
	}
	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}
}