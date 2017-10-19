package com.hontek.agri.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.agri.dao.AgriSupplierDao;
import com.hontek.agri.pojo.TbAgriSupplier;
import com.hontek.agri.service.inter.AgriSupplierServiceInter;
/**
 * <p>Title: 投入品供应商</p>
 * <p>Description: 投入品供应商service接口实现类</p>
 * @author qql
 */
public class AgriSupplierServiceImpl extends BaseServiceImpl implements AgriSupplierServiceInter {

	private AgriSupplierDao agriSupplierDao;
	/**
	 * 注入DAO
	 * @param agriSupplierDao
	 */
	public void setAgriSupplierDao(AgriSupplierDao agriSupplierDao) {
		this.agriSupplierDao = agriSupplierDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加投入品供应商
	 */
	public String addAgriSupplier(TbAgriSupplier agriSupplier) {
		String msg = "添加投入品供应商成功!";
		try {
			agriSupplier.setAsId(agriSupplierDao.getTableSequence("tb_agri_supplier".toUpperCase()));
			agriSupplier.setCrttime(DateUtil.formatDateTime());
			agriSupplierDao.save(agriSupplier);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加投入品供应商出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除投入品供应商
	 */
	public String deleteAgriSupplier(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbAgriSupplier agriSupplier = agriSupplierDao.get(TbAgriSupplier.class, Integer.valueOf(idArray[i]));
					if(agriSupplier!=null){
						agriSupplierDao.delete(agriSupplier);
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
	 * 分页查询投入品供应商
	 */
	public String findAgriSupplierList(TbAgriSupplier agriSupplier, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(agriSupplier!=null){						
				String asName = agriSupplier.getAsName();
				if(asName!=null&&!"".equals(asName)){
					condition.append(" and asName like '%"+asName+"%' ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order,"crttime desc"));		
		
			Pager<TbAgriSupplier>  pager = agriSupplierDao.findPager(TbAgriSupplier.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询投入品供应商出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改投入品供应商
	 */
	public String updateAgriSupplier(TbAgriSupplier agriSupplier) {
		String msg = "修改投入品供应商成功!";
		try {
			agriSupplierDao.update(agriSupplier);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改投入品供应商出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
