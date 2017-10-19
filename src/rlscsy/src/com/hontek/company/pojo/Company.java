package com.hontek.company.pojo;

import java.io.Serializable;

/**
 * 企业表
 * @ClassName: Company 
 * @author qql
 */
public class Company implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	private int comId;
	private int parentId;//经营者所在节点，流通节点此字段为0
	private String name;
	private String nature;//企业性质,企业法人或个体户
	private String comType;//经营者类型,主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
	private String comCode;//经营者编码
	private String nodeCode;//节点编码
	private String flag;//节点类型,1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
	private String linkMan;
	private String phone;
	private String fax;
	private String addr;
	private String email;
	private String area;
	private String introduction;
	private String corporate;
	private String regCode;//注册号或身份证号
	private String licenseNum;//营业执照\注册号
	private String licenseImg;//营业执照扫描件
	private String state;//1使用；2���用
	private String regTime;//注册时间
	private String recordDate;//
	private String remark;//
	private String entId;//监管机构
	private String comLogo;//企业logo

	//关联字段
	private String nodeName;
	private String areaName;
	
	//非表字段
	private String userName;
	
	private String account;
	private String password;
	
	private String nodeAnCom;//流通节点+经营者；
	
	public Company() {
	}
	
	public Company clone(){
		Company o = null;
        try{
            o = (Company)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getLicenseImg() {
		return licenseImg;
	}
	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getComLogo() {
		return comLogo;
	}


	public void setComLogo(String comLogo) {
		this.comLogo = comLogo;
	}


	public String getNodeAnCom() {
		return nodeAnCom;
	}


	public void setNodeAnCom(String nodeAnCom) {
		this.nodeAnCom = nodeAnCom;
	}

	
}
