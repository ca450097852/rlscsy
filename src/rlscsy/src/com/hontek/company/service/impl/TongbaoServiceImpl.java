package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.TongbaoDao;
import com.hontek.company.pojo.Tongbao;
import com.hontek.company.service.inter.TongbaoServiceInter;


/**
 * <p>Title: 通报处罚企业信息表</p>
 * <p>Description: 通报处罚企业信息表接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class TongbaoServiceImpl extends BaseServiceImpl implements TongbaoServiceInter {

	private TongbaoDao tongbaoDao;
	Logger logger = Logger.getLogger(TongbaoServiceImpl.class);
	
	public void setTongbaoDao(TongbaoDao tongbaoDao) {
		this.tongbaoDao = tongbaoDao;
	}

	/**
	 * 添加生产信息
	 */
	public String addTongbao(Tongbao tongbao) {
		String msg = "添加通报企业成功!";
		try {
			tongbao.setTid(tongbaoDao.getTableSequence("tb_tongbao".toUpperCase()));
			tongbaoDao.save(tongbao);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加通报企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除通报企业
	 */
	public String deleteTongbao(String ids) {
		String msg = "删除通报企业成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Tongbao tongbao = tongbaoDao.get(Tongbao.class, Integer.valueOf(idArray[i]));
					if(tongbao!=null){
						tongbaoDao.delete(tongbao);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除通报企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	

	/**
	 * 发布通报企业
	 */
	public String updatePublishTongbao(String ids) {
		String msg = "发布通报企业成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Tongbao tongbao = tongbaoDao.get(Tongbao.class, Integer.valueOf(idArray[i]));
					if(tongbao!=null){
						tongbao.setSts("1");
						tongbaoDao.update(tongbao);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "发布通报企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
	/**
	 * 分页查询通报企业
	 */
	public String findTongbaoPagerList(Tongbao tongbao,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(tongbao!=null){
				String entName = tongbao.getEntName();//产品情况
				if(entName!=null&&!"".equals(entName)){
					condition.append(" and entName like '%"+entName+"%' ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<Tongbao>  pager = tongbaoDao.findPager(Tongbao.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询通报企业出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改通报企业
	 */
	public String updateTongbao(Tongbao tongbao) {
		String msg = "修改通报企业成功!";
		try {
			tongbaoDao.update(tongbao);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改通报企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
