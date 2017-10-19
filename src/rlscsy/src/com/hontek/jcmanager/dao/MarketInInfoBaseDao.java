package com.hontek.jcmanager.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoBase;

public class MarketInInfoBaseDao extends BaseDao<MarketInInfoBase>{

	public Pager<MarketInInfoBase> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from MarketInInfoBase where 1=1 "+condition;
		List<MarketInInfoBase> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from MarketInInfoBase where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<MarketInInfoBase> pager = new Pager<MarketInInfoBase>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

}
