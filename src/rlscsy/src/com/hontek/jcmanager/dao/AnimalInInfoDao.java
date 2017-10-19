package com.hontek.jcmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.AnimalInInfo;
/**
 * 生猪进厂表
 * @author Administrator
 *
 */
public class AnimalInInfoDao extends BaseDao<AnimalInInfo>{

	public Pager<AnimalInInfo> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from AnimalInInfo where 1=1 "+condition;
		List<AnimalInInfo> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from AnimalInInfo where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<AnimalInInfo> pager = new Pager<AnimalInInfo>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}
	@SuppressWarnings("unchecked")
	/**
	 * 屠宰量查询
	 * @param condtions
	 * @return
	 */
	public List<Map<Object, Object>> getAnimalInList(String condtions){
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();
		String sql="select distinct in_date,sum(cast(quarantine_num as int)) from tb_animal_in_info tb where 1=1 ";
		if(condtions!=null&&!"".equals(condtions)){
			sql+=condtions;
		}
		sql+=" group by in_date having 1=1";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<Object[]> list = sqlQuery.list();
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	}
	/**
	 * 屠宰次数查询
	 * @param condtions
	 * @return
	 */
	public Object getAnimalInCount(String condtions){
		String sql = "SELECT sum(cast(quarantine_num as int)) FROM tb_animal_in_info tb where 1=1";
		if(condtions!=null&&!"".equals(condtions)){
			sql +=condtions;
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		Object obj = sqlQuery.uniqueResult();

		return obj;
		
	}

}
