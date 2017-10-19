package com.hontek.sys.pojo;

/**
 * TsEnterprise entity. @author MyEclipse Persistence Tools
 */

public class TsEnterprise extends EntExpand implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int entId;
	private int parentId;
	private String account;
	private String name;
	private int seq;
	private String simpleName;
	private String intro;
	private String tel;
	private String legalPerson;
	private String integrityRecord;

	private String entCode;
	private String regAddr;
	private String manageAddr;
	private String postCode;
	private String email;
	private String logoUrl;
	private String domName;
	private String sign;
	private int entType;
	private String typeName;
	private String flag;
	private String sts;
	private String crtDt;
	private String crtUserId;
	private String isReported;
	private String sysCode;
	private String orgCode;//组织机构代码
	private Integer areaId;
	private String townRsts;//0待提交，1待审核，2暂停，3不通过，4通过；默认为空
	private String areaRsts;
	private String cityRsts;
	private String provinceRsts;
	private String companyRsts;

	private String areaName;
	private String parentName;
	private String addUser;//开账号使用
	private String auditEnt;//开账号使用
	private String purl;//pc端链接地址
	private String murl;//移动设备端链接地址
	
	private int isbatch;//是否批次追溯 0否，1是。默认0
	
	private Integer bodyEntId;//所属主体
	
	//注册时使用
	private String password;
	private String proType;//产品类型
	private String recordType;//档案类型
	
	private String lat;//经度
	private String lng;//纬度
	
	private String distance;//距离
	
	// Constructors
	//查询条件使用
	private String startDate;
	private String endDate;
	private String typeId;
	private String chartType;//0柱形,1线形,2饼图
	private String mobile;
	private String tmpId;//同步企业信息时使用
	
	/** default constructor */
	public TsEnterprise() {
	}

	/** full constructor */

	public TsEnterprise(int entId, int parentId, String account, String name,
			int seq, String simpleName, String intro, String tel,
			String legalPerson,String integrityRecord,String entCode,
			String regAddr, String manageAddr, String postCode, String email,
			String logoUrl, String domName, String sign, int entType,
			String flag, String sts, String crtDt, String crtUserId,
			String isReported, String sysCode,String orgCode,int isbatch) {
		super();
		this.entId = entId;
		this.parentId = parentId;
		this.account = account;
		this.name = name;
		this.seq = seq;
		this.simpleName = simpleName;
		this.intro = intro;
		this.tel = tel;
		this.legalPerson = legalPerson;
		this.integrityRecord = integrityRecord;
		this.entCode = entCode;
		this.regAddr = regAddr;
		this.manageAddr = manageAddr;
		this.postCode = postCode;
		this.email = email;
		this.logoUrl = logoUrl;
		this.domName = domName;
		this.sign = sign;
		this.entType = entType;
		this.flag = flag;
		this.sts = sts;
		this.crtDt = crtDt;
		this.crtUserId = crtUserId;
		this.isReported = isReported;
		this.sysCode = sysCode;
		this.orgCode = orgCode;
		this.isbatch = isbatch;

	}

	// Property accessors
	public int getEntId() {
		return entId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTmpId() {
		return tmpId;
	}

	public void setTmpId(String tmpId) {
		this.tmpId = tmpId;
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

	public String getIntegrityRecord() {
		return integrityRecord;
	}

	public void setIntegrityRecord(String integrityRecord) {
		this.integrityRecord = integrityRecord;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
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

	public String getDomName() {
		return domName;
	}

	public void setDomName(String domName) {
		this.domName = domName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getEntType() {
		return entType;
	}

	public void setEntType(int entType) {
		this.entType = entType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	public String getIsReported() {
		return isReported;
	}

	public void setIsReported(String isReported) {
		this.isReported = isReported;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTownRsts() {
		return townRsts;
	}

	public void setTownRsts(String townRsts) {
		this.townRsts = townRsts;
	}

	public String getAreaRsts() {
		return areaRsts;
	}

	public void setAreaRsts(String areaRsts) {
		this.areaRsts = areaRsts;
	}

	public String getCityRsts() {
		return cityRsts;
	}

	public void setCityRsts(String cityRsts) {
		this.cityRsts = cityRsts;
	}

	public String getProvinceRsts() {
		return provinceRsts;
	}

	public void setProvinceRsts(String provinceRsts) {
		this.provinceRsts = provinceRsts;
	}

	public String getCompanyRsts() {
		return companyRsts;
	}

	public void setCompanyRsts(String companyRsts) {
		this.companyRsts = companyRsts;
	}

	public String getAuditEnt() {
		return auditEnt;
	}

	public void setAuditEnt(String auditEnt) {
		this.auditEnt = auditEnt;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public int getIsbatch() {
		return isbatch;
	}

	public void setIsbatch(int isbatch) {
		this.isbatch = isbatch;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Integer getBodyEntId() {
		return bodyEntId;
	}

	public void setBodyEntId(Integer bodyEntId) {
		this.bodyEntId = bodyEntId;
	}

	@Override
	public String toString() {
		return "TsEnterprise [areaId=" + areaId + ", areaName=" + areaName
				+ ", entCode="
				+ entCode + ", entId=" + entId + ", entType=" + entType
				+ ", flag=" + flag + ", integrityRecord=" + integrityRecord
				+ ", intro=" + intro + ", isReported=" + isReported
				+ ", murl=" + murl + ", name=" + name + ", orgCode=" + orgCode
				+ ", parentId=" + parentId + ", parentName=" + parentName
				+ ", purl=" + purl + ", regAddr=" + regAddr + ", seq=" + seq
				+ ", sign=" + sign + ", simpleName=" + simpleName
				+ ", startDate=" + startDate + ", sts=" + sts + ", sysCode="
				+ sysCode + ", tel=" + tel + ", tmpId=" + tmpId + ", townRsts="
				+ townRsts + ", typeId=" + typeId + ", typeName=" + typeName
				+ "]";
	}

}