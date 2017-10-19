package com.hontek.sys.pojo;

import java.util.List;


/**
 * TsRolePurv entity. @author MyEclipse Persistence Tools
 */

public class TsRolePurv implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int purvId;
	private int roleId;
	private int colId;
	private int entId;
	private int parentId;
	private String colName;
	private String colUrl;
	private int colLevel;
	private int seq;
	private String iconUrl;
	private List<TsRolePurv> clildrenList;
	// Constructors

	/** default constructor */
	public TsRolePurv() {
	}
	
	public TsRolePurv(int colId,String colName,String colUrl,String iconUrl) {
		super();
		setColId(colId);
		setColName(colName);
		setColUrl(colUrl);
		setIconUrl(iconUrl);
	}
	
	
	public TsRolePurv(int roleId,int entId,int colId,int parentId,String colName,String colUrl,int colLevel,int seq,String iconUrl) {
		this.roleId=roleId;
		this.colId = colId;
		this.entId = entId;
		this.parentId = parentId;
		this.colName = colName;
		this.colUrl = colUrl;
		this.colLevel = colLevel;
		this.seq = seq;
		this.iconUrl = iconUrl;
	}

	// Property accessors
	public int getPurvId() {
		return this.purvId;
	}

	public void setPurvId(int purvId) {
		this.purvId = purvId;
	}

	public int getEntId() {
		return this.entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
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

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getColId() {
		return colId;
	}


	public void setColId(int colId) {
		this.colId = colId;
	}

	public List<TsRolePurv> getClildrenList() {
		return clildrenList;
	}

	public void setClildrenList(List<TsRolePurv> clildrenList) {
		this.clildrenList = clildrenList;
	}

}