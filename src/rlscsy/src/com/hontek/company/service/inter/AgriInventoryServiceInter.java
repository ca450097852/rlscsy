package com.hontek.company.service.inter;

import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.pojo.AgriInventoryRecord;

/**
 * 投入品库存表
 * @author lzk
 *
 */
public interface AgriInventoryServiceInter {

	
	String findAgriInventoryList(AgriInventory agriInventory, int page,
			int rows, String sort, String order);

	String addAgriInventory(AgriInventory agriInventory);

	String updateAgriInvetnroy(AgriInventory agriInventory);

	String deleteAgriInventory(String ids);
}
