package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.sys.pojo.TsRole;

import com.hontek.sys.service.inter.RoleServiceInter;
/**
 * <p>Title: 角色表</p>
 * <p>Description: 角色Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RoleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleServiceInter roleService;
	private TsRole role;
	private String roleIds;
	private int entId;
	private String userId;
	
	/**
	 * 分页查询角色列表
	 */
	public void findRoleList(){		
		if(!isAdmin()){
			if(role==null)
				role = new TsRole();
			role.setEntId(getLoginUserEntId());
		}
		jsonMsg = roleService.findRoleList(role,page,rows);
		printJsonString(jsonMsg);		
	}
	
	/**
	 * 查询角色名称是否唯一
	 */
	public void findIsUnique(){
		jsonMsg = roleService.findIsUnique(role);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加角色
	 */
	public void addRole(){
		jsonMsg = roleService.addRole(role);
		printJsonString(jsonMsg);		
	}
	
	/**
	 * 修改角色
	 */
	public void updateRole(){
		jsonMsg = roleService.updateRole(role);
		printJsonString(jsonMsg);		
	}
	
	/**
	 * 禁用
	 */
	public void disableRole(){
		jsonMsg =	roleService.enableRole(roleIds,role.getSts());				
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 启用
	 */
	public void enableRole(){
		jsonMsg =	roleService.enableRole(roleIds,role.getSts());				
		this.printJsonString(jsonMsg);
	}
	/**
	 * 加载角色 - 下拉
	 * @return
	 * @throws AppException
	 */
	public void getRoleTree(){		
		if(getEnterprse()!=null){
			entId = getEnterprse().getEntId();
		}				
		String jsonstr = roleService.getEntRoleTree(entId);
		printJsonString(jsonstr);
	}

	/**
	 * 加载用户未拥有角色 - 下拉列表显示
	 * @return
	 * @throws AppException
	 */
	public void getEntRoleTreeList(){				
		String jsonstr = roleService.getEntRoleTree(entId,userId);
		printJsonString(jsonstr);
	}
	
	
	/**
	 * 加载用户已经拥有角色 - 下拉列表显示
	 * @return
	 * @throws AppException
	 */
	public void getUserRoleTreeList(){				
		String jsonstr = roleService.getUserRoleTreeList(userId);
		printJsonString(jsonstr);
	}
	
	/**
	 * 分配用户角色
	 */
	public void saveRoleUser(){
		printJsonString(roleService.addRoleUser(roleIds, userId));
	}
	//*******************************SET AND GET

	public TsRole getRole() {
		return role;
	}

	public void setRole(TsRole role) {
		this.role = role;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public void setRoleService(RoleServiceInter roleService) {
		this.roleService = roleService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}