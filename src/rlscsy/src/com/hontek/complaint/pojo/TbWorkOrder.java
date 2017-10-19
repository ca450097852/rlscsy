package com.hontek.complaint.pojo;
/**
 * 
 * <p>Title: 举报工单</p>
 * <p>Description: 举报工单 po类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class TbWorkOrder {
	private static final long serialVersionUID = 1L;
	private Long woId;				//工单编号
	private String cid;				//举报编号
	private Long sts;				//处理状态:0未处理，1处理中，2已处理
	private String	opinion;		//处理意见
	private String	dotime;			//处理时间
	private String	finalResult;	//最终处理结果
	private String	douser;			//处理人
	private String	remark;			//备注
	
	private String title;			//举报的标题
	private String proName;			//举报产品
	private String companyName;			//举报企业
	private String content;			//举报内容
	
	
	public TbWorkOrder(){
		
	}

	public TbWorkOrder(Long woId,String cid,Long sts,String	opinion,String dotime,
			String finalResult,String douser,String remark,String title,String proName,String companyName,String content){
		super();
		this.woId = woId;
		this.cid = cid;
		this.sts = sts;
		this.opinion = opinion;
		this.dotime = dotime;
		this.finalResult = finalResult;
		this.douser = douser;
		this.remark = remark;
		this.title = title;
		this.proName = proName;
		this.companyName = companyName;
		this.content = content;
	}

	public Long getWoId() {
		return woId;
	}

	public void setWoId(Long woId) {
		this.woId = woId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Long getSts() {
		return sts;
	}

	public void setSts(Long sts) {
		this.sts = sts;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getDotime() {
		return dotime;
	}

	public void setDotime(String dotime) {
		this.dotime = dotime;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getDouser() {
		return douser;
	}

	public void setDouser(String douser) {
		this.douser = douser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	

}
