package com.hontek.company.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AgriInventory;

/**
 * 投入品库存表
 * @author lzk
 *
 */
public class AgriInventoryDao extends BaseDao<AgriInventory>{

	public Pager<AgriInventory> findAgriInventoryList(String condition, int page,
			int rows, String sort, String order) {
		condition = condition==null?"":condition;
		
		String hql = "from AgriInventory where 1=1 "+condition;
		List<AgriInventory> list = this.find(hql, page, rows);
		String hql_ct = "select count(*) from AgriInventory where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<AgriInventory> pager = new Pager<AgriInventory>();
		pager.setTotal(total);
		pager.setData(list);
		return pager;
	}

}
