package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.AgriInventoryRecordDao;
import com.hontek.company.pojo.AgriInventoryRecord;
import com.hontek.company.service.inter.AgriInventoryRecordServiceInter;
/**
 * 投入品库存表
 * @author lzk
 *
 */
public class AgriInventoryRecordServiceImpl extends BaseServiceImpl implements  AgriInventoryRecordServiceInter{
	
	private AgriInventoryRecordDao agriInventoryRecordDao;


	public void setAgriInventoryRecordDao(
			AgriInventoryRecordDao agriInventoryRecordDao) {
		this.agriInventoryRecordDao = agriInventoryRecordDao;
	}

	public String addAgriInventory(AgriInventoryRecord agriInventory) {
		String jsonMsg = "添加成功";
		try {
			if(agriInventory!=null){
				agriInventory.setAgid(agriInventoryRecordDao.getTableSequence("tb_agri_inventory_record".toUpperCase()));
				agriInventory.setCrttime(DateUtil.formatDateTime());
				agriInventoryRecordDao.save(agriInventory);
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
				String hql = "delete from AgriInventoryRecord where agid in ("+ids+")";
				agriInventoryRecordDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findAgriInventoryList(AgriInventoryRecord agriInventory, int page,
			int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(agriInventory!=null){
			if(agriInventory.getAgname()!=null&&!"".equals(agriInventory.getAgname())){
				condition.append(" and agname like '%"+agriInventory.getAgname()+"%'");
			}
			if(agriInventory.getRecid()>0){
				condition.append(" and recid = "+agriInventory.getRecid()+"");
			}
		}
		Pager<AgriInventoryRecord> pager = agriInventoryRecordDao.findAgriInventoryList(condition.toString(),page,rows,sort,order);
		return this.createEasyUiJson(pager);
	}

	public String updateAgriInvetnroy(AgriInventoryRecord agriInventory) {
		String jsonMsg = "修改成功";
		try {
			if(agriInventory!=null){
				agriInventoryRecordDao.update(agriInventory);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}
	
}
