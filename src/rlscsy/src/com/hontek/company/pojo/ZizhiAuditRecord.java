package com.hontek.company.pojo;

import java.util.List;

/**
 * Zizhi entity. @author MyEclipse Persistence Tools
 */

public class ZizhiAuditRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int auditId;
	private int entId;
	private String entName;
	private int auditEntid;
	private String auditEntname;
	private String auditUsername;
	private String auditUser;
	private String auditState;
	private String opinion;
	private String auditTime;
	private String applyCause;
	private String applyTime;
	private String zid;
	private String zname;
	// Constructors

	/** default constructor */
	public ZizhiAuditRecord() {
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
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

	public int getAuditEntid() {
		return auditEntid;
	}

	public void setAuditEntid(int auditEntid) {
		this.auditEntid = auditEntid;
	}

	public String getAuditEntname() {
		return auditEntname;
	}

	public void setAuditEntname(String auditEntname) {
		this.auditEntname = auditEntname;
	}

	public String getAuditUsername() {
		return auditUsername;
	}

	public void setAuditUsername(String auditUsername) {
		this.auditUsername = auditUsername;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getApplyCause() {
		return applyCause;
	}

	public void setApplyCause(String applyCause) {
		this.applyCause = applyCause;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public String getZname() {
		return zname;
	}

	public void setZname(String zname) {
		this.zname = zname;
	}

	
	
}