package com.hontek.record.pojo;
/**
 * @ClassName: TbElement
 * @Description: TODO(对象档案参照表)
 * @date 2015-7-28 下午03:07:58
 * @author qql
 * @version 1.0
 */
public class TbElementTemplet {
	private static final long serialVersionUID = 1L;
	private int tempId;		//参照编号
	private int archivesType;		//档案类型:普通1；三品一标2；种植类3；畜牧类4
	private int elementId;		//要素编号
	
	//非表字段
	private String elementName;
	
	public TbElementTemplet(){
		
	}
	public TbElementTemplet(int tempId,int archivesType,int elementId){
		super();
		this.tempId = tempId;
		this.archivesType = archivesType;
		this.elementId = elementId;
	}
	public int getTempId() {
		return tempId;
	}
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}
	public int getArchivesType() {
		return archivesType;
	}
	public void setArchivesType(int archivesType) {
		this.archivesType = archivesType;
	}
	public int getElementId() {
		return elementId;
	}
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	

}
