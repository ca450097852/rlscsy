package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbObjElement;

/**
 * <p>Title:档案要素关系 </p>
 * <p>Description:档案要素关系 Dao  </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ObjElementDao extends BaseDao<TbObjElement>{
	
	
	/**
	* <p>Title: 添加档案要素关系</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public void addObjElement(TbObjElement objElement){
		this.save(objElement);
	}
		
	public Pager<TbObjElement> findObjElementPagerList(String condition, int page, int rows) {
		
		condition = condition==null?"":condition;
		
		String hql = "from TbObjElement where 1=1"+condition;
		List<TbObjElement> list = find(hql, page, rows);
		
		String hql_ct = "select count(*) from TbObjElement where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<TbObjElement> pager = new Pager<TbObjElement>();
		pager.setData(list);
		pager.setTotal(total);
		return pager;
	}
	
	/**
	 * 连接查询
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TbObjElement> findObjElementList(String condition, int page, int rows) {
		
		condition = condition==null?"":condition;
		
		String sql = "select t1.rel_id as relId,t1.element_id as elementId,t1.seq as seq,t1.obj_type_id as objTypeId,t1.obj_ID as objId," +
				"t2.element_icon as elementIcon,t2.element_url as elementUrl,t2.element_name as elementName,t2.table_name as tableName  " +
				"from tb_obj_element t1,tb_element t2 where t1.element_id=t2.element_ID ";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql+condition);

		query.setResultTransformer(Transformers.aliasToBean(TbObjElement.class));
		
		List<TbObjElement> list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
				
		String hql_ct = "select count(*) from tb_obj_element t1,tb_element t2 where t1.element_id=t2.element_ID "+condition;
		
		int total = this.countBySql(hql_ct).intValue();
		
		Pager<TbObjElement> pager = new Pager<TbObjElement>();
		
		pager.setData(list);
		
		pager.setTotal(total);
		
		return pager;
	}
	//判断是否有数据
	public boolean checkData(int entId, String recordType) {
		
		String hql = "select count(*) from TbObjElement where objTypeId="+recordType+" and objId="+entId;
		
		int ct = this.count(hql).intValue();
		
		return ct==0;
	}
}
