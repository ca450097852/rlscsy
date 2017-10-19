package com.hontek.element.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.AgriTypeDao;
import com.hontek.element.pojo.TbAgriType;
import com.hontek.element.service.inter.AgriTypeServiceInter;
import com.hontek.sys.pojo.TsEntType;
/**
 * <p>Title: 农业投入品类别</p>
 * <p>Description: 农业投入品类别service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriTypeServiceImpl extends BaseServiceImpl implements AgriTypeServiceInter {

	private AgriTypeDao agriTypeDao;
	/**
	 * 注入DAO
	 * @param agriTypeDao
	 */
	public void setAgriTypeDao(AgriTypeDao agriTypeDao) {
		this.agriTypeDao = agriTypeDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加农业投入品类别
	 */
	public String addAgriType(TbAgriType agriType) {
		String msg = "添加农业投入品类别成功!";
		try {
			agriType.setTypeId(agriTypeDao.getTableSequence("tb_agri_type".toUpperCase()));

			agriTypeDao.save(agriType);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加农业投入品类别出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除农业投入品类别
	 */
	public String deleteAgriType(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbAgriType agriType = agriTypeDao.get(TbAgriType.class, Integer.valueOf(idArray[i]));
					if(agriType!=null){
						agriTypeDao.delete(agriType);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 投入品种类选择
	 */
	public String findAgriTypeComboList(){
		String hql ="from TbAgriType where state=1";
		List<TbAgriType> list = agriTypeDao.find(hql);
		List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
		for (TbAgriType et : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", et.getTypeId());
			map.put("text", et.getTypeName());
			listResults.add(map);
		}
		return  getJSON(listResults);
	}
	
	
	/**
	 * 分页查询农业投入品类别
	 */
	public String findAgriTypeList(TbAgriType agriType, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(agriType!=null){						
				String typeName = agriType.getTypeName();
				if(typeName!=null&&!"".equals(typeName)){
					condition.append(" and typeName like '%"+typeName+"%' ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order,"seq"));		
		
			Pager<TbAgriType>  pager = agriTypeDao.findPager(TbAgriType.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询农业投入品类别出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改农业投入品类别
	 */
	public String updateAgriType(TbAgriType agriType) {
		String msg = "修改农业投入品类别成功!";
		try {
			agriTypeDao.update(agriType);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改农业投入品类别出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
