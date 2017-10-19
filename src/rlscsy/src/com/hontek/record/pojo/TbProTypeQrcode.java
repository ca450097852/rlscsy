package com.hontek.record.pojo;

import java.util.List;

/**
 * 分类二维码信息
 * @author lzk
 *
 */
public class TbProTypeQrcode {
	private int ptqId;
	private int entId;
	private int typeId;
	private String dimenno;
	private String url;
	private String codeImg;
	private String crrtime;
	private String certificate;
	private String quantity;
	private String listed;
	private String salearea;
	
	private String proName;
	
	private String proDesc;
	private String brandName;
	
	private String proState;	//0待提交；1待审核；2审核通过；3审核不通过
	private String submitTime;
	private String auditTime;
	private Integer auditEntId;
	
	private Integer isbatch;	//0否，1是。默认0
	
	
	
	private String chandi;
	private String typeImg;//种类图片
	
	//查询字段
	private String typeName;	//分类名称；
	private String companyName;	//企业名称；
	private String sysCode;		//系统编码
	private String intro;
	private String legalPerson;
	private String regAddr;
	private String manageAddr;
	private String tel;
	private String email;
	private String domName;
	private String typeClass;
	
	private List<TbProTypeArea> proTypeArea;
	
	private List<TbMassif> chlist;//基地地块
	
	public TbProTypeQrcode() {
		super();
	}
	public TbProTypeQrcode(int ptqId, int entId, int typeId, String dimenno,
			String url, String codeImg, String crrtime) {
		super();
		this.ptqId = ptqId;
		this.entId = entId;
		this.typeId = typeId;
		this.dimenno = dimenno;
		this.url = url;
		this.codeImg = codeImg;
		this.crrtime = crrtime;
	}
	public int getPtqId() {
		return ptqId;
	}
	public void setPtqId(int ptqId) {
		this.ptqId = ptqId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public Integer getIsbatch() {
		return isbatch;
	}
	public void setIsbatch(Integer isbatch) {
		this.isbatch = isbatch;
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
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getManageAddr() {
		return manageAddr;
	}
	public void setManageAddr(String manageAddr) {
		this.manageAddr = manageAddr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomName() {
		return domName;
	}
	public void setDomName(String domName) {
		this.domName = domName;
	}
	public String getChandi() {
		return chandi;
	}
	public void setChandi(String chandi) {
		this.chandi = chandi;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getListed() {
		return listed;
	}
	public void setListed(String listed) {
		this.listed = listed;
	}
	public String getSalearea() {
		return salearea;
	}
	public void setSalearea(String salearea) {
		this.salearea = salearea;
	}
	public List<TbProTypeArea> getProTypeArea() {
		return proTypeArea;
	}
	public void setProTypeArea(List<TbProTypeArea> proTypeArea) {
		this.proTypeArea = proTypeArea;
	}
	public String getTypeImg() {
		return typeImg;
	}
	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public List<TbMassif> getChlist() {
		return chlist;
	}
	public void setChlist(List<TbMassif> chlist) {
		this.chlist = chlist;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProState() {
		return proState;
	}
	public void setProState(String proState) {
		this.proState = proState;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditEntId() {
		return auditEntId;
	}
	public void setAuditEntId(Integer auditEntId) {
		this.auditEntId = auditEntId;
	}
	
	
}
