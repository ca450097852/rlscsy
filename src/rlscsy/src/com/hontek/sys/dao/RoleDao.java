package com.hontek.sys.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsRole;
/**
 * <p>Title: 角色表</p>
 * <p>Description: 角色Dao 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RoleDao extends BaseDao<TsRole>{

	private String entIds="";
	
	@SuppressWarnings("unchecked")
	public void getEnterpriseIds(Object entId){
		String hql = "select t.entId from TsEnterprise t where t.flag=2 and t.parentId="+entId;
		List<Object> list =  super.findObject(hql);
		if(list!=null&&!list.isEmpty()){
			for (Object object : list) {
				entIds+=object+",";				
				getEnterpriseIds(object);
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Pager<TsRole> findRoleList(Map mapCondition, int page, int rows) {
		
				
		Object obj = mapCondition.get("roleName");
		String conditionstr="";
		if(obj!=null){
			conditionstr+=" and tr.role_name like '%"+obj+"%'";
		}
		obj = mapCondition.get("sts");
		if(obj!=null){
			conditionstr += " and tr.sts="+(Integer)obj;
		}
		
		obj = mapCondition.get("ent_id");
		if(obj!=null){
			Long long1 = Long.valueOf(obj.toString());
			entIds="";
			getEnterpriseIds(long1);			
			conditionstr += " and tr.ent_id in("+entIds+obj+") ";
		}
		
		String sql = "select tr.role_id as \"roleId\",tr.role_name as \"roleName\",tr.role_desc as \"desc\"," +
				"tr.role_level as \"level\",te.name as \"account\",tr.sts as \"sts\" ,tr.ent_id as \"entId\" " +
				"from ts_role tr,TS_ENTERPRISE te where tr.ent_id = te.ent_id "+conditionstr;
				
		String hql_ct = "select count(*) from ts_role tr,TS_ENTERPRISE te where tr.ent_id = te.ent_id "+conditionstr;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("roleId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("roleName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("desc", StandardBasicTypes.STRING);
		sqlQuery.addScalar("level", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("sts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("account", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsRole.class));
		List<TsRole> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		Integer pagecount = this.countBySql(hql_ct);
		
		Pager<TsRole> pager = new Pager<TsRole>();
		pager.setData(list);
		pager.setTotal(pagecount.intValue());
		
		return pager;
	}
	

	public TsRole getRole(int roleId){
		String hql = "from TsRole where roleId="+roleId;
		return (TsRole)super.get(hql);
	}
	
	public void updateRoleSts(String roleIds, String sts) {
		String hql = "update TsRole set sts='"+sts+"' where roleId in ("+roleIds+")";
		this.executeHql(hql);
	}

	public boolean findIsUnique(TsRole role) {
		String hql = "select count(*) from TsRole where roleName='"+role.getAccount()+"' and entId="+role.getEntId();
		Long ct = this.count(hql);
		if(ct.longValue()==0L){
			return false;
		}
		return true;
	}

	public List<TreeVo> getRoleTree(int entId,String roleIds) {
		String sql = "select role_id as \"id\",role_name as \"text\" from ts_role where 1=1 ";
		if(entId!=0&&!"".equals(entId)){
			sql += " and ent_id="+entId;
		}
		if(roleIds!=null&&!"".equals(roleIds)){
			sql += " and role_id not in("+roleIds+")";
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}
	
	/**
	 * 读取角色
	 * @param condition
	 * @return
	 */
	public List<TreeVo> getUserRoleTree(String roleIds) {
		String sql = "select role_id as \"id\",role_name as \"text\" from ts_role where 1=1 ";
		if(roleIds!=null&&!"".equals(roleIds)){
			sql += " and role_id in("+roleIds+")";
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}
}
