package com.hontek.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.info.dao.InfoTypeDao;
import com.hontek.info.pojo.TInfoType;
import com.hontek.info.service.inter.InfoTypeServiceInter;
import com.hontek.sys.pojo.TreeVo;
/**
 * 资讯分类  service
 * @author qql
 *
 */
public class InfoTypeServiceImpl extends BaseServiceImpl implements InfoTypeServiceInter{
	
	private InfoTypeDao infoTypeDao;
	

	public InfoTypeDao getInfoTypeDao() {
		return infoTypeDao;
	}
 
	public void setInfoTypeDao(InfoTypeDao infoTypeDao) {
		this.infoTypeDao = infoTypeDao;
	}
	
	//log4j 日志处理
	Logger logger = Logger.getLogger(InfoTypeServiceImpl.class);

	/**
	 * 添加分类
	 */
	public String addInfoType(TInfoType tinfoType) {
		String jsonMsg = "添加成功";
		if(tinfoType.getRemark()==null){
			String remark= "";
			tinfoType.setRemark(remark);
		}
		if(tinfoType.getParentId()==null){
			tinfoType.setParentId(0l);
		}
		try {
			tinfoType.setTypeId(infoTypeDao.getTableSequence("TB_INFOTYPE")+0l);
			infoTypeDao.save(tinfoType);
		} catch (AppException e){
			e.printStackTrace();
			jsonMsg="添加资讯分类出现异常！"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}

	/**
	 * 删除分类
	 */
	public String deleteInfoType(String tinfoTypes) {
         String jsonMsg = "";
		 
		try {
			String [] infoTypeArray = tinfoTypes.split(",");
			int count1 = 0;
			int count2 = 0;
			int count = 0;
			for (int i = 0; i < infoTypeArray.length; i++) {
				TInfoType tinfoType = infoTypeDao.get(TInfoType.class, Long.parseLong( infoTypeArray[i]));
				count2+=infoTypeDao.deleteTinfo(tinfoType);
				count1++;
			}
			count = count1+count2;
			
			jsonMsg = "成功删除 "+count+" 条数据!";
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "删除数据出现异常!"+e.getMessage();
			logger.debug(jsonMsg);
		}
		return jsonMsg;		

	}

	/**
	 * 分类列表
	 */
	public String findInfoTypeList(TInfoType tinfoType, int page, int rows,
			String sort, String order) {
        String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(tinfoType!=null){
			String typeName = tinfoType.getTypeName();
			Long typeId = tinfoType.getTypeId();
			if(typeName!=null&&!"".equals(typeName)){
				condition.append(" and t1.type_name like '%"+typeName+"%' ");
			}
			if(typeId!=null){
				condition.append(" and t1.type_id ="+typeId+" ");
			}
		}
		System.out.println("condition=="+condition.toString());
//		condition.append(sortCondtion(sort,order));
		try {
			Pager<TInfoType> pager = infoTypeDao.findInfoTypeList(condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
		
	}


	/**
	 * 下拉
	 */
	public String getInfoTypeToSelect() {
		String jsonString = "";
		try {
			List<TInfoType> list = infoTypeDao.getInfoTypeToSelect();
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			for (TInfoType IT : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", IT.getTypeId());
				map.put("name", IT.getTypeName());
				listResults.add(map);
			}
			jsonString = getJSON(listResults);
		} catch (Exception e) {
			logger.error("查询：" + e.getMessage());
		}
		return jsonString;
	}

	/**
	 * 更新
	 */
	public String updateInfoType(TInfoType tinfoType) {
		String jsonMsg = "修改成功！";
		try{
			TInfoType oldInfoType = infoTypeDao.get(TInfoType.class, tinfoType.getTypeId());
			oldInfoType.setTypeName(tinfoType.getTypeName());
			oldInfoType.setParentId(tinfoType.getParentId());
			oldInfoType.setRemark(tinfoType.getRemark());
			infoTypeDao.update(oldInfoType);
		} catch (AppException e){
			e.printStackTrace();
			jsonMsg="修改出现异常！";
			logger.debug(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 下拉树
	 */
	public String getInfoTypeTree() {		
		
		List list = infoTypeDao.getInfoTypeTree(0);
		List<TreeVo> list1  = new ArrayList<TreeVo>();
					
			TreeVo treeVo = new TreeVo();
			treeVo.setId(0);
			treeVo.setText("请选择");
			list1.add(treeVo);	
			list1.addAll(list);
		
		String jsonstr = getJSON(list1);		
		return jsonstr;
	}
}
