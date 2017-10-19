package com.hontek.sys.pojo;
/**
 * 企业扩展信息
 * @author ZK
 *
 */
public class EntExpand{
	private int entId;
	private String ontrialStart;
	private String ontrialEnd;
	private String ischarge;//1是；2否
	private String expired;
	private String ismainbody;//1是；2否
	private String mbName;
	private String mbType;// 主体类型	1农业局；2协会；3企业	varchar(50)	FALSE	FALSE	FALSE
	private String mbDomain;
	private String userId;
	private String createTime;
	private String remark;
	private String validCode;//是否有验证二维码 1是；2否，默认2
	private String showCode;//是否显示标识管理 1是；2否，默认1
	private String auditPro;//1是；2否，默认1
	private String auditBatch;//1是；2否，默认1
	
	
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getOntrialStart() {
		return ontrialStart;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getShowCode() {
		return showCode;
	}
	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}
	public void setOntrialStart(String ontrialStart) {
		this.ontrialStart = ontrialStart;
	}
	public String getOntrialEnd() {
		return ontrialEnd;
	}
	public void setOntrialEnd(String ontrialEnd) {
		this.ontrialEnd = ontrialEnd;
	}
	public String getIscharge() {
		return ischarge;
	}
	public void setIscharge(String ischarge) {
		this.ischarge = ischarge;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	public String getIsmainbody() {
		return ismainbody;
	}
	public void setIsmainbody(String ismainbody) {
		this.ismainbody = ismainbody;
	}
	public String getMbName() {
		return mbName;
	}
	public void setMbName(String mbName) {
		this.mbName = mbName;
	}
	public String getMbType() {
		return mbType;
	}
	public void setMbType(String mbType) {
		this.mbType = mbType;
	}
	public String getMbDomain() {
		return mbDomain;
	}
	public void setMbDomain(String mbDomain) {
		this.mbDomain = mbDomain;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditPro() {
		return auditPro;
	}
	public void setAuditPro(String auditPro) {
		this.auditPro = auditPro;
	}
	public String getAuditBatch() {
		return auditBatch;
	}
	public void setAuditBatch(String auditBatch) {
		this.auditBatch = auditBatch;
	}
	
	
	
}
