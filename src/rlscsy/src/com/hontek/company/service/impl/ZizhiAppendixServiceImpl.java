package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ZizhiAppendixDao;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.service.inter.ZizhiAppendixServiceInter;

/**
 * <p>Title: 企业资质证书附件表</p>
 * <p>Description: 企业资质证书附件接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAppendixServiceImpl extends BaseServiceImpl implements ZizhiAppendixServiceInter {

	private ZizhiAppendixDao zizhiAppendixDao;
	Logger logger = Logger.getLogger(ZizhiAppendixServiceImpl.class);
	
	public void setZizhiAppendixDao(ZizhiAppendixDao zizhiAppendixDao) {
		this.zizhiAppendixDao = zizhiAppendixDao;
	}

	/**
	 * 添加企业资质证书附件
	 */
	public String addZizhiAppendix(ZizhiAppendix zizhiAppendix) {
		String msg = "添加成功!";
		try {
			zizhiAppendix.setAppId(zizhiAppendixDao.getTableSequence("tb_zzappendix".toUpperCase()));
			zizhiAppendixDao.save(zizhiAppendix);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteZizhiAppendix(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					ZizhiAppendix zizhiAppendix = zizhiAppendixDao.get(ZizhiAppendix.class, Integer.valueOf(idArray[i]));
					if(zizhiAppendix!=null){
						zizhiAppendixDao.delete(zizhiAppendix);
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
	 * 删除
	 */
	public int deleteZizhiAppendixByPath(String path) {
		int r = 0;
		try {
			if(path!=null&&!"".equals(path)){
				r = zizhiAppendixDao.deleteByPath(path);
			}
		} catch (AppException e) {
			e.printStackTrace();
			String msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return r;
	}
	
	
	/**
	 * 分页查询资质证书
	 */
	public String findZizhiAppendixPagerList(int zid ,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(zid!=0&&!"".equals(zid)){
				condition.append(" and zid = "+zid+" ");				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));				
			Pager<ZizhiAppendix>  pager = zizhiAppendixDao.findPager(ZizhiAppendix.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询资质证书出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateZizhiAppendix(ZizhiAppendix zizhiAppendix) {
		String msg = "修改成功!";
		try {
			zizhiAppendixDao.update(zizhiAppendix);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
