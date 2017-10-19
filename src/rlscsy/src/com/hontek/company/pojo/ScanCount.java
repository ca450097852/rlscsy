package com.hontek.company.pojo;

import java.io.Serializable;

/**
 * 扫描统计表
 * 
 * @author dream
 * 
 */
public class ScanCount implements Serializable {

	private int scId;
	private int comId;
	private String proId;
	private String proName;
	private String dimenno;
	private String scanTime;
	private String scanFrom;
	private String fromIp;
	private String userId;
	private String comName;

	public ScanCount() {
		super();
	}

	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	public String getScanFrom() {
		return scanFrom;
	}

	public void setScanFrom(String scanFrom) {
		this.scanFrom = scanFrom;
	}

	public String getFromIp() {
		return fromIp;
	}

	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

}
