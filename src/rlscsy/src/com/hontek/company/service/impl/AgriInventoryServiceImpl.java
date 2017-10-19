package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.AgriInventoryDao;
import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.pojo.AgriInventoryRecord;
import com.hontek.company.service.inter.AgriInventoryServiceInter;
/**
 * 投入品库存表
 * @author lzk
 *
 */
public class AgriInventoryServiceImpl extends BaseServiceImpl implements  AgriInventoryServiceInter{
	
	private AgriInventoryDao agriInventoryDao;

	public void setAgriInventoryDao(AgriInventoryDao agriInventoryDao) {
		this.agriInventoryDao = agriInventoryDao;
	}

	public String addAgriInventory(AgriInventory agriInventory) {
		String jsonMsg = "添加成功";
		try {
			if(agriInventory!=null){
				agriInventory.setAgid(agriInventoryDao.getTableSequence("tb_agri_inventory".toUpperCase()));
				agriInventory.setCrttime(DateUtil.formatDateTime());
				agriInventoryDao.save(agriInventory);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteAgriInventory(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from AgriInventory where agid in ("+ids+")";
				agriInventoryDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findAgriInventoryList(AgriInventory agriInventory, int page,
			int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(agriInventory!=null){
			if(agriInventory.getAgname()!=null&&!"".equals(agriInventory.getAgname())){
				condition.append(" and agname like '%"+agriInventory.getAgname()+"%'");
			}
		}
		Pager<AgriInventory> pager = agriInventoryDao.findAgriInventoryList(condition.toString(),page,rows,sort,order);
		return this.createEasyUiJson(pager);
	}

	public String updateAgriInvetnroy(AgriInventory agriInventory) {
		String jsonMsg = "修改成功";
		try {
			if(agriInventory!=null){
				agriInventoryDao.update(agriInventory);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}
}
