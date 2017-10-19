package com.hontek.jcmanager.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoDetail;

public class MarketInInfoDetailDao extends BaseDao<MarketInInfoDetail>{

	public Pager<MarketInInfoDetail> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from MarketInInfoDetail where 1=1 "+condition;
		List<MarketInInfoDetail> data = this.find(hql,page,rows);
		String hql_ct = "select count(*) from MarketInInfoDetail where 1=1 "+condition;
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		int total = this.count(hql_ct).intValue();
		
		Pager<MarketInInfoDetail> pager = new Pager<MarketInInfoDetail>();
		pager.setData(data);
		pager.setTotal(total);
		
		return pager;
	}

}
