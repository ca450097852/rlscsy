package com.hontek.company.pojo;

import java.io.Serializable;
/**
 * 审核控制表
 * @author dream
 *
 */
public class AuditCtrl implements Serializable {

	private int ctrId;
	private String entName;
	private int entId;
	private String auditEnt;
	private String ctrEnt;
	private String ctrUser;
	private String ctrtime;
	private String ctrSts;
	
	
	public AuditCtrl() {
		super();
	}
	public AuditCtrl(String entName, int entId, String auditEnt,
			String ctrEnt, String ctrUser, String ctrtime, String ctrSts) {
		super();
		this.entName = entName;
		this.entId = entId;
		this.auditEnt = auditEnt;
		this.ctrEnt = ctrEnt;
		this.ctrUser = ctrUser;
		this.ctrtime = ctrtime;
		this.ctrSts = ctrSts;
	}
	public AuditCtrl(int ctrId, String entName, int entId, String auditEnt,
			String ctrEnt, String ctrUser, String ctrtime, String ctrSts) {
		super();
		this.ctrId = ctrId;
		this.entName = entName;
		this.entId = entId;
		this.auditEnt = auditEnt;
		this.ctrEnt = ctrEnt;
		this.ctrUser = ctrUser;
		this.ctrtime = ctrtime;
		this.ctrSts = ctrSts;
	}
	public int getCtrId() {
		return ctrId;
	}
	public void setCtrId(int ctrId) {
		this.ctrId = ctrId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getAuditEnt() {
		return auditEnt;
	}
	public void setAuditEnt(String auditEnt) {
		this.auditEnt = auditEnt;
	}
	public String getCtrEnt() {
		return ctrEnt;
	}
	public void setCtrEnt(String ctrEnt) {
		this.ctrEnt = ctrEnt;
	}
	public String getCtrUser() {
		return ctrUser;
	}
	public void setCtrUser(String ctrUser) {
		this.ctrUser = ctrUser;
	}
	public String getCtrtime() {
		return ctrtime;
	}
	public void setCtrtime(String ctrtime) {
		this.ctrtime = ctrtime;
	}
	public String getCtrSts() {
		return ctrSts;
	}
	public void setCtrSts(String ctrSts) {
		this.ctrSts = ctrSts;
	}
	
}
