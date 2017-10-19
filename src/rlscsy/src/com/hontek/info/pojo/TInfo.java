package com.hontek.info.pojo;

import java.io.Serializable;
import java.sql.Clob;
/**
 * 资讯表 实体类
 * @author qql
 *
 */
public class TInfo implements Serializable,Cloneable {
	
	private static final long serialVersionUID = 1L;
	private Long infoId;		//资讯编号
	private Long typeId;		//资讯分类id
	private String title;		//标题
	private String content;		//资讯内容
	private Long seq;			//排序号
	private String userId;		//创建人
	private String crttime;		//创建日期
	private String auditor;		//审核人
	private String audiDate;	//审核日期
	private String opinion;		//审核意见
	private Long rsts;		//状态
	private String remark;		//备注
	private Long sysCode;		//接入系统编号
	private String titleImg;	//标题图片
	private Long entId;		//系统主体id
	
	private String typeName;	//分类名称
	private String nickName;	//用户名	
	
	
	public TInfo(){
		
	}
	
	public Object clone(){
		TInfo o = null;
        try{
            o = (TInfo)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAudiDate() {
		return audiDate;
	}

	public void setAudiDate(String audiDate) {
		this.audiDate = audiDate;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Long getRsts() {
		return rsts;
	}

	public void setRsts(Long rsts) {
		this.rsts = rsts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSysCode() {
		return sysCode;
	}

	public void setSysCode(Long sysCode) {
		this.sysCode = sysCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

}
