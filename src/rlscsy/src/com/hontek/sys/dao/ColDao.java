package com.hontek.sys.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsSysCol;

public class ColDao extends BaseDao<TsSysCol>{

	public Pager<TsSysCol> findColList(String condition) {
				
		condition += " order by seq";
		
		String hql = "from TsSysCol "+condition;
		
		List<TsSysCol> list = find(hql);
		
		String hql_ct = "select count(*) from TsSysCol";
		Long pagecount = count(hql_ct);
		
		Pager<TsSysCol> pager = new Pager<TsSysCol>();
		pager.setData(list);
		pager.setTotal(pagecount.intValue());
		
		return pager;
	}
	
	
	
	public void addCol(TsSysCol col) {
		this.save(col);
	}

	public void updateCol(TsSysCol col) {
		this.update(col);
		//更新关系表
		String hql = "update TsRolePurv set parentId="+col.get_parentId()+" , colName='"+col.getColName()+"' , " +
				"colUrl='"+col.getColUrl()+"' , colLevel="+col.getColLevel()+" , seq="+col.getSeq()+" , iconUrl=" +(col.getIconUrl()==null?null:"'"+col.getIconUrl()+"'")+
						" where colId="+col.getId();
		this.executeHql(hql);
	}

	public void deleteCol(String ids) {
		String hql = "delete from TsSysCol where id in ("+ids+")";
		executeHql(hql);
		//更新关系表
		String hql_r = "delete from TsRolePurv where colId in ("+ids+")";
		executeHql(hql_r);
	}

	@SuppressWarnings("unchecked")
	public List getSysColTree(int col_id) {
		String sql = "select col_id as \"id\",col_name as \"text\" from TS_SYS_COL where parent_id="+col_id+" order by seq";

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			vo.setChildren(this.getSysColTree(vo.getId()));
		}
		return list;
	}

	public List<TsSysCol> findFirstColList() {
		String hql = "from TsSysCol where _parentId=0";
		List<TsSysCol> list = find(hql);
		return list;
	}

	/**
	 * 根据栏目Id查询
	 * @param ids
	 * @return
	 */
	public List<TsSysCol> findColListByIds(String ids){
		String hql_col = "from TsSysCol where id in("+ids+")";
		List<TsSysCol> col_list = find(hql_col);
		return col_list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TreeVo> findColTree(int id){
		String sql = "select col_id as \"id\",col_name as \"text\",col_url as \"attributes\" from ts_sys_col where parent_id="+id+" order by seq";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.addScalar("attributes", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			vo.setChildren(this.findColTree(vo.getId()));
		}
		return list;
	}
	
	public List<TreeVo> findEntColTree(int l, int roleId) {
		String sql = "select col_id as \"id\",col_name as \"text\",col_url as \"attributes\" from ts_role_purv where parent_id="+l+" and role_id="+roleId+" order by seq";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.addScalar("attributes", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			vo.setChildren(this.findEntColTree(vo.getId(),roleId));
		}
		return list;
	}
}
