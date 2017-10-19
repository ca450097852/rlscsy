package com.hontek.sys.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.dao.InterAccountDao;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.InterAccountServiceInter;

/**
 * <p>Title: 接入系统账号表</p>
 * <p>Description: 接入系统账号接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class InterAccountServiceImpl extends BaseServiceImpl implements InterAccountServiceInter {

	private InterAccountDao interAccountDao;
	private EnterpriseDao enterpriseDao;
	
	Logger logger = Logger.getLogger(InterAccountServiceImpl.class);
	
	public void setInterAccountDao(InterAccountDao interAccountDao) {
		this.interAccountDao = interAccountDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}


	/**
	 * 添加接入系统账号
	 */
	public String addInterAccount(TbInterAccount interAccount) {
		String msg = "添加接入系统账号成功!";
		try {
			//根据区域获取系统标识
			int entId = interAccount.getAreaId();
			TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(entId);		
			String sysCode = enterprise.getEntCode();
			for (int i = 1; i < 99; i++) {
				String no = String.valueOf(i);
				if(no.length()<2){
					no = "0"+no;
				}
				if(interAccountDao.checkSysCode(sysCode+no)){
					interAccount.setSysCode(sysCode+no);		
					break;
				}
			}			
			interAccountDao.save(interAccount);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加接入系统账号出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除接入系统账号
	 */
	public String deleteInterAccount(String ids) {
		String msg = "删除接入系统账号成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbInterAccount interAccount = interAccountDao.get(TbInterAccount.class, idArray[i]);
					if(interAccount!=null){
						interAccountDao.delete(interAccount);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除接入系统账号出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询接入系统账号
	 */
	public String findInterAccountPagerList(TbInterAccount interAccount,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(interAccount!=null){
				String sysName = interAccount.getSysName();//系统名称
				if(sysName!=null&&!"".equals(sysName)){
					condition.append(" and sysName like '%"+sysName+"%' ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbInterAccount>  pager = interAccountDao.findPager(TbInterAccount.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询接入系统账号出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改接入系统账号
	 */
	public String updateInterAccount(TbInterAccount interAccount) {
		String msg = "修改接入系统账号成功!";
		try {
			interAccountDao.update(interAccount);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改接入系统账号出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	/**
	 * 查找接入用户信息
	 */
	public TbInterAccount getInterAccount(String condition) {
		return interAccountDao.getInterAccount(condition);
	}

	/**
	 * 返回true表示未存在
	 */
	public String findInterAccountExist(String account) {
		String condition = " and account = '"+account+"'";
		TbInterAccount interAccount = interAccountDao.getInterAccount(condition);		
		return String.valueOf(interAccount==null);
	}

}
