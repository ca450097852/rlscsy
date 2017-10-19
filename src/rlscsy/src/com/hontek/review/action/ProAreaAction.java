package com.hontek.review.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProArea;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProAreaServiceInter;
/**
 * <p>Title: 产地信息</p>
 * <p>Description: 产地信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProAreaAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProAreaServiceInter proAreaServiceInter;
	private TbProArea proArea;
	private String ids;
	private List<TbTraceAppdix> traceAppList;
	
	public List<TbTraceAppdix> getTraceAppList() {
		return traceAppList;
	}

	public void setTraceAppList(List<TbTraceAppdix> traceAppList) {
		this.traceAppList = traceAppList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbProArea getProArea() {
		return proArea;
	}

	public void setProArea(TbProArea trace) {
		this.proArea = trace;
	}


	public void setProAreaServiceInter(ProAreaServiceInter proAreaServiceInter) {
		this.proAreaServiceInter = proAreaServiceInter;
	}

	/**
	 * 查找产地信息
	 */
	public void findProAreaList(){
		jsonMsg = proAreaServiceInter.findProAreaList(proArea, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加产地
	 */
	public void addProArea(){
		jsonMsg = proAreaServiceInter.addProArea(proArea);
		this.printJsonString(jsonMsg);
	}
	
	public void addProAreaAndAppend(){
		jsonMsg = proAreaServiceInter.addProArea(proArea,traceAppList);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 修改产地
	 */
	public void updateProArea(){
		jsonMsg = proAreaServiceInter.updateProArea(proArea);
		this.printJsonString(jsonMsg);
	}
	
	public void updateProAreaAndApp(){
		jsonMsg = proAreaServiceInter.updateProAreaAndApp(proArea,traceAppList);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 删除产地
	 */
	public void deleteProArea(){
		jsonMsg = proAreaServiceInter.deleteProArea(ids);
		printJsonString(jsonMsg);
	}
}
