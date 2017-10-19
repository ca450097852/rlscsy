package com.hontek.company.dao;


import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AssessItem;

/**
 * 考核项目表
 * @author chenan
 *
 */
public class AssessItemDao extends BaseDao<AssessItem>{
	public Pager<AssessItem> findAssessItemList(String condition, int page,int rows, String sort, String order) {
		condition = condition==null?"":condition;
		String hql = "from AssessItem where 1=1 "+condition;
		List<AssessItem> list = this.find(hql, page, rows);
		String hql_ct = "select count(*) from AssessItem where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<AssessItem> pager = new Pager<AssessItem>();
		pager.setTotal(total);
		pager.setData(list);
		return pager;
	}
	
	public Pager<AssessItem> findAssessItemListByNodeType(String nodeType, int page,int rows, String sort, String order) {
		nodeType = nodeType==null?"":nodeType;
		String hql = "from AssessItem where 1=1 and nodeType='"+nodeType+"'";
		List<AssessItem> list = this.find(hql, page, rows);
		String hql_ct = "select count(*) from AssessItem where 1=1 and nodeType='"+nodeType+"'";
		int total = this.count(hql_ct).intValue();
		Pager<AssessItem> pager = new Pager<AssessItem>();
		pager.setTotal(total);
		pager.setData(list);
		return pager;
	}
}
