package com.hontek.jcmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MeatOutInfoBase;

public class MeatOutInfoBaseDao extends BaseDao<MeatOutInfoBase>{

	public Pager<MeatOutInfoBase> findList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		
		String hql = "from MeatOutInfoBase where 1=1 "+condition;
		List<MeatOutInfoBase> list = this.find(hql, page, rows);
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from MeatOutInfoBase where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		
		Pager<MeatOutInfoBase> pager = new Pager<MeatOutInfoBase>();
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}
	/**
	 * 销售量分析
	 * @param condtions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<Object, Object>> getMarketList(String condtions){
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();
		String sql = "SELECT distinct tran_date ,sum(cast(de.weight as int)) " +
				"FROM tb_meat_out_info_base ba INNER JOIN tb_meat_out_info_detail de on ba.moib_id=de.moib_id where 1=1 ";
		if(condtions!=null&&!"".equals(condtions)){
			sql+=condtions;
		}
		sql+=" group by tran_date";	
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
	 * 销售总量
	 * @param condtions
	 * @return
	 */
	public Object getMarketCount(String condtions){
		String sql="SELECT sum(cast(de.weight as int)) FROM tb_meat_out_info_base ba INNER JOIN tb_meat_out_info_detail de on ba.moib_id=de.moib_id where 1=1";
		if(condtions!=null&&!"".equals(condtions)){
			sql +=condtions;
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		Object obj = sqlQuery.uniqueResult();

		return obj;
		
	}
}
