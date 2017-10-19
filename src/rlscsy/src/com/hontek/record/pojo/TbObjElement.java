/**
 * 
 */
package com.hontek.record.pojo;

/**
 * @ClassName: TbObjelement
 * @Description: TODO(档案要素关系)
 * @date 2015-7-28 下午04:58:48
 * @author qql
 * @version 1.0
 */
public class TbObjElement {
	private int relId; //关系编号
	private int elementId;//要素编号
	private int seq;//顺序
	private int objTypeId;//对象类型 :企业1；分类2；产品3
	private int objId;//对象编号:企业ID；分类二维码ID；产品ID
	
	private String tableName;		//要素表名
	private String elementName;		//要素名称
	private String elementIcon;		//要素图标
	private String elementUrl;		//链接地址
	
	public TbObjElement(){
		super();
	}

	public TbObjElement(int relId,int elementId,int seq,int objTypeId,int objId){
		super();
		this.relId = relId;
		this.elementId = elementId;
		this.seq = seq;
		this.objTypeId = objTypeId;
		this.objId = objId;
	}

	public int getRelId() {
		return relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getObjTypeId() {
		return objTypeId;
	}

	public void setObjTypeId(int objTypeId) {
		this.objTypeId = objTypeId;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementIcon() {
		return elementIcon;
	}

	public void setElementIcon(String elementIcon) {
		this.elementIcon = elementIcon;
	}

	public String getElementUrl() {
		return elementUrl;
	}

	public void setElementUrl(String elementUrl) {
		this.elementUrl = elementUrl;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
}
