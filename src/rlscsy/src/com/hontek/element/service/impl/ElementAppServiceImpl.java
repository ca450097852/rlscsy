package com.hontek.element.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.service.inter.ElementAppServiceInter;
import com.hontek.element.dao.ElementAppDao;
import com.hontek.element.pojo.TbElementApp;
/**
 * <p>Title: 附件信息</p>
 * <p>Description: 附件信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ElementAppServiceImpl extends BaseServiceImpl implements ElementAppServiceInter {

	private ElementAppDao elementAppDao;
	/**
	 * 注入DAO
	 * @param elementAppDao
	 */
	public void setElementAppDao(ElementAppDao elementAppDao) {
		this.elementAppDao = elementAppDao;
	}
	

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加附件信息
	 */
	public String addElementApp(TbElementApp elementApp) {
		String msg = "添加附件信息成功!";
		try {
			elementApp.setAppId(elementAppDao.getTableSequence("tb_element_App".toUpperCase()));
			elementApp.setUploadTime(DateUtil.formatDateTime());
			elementAppDao.save(elementApp);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加附件信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除附件信息
	 */
	public String deleteElementApp(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbElementApp elementApp = elementAppDao.get(TbElementApp.class, Integer.valueOf(idArray[i]));
					if(elementApp!=null){
						elementAppDao.delete(elementApp);
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
	 * 分页查询附件信息
	 */
	public String findElementAppList(TbElementApp elementApp, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(elementApp!=null){
				
				int fkId = elementApp.getFkId();
				if(fkId!=0){
					condition.append(" and fkId = "+fkId+" ");
				}
				
				String appType = elementApp.getAppType();
				
				if(appType!=null&&!"".equals(appType)){
					condition.append(" and appType = '"+appType+"' ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbElementApp>  pager = elementAppDao.findPager(TbElementApp.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询附件信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}


	/**
	 * 修改附件信息
	 */
	public String updateElementApp(TbElementApp elementApp) {
		String msg = "修改附件信息成功!";
		try {
			elementAppDao.update(elementApp);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改附件信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


}
