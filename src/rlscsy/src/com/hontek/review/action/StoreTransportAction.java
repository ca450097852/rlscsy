package com.hontek.review.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbStoreTransport;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.StoreTransportServiceInter;

public class StoreTransportAction extends BaseAction{
	private StoreTransportServiceInter storeTransportService;
	private List<TbTraceAppdix> traceAppList;

	private TbStoreTransport storeTransport;
	private String ids;
	
	public TbStoreTransport getStoreTransport() {
		return storeTransport;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setStoreTransport(TbStoreTransport storeTransport) {
		this.storeTransport = storeTransport;
	}

	public List<TbTraceAppdix> getTraceAppList() {
		return traceAppList;
	}

	public void setTraceAppList(List<TbTraceAppdix> traceAppList) {
		this.traceAppList = traceAppList;
	}

	public void setStoreTransportService(
			StoreTransportServiceInter storeTransportService) {
		this.storeTransportService = storeTransportService;
	}
	/**
	 * 查询
	 */
	public void findStoreTransportList(){
		printJsonString(this.storeTransportService.findStoreTransportList(storeTransport,page,rows,sort,order));
	}
	
	/**
	 * 添加
	 */
	public void addStoreTransport(){
/*		storeTransport = new TbStoreTransport();
		storeTransport.setStorageWay("仓储方式");
		storeTransport.setStorageCondi("仓储条件");
		storeTransport.setTransportWay("运输方式");
		storeTransport.setProId(2143);*/
		printJsonString(storeTransportService.addStoreTransport(storeTransport,traceAppList));
	}
	/**
	 * 删除
	 */
	public void deleteStoreTransportByIds(){
		printJsonString(storeTransportService.deleteStoreTransportByIds(ids));
	}
	/**
	 * 修改 
	 */
	public void updateStoreTransport(){
		printJsonString(storeTransportService.updateStoreTransport(storeTransport,traceAppList));
	}
	
}
