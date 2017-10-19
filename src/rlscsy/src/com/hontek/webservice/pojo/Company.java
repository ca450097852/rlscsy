package com.hontek.webservice.pojo;
/**
 * 第三方系统同步系统信息实体类
 * @author lzk
 *
 */
public class Company {
//	private String comId;//原系统的ID
	private String companyName;//企业名称
	private String simpleName;//企业简称
	private String intro;//企业简介
	private String tel;//固话
	private String legalPerson;//企业法人
	private String postCode;//邮编
	private String email;//电子信箱
	private String logoUrl;//企业logo
	private byte[] logoBytes;//企业logo数据
	private String domName;//企业网址
	private String regAddr;//注册地址
	private String managerAddr;//经营地址
	private String orgCode;//组织机构代码
//	private String crtDt;//创建时间 
	private String mobile;//手机号码
	
	private String purl;//pc端链接地址
	private String murl;//移动设备端链接地址
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
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
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public byte[] getLogoBytes() {
		return logoBytes;
	}
	public void setLogoBytes(byte[] logoBytes) {
		this.logoBytes = logoBytes;
	}
	public String getDomName() {
		return domName;
	}
	public void setDomName(String domName) {
		this.domName = domName;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getManagerAddr() {
		return managerAddr;
	}
	public void setManagerAddr(String managerAddr) {
		this.managerAddr = managerAddr;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	
	
	
}
