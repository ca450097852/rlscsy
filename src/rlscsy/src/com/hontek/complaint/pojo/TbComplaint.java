package com.hontek.complaint.pojo;
/**
 * 
 * <p>Title:投诉举报   </p>
 * <p>Description: 投诉举报 po</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class TbComplaint {
	private static final long serialVersionUID = 1L;
	private String cid;			//举报编号
	private Long entId;			//区域:从ent表中“企业标识”为区选取
	private String title;		//标题
	private String proName;		//产品名称
	private String companyName;		//企业名称
	private String content;		//举报内容
	private String crttime;		//举报时间
	private String phone;		//举报人电话	
	private String appName;		//举报附件
	private String userName;		//举报人
	private String userAddr;		//通讯地址
	private String typeNo;		//举报类型

	private String remark;		//备注->结果;
	private Long sts;			//状态	:0未处理，1处理中，2已处理
	private String sysCode;		//接入系统编号	:省平台录入默认为0
	private Long mainentid;		//主体entId
	
	//非表字段
	private String entName;		//区域名称
	private String isCreatGd;	//是否创建工单
	private String douser;		//工单处理人
	private String opinion;		//工单处理意见
	private String finalResult;		//工单处理意见
	public TbComplaint(){
		
	}
	public TbComplaint(String cid,Long entId,String title,String proName,String companyName,String content,
			String crttime,String phone,String appName,String userName,
			String remark,Long sts,String sysCode,String isCreatGd,
			String douser,String opinion,String finalResult){
		super();
		this.cid = cid;
		this.entId = entId;
		this.title = title;
		this.proName = proName;
		this.companyName = companyName;
		this.content = content;
		this.crttime = crttime;
		this.phone = phone;
		this.appName = appName;
		this.userName = userName;
		this.remark = remark;
		this.sts = sts;
		this.sysCode = sysCode;
		this.isCreatGd = isCreatGd;
		this.douser = douser;
		this.opinion = opinion;
		this.finalResult = finalResult;
	}
	public TbComplaint(String cid,Long entId,String title,String content,
			String crttime,String phone,String appName,String userName,
			String remark,Long sts,String sysCode,String isCreatGd,
			String douser,String opinion,String finalResult){
		super();
		this.cid = cid;
		this.entId = entId;
		this.title = title;
		this.content = content;
		this.crttime = crttime;
		this.phone = phone;
		this.appName = appName;
		this.userName = userName;
		this.remark = remark;
		this.sts = sts;
		this.sysCode = sysCode;
		this.isCreatGd = isCreatGd;
		this.douser = douser;
		this.opinion = opinion;
		this.finalResult = finalResult;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Long getEntId() {
		return entId;
	}
	public void setEntId(Long entId) {
		this.entId = entId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCrttime() {
		return crttime;
	}
	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getSts() {
		return sts;
	}
	public void setSts(Long sts) {
		this.sts = sts;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getIsCreatGd() {
		return isCreatGd;
	}
	public void setIsCreatGd(String isCreatGd) {
		this.isCreatGd = isCreatGd;
	}
	public String getDouser() {
		return douser;
	}
	public void setDouser(String douser) {
		this.douser = douser;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public Long getMainentid() {
		return mainentid;
	}
	public void setMainentid(Long mainentid) {
		this.mainentid = mainentid;
	}
	
}
