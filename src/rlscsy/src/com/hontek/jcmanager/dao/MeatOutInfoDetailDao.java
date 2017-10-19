package com.hontek.jcmanager.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.MeatOutInfoDetail;

public class MeatOutInfoDetailDao extends BaseDao<MeatOutInfoDetail>{

	public Pager<MeatOutInfoDetail> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from MeatOutInfoDetail where 1=1 "+condition;
		List<MeatOutInfoDetail> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from MeatOutInfoDetail where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<MeatOutInfoDetail> pager = new Pager<MeatOutInfoDetail>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

}
