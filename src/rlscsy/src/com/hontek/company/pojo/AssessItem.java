package com.hontek.company.pojo;
/**
 * 考核项目表
 * @author chenan
 *
 */
public class AssessItem {
	private int itemId;
	private String itemName;
	private String itemDesc;
	private String nodeType;
	private String seq;
	private String crrtime;
	
	public AssessItem() {
		super();
	}

	public AssessItem(int itemId, String itemName, String itemDesc,
			String nodeType, String seq, String crrtime) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.nodeType = nodeType;
		this.seq = seq;
		this.setCrrtime(crrtime);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCrrtime() {
		return crrtime;
	}

	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}


	
}
