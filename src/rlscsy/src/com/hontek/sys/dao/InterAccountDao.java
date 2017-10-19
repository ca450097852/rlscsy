package com.hontek.sys.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TbInterAccount;

/**
 * <p>Title: 接入系统账号表</p>
 * <p>Description: 接入系统账号表DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class InterAccountDao extends BaseDao<TbInterAccount>{

	/**
	 * 查询系统标识是否已经使用
	 * @param sysCode
	 * @return true 未使用
	 */
	public boolean checkSysCode(String sysCode){
		String hql = "from TbInterAccount where sysCode = '"+sysCode+"'";
		List<TbInterAccount> list = super.find(hql);
		return list.isEmpty();
	}

	public TbInterAccount getInterAccount(String condition) {
		if(condition==null||"".equals(condition)){
			return null;
		}
		String hql = "from TbInterAccount where 1=1 "+condition;
		return get(hql);
	}
}
