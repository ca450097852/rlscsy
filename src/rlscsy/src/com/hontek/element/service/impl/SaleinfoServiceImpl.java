package com.hontek.element.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.service.inter.SaleinfoServiceInter;
import com.hontek.element.dao.SaleinfoDao;
import com.hontek.element.pojo.TbSaleinfo;
/**
 * <p>Title: 销售信息</p>
 * <p>Description: 销售信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class SaleinfoServiceImpl extends BaseServiceImpl implements SaleinfoServiceInter {

	private SaleinfoDao saleinfoDao;
	/**
	 * 注入DAO
	 * @param saleinfoDao
	 */
	public void setSaleinfoDao(SaleinfoDao saleinfoDao) {
		this.saleinfoDao = saleinfoDao;
	}
	

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加销售信息
	 */
	public String addSaleinfo(TbSaleinfo saleinfo) {
		String msg = "添加销售信息成功!";
		try {
			saleinfo.setSaleId(saleinfoDao.getTableSequence("tb_saleinfo".toUpperCase()));
			saleinfo.setCrttime(DateUtil.formatDateTime());
			saleinfoDao.save(saleinfo);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加销售信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除销售信息
	 */
	public String deleteSaleinfo(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbSaleinfo saleinfo = saleinfoDao.get(TbSaleinfo.class, Integer.valueOf(idArray[i]));
					if(saleinfo!=null){
						saleinfoDao.delete(saleinfo);
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
	 * 分页查询销售信息
	 */
	public String findSaleinfoList(TbSaleinfo saleinfo, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(saleinfo!=null){
				
				int recId = saleinfo.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbSaleinfo>  pager = saleinfoDao.findPager(TbSaleinfo.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询销售信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}


	/**
	 * 修改销售信息
	 */
	public String updateSaleinfo(TbSaleinfo saleinfo) {
		String msg = "修改销售信息成功!";
		try {
			saleinfoDao.update(saleinfo);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改销售信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


}
