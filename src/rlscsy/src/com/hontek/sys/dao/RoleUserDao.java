package com.hontek.sys.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TsRoleUser;
/**
 * 用户跟角色对应表
 * @author LBBMNM
 *
 */
public class RoleUserDao extends BaseDao<TsRoleUser>{
	
	
	public void deleteRoleToUser(String userId){
		String hql = "delete from TsRoleUser where userId='"+userId+"'";
		executeHql(hql);
	}
	
	public List<TsRoleUser> findRoleUserByUserId(String userid){
		String hql = "from TsRoleUser where userId='"+userid+"'";
		List<TsRoleUser> roles = find(hql);
		return roles;
	}

}
