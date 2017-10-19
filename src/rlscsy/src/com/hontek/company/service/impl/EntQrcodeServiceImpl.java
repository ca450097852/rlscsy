package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.EntQrcodeDao;
import com.hontek.company.pojo.EntQrcode;
import com.hontek.company.service.inter.EntQrcodeServiceInter;

/**
 * <p>Title: 企业二维码信息表</p>
 * <p>Description: 企业二维码信息接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EntQrcodeServiceImpl extends BaseServiceImpl implements EntQrcodeServiceInter {

	private EntQrcodeDao entQrcodeDao;
	Logger logger = Logger.getLogger(EntQrcodeServiceImpl.class);
	
	public void setEntQrcodeDao(EntQrcodeDao entQrcodeDao) {
		this.entQrcodeDao = entQrcodeDao;
	}

	/**
	 * 添加企业二维码信息
	 */
	public String addEntQrcode(EntQrcode entQrcode) {
		String msg = "添加成功!";
		try {
			entQrcode.setEqId(entQrcodeDao.getTableSequence("tb_entQrcode".toUpperCase()));
			entQrcodeDao.save(entQrcode);
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
	public String deleteEntQrcode(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					EntQrcode entQrcode = entQrcodeDao.get(EntQrcode.class, Integer.valueOf(idArray[i]));
					if(entQrcode!=null){
						entQrcodeDao.delete(entQrcode);
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
	 * 分页查询资质证书
	 */
	public String findEntQrcodePagerList(EntQrcode entQrcode,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(entQrcode!=null){
				int entId = entQrcode.getEntId();//名称
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId);
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<EntQrcode>  pager = entQrcodeDao.findPager(EntQrcode.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询资质证书出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateEntQrcode(EntQrcode entQrcode) {
		String msg = "修改成功!";
		try {
			entQrcodeDao.update(entQrcode);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
