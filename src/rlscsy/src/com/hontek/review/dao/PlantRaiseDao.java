package com.hontek.review.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbPlantRaise;
/**
 * @ClassName: PlantRaiseDao
 * @Description: TODO(施肥喂养表)
 * @date 2014-11-19 下午05:10:05
 * @author qql
 * @version 1.0
 */
public class PlantRaiseDao extends BaseDao<TbPlantRaise>{
	
	public void deletePlantRaiseByIds(String ids) {
		String hql = "delete from TbPlantRaise where prId in ("+ids+")";
		this.executeHql(hql);
	}

	public Pager<TbPlantRaise> findList(String condition,int page,int rows) {
		String hql = "from TbPlantRaise where 1=1 "+condition;
		int index = hql.lastIndexOf("order by");
		String hql_ct = "select count(*) " + (index==-1?hql:hql.substring(0,index));
		List<TbPlantRaise> list = this.find(hql, page, rows);
		int ct = this.count(hql_ct).intValue();
		Pager<TbPlantRaise> pager = new Pager<TbPlantRaise>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}
	@SuppressWarnings("unchecked")
	public Pager<TbPlantRaise> findPlantRaiseList(String condition,int page,int rows) {
		String sql = "select pr.pr_id as prId,"+
		  "pr.pro_id as proId,"+
		  "pr.feed_name as feedName,"+
		  "pr.feed_company as feedCompany,"+
		  "pr.feed_way as feedWay,"+
		  "pr.feed_cycle as feedCycle,"+         
		  "pr.feed_time as feedTime,"+
		  "pr.dosage as dosage "+         
		  "from tb_plant_raise pr where 1=1 ";
		
		sql = sql.concat(condition);
		sql+=" order by pr.feed_time asc";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("prId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("feedName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("feedCompany", StandardBasicTypes.STRING);
		sqlQuery.addScalar("feedWay", StandardBasicTypes.STRING);
		sqlQuery.addScalar("feedCycle", StandardBasicTypes.STRING);
		sqlQuery.addScalar("feedTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("dosage", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbPlantRaise.class));
		
		List<TbPlantRaise> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		String hql_ct = "select count(*) from tb_plant_raise pr where 1=1";
		hql_ct = hql_ct.concat(condition);
		int ct = this.countBySql(hql_ct).intValue();
		Pager<TbPlantRaise> pager = new Pager<TbPlantRaise>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}

}
