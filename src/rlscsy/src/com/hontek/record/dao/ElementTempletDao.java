package com.hontek.record.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbElementTemplet;

/**
 * <p>Title:对象档案参照 </p>
 * <p>Description:对象档案参照 Dao  </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ElementTempletDao extends BaseDao<TbElementTemplet>{
	
	
	/**
	* <p>Title: 添加对象档案参照</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public void addElementTemplet(TbElementTemplet templet){
		this.save(templet);
	}
	

	/**
	* <p>Title: 对象档案参照列表</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	@SuppressWarnings("unchecked")
	public Pager<TbElementTemplet> findElementTempletList(String condition, int page, int rows) throws AppException {
		String sql="select " +
				"t1.temp_id as tempId," +
				"t1.archives_type as archivesType," +
				"t1.element_id as elementId," +
				"t2.element_name as elementName " +
		        "from tb_element_templet t1 ,tb_element t2 " +
				"where 1=1 and t1.element_id = t2.element_id ";
		sql = sql.concat(condition); 
		String orderby = " order by t1.archives_type,t2.seq asc ";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql+orderby);
		query.addScalar("tempId", StandardBasicTypes.INTEGER);
		query.addScalar("archivesType", StandardBasicTypes.INTEGER);
		query.addScalar("elementId", StandardBasicTypes.INTEGER);
		query.addScalar("elementName", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(TbElementTemplet.class));
		String count_sql = "select count(*) from tb_element_templet t1 ,tb_element t2  where 1=1 and t1.element_id = t2.element_id ";
		count_sql = count_sql.concat(condition); 
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TbElementTemplet> info = new Pager<TbElementTemplet>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}
	
	/**
	 * @Title: findElementTempletList
	 * @Description: TODO(按类型查询：参照要素)
	 * @param @param archivesType
	 * @param @return 
	 * @return List<TbElementTemplet>    返回类型
	 * @throws
	 */
	public List<TbElementTemplet> findElementTempletList(String archivesType ){
		String hql = " from TbElementTemplet where archivesType = "+archivesType;
		 List<TbElementTemplet>  list = null;
		if(archivesType!=null&&!"".equals(archivesType)){
			list = this.find(hql);
		}
		return list;
	}
	
	//清空已有记录
	public void deleteByArchivesType(String archivesType){
		//TsRolePurv		
		String hql_del = "delete from TbElementTemplet where archivesType="+archivesType;
		this.executeHql(hql_del);
	}
	
	//清空全部记录
	public void deleteAll( ){
		//TsRolePurv		
		String hql_del = "delete from TbElementTemplet ";
		this.executeHql(hql_del);
	}
	
	/**
	 * 重置table的序列
	 * @param tableName
	 * @return
	 */
	public void setTableSequence(){
		try {
			String sql = "update t_sequence set current_value=2 where table_name = 'tb_element_templet'";		
			
			//更新
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);	
			sqlQuery = getCurrentSession().createSQLQuery(sql);
			sqlQuery.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}
	
}
