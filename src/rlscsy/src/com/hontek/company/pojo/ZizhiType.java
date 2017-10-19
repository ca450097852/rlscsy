package com.hontek.company.pojo;
/**
 * 资质类型表
 * @author lzk
 *
 */
public class ZizhiType {
	private int zizhiTypeId;
	private String typeName;
	private String remark;
	private int levelId;
	private int zid;
	public int getZizhiTypeId() {
		return zizhiTypeId;
	}
	public void setZizhiTypeId(int zizhiTypeId) {
		this.zizhiTypeId = zizhiTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	
}
