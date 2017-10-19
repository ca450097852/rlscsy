package com.hontek.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.RoleDao;
import com.hontek.sys.dao.RoleUserDao;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsRole;
import com.hontek.sys.pojo.TsRoleUser;
import com.hontek.sys.service.inter.RoleServiceInter;

/**
 * <p>Title: 角色表</p>
 * <p>Description: 角色serviceImpl 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RoleServiceImpl extends BaseServiceImpl implements RoleServiceInter{
	private RoleDao roleDao;
	
	private RoleUserDao roleUserDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setRoleUserDao(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	public int getTableSequence() {
		return roleDao.getTableSequence("TS_ROLE");
	}
	
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 分页查询角色列表
	 */
	public String findRoleList(TsRole role, int page, int rows) {
		String jsonMsg = "";		
		try {
			Map<String,Object> mapCondition = new HashMap<String,Object>();
			if(role!=null){
				if(role.getRoleName()!=null){
					mapCondition.put("roleName", role.getRoleName());
				}
				if(role.getSts()!=null){
					mapCondition.put("sts", Integer.parseInt(role.getSts()));
				}
				if(role.getEntId()!=0){
					mapCondition.put("ent_id", role.getEntId());
				}						
			}		
			Pager<TsRole> pager = roleDao.findRoleList(mapCondition,page,rows);
			jsonMsg = createEasyUiJson(pager);			
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg="查询角色列表出现错误!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		
		return jsonMsg;
	}
	
	/**
	 * 添加角色
	 */
	public String addRole(TsRole role) {
		String jsonMsg = "添加角色成功!";		
		try {
			role.setRoleId(getTableSequence());
			roleDao.save(role);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "添加角色失败!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 修改角色
	 */
	public String updateRole(TsRole role) {
		String jsonMsg = "修改角色成功!";		
		try {
			roleDao.update(role);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "添加角色失败!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 根据角色名称查询是否已经存在
	 */
	public String findIsUnique(TsRole role) {
		boolean b = roleDao.findIsUnique(role);
		return String.valueOf(b);
	}

	public String getRole(int roleId) {
		String jsonMsg = "";
		try {
			TsRole role = roleDao.getRole(roleId);
			jsonMsg = getJSON(role);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "查询角色出现错误!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 修改角色状态
	 * 禁用方法
	 */
	public String disableRole(String roleIds, String sts) {
		String jsonMsg = "禁用成功!";
		try {
			 roleDao.updateRoleSts(roleIds, sts);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "操作出现错误!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 修改角色状态
	 * 启用方法
	 */
	public String enableRole(String roleIds, String sts) {
		String jsonMsg = "启用成功!";
		try {
			 roleDao.updateRoleSts(roleIds, sts);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "操作出现错误!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}

	/**
	 * 加载角色下拉
	 * @param entId
	 * @return
	 */
	public String getEntRoleTree(int entId) {
		List<TreeVo> list = roleDao.getRoleTree(entId,"");		
		return getJSON(list);
	}
	
	/**
	 * 加载角色下拉列表
	 * 分配角色时调用
	 * @param entId
	 * @return
	 */
	public String getEntRoleTree(int entId,String userId) {
		List<TreeVo> list = roleDao.getRoleTree(entId,getUserRoleIds(userId));		
		return getSelectOptions(list);
	}
	
	/**
	 * 获取用户已经拥有的角色
	 */
	public String getUserRoleTreeList(String userId){
		String roleIds = getUserRoleIds(userId);
		if(!"".equals(roleIds)){
			List<TreeVo> list2 = roleDao.getUserRoleTree(roleIds);		
			return getSelectOptions(list2);
		}
		return "";
	}
	
	/**
	 * 分配用户角色
	 */
	public String addRoleUser(String roleIds,String userId){
		String jsonMsg = "分配用户角色成功!";
		try {
			//删除已有的角色
			roleUserDao.deleteRoleToUser(userId);
			if(roleIds!=null&&!"".equals(roleIds)){
				String [] roleIdArray  = roleIds.split(",");
				for (String roleId : roleIdArray) {					
					TsRoleUser tsRoleUser = new TsRoleUser(userId, Integer.valueOf(roleId));
					tsRoleUser.setRuId(roleUserDao.getTableSequence("TS_ROLE_USER"));
					roleUserDao.save(tsRoleUser);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "分配用户角色失败!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
	
	
	/**
	 * 获取用户所拥有的角色Id（多个角色id使用“,”相连）
	 * @param userId
	 * @return
	 */
	public String getUserRoleIds(String userId){
		List<TsRoleUser> list = roleUserDao.findRoleUserByUserId(userId);
		StringBuffer buffer = new StringBuffer("");
		for (TsRoleUser tsRoleUser : list) {
			buffer.append(tsRoleUser.getRoleId());
			buffer.append(",");
		}
		String roleIds = buffer.toString();
		if(roleIds.endsWith(",")){
			roleIds = roleIds.substring(0,roleIds.length()-1);
		}		
		return roleIds;
	}

	/**
	 * 分配用户角色，新建机构时调用
	 */
	public String addRoleUser(TsRoleUser tsRoleUser) {
		String jsonMsg = "分配用户角色成功";
		try {
			tsRoleUser.setRuId(roleUserDao.getTableSequence("TS_ROLE_USER"));
			roleUserDao.save(tsRoleUser);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "分配用户角色失败!"+e.getLocalizedMessage();
		}
		return jsonMsg;
	}
	
	
	
}
