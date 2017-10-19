package com.hontek.sys.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.sys.dao.ColDao;
import com.hontek.sys.dao.RolePurvDefDao;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsRolePurvDef;
import com.hontek.sys.pojo.TsSysCol;
import com.hontek.sys.service.inter.RolePurvDefServiceInter;
/**
 * <p>Title: 默认权限表</p>
 * <p>Description: 默认权限service 实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class RolePurvDefServiceImpl extends BaseServiceImpl implements RolePurvDefServiceInter {

	private RolePurvDefDao rolePurvDefDao;
	private ColDao colDao;
	
	public void setRolePurvDefDao(RolePurvDefDao rolePurvDefDao) {
		this.rolePurvDefDao = rolePurvDefDao;
	}

	public void setColDao(ColDao colDao) {
		this.colDao = colDao;
	}

	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 保存默认权限
	 */
	public String addRolePurvDef(int typeId,String colIds) {
		String jsonString = "保存成功!";
		try{
			//先删除
			rolePurvDefDao.deleteRolePurvDef(typeId);
			
			List<TsSysCol> colList = colDao.findColListByIds(colIds);
			TsSysCol col = null;
			for(int i=0;i<colList.size();i++){
				col = (TsSysCol) colList.get(i);
				TsRolePurvDef rolePurv = new TsRolePurvDef(col.getId(),col.get_parentId(),col.getColName(),col.getColUrl(),col.getColLevel(),col.getSeq(),col.getIconUrl(),typeId);
				rolePurv.setPurvId(rolePurvDefDao.getTableSequence("ts_role_purv"));
				rolePurvDefDao.save(rolePurv);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonString = "保存失败!"+e.getLocalizedMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}

	/**
	 * 读取默认权限
	 */
	public String getPowerByTypeId(int typeId) {
		List<TsRolePurvDef> listP= rolePurvDefDao.getPowerByTypeId(typeId);				
		List<TreeVo> list = colDao.findColTree(0);				
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
		return getJSON(list);
	}

}
