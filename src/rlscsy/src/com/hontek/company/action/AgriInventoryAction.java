package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.service.inter.AgriInventoryServiceInter;
/**
 * 投入品库存表
 * @author lzk
 *
 */
public class AgriInventoryAction extends BaseAction{

	private static final long serialVersionUID = 1763372787142057078L;
	
	private AgriInventoryServiceInter agriInventoryServiceInter;
	
	private AgriInventory agriInventory;
	
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setAgriInventoryServiceInter(
			AgriInventoryServiceInter agriInventoryServiceInter) {
		this.agriInventoryServiceInter = agriInventoryServiceInter;
	}

	public AgriInventory getAgriInventory() {
		return agriInventory;
	}

	public void setAgriInventory(AgriInventory agriInventory) {
		this.agriInventory = agriInventory;
	}
	
	public void findAgriInventoryList(){
		jsonMsg = agriInventoryServiceInter.findAgriInventoryList(agriInventory, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	public void addAgriInventory(){
		jsonMsg = agriInventoryServiceInter.addAgriInventory(agriInventory);
		printJsonString(jsonMsg);
	}
	
	public void updateAgriInventory(){
		jsonMsg = agriInventoryServiceInter.updateAgriInvetnroy(agriInventory);
		printJsonString(jsonMsg);
	}
	
	public void deleteAgriInventory(){
		jsonMsg = agriInventoryServiceInter.deleteAgriInventory(ids);
		printJsonString(jsonMsg);
	}
	
}
