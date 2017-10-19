package com.hontek.element.service.impl;


import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.element.service.inter.EartagServiceInter;
import com.hontek.element.dao.EartagDao;
import com.hontek.element.pojo.TbEartag;
/**
 * <p>Title: 耳标信息</p>
 * <p>Description: 耳标信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class EartagServiceImpl extends BaseServiceImpl implements EartagServiceInter {

	private EartagDao eartagDao;
	/**
	 * 注入DAO
	 * @param eartagDao
	 */
	public void setEartagDao(EartagDao eartagDao) {
		this.eartagDao = eartagDao;
	}
	

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加耳标信息
	 */
	public String addEartag(TbEartag eartag) {
		String msg = "添加耳标信息成功!";
		try {
			eartag.setEarId(eartagDao.getTableSequence("tb_eartag".toUpperCase()));
			eartagDao.save(eartag);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加耳标信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除耳标信息
	 */
	public String deleteEartag(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbEartag eartag = eartagDao.get(TbEartag.class, Integer.valueOf(idArray[i]));
					if(eartag!=null){
						eartagDao.delete(eartag);
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
	 * 分页查询耳标信息
	 */
	public String findEartagList(TbEartag eartag, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(eartag!=null){
				
				int recId = eartag.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbEartag>  pager = eartagDao.findPager(TbEartag.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询耳标信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}


	/**
	 * 修改耳标信息
	 */
	public String updateEartag(TbEartag eartag) {
		String msg = "修改耳标信息成功!";
		try {
			eartagDao.update(eartag);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改耳标信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


}
