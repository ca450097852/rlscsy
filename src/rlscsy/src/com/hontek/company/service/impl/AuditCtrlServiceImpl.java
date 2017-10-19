package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.AuditCtrlDao;
import com.hontek.company.pojo.AuditCtrl;
import com.hontek.company.service.inter.AuditCtrlServiceInter;

/**
 * <p>Title: 企业审核控制表</p>
 * <p>Description: 企业审核控制接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class AuditCtrlServiceImpl extends BaseServiceImpl implements AuditCtrlServiceInter {

	private AuditCtrlDao auditCtrlDao;
	
	Logger logger = Logger.getLogger(AuditCtrlServiceImpl.class);
	
	public void setAuditCtrlDao(AuditCtrlDao auditCtrlDao) {
		this.auditCtrlDao = auditCtrlDao;
	}


	/**
	 * 添加企业审核控制
	 */
	public String addAuditCtrl(AuditCtrl auditCtrl) {
		String msg = "添加成功!";
		try {
			auditCtrl.setCtrId(auditCtrlDao.getTableSequence("tb_audit_ctrl".toUpperCase()));
			auditCtrlDao.save(auditCtrl);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteAuditCtrl(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					AuditCtrl auditCtrl = auditCtrlDao.get(AuditCtrl.class, Integer.valueOf(idArray[i]));
					if(auditCtrl!=null){
						auditCtrlDao.delete(auditCtrl);
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
	 * 分页查询审核控制
	 */
	public String findAuditCtrlPagerList(AuditCtrl auditCtrl,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(auditCtrl!=null){
				String auditCtrlName = auditCtrl.getEntName();//名称
				if(auditCtrlName!=null&&!"".equals(auditCtrlName)){
					condition.append(" and auditCtrlName like '%"+auditCtrlName+"%' ");
				}		
				
				int entId = auditCtrl.getEntId();
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<AuditCtrl>  pager = auditCtrlDao.findPager(AuditCtrl.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询审核控制出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateAuditCtrl(AuditCtrl auditCtrl) {
		String msg = "修改成功!";
		try {
			auditCtrlDao.update(auditCtrl);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}


	public AuditCtrl getAuditCtrlByEntId(int entId) {
		return auditCtrlDao.getAuditCtrlByEntId(entId);
	}

}
