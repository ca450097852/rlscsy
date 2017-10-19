package com.hontek.review.pojo;
/**
 * <p>Title: 产品二维码信息</p>
 * <p>Description: 产品二维码信息po类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbProQrcode {
	private long retroId;
	private long proId;
	private String dimenno;
	private String url;
	private String codeImg;
	private String crrtime;
	public long getRetroId() {
		return retroId;
	}
	public void setRetroId(long retroId) {
		this.retroId = retroId;
	}
	public long getProId() {
		return proId;
	}
	public void setProId(long proId) {
		this.proId = proId;
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
		this.codeImg = codeImg;
	}
	public String getCrrtime() {
		return crrtime;
	}
	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}
	
}
