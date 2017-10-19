package com.hontek.info.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.info.pojo.TInfoType;
import com.hontek.sys.pojo.TreeVo;



/**
 * 资讯分类 DAO
 * @author qql
 *
 */

public class InfoTypeDao extends BaseDao<TInfoType>{
	public List<TInfoType> getInfoTypeToSelect() throws AppException {
		List<TInfoType> list = new ArrayList<TInfoType>();
		String hql="from TInfoType";
		list  = this.find(hql);
		return list;
	}
	
	public List getInfoTypeTree(int id) {
		String sql = "select TYPE_ID as id,type_name as text from TB_INFOTYPE where parent_id="+id;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			vo.setChildren(this.getInfoTypeTree(vo.getId()));
		}
		return list;
	}
	
	public TInfoType getTypeByFaId(int id) {
		String hql = "from TInfoType";
		if(!"".equals(id)){
			hql += "  where parentId="+id;
		}
		List list = this.find(hql);
		TInfoType tinfoType =null;
		if(list.size()>0){
			tinfoType = (TInfoType) list.get(0);
		}
		return tinfoType;
	}
	
	
	@SuppressWarnings("unchecked")
	public Pager<TInfoType> findInfoTypeList(String condition, int page, int rows) throws AppException {
		
		String sql="select t1.TYPE_ID as typeId," +
				          "t1.TYPE_NAME as typeName," +
				          "t1.CRTTIME as crttime,"+
				          "t1.USER_ID as userId,"+
				          "t1.REMARK as remark, " +
				          "t2.TYPE_ID as parentId, " +
				          "t2.TYPE_NAME as parName," +
				          "tu.NICKNAME as nickName " +
				          "from tb_infotype t1 left join tb_infotype t2 on t1.parent_id =t2.type_id " +
				          "left join ts_user tu on t1.user_id=tu.user_id where 1=1 ";
		sql = sql.concat(condition); 
		sql+=" order by t1.CRTTIME asc";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("typeId", StandardBasicTypes.LONG);
		query.addScalar("typeName", StandardBasicTypes.STRING);
		query.addScalar("crttime", StandardBasicTypes.STRING);
		query.addScalar("userId", StandardBasicTypes.STRING);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("parentId", StandardBasicTypes.LONG);
		query.addScalar("parName", StandardBasicTypes.STRING);
		query.addScalar("nickName", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(TInfoType.class));
		String count_sql = "select count(*) from tb_infotype t1 left join tb_infotype t2 on t1.parent_id =t2.type_id " +
				"left join ts_user tu on t1.user_id=tu.user_id where 1=1";
		count_sql = count_sql.concat(condition); 
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TInfoType> info = new Pager<TInfoType>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}


	public int deleteTinfo(TInfoType tinfoType) throws AppException {
		Long id = tinfoType.getTypeId();
		String hql = "from TInfoType";
		if(!"".equals(id)){
			hql += "  where parentId="+id;
		}
		List list = this.find(hql);
		
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				TInfoType tinfoType1 =null;
				tinfoType1=(TInfoType) list.get(i);
				if (tinfoType1!= null) {
					this.getCurrentSession().delete(tinfoType1);
				}
			}
		}
		if (tinfoType != null) {
			this.getCurrentSession().delete(tinfoType);
		}
		return list.size();
	}

}
