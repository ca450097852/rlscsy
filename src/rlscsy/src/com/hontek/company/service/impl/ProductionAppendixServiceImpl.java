package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ProductionAppendixDao;
import com.hontek.company.pojo.ProductionAppendix;
import com.hontek.company.service.inter.ProductionAppendixServiceInter;

/**
 * <p>Title: 生产信息附件表</p>
 * <p>Description: 生产信息附件接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionAppendixServiceImpl extends BaseServiceImpl implements ProductionAppendixServiceInter {

	private ProductionAppendixDao productionAppendixDao;
	Logger logger = Logger.getLogger(ProductionAppendixServiceImpl.class);
	
	public void setProductionAppendixDao(ProductionAppendixDao productionAppendixDao) {
		this.productionAppendixDao = productionAppendixDao;
	}

	/**
	 * 添加生产信息附件
	 */
	public String addProductionAppendix(ProductionAppendix productionAppendix) {
		String msg = "添加成功!";
		try {
			productionAppendix.setAppId(productionAppendixDao.getTableSequence("tb_pro_appendix".toUpperCase()));
			productionAppendixDao.save(productionAppendix);
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
	public String deleteProductionAppendix(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					ProductionAppendix productionAppendix = productionAppendixDao.get(ProductionAppendix.class, Integer.valueOf(idArray[i]));
					if(productionAppendix!=null){
						productionAppendixDao.delete(productionAppendix);
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
	public int deleteProductionAppendixByPath(String path) {
		int r = 0;
		try {
			if(path!=null&&!"".equals(path)){
				r = productionAppendixDao.deleteByPath(path);
			}
		} catch (AppException e) {
			e.printStackTrace();
			String msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return r;
	}
	
	
	/**
	 * 分页查询生产信息
	 */
	public String findProductionAppendixPagerList(int proId ,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(proId!=0&&!"".equals(proId)){
				condition.append(" and proId = "+proId+" ");				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order, "uploadtime"));				
			Pager<ProductionAppendix>  pager = productionAppendixDao.findPager(ProductionAppendix.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询生产信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateProductionAppendix(ProductionAppendix productionAppendix) {
		String msg = "修改成功!";
		try {
			productionAppendixDao.update(productionAppendix);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
