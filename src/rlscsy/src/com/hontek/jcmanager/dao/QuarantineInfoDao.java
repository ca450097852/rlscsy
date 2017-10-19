package com.hontek.jcmanager.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.QuarantineInfo;
/**
 * 屠宰厂检疫检验信息表
 * @author Administrator
 *
 */
public class QuarantineInfoDao extends BaseDao<QuarantineInfo>{

	public Pager<QuarantineInfo> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from QuarantineInfo where 1=1 "+condition;
		List<QuarantineInfo> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from QuarantineInfo where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<QuarantineInfo> pager = new Pager<QuarantineInfo>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

}
