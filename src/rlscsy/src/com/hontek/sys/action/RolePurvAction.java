package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.sys.service.inter.RolePurvDefServiceInter;
import com.hontek.sys.service.inter.RolePurvServiceInter;
/**
 * <p>Title: 权限表</p>
 * <p>Description: 权限Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RolePurvAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RolePurvServiceInter rolePurvServiceInter;
	private RolePurvDefServiceInter rolePurvDefServiceInter;
	private int typeId;
	private String colIds;
	private int roleId;
	private int entId;
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getColIds() {
		return colIds;
	}

	public void setColIds(String colIds) {
		this.colIds = colIds;
	}
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public void setRolePurvServiceInter(RolePurvServiceInter rolePurvServiceInter) {
		this.rolePurvServiceInter = rolePurvServiceInter;
	}

	public void setRolePurvDefServiceInter(RolePurvDefServiceInter rolePurvDefServiceInter) {
		this.rolePurvDefServiceInter = rolePurvDefServiceInter;
	}

	/**
	 * 查询默认权限
	 */
	public void findDefaultColTree(){
		jsonMsg = rolePurvDefServiceInter.getPowerByTypeId(typeId);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 保存默认授权
	 * @return
	 */
	public void saveDefPower(){
		jsonMsg=rolePurvDefServiceInter.addRolePurvDef(typeId,colIds);			
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询权限
	 */
	public void findColTree(){
		jsonMsg = rolePurvServiceInter.findRolePurvTree(getLoginUserId(),roleId,isAdmin());
		printJsonString(jsonMsg);
	}
	
	/**
	 * 保存授权
	 * @return
	 */
	public void savePower(){
		jsonMsg=rolePurvServiceInter.addRolePurv(roleId,entId,colIds);			
		printJsonString(jsonMsg);
	}
}
