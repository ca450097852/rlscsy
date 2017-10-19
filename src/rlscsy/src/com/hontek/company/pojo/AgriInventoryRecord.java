package com.hontek.company.pojo;
/**
 * 投入品出库记录表
 * @author lzk
 *
 */
public class AgriInventoryRecord {
	private int agid;
	private String agname;
	private String agsupplier;
	private String agspc;
	private String agcount;
	private String agnum;
	private String crttime;
	private int recid;
	
	
	
	public AgriInventoryRecord() {
		super();
	}
	public AgriInventoryRecord(int agid, String agname, String agsupplier,
			String agspc, String agcount, String agnum, String crttime,
			int recid) {
		super();
		this.agid = agid;
		this.agname = agname;
		this.agsupplier = agsupplier;
		this.agspc = agspc;
		this.agcount = agcount;
		this.agnum = agnum;
		this.crttime = crttime;
		this.recid = recid;
	}
	public int getAgid() {
		return agid;
	}
	public void setAgid(int agid) {
		this.agid = agid;
	}
	public String getAgname() {
		return agname;
	}
	public void setAgname(String agname) {
		this.agname = agname;
	}
	public String getAgsupplier() {
		return agsupplier;
	}
	public void setAgsupplier(String agsupplier) {
		this.agsupplier = agsupplier;
	}
	public String getAgspc() {
		return agspc;
	}
	public void setAgspc(String agspc) {
		this.agspc = agspc;
	}
	public String getAgcount() {
		return agcount;
	}
	public void setAgcount(String agcount) {
		this.agcount = agcount;
	}
	public String getAgnum() {
		return agnum;
	}
	public void setAgnum(String agnum) {
		this.agnum = agnum;
	}
	public String getCrttime() {
		return crttime;
	}
	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}

	
}
