package com.hontek.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ProductionAppendixDao;
import com.hontek.company.dao.ProductionDao;
import com.hontek.company.pojo.Production;
import com.hontek.company.pojo.ProductionAppendix;
import com.hontek.company.service.inter.ProductionServiceInter;

/**
 * <p>Title: 生产信息表</p>
 * <p>Description: 生产信息接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionServiceImpl extends BaseServiceImpl implements ProductionServiceInter {

	private ProductionDao productionDao;
	private ProductionAppendixDao productAppDao;
	
	Logger logger = Logger.getLogger(ProductionServiceImpl.class);
	
	public void setProductionDao(ProductionDao productionDao) {
		this.productionDao = productionDao;
	}

	public void setProductAppDao(ProductionAppendixDao productAppDao) {
		this.productAppDao = productAppDao;
	}

	/**
	 * 添加生产信息
	 */
	public String addProduction(Production production) {
		String msg = "添加生产信息成功!";
		try {
			production.setProId(productionDao.getTableSequence("tb_production".toUpperCase()));
			productionDao.save(production);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加生产信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除生产信息
	 */
	public String deleteProduction(String ids) {
		String msg = "删除生产信息成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Production production = productionDao.get(Production.class, Integer.valueOf(idArray[i]));
					if(production!=null){
						productionDao.delete(production);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除生产信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询生产信息
	 */
	public String findProductionPagerList(Production production,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(production!=null){
				String productinfo = production.getProductinfo();//产品情况
				if(productinfo!=null&&!"".equals(productinfo)){
					condition.append(" and productinfo like '%"+productinfo+"%' ");
				}
				int entId = production.getEntId();
				if(entId>0){
					condition.append(" and tp.ent_id = "+entId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			//Pager<Production>  pager = productionDao.findPager(Production.class,condition.toString(), page, rows);
			Pager<Production>  pager = productionDao.findPager(condition.toString(), page, rows);

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询生产信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改生产信息
	 */
	public String updateProduction(Production production) {
		String msg = "修改生产信息成功!";
		try {
			productionDao.update(production);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改生产信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String findProductForMobile(String entCode, int page, int rows) {
		Pager<Production> pager = productionDao.findPager(" and ent_code='"+entCode+"'", page, rows);
		
		List<Production> list = pager.getData();
		
		for(Production prod:list){
			List<ProductionAppendix> ap = this.productAppDao.findProductAppend(" and proId="+prod.getProId(),page,rows);
			prod.setAppList(ap);
		}
		
		return this.getJSON(pager);
	}

	public String addProductionAndAppend(Production production,
			List<ProductionAppendix> proAppList) {
		String msg = "添加生产信息成功!";
		try {
			production.setProId(productionDao.getTableSequence("tb_production".toUpperCase()));
			productionDao.save(production);
			
			if(proAppList!=null&&!proAppList.isEmpty()){
				for(ProductionAppendix pa:proAppList){
					if(pa!=null){
						pa.setAppId(productionDao.getTableSequence("tb_pro_appendix".toUpperCase()));
						pa.setProId(production.getProId());
						pa.setUploadtime(DateUtil.formatDateTime());
						productAppDao.save(pa);
					}					
				}
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加生产信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String findProductionAndappend(int entId) {
		List<Production> proList = productionDao.find("from Production where entId="+entId);
		List<ProductionAppendix> paList = null;
		for(Production pro:proList){
			paList = productAppDao.findProductAppend(" and proId="+pro.getProId(),1,1000);
			pro.setAppList(paList);
		}
		return this.getJSON(proList);
	}

	public String updateProductionAndAppend(Production production,
			List<ProductionAppendix> proAppList) {
		String msg = "修改生产信息成功!";
		try {
			productionDao.update(production);
			
			productAppDao.deleteAppByProId(production.getProId());
			
			if(proAppList!=null&&!proAppList.isEmpty()){
				for(ProductionAppendix pa:proAppList){
					if(pa!=null){
						pa.setAppId(productionDao.getTableSequence("tb_pro_appendix".toUpperCase()));
						pa.setProId(production.getProId());
						pa.setUploadtime(DateUtil.formatDateTime());
						productAppDao.save(pa);
					}					
				}
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改生产信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
