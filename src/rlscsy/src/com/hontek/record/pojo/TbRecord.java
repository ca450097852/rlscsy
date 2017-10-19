package com.hontek.record.pojo;

import java.util.List;

/**
 * 档案表
 * @author lzk
 *
 */
public class TbRecord {
	private int recId;
	private String recName;
	private String crttime;
	private int seq;
	private String crtUser;
	private String recSts;
	private int objId;		//企业ID；分类ID；产品ID
	private int objTypeId;	//企业1；分类二维码2；产品3
	private String typeName;//关联获取的tb_protype的type_name
	private String proName;//关联获取的tb_pro_type_qrcode的pro_name
	private int ptqId;
	
	private int entId;
	private String entName;
	
	private List<TbElement> elements;//要素集合
	
	private List<TbProTypeBatch> batch;
	
	public TbRecord() {
		super();
	}
	public TbRecord(int recId, String recName, String crttime, int seq,
			String crtUser, String recSts, int objId, int objTypeId) {
		super();
		this.recId = recId;
		this.recName = recName;
		this.crttime = crttime;
		this.seq = seq;
		this.crtUser = crtUser;
		this.recSts = recSts;
		this.objId = objId;
		this.objTypeId = objTypeId;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public int getPtqId() {
		return ptqId;
	}
	public void setPtqId(int ptqId) {
		this.ptqId = ptqId;
	}
	public String getCrttime() {
		return crttime;
	}
	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getRecSts() {
		return recSts;
	}
	public void setRecSts(String recSts) {
		this.recSts = recSts;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public int getObjTypeId() {
		return objTypeId;
	}
	public void setObjTypeId(int objTypeId) {
		this.objTypeId = objTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<TbElement> getElements() {
		return elements;
	}
	public void setElements(List<TbElement> elements) {
		this.elements = elements;
	}
	public List<TbProTypeBatch> getBatch() {
		return batch;
	}
	public void setBatch(List<TbProTypeBatch> batch) {
		this.batch = batch;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	
}
