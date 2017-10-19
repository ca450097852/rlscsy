package com.hontek.sys.dao;

import java.util.List;
import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TsRolePurvDef;
/**
 * <p>Title: 默认权限表</p>
 * <p>Description: 默认权限Dao 实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RolePurvDefDao extends BaseDao<TsRolePurvDef> {

	/**
	 * 读取菜单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TsRolePurvDef> findCol(int parentId) {
		String hql = "select new com.hontek.sys.pojo.TsRolePurvDef(col.id,col.colName,col.colUrl) from TsSysCol col where _parentId=" + parentId + " order by col.seq";
		List<TsRolePurvDef> list = find(hql);
		TsRolePurvDef col = null;
		for (int i = 0; i < list.size(); i++) {
			col = (TsRolePurvDef) list.get(i);
			List child = findCol(col.getColId());
			col.setClildrenList(child);
		}
		return list;
	}
	
	/**
	 * 根据类型读取默认权限
	 * @param typeId 0为默认管理员，1为默认普通角色
	 * @return
	 */
	public List<TsRolePurvDef> getPowerByTypeId(int typeId) {
		String hql = "from TsRolePurvDef where typeId="+typeId;
		List<TsRolePurvDef> list = find(hql);
		return list;
	}

	/**
	 * 删除默认权限
	 * @param typeId
	 */
	public void deleteRolePurvDef(int typeId){
		String hql_del = "delete from TsRolePurvDef where typeId="+typeId;
		this.executeHql(hql_del);
	}
}
