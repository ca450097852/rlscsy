package com.hontek.company.pojo;
/**
 * 级别表
 * @author lzk
 *
 */
public class Level {
	private int levelId;
	private String levelTitle;
	private String remarks;
	private int seq;
	private int typeClass;
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getLevelTitle() {
		return levelTitle;
	}
	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(int typeClass) {
		this.typeClass = typeClass;
	}
	
	
}
