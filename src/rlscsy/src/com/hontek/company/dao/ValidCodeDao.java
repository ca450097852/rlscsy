package com.hontek.company.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.ValidCode;

/**
 * 二维码验证表
 * @author IMZK
 *
 */
public class ValidCodeDao extends BaseDao<ValidCode>{

	public int countByCode(String dimenno) {
		String hql = "select count(*) from ValidCode where dimenno = '"+dimenno+"'";
		return this.count(hql).intValue();
	}

	public Set<String> getCodesByDimenno(String dimenno) {
		String sql = "select validCode from tb_valid_code where dimenno = '"+dimenno+"'";
		List list = this.findObjectListBySql(sql);
		
		Set<String> setCodes = new HashSet<String>();
		for(Object obj:list){
			setCodes.add(String.valueOf(obj));
		}
		
		return setCodes;
	}

}
