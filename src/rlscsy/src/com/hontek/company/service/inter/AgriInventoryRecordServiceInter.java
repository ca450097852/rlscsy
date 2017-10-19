package com.hontek.company.service.inter;

import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.pojo.AgriInventoryRecord;

/**
 * 投入品库存表
 * @author lzk
 *
 */
public interface AgriInventoryRecordServiceInter {

	String findAgriInventoryList(AgriInventoryRecord agriInventoryRecord, int page,
			int rows, String sort, String order);

	String addAgriInventory(AgriInventoryRecord agriInventoryRecord);

	String updateAgriInvetnroy(AgriInventoryRecord agriInventoryRecord);

	String deleteAgriInventory(String ids);

}
