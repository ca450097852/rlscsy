package com.hontek.sys.pojo;

import java.util.List;


/**
 * 默认权限设置
 * TsRolePurvDef entity. @author MyEclipse Persistence Tools
 */

public class TsRolePurvDef implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int purvId;
	private int colId;
	private int parentId;
	private String colName;
	private String colUrl;
	private int colLevel;
	private int seq;
	private String iconUrl;
	private List<TsRolePurvDef> clildrenList;
	private int typeId;
	// Constructors

	/** default constructor */
	public TsRolePurvDef() {
	}
	
	public TsRolePurvDef(int colId,String colName,String colUrl) {
		super();
		setColId(colId);
		setColName(colName);
		setColUrl(colUrl);
	}
	
	
	public TsRolePurvDef(int colId,int parentId,String colName,String colUrl,int colLevel,int seq,String iconUrl) {
		this.colId = colId;
		this.parentId = parentId;
		this.colName = colName;
		this.colUrl = colUrl;
		this.colLevel = colLevel;
		this.seq = seq;
		this.iconUrl = iconUrl;
	}
	
	public TsRolePurvDef(int colId,int parentId,String colName,String colUrl,int colLevel,int seq,String iconUrl,int typeId) {
		this.colId = colId;
		this.parentId = parentId;
		this.colName = colName;
		this.colUrl = colUrl;
		this.colLevel = colLevel;
		this.seq = seq;
		this.iconUrl = iconUrl;
		this.typeId = typeId;
	}

	public TsRolePurvDef(int purvId,  int colId,
			int parentId, String colName, String colUrl, int colLevel,
			int seq, String iconUrl, List<TsRolePurvDef> clildrenList,
			int typeId) {
		super();
		this.purvId = purvId;
		this.colId = colId;
		this.parentId = parentId;
		this.colName = colName;
		this.colUrl = colUrl;
		this.colLevel = colLevel;
		this.seq = seq;
		this.iconUrl = iconUrl;
		this.clildrenList = clildrenList;
		this.typeId = typeId;
	}
	
	

	// Property accessors

	public int getPurvId() {
		return this.purvId;
	}

	public void setPurvId(int purvId) {
		this.purvId = purvId;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColUrl() {
		return this.colUrl;
	}

	public void setColUrl(String colUrl) {
		this.colUrl = colUrl;
	}

	public int getColLevel() {
		return this.colLevel;
	}

	public void setColLevel(int colLevel) {
		this.colLevel = colLevel;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getColId() {
		return colId;
	}


	public void setColId(int colId) {
		this.colId = colId;
	}

	public List<TsRolePurvDef> getClildrenList() {
		return clildrenList;
	}

	public void setClildrenList(List<TsRolePurvDef> clildrenList) {
		this.clildrenList = clildrenList;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}