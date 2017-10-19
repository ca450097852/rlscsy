package com.hontek.record.pojo;
/**
 * @ClassName: TbElement
 * @Description: TODO(档案要素表)
 * @date 2015-7-28 下午03:07:58
 * @author qql
 * @version 1.0
 */
public class TbElement {
	private static final long serialVersionUID = 1L;
	private Long elementId;		//要素编号
	private String elementName;		//要素名称
	private String tableName;		//要素表名
	private String seq;				//顺序
	private String remark;			//备注
	private String elementIcon;		//要素图标
	private String elementUrl;		//链接地址
	public TbElement(){
		
	}
	public TbElement(Long elementId,String elementName,String tableName,String seq,
			String remark,String elementIcon,String elementUrl){
		super();
		this.elementId = elementId;
		this.elementName = elementName;
		this.tableName = tableName;
		this.seq = seq;
		this.remark = remark;
		this.elementIcon = elementIcon;
		this.elementUrl = elementUrl;
	}
	public Long getElementId() {
		return elementId;
	}
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	

}
