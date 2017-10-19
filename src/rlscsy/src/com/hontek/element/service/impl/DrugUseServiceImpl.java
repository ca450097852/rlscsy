package com.hontek.element.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.DrugUseDao;
import com.hontek.element.pojo.DrugUse;
import com.hontek.element.service.inter.DrugUseServiceInter;
/**
 * <p>Title: 兽药农业投入品</p>
 * <p>Description: 农业投入品service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class DrugUseServiceImpl extends BaseServiceImpl implements DrugUseServiceInter {

	private DrugUseDao drugUseDao;
	/**
	 * 注入DAO
	 * @param DrugUseDao
	 */
	public void setDrugUseDao(DrugUseDao drugUseDao) {
		this.drugUseDao = drugUseDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加农业投入品
	 */
	public String addDrugUse(DrugUse drugUse) {
		String msg = "添加农业投入品成功!";
		try {
			drugUse.setDrugId(drugUseDao.getTableSequence("tb_drug_use".toUpperCase()));
			drugUse.setCrttime(DateUtil.formatDate());
			
			Object b= drugUseDao.save(drugUse);
			
			System.out.println(b);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加农业投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除农业投入品
	 */
	public String deleteDrugUse(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					DrugUse DrugUse = drugUseDao.get(DrugUse.class, Integer.valueOf(idArray[i]));
					if(DrugUse!=null){
						drugUseDao.delete(DrugUse);
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
	 * 分页查询农业投入品
	 */
	public String findDrugUseList(DrugUse DrugUse, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(DrugUse!=null){						
				int recId = DrugUse.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<DrugUse>  pager = drugUseDao.findPager(DrugUse.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询农业投入品出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改农业投入品
	 */
	public String updateDrugUse(DrugUse DrugUse) {
		String msg = "修改农业投入品成功!";
		try {
			drugUseDao.update(DrugUse);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改农业投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
