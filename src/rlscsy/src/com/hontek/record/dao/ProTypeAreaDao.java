package com.hontek.record.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.record.pojo.TbProTypeArea;
/**
 * 分类产地信息DAO
 *
 */
public class ProTypeAreaDao extends BaseDao<TbProTypeArea>{

	public List<TbProTypeArea> findProTypeAreaList(int entId){
		String hql = "from TbProTypeArea WHERE entId ="+entId;
		return find(hql);
	}
}
