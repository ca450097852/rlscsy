package com.hontek.info.pojo;

public class TInfoType {
	
	private static final long serialVersionUID = 1L;
	private Long typeId;//分类编号
	private Long parentId;//上级分类
	private String typeName;//分类名称
	private String crttime;//创建时间
	private String userId;//创建人
	private String remark;//备注
	
	private String parName;//父类名称
	private String nickName;//用户昵称
	
	public TInfoType(){
		
	}
	
	public TInfoType(Long typeId,Long parentId,String typeName,String crttime,
			String userId,String remark,String parName,String nickName){
		super();
		this.typeId = typeId;
		this.parentId = parentId;
		this.typeName = typeName;
		this.crttime = crttime;
		this.userId = userId;
		this.remark = remark;
		this.parName = parName;
		this.nickName = nickName;
	}


	public Long getTypeId() {
		return typeId;
	}


	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getCrttime() {
		return crttime;
	}


	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getParName() {
		return parName;
	}


	public void setParName(String parName) {
		this.parName = parName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	

}
