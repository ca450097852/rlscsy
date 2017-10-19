package com.hontek.element.pojo;
/**
 * 肥料投入品使用记录信息表
 * @author lzk
 *
 */
public class FertilizerUse {
	private int fuid;
	private int recId;
	private String funame;
	private String purpose;
	private String userarea;
	private String usedate;
	private String usertotal;
	private String useman;
	private String remark;
	private String crttime;
	
	public FertilizerUse() {
		super();
	}
	public FertilizerUse(int fuid, int recId, String funame, String purpose,
			String userarea, String usedate, String usertotal, String useman,
			String remark, String crttime) {
		super();
		this.fuid = fuid;
		this.recId = recId;
		this.funame = funame;
		this.purpose = purpose;
		this.userarea = userarea;
		this.usedate = usedate;
		this.usertotal = usertotal;
		this.useman = useman;
		this.remark = remark;
		this.crttime = crttime;
	}
	public int getFuid() {
		return fuid;
	}
	public void setFuid(int fuid) {
		this.fuid = fuid;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getFuname() {
		return funame;
	}
	public void setFuname(String funame) {
		this.funame = funame;
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
