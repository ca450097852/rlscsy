package com.hontek.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.EntTypeDao;
import com.hontek.sys.pojo.TsEntType;
import com.hontek.sys.service.inter.EntTypeServiceInter;

public class EntTypeServiceImpl extends BaseServiceImpl implements EntTypeServiceInter {
    private EntTypeDao entTypeDao;
    
	public void setEntTypeDao(EntTypeDao entTypeDao) {
		this.entTypeDao = entTypeDao;
	}

	private Logger logger = Logger.getLogger(this.getClass());

    /**
     *  根据条件分页查询所有的组织机构类型
     */
	public String findEntTypeList(TsEntType entType, int page, int rows){
		String jsonString="";	
		try {
			Pager<TsEntType> pager = entTypeDao.findEntTypeList(entType, page, rows);
			jsonString = super.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonString="查询机构类别错误："+e.getLocalizedMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	
	/**
	 * 读取主键序列号
	 */
	public int getTableSequence() {
		return entTypeDao.getTableSequence("TS_ENT_TYPE");
	}
	
	/**
	 *  添加组织机构类别
	 */
	public String addEntType(TsEntType entType){
		String jsonString="保存成功!";	
		try {
			entType.setTypeId(getTableSequence());
			entTypeDao.save(entType); 
		} catch (Exception e) {
			jsonString = "保存失败! "+e.getLocalizedMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	
	/**
	 *  修改组织机构类型
	 */
	public String updateEntType(TsEntType entType){
        String jsonString="修改成功!";	
		try {
			entTypeDao.update(entType); 
		} catch (Exception e) {
			jsonString = "修改失败! "+e.getLocalizedMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	
	/**
	 *  删除组织机构类别
	 */
	public String deleteEntType(String entTypeIds){
		String jsonString="删除成功!";	
		try {
			String []idArray = entTypeIds.split(",");
			for (String id : idArray) {
				entTypeDao.delete(entTypeDao.get(TsEntType.class, Integer.valueOf(id))); 
			}
		} catch (Exception e) {
			jsonString = "删除失败! "+e.getLocalizedMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	
    /**
     *  加载组织机构类别 -- 下拉
     */
	public String getEntTypeToSelect(String param) {
		String jsonString = "";
		try {
			List<TsEntType> list = entTypeDao.getEntTypeToSelect();
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			if(param==null){//全部
				for (TsEntType et : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", et.getTypeId());
					map.put("text", et.getTypeName());
					listResults.add(map);
				}
			}else if("1".equals(param)){//监管机构
				for (TsEntType et : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					String typeName = et.getTypeName();
					if("政府单位".equals(typeName)){
						map.put("id", et.getTypeId());
						map.put("text", typeName);
						listResults.add(map);
					}
				}
			}else if("2".equals(param)){//企业类型
				for (TsEntType et : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					String typeName = et.getTypeName();
					if(!"政府单位".equals(typeName)){
						map.put("id", et.getTypeId());
						map.put("text", typeName);
						listResults.add(map);
					}
				}
			}
			
			jsonString = getJSON(listResults);
		} catch (Exception e) {
			logger.error("查询组织机构类型下拉：" + e.getMessage());
		}
		return jsonString;
	}
	
	/**
	 * 检查机构名称是否已经存在
	 * @param typeName
	 * @return
	 */
	public String checkEntTypeIsExist(String typeName){
		return String.valueOf(entTypeDao.checkEntTypeIsExist(typeName));
	}
}
