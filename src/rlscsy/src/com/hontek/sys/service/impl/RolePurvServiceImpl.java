package com.hontek.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.sys.dao.ColDao;
import com.hontek.sys.dao.RolePurvDao;
import com.hontek.sys.dao.RolePurvDefDao;
import com.hontek.sys.dao.RoleUserDao;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsRolePurv;
import com.hontek.sys.pojo.TsRolePurvDef;
import com.hontek.sys.pojo.TsRoleUser;
import com.hontek.sys.pojo.TsSysCol;
import com.hontek.sys.service.inter.RolePurvServiceInter;
/**
 * <p>Title: 权限表</p>
 * <p>Description: 权限service 实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RolePurvServiceImpl extends BaseServiceImpl implements RolePurvServiceInter {

	private RolePurvDao rolePurvDao;
	public void setRolePurvDao(RolePurvDao rolePurvDao) {
		this.rolePurvDao = rolePurvDao;
	}

	private RolePurvDefDao rolePurvDefDao;
	public void setRolePurvDefDao(RolePurvDefDao rolePurvDefDao) {
		this.rolePurvDefDao = rolePurvDefDao;
	}

	private ColDao colDao;
	public void setColDao(ColDao colDao) {
		this.colDao = colDao;
	}
	
	private RoleUserDao roleUserDao;	
	public void setRoleUserDao(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	/**
	 * 查询角色权限
	 */
	public String findRolePurvTree(String loginUserId,int roleId,boolean isAdmin){
		String jsonMsg="";		
		//查询当前用户所拥有的栏目-树形
		List<TreeVo> list = new ArrayList<TreeVo>();
		if(isAdmin){
			list = colDao.findColTree(0);
		}else{
			List<TsRoleUser> roleUsers = roleUserDao.findRoleUserByUserId(loginUserId);
			for (TsRoleUser tsRoleUser : roleUsers) {
				List<TreeVo> list2 = colDao.findEntColTree(0,tsRoleUser.getRoleId());
				for (TreeVo treeVo : list2) {
					if(!list.contains(treeVo)){
						list.add(treeVo);
					}
				}
			}
		}
		
		List<TsRolePurv> listP = rolePurvDao.findRolePurvList(roleId);
				
		for(int i=0;i<list.size();i++){
			TreeVo roleVo = list.get(i);
			List<TreeVo> childrens = list.get(i).getChildren();
			if(childrens!=null&&!childrens.isEmpty()){
				for(int k=0;k<childrens.size();k++){
					TreeVo children = childrens.get(k);
					for(int j=0;j<listP.size();j++){
						if(children.getId()==listP.get(j).getColId()){
							children.setChecked(true);
							break;
						}
					}
				}
			}else{
				for(int j=0;j<listP.size();j++){
					if(roleVo.getId()==listP.get(j).getColId()){
						roleVo.setChecked(true);
						break;
					}
				}
			}
		}
		
		jsonMsg = getJSON(list);
		
		return jsonMsg;
	}
	
	/**
	 * 保存权限
	 */
	public String addRolePurv(int roleId,int entId, String colIds) {		
		String jsonMsg = "保存权限成功!";
		try{			
			List<TsSysCol> col_list = colDao.findColListByIds(colIds);
			rolePurvDao.deleteRolePurv(roleId);			
			
			for(int i=0;i<col_list.size();i++){				
				TsSysCol col = (TsSysCol) col_list.get(i);
				TsRolePurv rolePurv = new TsRolePurv(roleId,entId,col.getId(),col.get_parentId(),col.getColName(),col.getColUrl(),col.getColLevel(),col.getSeq(),col.getIconUrl());
				rolePurv.setPurvId(rolePurvDao.getTableSequence("ts_role_purv"));			
				rolePurvDao.save(rolePurv);
			}
			}catch(Exception e){
				e.printStackTrace();
				jsonMsg = "保存权限成功!"+e.getLocalizedMessage();
			}		
		return jsonMsg;
	}


	/**
	 * 保存权限
	 * 新建机构时调用
	 */
	public String addRolePurv(int roleId,int entId, int typeId) {	
		String jsonMsg = "保存权限成功!";
		try{
			rolePurvDao.deleteRolePurv(roleId);			
			List<TsRolePurvDef> rolePurvDefList = rolePurvDefDao.getPowerByTypeId(typeId);
			for(int i=0;i<rolePurvDefList.size();i++){				
				TsRolePurvDef col = rolePurvDefList.get(i);
				TsRolePurv rolePurv = new TsRolePurv(roleId,entId,col.getColId(),col.getParentId(),col.getColName(),col.getColUrl(),col.getColLevel(),col.getSeq(),col.getIconUrl());
				rolePurv.setPurvId(rolePurvDao.getTableSequence("ts_role_purv"));			
				rolePurvDao.save(rolePurv);
			}
			}catch(Exception e){
				e.printStackTrace();
				jsonMsg = "保存权限出错!"+e.getLocalizedMessage();
			}		
		return jsonMsg;
	}

	/**
	 * 登录时读取权限
	 * @param parentId
	 * @return
	 */
	public List<TsRolePurv> findLoginUserCol(String userId){
		if(userId==null||"".equals(userId)){
			return rolePurvDao.findAdminCol(0);
		}else{
			List<TsRoleUser> roleUsers = roleUserDao.findRoleUserByUserId(userId);
			
			List<TsRolePurv> list = new ArrayList<TsRolePurv>();
			
			for (TsRoleUser tsRoleUser : roleUsers) {
				List<TsRolePurv> listTemp = rolePurvDao.findRolePurvByRoleId(0, tsRoleUser.getRoleId());
				for (TsRolePurv tsRolePurv : listTemp) {
					if(!list.contains(tsRolePurv)){
						list.add(tsRolePurv);
					}
				}
			}			
			return list;
		}
		
	}

}
