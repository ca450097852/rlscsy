package com.hontek.review.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbTraceAppdix;
/**
 * 
 * @ClassName: TraceAppdixDao
 * @Description: TODO(附件管理)
 * @date 2014-11-19 下午04:17:28
 * @author qql
 * @version 1.0
 */
public class TraceAppdixDao extends BaseDao<TbTraceAppdix>{

	/**
	 * 根据产品ID查询附件信息
	 * @param proId 产品ID
	 * @param appdixType 附件类型 1产地附件; 2种子种苗附件; 3种植喂养附件; 4防疫附件; 5加工包装附件; 6仓储运输附件; 7产品检测报告附件;
	 * @return
	 */
	public List<TbTraceAppdix> findTraceAppdixsListByProId(int proId,String appdixType){
		String hql = "from TbTraceAppdix where proId="+proId+" and appdixType='"+appdixType+"'";
		List<TbTraceAppdix> list = this.find(hql);
		return list;
	}
	
	
	public Pager<TbTraceAppdix> findAppList(String condition,int page,int rows) {
		String hql = "from TbTraceAppdix where 1=1 "+condition;
		int index = hql.lastIndexOf("order by");
		String hql_ct = "select count(*) " + (index==-1?hql:hql.substring(0,index));
		List<TbTraceAppdix> list = this.find(hql, page, rows);
		int ct = this.count(hql_ct).intValue();
		Pager<TbTraceAppdix> pager = new Pager<TbTraceAppdix>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}
	
	public Pager<TbTraceAppdix> findTraceAppdixList(String condition,int page,int rows) {
		String sql = "select app.p_id as pId,"+
		  "app.pro_id as proId,"+
		  "app.appdix_id as appdixId,"+
		  "app.appdix_name as appdixName,"+
		  "app.appdix_url as appdixUrl,"+
		  "app.upload_time as uploadTime,"+         
		  "app.appdix_type as appdixType "+         
		  "from tb_trace_appdix app where 1=1 ";
		
		sql = sql.concat(condition);
		sql+=" order by app.upload_time desc";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("pId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("appdixId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("appdixName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("appdixUrl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("uploadTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("appdixType", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbTraceAppdix.class));
		
		List<TbTraceAppdix> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		String hql_ct = "select count(*) from tb_trace_appdix app where 1=1";
		hql_ct = hql_ct.concat(condition);
		int ct = this.countBySql(hql_ct).intValue();
		Pager<TbTraceAppdix> pager = new Pager<TbTraceAppdix>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}


	public void deleteByPid(int pId,String appdixType) {
		String hql = "delete from TbTraceAppdix where pid in ("+pId+") and appdixType='"+appdixType+"'";
		this.executeHql(hql);
	}


	public void deleteByPids(String pIds,String appdixType) {
		String hql = "delete from TbTraceAppdix where pid in ("+pIds+") and appdixType='"+appdixType+"'";
		this.executeHql(hql);
	}

	public List<TbTraceAppdix> findTraceAppdixsListByPid(int pId,String appdixType) {
		String hql = "from TbTraceAppdix where pid="+pId+" and appdixType='"+appdixType+"'";
		List<TbTraceAppdix> list = this.find(hql);
		return list;
	}

}
