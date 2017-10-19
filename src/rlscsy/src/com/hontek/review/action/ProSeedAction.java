package com.hontek.review.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProSeedServiceInter;
/**
 * <p>Title: 种源信息</p>
 * <p>Description: 种源信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProSeedAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProSeedServiceInter proSeedServiceInter;
	private TbProSeed proSeed;
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

	public TbProSeed getProSeed() {
		return proSeed;
	}

	public void setProSeed(TbProSeed trace) {
		this.proSeed = trace;
	}
	public void setProSeedServiceInter(ProSeedServiceInter traceServiceInter) {
		this.proSeedServiceInter = traceServiceInter;
	}
	/**
	 * 查找种源信息
	 */
	public void findProSeedList(){
		jsonMsg = proSeedServiceInter.findProSeedList(proSeed, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加种源
	 */
	public void addProSeed(){
		jsonMsg = proSeedServiceInter.addProSeed(proSeed);
		this.printJsonString(jsonMsg);
	}
	
	public void addProSeedAndAppdix(){
		jsonMsg = proSeedServiceInter.addProSeed(proSeed,traceAppList);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改种源
	 */
	public void updateProSeed(){
		jsonMsg = proSeedServiceInter.updateProSeed(proSeed);
		this.printJsonString(jsonMsg);
	}
	
	public void updateProSeedAndAppdix(){
		jsonMsg = proSeedServiceInter.updateProSeed(proSeed,traceAppList);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 删除种源
	 */
	public void deleteProSeed(){
		jsonMsg = proSeedServiceInter.deleteProSeed(ids);
		printJsonString(jsonMsg);
	}
}
