package com.hontek.sys.service.inter;

import java.util.List;



public interface RolePurvServiceInter {

	public String addRolePurv(int roleId,int entId, String colIds);
	
	public String addRolePurv(int roleId,int entId, int typeId);
	
	public String findRolePurvTree(String loginUserId,int roleId,boolean isAdmin);
	
	public List findLoginUserCol(String userId);
}
