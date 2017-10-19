package com.hontek.company.pojo;

import java.io.Serializable;
/**
 * 审核记录表
 * @author dream
 *
 */
public class AuditRecord implements Serializable {

	private int auditId;
	private String entName;
	private int entId;
	private String auditEntName;
	private int auditEntId;
	private String auditUserName;
	private String auditUserId;
	private String auditState;
	private String auditOpinion;
	private String auditTime;
	private String parentAuditEntName;
	private String parentAuditEntId;

	
	
	public AuditRecord() {
		super();
	}



	public AuditRecord(int auditId, String entName, int entId,
			String auditEntName, int auditEntId, String auditUserName,
			String auditUserId, String auditState, String auditOpinion,
			String auditTime, String parentAuditEntName, String parentAuditEntId) {
		super();
		this.auditId = auditId;
		this.entName = entName;
		this.entId = entId;
		this.auditEntName = auditEntName;
		this.auditEntId = auditEntId;
		this.auditUserName = auditUserName;
		this.auditUserId = auditUserId;
		this.auditState = auditState;
		this.auditOpinion = auditOpinion;
		this.auditTime = auditTime;
		this.parentAuditEntName = parentAuditEntName;
		this.parentAuditEntId = parentAuditEntId;
	}



	public int getAuditId() {
		return auditId;
	}



	public void setAuditId(int auditId) {
		this.auditId = auditId;
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



	public String getAuditEntName() {
		return auditEntName;
	}



	public void setAuditEntName(String auditEntName) {
		this.auditEntName = auditEntName;
	}



	public int getAuditEntId() {
		return auditEntId;
	}



	public void setAuditEntId(int auditEntId) {
		this.auditEntId = auditEntId;
	}



	public String getAuditUserName() {
		return auditUserName;
	}



	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}



	public String getAuditUserId() {
		return auditUserId;
	}



	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}



	public String getAuditState() {
		return auditState;
	}



	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}



	public String getAuditOpinion() {
		return auditOpinion;
	}



	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}



	public String getAuditTime() {
		return auditTime;
	}



	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}



	public String getParentAuditEntName() {
		return parentAuditEntName;
	}



	public void setParentAuditEntName(String parentAuditEntName) {
		this.parentAuditEntName = parentAuditEntName;
	}



	public String getParentAuditEntId() {
		return parentAuditEntId;
	}



	public void setParentAuditEntId(String parentAuditEntId) {
		this.parentAuditEntId = parentAuditEntId;
	}
	
	
}
