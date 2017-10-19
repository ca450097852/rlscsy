package com.hontek.review.pojo;

import java.util.List;

/**
 * <p>Title: 检验报告表</p>
 * <p>Description: 检验报告PO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TbProCheck {
	private int checkId;
	private String checkName;	//报告名称
	private String checkNum;	//报告编号
	private String checkUnit;	//检验单位
	private String checkTime;	//检验时间
	private String checkResult;	//检验结果
	private int proId;			//产品ID
	
	private int entId;
	private String checkType;//1生产档案检测报告；2企业自检报告；
	
	
	private List<TbTraceAppdix> traceAppdixs;
	

	public TbProCheck() {
		super();
	}
	public TbProCheck(int checkId, String checkName, String checkNum,
			String checkUnit, String checkTime, String checkResult, int proId) {
		super();
		this.checkId = checkId;
		this.checkName = checkName;
		this.checkNum = checkNum;
		this.checkUnit = checkUnit;
		this.checkTime = checkTime;
		this.checkResult = checkResult;
		this.proId = proId;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getCheckUnit() {
		return checkUnit;
	}
	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}
	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	
}
