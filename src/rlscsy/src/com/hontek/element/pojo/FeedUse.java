package com.hontek.element.pojo;
/**
 * 饲料投入品使用记录信息表
 *
 */
public class FeedUse {
	private int feedid;
	private int recId;
	private String feedname;
	private String purpose;
	private String userarea;
	private String usedate;
	private String usertotal;
	private String useman;
	private String remark;
	private String crttime;
	
	public FeedUse() {
		super();
	}
	public FeedUse(int feedid, int recId, String feedname, String purpose,
			String userarea, String usedate, String usertotal, String useman,
			String remark, String crttime) {
		super();
		this.feedid = feedid;
		this.recId = recId;
		this.feedname = feedname;
		this.purpose = purpose;
		this.userarea = userarea;
		this.usedate = usedate;
		this.usertotal = usertotal;
		this.useman = useman;
		this.remark = remark;
		this.crttime = crttime;
	}
	
	public int getFeedid() {
		return feedid;
	}
	public void setFeedid(int feedid) {
		this.feedid = feedid;
	}
	public String getFeedname() {
		return feedname;
	}
	public void setFeedname(String feedname) {
		this.feedname = feedname;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getUserarea() {
		return userarea;
	}
	public void setUserarea(String userarea) {
		this.userarea = userarea;
	}
	public String getUsedate() {
		return usedate;
	}
	public void setUsedate(String usedate) {
		this.usedate = usedate;
	}
	public String getUsertotal() {
		return usertotal;
	}
	public void setUsertotal(String usertotal) {
		this.usertotal = usertotal;
	}
	public String getUseman() {
		return useman;
	}
	public void setUseman(String useman) {
		this.useman = useman;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCrttime() {
		return crttime;
	}
	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}
	
}
