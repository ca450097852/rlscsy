package com.hontek.element.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.AgriUseDao;
import com.hontek.element.pojo.TbAgriUse;
import com.hontek.element.service.inter.AgriUseServiceInter;
/**
 * <p>Title: 农业投入品</p>
 * <p>Description: 农业投入品service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriUseServiceImpl extends BaseServiceImpl implements AgriUseServiceInter {

	private AgriUseDao agriUseDao;
	/**
	 * 注入DAO
	 * @param agriUseDao
	 */
	public void setAgriUseDao(AgriUseDao agriUseDao) {
		this.agriUseDao = agriUseDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加农业投入品
	 */
	public String addAgriUse(TbAgriUse agriUse) {
		String msg = "添加农业投入品成功!";
		try {
			agriUse.setArchId(agriUseDao.getTableSequence("tb_agri_use".toUpperCase()));
			agriUse.setCrttime(DateUtil.formatDate());
			
			Object b= agriUseDao.save(agriUse);
			
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
	public String deleteAgriUse(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbAgriUse agriUse = agriUseDao.get(TbAgriUse.class, Integer.valueOf(idArray[i]));
					if(agriUse!=null){
						agriUseDao.delete(agriUse);
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
	public String findAgriUseList(TbAgriUse agriUse, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(agriUse!=null){						
				int recId = agriUse.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbAgriUse>  pager = agriUseDao.findPager(TbAgriUse.class,condition.toString(), page, rows);			

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
	public String updateAgriUse(TbAgriUse agriUse) {
		String msg = "修改农业投入品成功!";
		try {
			agriUseDao.update(agriUse);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改农业投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
