package com.hontek.sys.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TsSysCol entity. @author MyEclipse Persistence Tools
 */

public class TsSysCol implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String colName;
	private int _parentId;
	private String remarks;
	private String colUrl;
	private int colLevel;
	private int seq;
	private String iconUrl;
	private List<TsRolePurv> clildrenList;
	// Constructors

	/** default constructor */
	public TsSysCol() {
	}

	// Property accessors


	public String getColName() {
		return this.colName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}


	public int get_parentId() {
		return _parentId;
	}

	public void set_parentId(int parentId) {
		_parentId = parentId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<TsRolePurv> getClildrenList() {
		return clildrenList;
	}

	public void setClildrenList(List<TsRolePurv> clildrenList) {
		this.clildrenList = clildrenList;
	}
	
}