package com.hontek.element.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.MaterialsDao;
import com.hontek.element.pojo.Materials;
import com.hontek.element.pojo.TbEartag;
import com.hontek.element.service.inter.MaterialsServiceInter;

/**
 * 原料信息表
 * @author IMZK
 *
 */

public class MaterialsServiceImpl extends BaseServiceImpl implements MaterialsServiceInter{
	private MaterialsDao materialsDao;

	public void setMaterialsDao(MaterialsDao materialsDao) {
		this.materialsDao = materialsDao;
	}

	public String add(Materials materials) {
		String msg = "添加失败";
		
		try {
			materials.setMaId(materialsDao.getTableSequence("tb_materials".toUpperCase()));
			materials.setCrttime(DateUtil.formatDateTime());
			materialsDao.save(materials);
			msg = "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public String update(Materials materials) {
		String msg = "修改失败";
		
		try {
			materialsDao.update(materials);
			msg = "修改成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public String delete(String ids) {
		String msg = "删除失败";
		
		try {
			if(ids!=null && !"".equals(ids))
				materialsDao.executeHql("delete from Materials where maId in ("+ids+")");
			msg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public String findPager(Materials materials, int page, int rows,
			String sort, String order) {
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(materials!=null){
				
				int recId = materials.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<Materials>  pager = materialsDao.findPager(Materials.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询信息出现异常!"+e.getMessage();
		}		
		return jsonMsg;
	}
	
}
