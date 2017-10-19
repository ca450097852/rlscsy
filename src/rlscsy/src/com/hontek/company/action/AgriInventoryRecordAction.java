package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.pojo.AgriInventoryRecord;
import com.hontek.company.service.inter.AgriInventoryRecordServiceInter;
import com.hontek.company.service.inter.AgriInventoryServiceInter;
/**
 * 投入品库存表
 * @author lzk
 *
 */
public class AgriInventoryRecordAction extends BaseAction{

	private static final long serialVersionUID = 1763372787142057078L;
	
	private AgriInventoryRecordServiceInter agriInventoryRecordServiceInter;
	
	private AgriInventoryRecord agriInventoryRecord;
	
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	public void setAgriInventoryRecordServiceInter(
			AgriInventoryRecordServiceInter agriInventoryRecordServiceInter) {
		this.agriInventoryRecordServiceInter = agriInventoryRecordServiceInter;
	}

	
	public AgriInventoryRecord getAgriInventoryRecord() {
		return agriInventoryRecord;
	}

	public void setAgriInventoryRecord(AgriInventoryRecord agriInventoryRecord) {
		this.agriInventoryRecord = agriInventoryRecord;
	}

	public void findAgriInventoryList(){
		jsonMsg = agriInventoryRecordServiceInter.findAgriInventoryList(agriInventoryRecord, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	public void addAgriInventory(){
		jsonMsg = agriInventoryRecordServiceInter.addAgriInventory(agriInventoryRecord);
		printJsonString(jsonMsg);
	}
	
	public void updateAgriInventory(){
		jsonMsg = agriInventoryRecordServiceInter.updateAgriInvetnroy(agriInventoryRecord);
		printJsonString(jsonMsg);
	}
	
	public void deleteAgriInventory(){
		jsonMsg = agriInventoryRecordServiceInter.deleteAgriInventory(ids);
		printJsonString(jsonMsg);
	}
	
}
