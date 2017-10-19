package com.hontek.element.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.element.service.inter.ThreeaServiceInter;
import com.hontek.element.dao.ThreeaDao;
import com.hontek.element.pojo.TbThreea;
/**
 * <p>Title: 三品一标信息</p>
 * <p>Description: 三品一标信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ThreeaServiceImpl extends BaseServiceImpl implements ThreeaServiceInter {

	private ThreeaDao threeaDao;
	/**
	 * 注入DAO
	 * @param threeaDao
	 */
	public void setThreeaDao(ThreeaDao threeaDao) {
		this.threeaDao = threeaDao;
	}
	

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加三品一标信息
	 */
	public String addThreea(TbThreea threea) {
		String msg = "添加三品一标信息成功!";
		try {
			threea.setThId(threeaDao.getTableSequence("tb_threea".toUpperCase()));
			threeaDao.save(threea);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加三品一标信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除三品一标信息
	 */
	public String deleteThreea(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbThreea threea = threeaDao.get(TbThreea.class, Integer.valueOf(idArray[i]));
					if(threea!=null){
						threeaDao.delete(threea);
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
	 * 分页查询三品一标信息
	 */
	public String findThreeaList(TbThreea threea, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(threea!=null){
				
				int recId = threea.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbThreea>  pager = threeaDao.findPager(TbThreea.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询三品一标信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}


	/**
	 * 修改三品一标信息
	 */
	public String updateThreea(TbThreea threea) {
		String msg = "修改三品一标信息成功!";
		try {
			threeaDao.update(threea);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改三品一标信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


}
