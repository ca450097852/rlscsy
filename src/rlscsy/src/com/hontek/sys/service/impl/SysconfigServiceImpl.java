package com.hontek.sys.service.impl;
import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.sys.dao.SysconfigDao;
import com.hontek.sys.pojo.TbSysconfig;
import com.hontek.sys.service.inter.SysconfigServiceInter;
/**
 * @ClassName: SysconfigServiceImpl
 * @Description: TODO(系统配置信息表 service 实现类)
 * @date 2015-7-21 下午03:28:44
 * @author qql
 * @version 1.0
 */
public class SysconfigServiceImpl extends BaseServiceImpl implements SysconfigServiceInter{
	private SysconfigDao sysconfigDao;

	
	public void setSysconfigDao(SysconfigDao sysconfigDao) {
		this.sysconfigDao = sysconfigDao;
	}


	//日志管理
	Logger logger = Logger.getLogger(SysconfigServiceImpl.class);


	public String getSysconfig() {
		String jsonMsg ="";
		try {
			jsonMsg = super.getObjectJSON(this.sysconfigDao.getSysconfig());
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "查询系统配置信息出现错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}


	public String updateSysconfig(TbSysconfig col) {
		String jsonMsg = "修改系统配置信息成功";
		try {
			this.sysconfigDao.update(col);
		}catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改系统配置信息失败";
		}
		return jsonMsg;
	}
	
	public TbSysconfig getSysconfig2() {
		TbSysconfig sysconfig =null;
		try {
			sysconfig = this.sysconfigDao.getSysconfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysconfig;
	}
	

}
