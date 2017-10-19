package com.hontek.company.pojo;
/**
 * 企业考核明细表
 * @author lzk
 *
 */
public class AssessDetail {
	private int cadId;
	private int caId;
	private int itemId;
	private String checkSelf;
	private String checkAudit;
	private String crrtime;
	
	public AssessDetail() {
		super();
	}

	public int getCadId() {
		return cadId;
	}

	public void setCadId(int cadId) {
		this.cadId = cadId;
	}

	public int getCaId() {
		return caId;
	}

	public void setCaId(int caId) {
		this.caId = caId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getCheckSelf() {
		return checkSelf;
	}

	public void setCheckSelf(String checkSelf) {
		this.checkSelf = checkSelf;
	}

	public String getCheckAudit() {
		return checkAudit;
	}

	public void setCheckAudit(String checkAudit) {
		this.checkAudit = checkAudit;
	}

	public String getCrrtime() {
		return crrtime;
	}

	public void setCrrtime(String crrtime) {
		this.crrtime = crrtime;
	}

	public AssessDetail(int cadId, int caId, int itemId, String checkSelf,
			String checkAudit, String crrtime) {
		super();
		this.cadId = cadId;
		this.caId = caId;
		this.itemId = itemId;
		this.checkSelf = checkSelf;
		this.checkAudit = checkAudit;
		this.crrtime = crrtime;
	}
	
}
