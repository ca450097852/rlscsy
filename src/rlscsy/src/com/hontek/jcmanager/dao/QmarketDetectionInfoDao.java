package com.hontek.jcmanager.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.QmarketDetectionInfo;

public class QmarketDetectionInfoDao extends BaseDao<QmarketDetectionInfo>{

	public Pager<QmarketDetectionInfo> findList(String condition, int page,
			int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from QmarketDetectionInfo where 1=1 "+condition;
		List<QmarketDetectionInfo> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from QmarketDetectionInfo where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<QmarketDetectionInfo> pager = new Pager<QmarketDetectionInfo>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

}
