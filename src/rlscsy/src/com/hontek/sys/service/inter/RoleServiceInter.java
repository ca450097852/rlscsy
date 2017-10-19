package com.hontek.sys.service.inter;

import com.hontek.sys.pojo.TsRole;
import com.hontek.sys.pojo.TsRoleUser;


public interface RoleServiceInter {
	
	public String findRoleList(TsRole role,int page,int rows);

	public String addRole(TsRole role);

	public String updateRole(TsRole role);

	public String disableRole(String roleIds, String sts);
	
	public String enableRole(String roleIds, String sts);

	public String findIsUnique(TsRole role);

	public String getRole(int roleId);
	
	public String getEntRoleTree(int entId);
	
	public String getEntRoleTree(int entId,String userId);
	
	public String getUserRoleTreeList(String userId);
	
	public String addRoleUser(String roleIds,String userId);
	
	public String addRoleUser(TsRoleUser roleuser);
		
	public int getTableSequence();
}
