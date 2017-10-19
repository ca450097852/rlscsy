package com.hontek.company.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AgriInventory;
import com.hontek.company.pojo.AgriInventoryRecord;

/**
 * 投入品出库记录表
 * @author lzk
 *
 */
public class AgriInventoryRecordDao extends BaseDao<AgriInventoryRecord>{

	public Pager<AgriInventoryRecord> findAgriInventoryList(String condition, int page,int rows, String sort, String order) {
		condition = condition==null?"":condition;
		
		String hql = "from AgriInventoryRecord where 1=1 "+condition;
		List<AgriInventoryRecord> list = this.find(hql, page, rows);
		String hql_ct = "select count(*) from AgriInventoryRecord where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<AgriInventoryRecord> pager = new Pager<AgriInventoryRecord>();
		pager.setTotal(total);
		pager.setData(list);
		return pager;
	}

}
