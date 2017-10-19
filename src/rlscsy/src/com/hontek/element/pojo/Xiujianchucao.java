package com.hontek.element.pojo;
/**
 * 修剪除草
 * @author lzk
 *
 */
public class Xiujianchucao {
	private int giid;
	private String dodate;
	private String dodesc;
	private int dotype;
	private int recId;
	
	public Xiujianchucao() {
		super();
	}
	public Xiujianchucao(int giid, String dodate, String dodesc, int dotype,
			int recId) {
		super();
		this.giid = giid;
		this.dodate = dodate;
		this.dodesc = dodesc;
		this.dotype = dotype;
		this.recId = recId;
	}
	public int getGiid() {
		return giid;
	}
	public void setGiid(int giid) {
		this.giid = giid;
	}
	public String getDodate() {
		return dodate;
	}
	public void setDodate(String dodate) {
		this.dodate = dodate;
	}
	public String getDodesc() {
		return dodesc;
	}
	public void setDodesc(String dodesc) {
		this.dodesc = dodesc;
	}
	public int getDotype() {
		return dotype;
	}
	public void setDotype(int dotype) {
		this.dotype = dotype;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	
}
