package com.hontek.company.dao;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.Level;

/**
 * <p>Title: 级别表</p>
 * <p>Description: 级别DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class LevelDao extends BaseDao<Level>{

	public int getcount(String condition) {
		String hql = "select count(*) from Level where 1=1 "+condition;
		
		return this.count(hql).intValue();
	}

	public void deleteLevels(String ids) {
		String hql = "delete from Level where levelId in ("+ids+")";
		this.executeHql(hql);
	}

}
