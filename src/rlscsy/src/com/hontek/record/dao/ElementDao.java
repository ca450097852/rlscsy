package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbElement;
import com.hontek.sys.pojo.ComboboxData;
import com.hontek.sys.pojo.TreeVo;

/**
 * <p>Title:档案要素 </p>
 * <p>Description:档案要素 Dao  </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ElementDao extends BaseDao<TbElement>{
	
	
	/**
	* <p>Title: 添加档案要素</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public void addElement(TbElement element){
		this.save(element);
	}
	

	/**
	* <p>Title: 档案要素列表</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	@SuppressWarnings("unchecked")
	public Pager<TbElement> findElementList(String condition, int page, int rows) throws AppException {
		String sql="select " +
				"t1.element_id as elementId," +
		        "t1.element_name as elementName," +
		        "t1.table_name as tableName," +
		        "t1.seq as seq,"+
		        "t1.element_icon as elementIcon,"+
		        "t1.element_url as elementUrl,"+
		        "t1.remark as remark  " +
		        "from tb_element t1 " +
				"where 1=1 ";
		sql = sql.concat(condition); 
		sql+=" order by t1.seq ";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("elementId", StandardBasicTypes.LONG);
		query.addScalar("elementName", StandardBasicTypes.STRING);
		query.addScalar("tableName", StandardBasicTypes.STRING);
		query.addScalar("seq", StandardBasicTypes.STRING);
		query.addScalar("elementIcon", StandardBasicTypes.STRING);
		query.addScalar("elementUrl", StandardBasicTypes.STRING);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(TbElement.class));
		String count_sql = "select count(*) from tb_element t1 " +
				"where 1=1 ";
		count_sql = count_sql.concat(condition); 
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TbElement> info = new Pager<TbElement>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}

	/**
	 * 根据档案分类获取档案要素
	 * @param recordType
	 * @return
	 */
	public List<TbElement> findListByrecordType(String recordType) {
		if(recordType!=null&&!"".equals(recordType)){
			String hql = "from TbElement where  elementId in (select elementId from TbElementTemplet where archivesType="+recordType+")";
			return find(hql);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComboboxData> getElementCombobox( ) {
		String sql = "select element_id as id,element_name as text from tb_element where 1=1";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(ComboboxData.class));
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	public List<TreeVo> findElementTree(){
		String sql = "select element_id as \"id\",element_name as \"text\" from tb_element where 1=1 order by seq";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		
		return list;
	}
	
	
	public List<TbElement> findByElementIds(String ids) {
		if(ids!=null&&!"".equals(ids)){
			String hql = "from TbElement where  elementId in ("+ids+")";
			return find(hql);
		}
		return null;
	}
	
}
