package com.hontek.review.pojo;

import java.util.List;



/**
 * 产品产地实体类
 */

public class TbProArea implements java.io.Serializable {

	// Fields
	private int areaId;
	private int proId;
	private String areaName;
	private String areaAddr;
	private String areaIntro;
	private String edatope;
	private String areaWater;
	private String climatope;
	private List<TbTraceAppdix> traceAppdixs;
	
	public TbProArea() {
		super();
	}
	public TbProArea(int areaId, int proId, String areaName, String areaAddr,
			String areaIntro, String edatope, String areaWater,
			String climatope) {
		super();
		this.areaId = areaId;
		this.proId = proId;
		this.areaName = areaName;
		this.areaAddr = areaAddr;
		this.areaIntro = areaIntro;
		this.edatope = edatope;
		this.areaWater = areaWater;
		this.climatope = climatope;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaAddr() {
		return areaAddr;
	}
	public void setAreaAddr(String areaAddr) {
		this.areaAddr = areaAddr;
	}
	public String getAreaIntro() {
		return areaIntro;
	}
	public void setAreaIntro(String areaIntro) {
		this.areaIntro = areaIntro;
	}
	public String getEdatope() {
		return edatope;
	}
	public void setEdatope(String edatope) {
		this.edatope = edatope;
	}
	public String getAreaWater() {
		return areaWater;
	}
	public void setAreaWater(String areaWater) {
		this.areaWater = areaWater;
	}
	public String getClimatope() {
		return climatope;
	}
	public void setClimatope(String climatope) {
		this.climatope = climatope;
	}
	public List<TbTraceAppdix> getTraceAppdixs() {
		return traceAppdixs;
	}
	public void setTraceAppdixs(List<TbTraceAppdix> traceAppdixs) {
		this.traceAppdixs = traceAppdixs;
	}
	
}