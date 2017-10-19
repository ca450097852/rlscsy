package com.hontek.element.service.impl;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.service.inter.CheckinfoServiceInter;
import com.hontek.element.dao.CheckinfoDao;
import com.hontek.element.dao.ElementAppDao;
import com.hontek.element.pojo.TbCheckinfo;
import com.hontek.element.pojo.TbElementApp;
/**
 * <p>Title: 检验信息</p>
 * <p>Description: 检验信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class CheckinfoServiceImpl extends BaseServiceImpl implements CheckinfoServiceInter {

	private CheckinfoDao checkinfoDao;
	
	private ElementAppDao elementAppDao;
	
	
	/**
	 * 注入DAO
	 * @param checkinfoDao
	 */
	public void setCheckinfoDao(CheckinfoDao checkinfoDao) {
		this.checkinfoDao = checkinfoDao;
	}
	/**
	 * 注入DAO
	 * @param elementAppDao
	 */
	public void setElementAppDao(ElementAppDao elementAppDao) {
		this.elementAppDao = elementAppDao;
	}
	
	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加检验信息
	 */
	public String addCheckinfo(TbCheckinfo checkinfo, String jsonApp) {
		String msg = "添加检验信息成功!";
		try {
			checkinfo.setCheckId(checkinfoDao.getTableSequence("tb_checkinfo".toUpperCase()));
			checkinfoDao.save(checkinfo);
			
			if(jsonApp!=null){
				JSONArray arr = JSONArray.fromObject(jsonApp);
				Iterator<JSONObject> it = arr.iterator();
				while(it.hasNext()){
					JSONObject obj = it.next();
					TbElementApp elementApp = (TbElementApp) obj.toBean(obj, TbElementApp.class);
					elementApp.setAppId(elementAppDao.getTableSequence("tb_element_app".toUpperCase()));
					elementApp.setFkId(checkinfo.getCheckId());
					elementApp.setAppType("1");
					elementApp.setSeq(5);
					elementApp.setUploadTime(DateUtil.formatDateTime());
					elementAppDao.save(elementApp);
				}
				
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加检验信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除检验信息
	 */
	public String deleteCheckinfo(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbCheckinfo checkinfo = checkinfoDao.get(TbCheckinfo.class, Integer.valueOf(idArray[i]));
					if(checkinfo!=null){
						checkinfoDao.delete(checkinfo);
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
	 * 分页查询检验信息
	 */
	public String findCheckinfoList(TbCheckinfo checkinfo, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(checkinfo!=null){
				
				int recId = checkinfo.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbCheckinfo>  pager = checkinfoDao.findPager(TbCheckinfo.class,condition.toString(), page, rows);	
			List<TbCheckinfo> list = pager.getData();
			if(list.size()>0){
				for(TbCheckinfo check:list){
					int checkId = check.getCheckId();
					List<TbElementApp> applist = elementAppDao.findList(TbElementApp.class, " and fkId="+checkId+" and appType=1");
					check.setElementApp(applist);
				}
			}
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询检验信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 分页查询检验信息，带附件
	 */
	public String findListWithApp(TbCheckinfo checkinfo, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(checkinfo!=null){
				
				int recId = checkinfo.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbCheckinfo>  pager = checkinfoDao.findPager(TbCheckinfo.class,condition.toString(), page, rows);
			List<TbCheckinfo> list = pager.getData();
			if(list.size()>0){
				for(TbCheckinfo check:list){
					int checkId = check.getCheckId();
					List<TbElementApp> applist = elementAppDao.findList(TbElementApp.class, " and fkId="+checkId+" and appType=1");
					check.setElementApp(applist);
				}
			}
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询检验信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
	

	/**
	 * 修改检验信息
	 */
	public String updateCheckinfo(TbCheckinfo checkinfo, String jsonApp,String ids) {
		String msg = "修改检验信息成功!";
		try {
			checkinfoDao.update(checkinfo);
			if(jsonApp!=null){
				JSONArray arr = JSONArray.fromObject(jsonApp);
				Iterator<JSONObject> it = arr.iterator();
				while(it.hasNext()){
					JSONObject obj = it.next();
					TbElementApp elementApp = (TbElementApp) obj.toBean(obj, TbElementApp.class);
					elementApp.setAppId(elementAppDao.getTableSequence("tb_element_app".toUpperCase()));
					elementApp.setFkId(checkinfo.getCheckId());
					elementApp.setAppType("1");
					elementApp.setSeq(5);
					elementApp.setUploadTime(DateUtil.formatDateTime());
					elementAppDao.save(elementApp);
				}
			}
			if(ids!=null){
				elementAppDao.deleteApps(ids,1);
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改检验信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


}
