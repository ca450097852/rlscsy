package com.hontek.sys.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TbSysconfig;

public class SysconfigDao extends BaseDao<TbSysconfig>{


	public TbSysconfig getSysconfig() {
		
		TbSysconfig sysconfig = null;
		String hql = "from TbSysconfig order by updatetime desc ";
		List<TbSysconfig> list = this.find(hql);
		
		if(list!=null&&list.size()>0){
			sysconfig = list.get(0);
		}
		return sysconfig;
	}
	

	public void update(TbSysconfig col) {
//		String hql = "delete TbSysconfig ";
//		this.executeHql(hql);
		this.saveOrUpdate(col);
	}

}
