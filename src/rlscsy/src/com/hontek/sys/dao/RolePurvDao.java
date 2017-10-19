package com.hontek.sys.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TsRolePurv;
/**
 * <p>Title: 权限表</p>
 * <p>Description: 权限serviceImpl 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RolePurvDao extends BaseDao<TsRolePurv>{
	
	
	/**
	 * 读取超管菜单
	 * 
	 * @return
	 */
	public List<TsRolePurv> findAdminCol(int parentId) {
		String hql = "select new com.hontek.sys.pojo.TsRolePurv(col.id,col.colName,col.colUrl,col.iconUrl) from TsSysCol col where _parentId=" + parentId + " order by col.seq";
		List<TsRolePurv> list = find(hql);
		TsRolePurv col = null;
		for (int i = 0; i < list.size(); i++) {
			col = (TsRolePurv) list.get(i);
			List<TsRolePurv> child = findAdminCol(col.getColId());
			col.setClildrenList(child);
		}
		return list;
	}
	
	/**
	 * 获取用户所拥有的栏目
	 * 
	 * @param roleId
	 * @return
	 */
	public List<TsRolePurv> findRolePurvByRoleId(int parentId, int roleId) {
		String hql = "from TsRolePurv where parentId=" + parentId + " and roleId=" + roleId + " order by seq";
		List<TsRolePurv> list = find(hql);
		TsRolePurv col = null;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			col = (TsRolePurv) list.get(i);
			List<TsRolePurv> child = findRolePurvByRoleId(col.getColId(), roleId);
			col.setClildrenList(child);
		}
		return list;
	}
	
	
	//清空已有记录
	public void deleteRolePurv(int roleId){
		//TsRolePurv		
		String hql_del = "delete from TsRolePurv where roleId="+roleId;
		this.executeHql(hql_del);
	}
	
	/**
	 * 查询角色的所有权限
	 * @param roleId
	 * @return
	 */
	public List<TsRolePurv> findRolePurvList(int roleId){
		//TsRolePurv		
		String hql = "from TsRolePurv where roleId="+roleId;
		return find(hql);
	}
	

}
